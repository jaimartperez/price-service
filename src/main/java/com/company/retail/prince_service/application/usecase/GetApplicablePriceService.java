package com.company.retail.prince_service.application.usecase;

import com.company.retail.prince_service.application.ports.inbound.GetApplicablePriceUseCase;
import com.company.retail.prince_service.application.ports.outbound.PriceRepositoryPort;
import com.company.retail.prince_service.domain.exception.PriceNotFoundException;
import com.company.retail.prince_service.domain.model.Price;
import java.time.LocalDateTime;

public class GetApplicablePriceService implements GetApplicablePriceUseCase {

    private final PriceRepositoryPort priceRepositoryPort;

    public GetApplicablePriceService(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    @Override
    public Price execute(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return priceRepositoryPort.findApplicablePrice(applicationDate, productId, brandId)
                .orElseThrow(() -> new PriceNotFoundException(productId, brandId, applicationDate));
    }
}
