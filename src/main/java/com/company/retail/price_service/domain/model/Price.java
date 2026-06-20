package com.company.retail.price_service.domain.model;

import java.time.LocalDateTime;

public class Price {
    private final Integer brandId;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final Integer priceList;
    private final Integer productId;
    private final Integer priority;
    private final Money money;

    public Price(Integer brandId, LocalDateTime startDate, LocalDateTime endDate,
                 Integer priceList, Integer productId, Integer priority, Money money) {
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.money = money;
    }

    // Getters manuales para mantener el dominio libre
    public Integer getBrandId() { return brandId; }
    public LocalDateTime getStartDate() { return startDate; }
    public LocalDateTime getEndDate() { return endDate; }
    public Integer getPriceList() { return priceList; }
    public Integer getProductId() { return productId; }
    public Integer getPriority() { return priority; }
    public Money getMoney() { return money; }
}
