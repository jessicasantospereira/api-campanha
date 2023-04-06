package com.serverless;

import java.net.HttpURLConnection;
import java.util.Map;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.google.gson.Gson;
import com.serverless.core.dto.CodigoPromocionalDto;
import com.serverless.core.entidade.ApiGatewayResponse;
import com.serverless.outbound.CodigoPromocionalRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Handler implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {

	private static final Logger LOG = LogManager.getLogger(Handler.class);


	@Override
	public AwsProxyResponse handleRequest(AwsProxyRequest request, Context context) {
		AwsProxyResponse response = new AwsProxyResponse((HttpURLConnection.HTTP_OK));
		LOG.info("Bin recebido para consulta {} " , request.getPathParameters().get("valor"));

		String bin = request.getPathParameters().get("valor");

		CodigoPromocionalRepository repository = new CodigoPromocionalRepository();
		CodigoPromocionalDto codigoPromocional = repository.findByBin(bin);

		if (codigoPromocional == null) {
			LOG.info("código não localizado");
			response = new AwsProxyResponse((HttpURLConnection.HTTP_BAD_REQUEST));
			response.setBody("código não localizado");
			return response;
		}
		LOG.info("Código encontrado: {}", codigoPromocional.toString());

		response.setBody(codigoPromocional.toString());
		return response;
	}
}
