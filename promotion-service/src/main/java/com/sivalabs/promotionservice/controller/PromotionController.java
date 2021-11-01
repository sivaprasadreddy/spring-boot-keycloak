package com.sivalabs.promotionservice.controller;

import com.sivalabs.promotionservice.model.Promotion;
import com.sivalabs.promotionservice.repository.PromotionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PromotionController {
    private final PromotionRepository promotionRepository;

    @GetMapping("/api/promotions")
    public List<Promotion> getPromotions() {
        return promotionRepository.getPromotions();
    }
}
