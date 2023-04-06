package com.serverless;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.google.gson.Gson;
import com.serverless.core.dto.CodigoPromocionalDto;
import com.serverless.core.entidade.ApiGatewayResponse;
import com.serverless.core.entidade.Response;
import com.serverless.outbound.CodigoPromocionalRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Handler implements RequestHandler<AwsProxyRequest, ApiGatewayResponse> {
	private static final Logger LOG = LogManager.getLogger(Handler.class);

	@Override
	public ApiGatewayResponse handleRequest(AwsProxyRequest request, Context context) {
		LOG.info("Bin recebido para consulta {} " , request.getPathParameters().get("valor"));
		String bin = request.getPathParameters().get("valor");
		CodigoPromocionalRepository repository = new CodigoPromocionalRepository();
		CodigoPromocionalDto codigoPromocional = repository.findByBin(bin);
		if (codigoPromocional == null) {
			LOG.info("código não localizado");
			return ApiGatewayResponse.builder().setStatusCode(404).build();
		}
		LOG.info("Código encontrado: {}", codigoPromocional.toString());
		return ApiGatewayResponse
				.builder()
				.setStatusCode(200)
				.setRawBody(new Gson().toJson(codigoPromocional))
				.build();
	}
}
