package com.sivalabs.productservice.controller;

import com.sivalabs.productservice.model.Product;
import com.sivalabs.productservice.model.ProductResult;
import com.sivalabs.productservice.model.Promotion;
import com.sivalabs.productservice.repository.ProductRepository;
import com.sivalabs.productservice.service.PromotionServiceClient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final PromotionServiceClient promotionServiceClient;
    private final OAuth2AuthorizedClientManager authorizedClientManager;

    @GetMapping("/api/products")
    public List<ProductResult> getProducts(@RequestHeader("Authorization") String authorizationHeader) {
        System.out.println("authorizationHeader:" + authorizationHeader);
        List<Product> products = productRepository.getProducts();

        authorizationHeader = "Bearer " +this.getOAuth2AccessToken().getTokenValue();
        Map<Long, List<Promotion>> promotionsMap = promotionServiceClient.getProductPromotions(authorizationHeader)
            .stream().collect(Collectors.groupingBy(Promotion::getProductId));

        List<ProductResult> productResults = new ArrayList<>(products.size());
        for (Product product : products) {
            ProductResult productResult = new ProductResult();
            productResult.setId(product.getId());
            productResult.setName(product.getName());
            productResult.setOriginalPrice(product.getPrice());
            if(promotionsMap.containsKey(product.getId())) {
                Promotion promotion = promotionsMap.get(product.getId()).get(0);
                productResult.setDiscount(promotion.getDiscount());
                productResult.setPrice(product.getPrice().subtract(promotion.getDiscount()));
            } else {
                productResult.setDiscount(BigDecimal.ZERO);
                productResult.setPrice(product.getPrice());
            }
            productResults.add(productResult);
        }
        return productResults;
    }

    private OAuth2AccessToken getOAuth2AccessToken() {
        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("product-service-client")
            .principal("dummy") // This value is unnecessary, but if you don't give it a value, it throws an exception.
            .build();
        OAuth2AuthorizedClient authorizedClient = this.authorizedClientManager.authorize(authorizeRequest);

        return authorizedClient.getAccessToken();
    }
}
