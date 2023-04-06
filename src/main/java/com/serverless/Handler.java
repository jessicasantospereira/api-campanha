package com.serverless;

import java.util.Collections;
import java.util.Map;

import com.google.gson.Gson;
import com.serverless.core.dto.CodigoPromocionalDto;
import com.serverless.core.entidade.ApiGatewayResponse;
import com.serverless.core.entidade.Response;
import com.serverless.outbound.CodigoPromocionalRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Handler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private static final Logger LOG = LogManager.getLogger(Handler.class);

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

		CodigoPromocionalRepository repository = new CodigoPromocionalRepository();
		CodigoPromocionalDto codigoPromocional = repository.findByBin(input.get("bin").toString());

		if (codigoPromocional == null) {
			LOG.info("código não localizado");
			return ApiGatewayResponse.builder()
					.setStatusCode(404)
					.build();
		}
		LOG.info("Código encontrado: {}", codigoPromocional);
		return ApiGatewayResponse.builder()
				.setStatusCode(200)
				.setObjectBody(codigoPromocional)
				.build();
	}
}
