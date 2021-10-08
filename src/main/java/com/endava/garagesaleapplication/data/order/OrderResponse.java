package com.endava.garagesaleapplication.data.order;

import com.endava.garagesaleapplication.data.asset.AssetResponse;
import com.endava.garagesaleapplication.data.card.CardResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderResponse {

    private final List<AssetResponse> assetResponseList;
    private final Double totalPrice;
    private final CardResponse cardResponse;
}