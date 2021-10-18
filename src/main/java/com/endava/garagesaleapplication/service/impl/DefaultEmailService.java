package com.endava.garagesaleapplication.service.impl;

import com.endava.garagesaleapplication.data.asset.AssetResponse;
import com.endava.garagesaleapplication.data.issue.IssueResponse;
import com.endava.garagesaleapplication.data.order.OrderResponse;
import com.endava.garagesaleapplication.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultEmailService implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    private StringBuilder stringBuilder;

    @Override
    public void sendOrderPlacedEmail(String to, String subject, OrderResponse orderResponse) {
        this.stringBuilder = new StringBuilder();
        buildOrderPlacedMessage(orderResponse);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("razvancristea1994@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(this.stringBuilder.toString());

        emailSender.send(message);
    }

    private void buildOrderPlacedMessage(OrderResponse orderResponse) {
        this.stringBuilder.append("Order placed successfully\n\n\n")
                .append("Your order items:\n");
        assetListToString(orderResponse.getAssetResponseList());
        this.stringBuilder.append("Total prince: " + orderResponse.getTotalPrice());
        this.stringBuilder.append(orderResponse.getCardResponse());
    }

    private void assetListToString(List<AssetResponse> assetResponseList) {
        assetResponseList.forEach(asset -> {
            stringBuilder.append("\n")
                    .append(asset.getCategoryResponse())
                    .append("\n");
            stringBuilder.append("Price: ")
                    .append(asset.getPrice())
                    .append("\n");
            stringBuilder.append("Issues: \n")
                    .append(issuesListToString(asset.getIssueResponseList()))
                    .append("\n");
        });
    }

    private String issuesListToString(List<IssueResponse> issueResponseList) {
        StringBuilder issuesStringBuilder = new StringBuilder();
        issueResponseList.forEach(issuesStringBuilder::append);

        return issuesStringBuilder.toString();
    }
}