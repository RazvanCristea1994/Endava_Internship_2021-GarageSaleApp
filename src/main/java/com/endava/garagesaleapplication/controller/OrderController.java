package com.endava.garagesaleapplication.controller;

import com.endava.garagesaleapplication.data.order.OrderRequest;
import com.endava.garagesaleapplication.data.order.OrderResponse;
import com.endava.garagesaleapplication.facade.order.OrderFacade;
import com.endava.garagesaleapplication.model.Order;
import com.endava.garagesaleapplication.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderFacade orderFacade;
    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/pay")
    @ResponseBody
    public ResponseEntity<OrderResponse> placeOrder(
            @Valid @RequestBody OrderRequest orderRequest, BindingResult bindingResult) {
        checkOrderRequestFieldsValidity(orderRequest, bindingResult);

        try {
            OrderResponse orderResponse = handleOrderFlow(orderRequest);
            return ResponseEntity.ok(orderResponse);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<OrderResponse>> getAll() {
        List<OrderResponse> orderResponseList = this.orderFacade.getAll(this.orderService.getAll());
        return ResponseEntity.ok(orderResponseList);
    }

    private void checkOrderRequestFieldsValidity(OrderRequest orderRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> errorList = bindingResult.getFieldErrors();
            errorList.forEach(errorField -> stringBuilder.append(errorField.getDefaultMessage()));

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, stringBuilder.toString(), new IllegalArgumentException());
        }
    }

    private OrderResponse handleOrderFlow(OrderRequest orderRequest) {
        Order order = this.orderService.placeOrder(this.orderFacade.convertToOrder(orderRequest));
        OrderResponse orderResponse = this.orderFacade.convertToOrderResponse(order);

        return orderResponse;
    }
}