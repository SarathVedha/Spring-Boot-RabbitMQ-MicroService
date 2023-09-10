package com.vedha.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEventDTO {

    private String status;

    private String message;

    private OrderDTO orderDTO;
}
