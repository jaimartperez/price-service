package com.company.retail.price_service.domain.exception;

import java.time.LocalDateTime;

public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException(Integer productId, Integer brandId, LocalDateTime dateTime) {
        super(String.format("No se encontró tarifa aplicable para el producto %d, cadena %d en la fecha %s",
                productId, brandId, dateTime));
    }
}
