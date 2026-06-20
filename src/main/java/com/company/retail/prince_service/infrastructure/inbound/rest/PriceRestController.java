package com.company.retail.prince_service.infrastructure.inbound.rest;

import com.company.retail.prince_service.application.ports.inbound.GetApplicablePriceUseCase;
import com.company.retail.prince_service.domain.model.Price;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/prices")
@Tag(name = "Prices API", description = "Endpoint para la consulta de tarifas de precios aplicables a productos.")
public class PriceRestController {

    private final GetApplicablePriceUseCase getApplicablePriceUseCase;

    public PriceRestController(GetApplicablePriceUseCase getApplicablePriceUseCase) {
        this.getApplicablePriceUseCase = getApplicablePriceUseCase;
    }

    @GetMapping("/applicable")
    @Operation(summary = "Obtener el precio final aplicable", 
               description = "Devuelve la tarifa vigente de mayor prioridad para un producto, cadena y fecha dados.")
    @ApiResponse(responseCode = "200", description = "Tarifa encontrada con éxito.")
    @ApiResponse(responseCode = "404", description = "No se encontró ninguna tarifa que coincida con los parámetros.")
    public ResponseEntity<PriceResponse> getApplicablePrice(            
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,            
            @RequestParam Integer productId,            
            @RequestParam Integer brandId) {

        Price price = getApplicablePriceUseCase.execute(applicationDate, productId, brandId);
        
        PriceResponse response = PriceResponse.builder()
                .productId(price.getProductId())
                .brandId(price.getBrandId())
                .priceList(price.getPriceList())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .finalPrice(price.getMoney().amount())
                .currency(price.getMoney().currency())
                .build();

        return ResponseEntity.ok(response);
    }
}
