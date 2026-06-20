package com.company.retail.prince_service.application.ports.inbound;

import java.time.LocalDateTime;
import com.company.retail.prince_service.domain.model.Price;

public interface GetApplicablePriceUseCase {
    Price execute(LocalDateTime applicationDate, Integer productId, Integer brandId);
}
