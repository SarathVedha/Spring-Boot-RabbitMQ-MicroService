package com.vedha.order.controller;

import com.vedha.order.dto.OrderDTO;
import com.vedha.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
@Tag(name = "Order Controller", description = "Makes Order")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Place Order", description = "Place Order And Send Message To RabbitMQ")
    @ApiResponse(responseCode = "200", description = "Http Status 200 Ok")
    @PostMapping(value = "v1/place", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> placeOrder(@RequestBody OrderDTO orderDTO) {

        String order = orderService.makeOrder(orderDTO);

        return ResponseEntity.ok(order);
    }
}
