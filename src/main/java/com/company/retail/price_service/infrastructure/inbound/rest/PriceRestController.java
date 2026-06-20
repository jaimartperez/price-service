package com.company.retail.price_service.infrastructure.inbound.rest;

import com.company.retail.price_service.application.ports.inbound.GetApplicablePriceUseCase;
import com.company.retail.price_service.domain.model.Price;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/prices")
@Validated
public class PriceRestController {

    private final GetApplicablePriceUseCase getApplicablePriceUseCase;

    public PriceRestController(GetApplicablePriceUseCase getApplicablePriceUseCase) {
        this.getApplicablePriceUseCase = getApplicablePriceUseCase;
    }

    @GetMapping
    public ResponseEntity<PriceResponse> getApplicablePrice(
            @RequestParam("applicationDate") 
            @NotNull(message = "La fecha de aplicación es requerida") 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            
            @RequestParam("productId") 
            @NotNull(message = "El ID del producto es requerido") 
            @Positive(message = "El ID del producto debe ser un número positivo") Integer productId,
            
            @RequestParam("brandId") 
            @NotNull(message = "El ID de la cadena (brand) es requerido") 
            @Positive(message = "El ID de la cadena debe ser un número positivo") Integer brandId) {
        
        Price price = getApplicablePriceUseCase.execute(applicationDate, productId, brandId);
        
        PriceResponse response = PriceResponse.builder()
                .productId(price.getProductId())
                .brandId(price.getBrandId())
                .priceList(price.getPriceList())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .finalPrice(price.getMoney().getAmount())
                .currency(price.getMoney().getCurrency())
                .build();

        return ResponseEntity.ok(response);
    }
}