package com.vedha.stock.service.impl;

import com.vedha.stock.dto.OrderEventDTO;
import com.vedha.stock.producer.StockProducer;
import com.vedha.stock.service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockProducer stockProducer;

    @Override
    public void processOrder(OrderEventDTO orderEventDTO) {

        orderEventDTO.setStatus("PROCESSED");
        orderEventDTO.setMessage("Order Processed SuccessFully");

        stockProducer.sendOrderEmailMessage(orderEventDTO);
    }
}
