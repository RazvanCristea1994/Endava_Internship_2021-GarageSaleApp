package com.endava.garagesaleapplication.data.asset;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class CustomerAssetRequest {

    @NotNull(message = "[ID] is a required field ")
    private Integer id;

    public CustomerAssetRequest() {
    }
}