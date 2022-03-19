package com.example.backend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class API {

    @Autowired
    private RestTemplate restTemplate;

    public <T> T get(String url, Class<T> responeType, Object... pathParams) {

        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        ResponseEntity<T> response = restTemplate.getForEntity(String.format(url, pathParams), responeType);
        return response.getBody();
    }

    public <T> T post(String url, Class<T> responeType, Object requestBody, Object... pathParams) {

        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        ResponseEntity<T> response = restTemplate.postForEntity(String.format(url, pathParams), requestBody, responeType);
        return response.getBody();
    }
}
