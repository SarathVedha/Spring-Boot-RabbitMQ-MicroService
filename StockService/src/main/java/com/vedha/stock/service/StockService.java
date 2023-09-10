package com.vedha.stock.service;

import com.vedha.stock.dto.OrderEventDTO;

public interface StockService {

    void processOrder(OrderEventDTO orderEventDTO);
}
