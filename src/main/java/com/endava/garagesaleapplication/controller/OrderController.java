package com.endava.garagesaleapplication.controller;

import com.endava.garagesaleapplication.data.order.OrderRequest;
import com.endava.garagesaleapplication.data.order.OrderResponse;
import com.endava.garagesaleapplication.facade.order.OrderFacade;
import com.endava.garagesaleapplication.model.Order;
import com.endava.garagesaleapplication.service.EmailService;
import com.endava.garagesaleapplication.service.OrderService;
import com.endava.garagesaleapplication.validator.BindingRequestValidation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderFacade orderFacade;

    @Autowired
    private OrderService orderService;

    @Autowired
    private EmailService emailService;

    /**
     * API exposing creation of an order
     *
     * @param orderRequest  - order send by the client
     * @param bindingResult - result of validation
     * @return {@link OrderResponse} containing newly placed order
     */
    @PostMapping(value = "/pay")
    @ResponseBody
    @Operation(summary = "Places a new order with a list of assets")
    public ResponseEntity<OrderResponse> placeOrder(
            @Valid @RequestBody OrderRequest orderRequest, BindingResult bindingResult) {
        BindingRequestValidation.check(bindingResult);

        OrderResponse orderResponse = handleOrderFlow(orderRequest);

        //ToDo: Uncomment this only for demo
        this.emailService.sendOrderPlacedEmail(orderRequest.getEmail(), "Order placed", orderResponse);

        return ResponseEntity.ok(orderResponse);
    }

    /**
     * API exposing order history
     *
     * @return {@link OrderResponse} a list containing all the order history
     */
    @GetMapping("/all")
    @ResponseBody
    @Operation(summary = "Gets all the order history")
    public ResponseEntity<List<OrderResponse>> getAll() {
        List<OrderResponse> orderResponseList = this.orderFacade.getAll(this.orderService.getAll());
        return ResponseEntity.ok(orderResponseList);
    }

    private OrderResponse handleOrderFlow(OrderRequest orderRequest) {
        Order order = this.orderService.placeOrder(this.orderFacade.convertToOrder(orderRequest));
        return this.orderFacade.convertToOrderResponse(order);
    }
}