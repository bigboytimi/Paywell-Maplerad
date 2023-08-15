package com.paywell.demomaplerad.integration.impl;

import com.paywell.demomaplerad.exceptions.APIConnectionException;
import com.paywell.demomaplerad.integration.ApiConnection;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class ApiConnectionImpl implements ApiConnection {
    @Value("${api.secret-key}")
    private String secretKey;
    @Override
    public <T, R> R connectAndPost(T postBody, String url, HttpMethod method, Class<R> responseBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept","application/json");
        headers.set("content-type","application/json");
        headers.set("Authorization","Bearer "+secretKey);

        HttpEntity<T> httpEntity = new HttpEntity<>(postBody,headers);

        try{
            ResponseEntity<R> response = getRestTemplate().exchange(
                    url, method,httpEntity,responseBody);
            return response.getBody();
        }catch(Exception e){
            e.printStackTrace();
            throw new APIConnectionException("Request Failed, Please Try Again");
        }
    }

    @Override
    public <R> R connectAndGet(String url, HttpMethod method, Class<R> responseClass) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("accept","application/json");
        headers.set("content-type","application/json");
        headers.set("Authorization","Bearer "+secretKey);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        try{
            ResponseEntity<R> response = getRestTemplate().exchange(
                    url, method,httpEntity,responseClass);
            return response.getBody();
        }catch(Exception e){
            e.printStackTrace();
            throw new APIConnectionException("Request Failed, Please Try Again");
        }
    }


    private static RestTemplate getRestTemplate() {

        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectionRequestTimeout(45, TimeUnit.SECONDS)
                        .setResponseTimeout(40,TimeUnit.SECONDS)
                        .build())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(requestFactory);
    }
}
