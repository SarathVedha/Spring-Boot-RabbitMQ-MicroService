package com.vedha.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "OrderDTO", title = "Order", description = "Makes Order")
public class OrderDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    private String name;

    private int qty;

    private double price;
}
