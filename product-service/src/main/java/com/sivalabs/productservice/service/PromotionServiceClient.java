package com.sivalabs.productservice.service;

import com.sivalabs.productservice.model.Promotion;
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
public class PromotionServiceClient {
    private final RestTemplate restTemplate;

    public List<Promotion> getProductPromotions(String authorizationHeader) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", authorizationHeader);
            HttpEntity httpEntity = new HttpEntity(headers);
            ResponseEntity<List<Promotion>> response = restTemplate.exchange(
                "http://localhost:8182/api/promotions", HttpMethod.GET, httpEntity,
                new ParameterizedTypeReference<>() {});
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
