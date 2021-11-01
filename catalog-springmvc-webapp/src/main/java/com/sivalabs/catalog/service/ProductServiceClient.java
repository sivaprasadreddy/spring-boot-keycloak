package com.sivalabs.catalog.service;

import com.sivalabs.catalog.model.ProductResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ProductServiceClient {
    private final SecurityHelper securityHelper;
    private final RestTemplate restTemplate;

    public List<ProductResult> getProducts() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer "+securityHelper.getAccessToken());
            HttpEntity httpEntity = new HttpEntity(headers);
            ResponseEntity<List<ProductResult>> response = restTemplate.exchange(
                "http://localhost:8181/api/products", HttpMethod.GET, httpEntity,
                new ParameterizedTypeReference<>() {
                });
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
