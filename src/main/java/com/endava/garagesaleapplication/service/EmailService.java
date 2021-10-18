package com.endava.garagesaleapplication.service;

import com.endava.garagesaleapplication.data.order.OrderResponse;

public interface EmailService {

    void sendOrderPlacedEmail(String to, String subject, OrderResponse orderResponse);
}