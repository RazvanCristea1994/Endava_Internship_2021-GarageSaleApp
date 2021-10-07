package com.endava.garagesaleapplication.data.shoppingcart;

import com.endava.garagesaleapplication.data.asset.AssetRequest;
import com.endava.garagesaleapplication.data.card.CardRequest;
import com.endava.garagesaleapplication.validator.EmailValidation;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
public class ShoppingCartRequest {

    @NotNull(message = "This is a required field ")
    @Pattern(regexp = "^[a-zA-Z '-]+$", message = "Wrong name format. Please use only letters and special characters: ' - ")
    private final String customerName;

    @Valid
    @NotNull(message = "This is a required field ")
    private final List<AssetRequest> assetRequestList;

    @NotBlank(message = "This is a required field ")
    private final String email;

    @Valid
    @NotNull(message = "This is a required field ")
    private final CardRequest cardRequest;

    public ShoppingCartRequest(String customerName, List<AssetRequest> assetRequestList, String email, CardRequest cardRequest) {

        this.customerName = customerName;
        if (assetRequestList.isEmpty()) {
            throw new IllegalArgumentException("There must be at least one item in your shopping cart ");
        }
        this.assetRequestList = assetRequestList;
        this.email = EmailValidation.checkEmailValidity(email);
        this.cardRequest = cardRequest;
    }
}