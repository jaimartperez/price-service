package com.company.retail.price_service.infrastructure.outbound.db;

import com.company.retail.price_service.application.ports.outbound.PriceRepositoryPort;
import com.company.retail.price_service.domain.model.Money;
import com.company.retail.price_service.domain.model.Price;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final PriceJpaRepository jpaRepository;

    public PriceRepositoryAdapter(PriceJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Price> findApplicablePrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return jpaRepository.findTopApplicablePrice(applicationDate, productId, brandId)
                .map(this::mapToDomain);
    }

    private Price mapToDomain(PriceEntity entity) {
        return new Price(
                entity.getBrandId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPriceList(),
                entity.getProductId(),
                entity.getPriority(),
                new Money(entity.getPrice(), entity.getCurr())
        );
    }
}
