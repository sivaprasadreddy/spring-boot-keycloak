package com.sivalabs.promotionservice.repository;

import com.sivalabs.promotionservice.model.Promotion;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

@Repository
public class PromotionRepository {
    private static final List<Promotion> PROMOTIONS = new ArrayList<>();

    @PostConstruct
    void init() {
        Promotion p1 = new Promotion(1L, 1L, BigDecimal.TEN);
        Promotion p2 = new Promotion(2L, 2L, BigDecimal.ONE);
        PROMOTIONS.add(p1);
        PROMOTIONS.add(p2);
    }

    public List<Promotion> getPromotions() {
        return PROMOTIONS;
    }
}
