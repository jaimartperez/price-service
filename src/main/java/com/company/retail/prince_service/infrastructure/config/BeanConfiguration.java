package com.company.retail.prince_service.infrastructure.config;

import com.company.retail.prince_service.application.ports.outbound.PriceRepositoryPort;
import com.company.retail.prince_service.application.usecase.GetApplicablePriceService;
import com.company.retail.prince_service.application.ports.inbound.GetApplicablePriceUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public GetApplicablePriceUseCase getApplicablePriceUseCase(PriceRepositoryPort priceRepositoryPort) {
        return new GetApplicablePriceService(priceRepositoryPort);
    }
}
