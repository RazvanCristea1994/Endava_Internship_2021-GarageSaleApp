package com.endava.garagesaleapplication.data.order;

import com.endava.garagesaleapplication.data.asset.CustomerAssetRequest;
import com.endava.garagesaleapplication.data.card.CardRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderRequest {

    @NotNull(message = "This is a required field ")
    @Pattern(regexp = "^[a-zA-Z '-]+$", message = "Wrong name format. Please use only letters and special characters: ' - ")
    private final String customerName;

    @Valid
    @NotNull(message = "This is a required field ")
    private final List<CustomerAssetRequest> customerAssetRequestList;

    @NotBlank(message = "This is a required field ")
    private final String email;

    @Valid
    @NotNull(message = "This is a required field ")
    private final CardRequest cardRequest;
}