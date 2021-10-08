package com.endava.garagesaleapplication.data.asset;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class AssetRequest {

    @NotNull(message = "This is a required field ")
    private Integer id;

    public AssetRequest() {
    }
}