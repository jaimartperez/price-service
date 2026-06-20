package com.company.retail.price_service.application.ports.inbound;

import java.time.LocalDateTime;
import com.company.retail.price_service.domain.model.Price;

public interface GetApplicablePriceUseCase {
    Price execute(LocalDateTime applicationDate, Integer productId, Integer brandId);
}
