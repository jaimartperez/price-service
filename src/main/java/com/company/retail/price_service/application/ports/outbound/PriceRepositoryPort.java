package com.company.retail.price_service.application.ports.outbound;

import java.time.LocalDateTime;
import java.util.Optional;
import com.company.retail.price_service.domain.model.Price;

public interface PriceRepositoryPort {
    Optional<Price> findApplicablePrice(LocalDateTime applicationDate, Integer productId, Integer brandId);
}
