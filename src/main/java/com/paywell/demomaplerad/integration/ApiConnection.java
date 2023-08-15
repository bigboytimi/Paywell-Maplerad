package com.paywell.demomaplerad.integration;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public interface ApiConnection {
    public <T, R> R connectAndPost(T postBody, String url, HttpMethod method, Class<R> responseBody);

    public <R> R connectAndGet(String url, HttpMethod method, Class<R> responseClass);
}
