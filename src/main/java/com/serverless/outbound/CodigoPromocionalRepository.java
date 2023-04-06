package com.serverless.outbound;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.serverless.core.dto.CodigoPromocionalDto;
import lombok.val;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CodigoPromocionalRepository {
    private static final Logger log = LogManager.getLogger(CodigoPromocionalRepository.class);
    private AmazonDynamoDB dynamoDB;

    public CodigoPromocionalRepository(){
        this.dynamoDB = AmazonDynamoDBClientBuilder
                            .standard()
                            .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                            .withRegion("us-east-1").build();
    }

    public CodigoPromocionalDto findByBin(String bin) {
        Map<String, String> attributesNames = new HashMap<>();
        attributesNames.put("#bin", "bin");
        Map<String, AttributeValue> attributeValues = new HashMap<>();
        attributeValues.put(":bin", new AttributeValue().withS(bin));
        QueryRequest query = new QueryRequest("codigoPromocional")
                .withIndexName("bin-index")
                .withKeyConditionExpression("#bin = :bin")
                .withExpressionAttributeNames(attributesNames)
                .withExpressionAttributeValues(attributeValues);
        QueryResult result = this.dynamoDB.query(query);

        if (result.getCount() > 0) {
            CodigoPromocionalDto codigo = new CodigoPromocionalDto();

            codigo.setId(result.getItems().get(0).get("id").getS());
            codigo.setCodigoPromocional(result.getItems().get(0).get("codigoPromocional").getS());
            codigo.setDescricaoProduto(result.getItems().get(0).get("descricao").getS());
            codigo.setValorTarifa(result.getItems().get(0).get("valorTarifa").getS());
            codigo.setNumeroMesesBonificacao(result.getItems().get(0).get("numeroMesesBonificacao").getS());

            return codigo;
        }

        return null;
    }

}
