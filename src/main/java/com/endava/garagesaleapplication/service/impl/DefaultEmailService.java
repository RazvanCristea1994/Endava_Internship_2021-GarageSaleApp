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

    /**
     * This method structures the steps for sending an email after an order was placed
     *
     * @param to            the email of the client
     * @param subject       of the email
     * @param orderResponse contains the details of the order that will be shown to the client in the email
     */
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

    /**
     * This method builds the body of the email
     *
     * @param orderResponse contains the details of the order that will be shown to the client in the email
     */
    private void buildOrderPlacedMessage(OrderResponse orderResponse) {
        this.stringBuilder.append("Order placed successfully\n\n\n")
                .append("Your order items:\n");
        buildAssetListToString(orderResponse.getAssetResponseList());
        this.stringBuilder.append("Total prince: " + orderResponse.getTotalPrice());
        this.stringBuilder.append(orderResponse.getCardResponse());
    }

    /**
     * This method builds the product list that will be printed in an email
     *
     * @param assetResponseList the products that were ordered by the client with details that are supposed to be shown
     *                          in an email
     */
    private void buildAssetListToString(List<AssetResponse> assetResponseList) {
        assetResponseList.forEach(asset -> {
            stringBuilder.append("\n")
                    .append(asset.getCategoryResponse())
                    .append("\n");
            stringBuilder.append("Price: ")
                    .append(asset.getPrice())
                    .append("\n");
            stringBuilder.append("Issues: \n")
                    .append(buildIssueListToString(asset.getIssueResponseList()))
                    .append("\n");
        });
    }

    /**
     * This method builds the issue list that will be printed in an email
     *
     * @param issueResponseList issues of each product
     * @return the list of issues of a single product as string
     */
    private String buildIssueListToString(List<IssueResponse> issueResponseList) {
        StringBuilder issuesStringBuilder = new StringBuilder();
        issueResponseList.forEach(issuesStringBuilder::append);

        return issuesStringBuilder.toString();
    }
}