package com.endava.garagesaleapplication.data.asset;

import com.endava.garagesaleapplication.data.category.CategoryRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Getter
@AllArgsConstructor
public class NewAssetRequest {

    @Valid
    @NotNull(message = "This is a required field ")
    private CategoryRequest categoryRequest;

    @NotNull(message = "This is a required field ")
    @PositiveOrZero(message = "Price cannot be a negative number")
    @Digits(integer = 8, fraction = 2, message = "Example: 4.99")
    private double price;

    @NotNull(message = "This is a required field ")
    @NotEmpty(message = "This is a required field ")
    private List<String> issues;

    @NotNull(message = "This is a required field ")
    @Positive(message = "The value must be positive ")
    private final int quantity;
}