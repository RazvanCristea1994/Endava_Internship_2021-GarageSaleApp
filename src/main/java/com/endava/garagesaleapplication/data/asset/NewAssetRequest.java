package com.endava.garagesaleapplication.data.asset;

import com.endava.garagesaleapplication.data.category.CategoryRequest;
import com.endava.garagesaleapplication.data.issue.IssueRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Getter
@AllArgsConstructor
public class NewAssetRequest {

    @Valid
    @NotNull(message = "[Category] is a required field ")
    private CategoryRequest categoryRequest;

    @NotNull(message = "[Price] is a required field ")
    @PositiveOrZero(message = "Price cannot be a negative number")
    @Digits(integer = 8, fraction = 2, message = "Price example: 4.99")
    private double price;

    @NotNull(message = "[Issues] is a required field ")
    @NotEmpty(message = "[Issues] is a required field ")
    private List<IssueRequest> issues;

    @NotNull(message = "[Quantity] is a required field ")
    @Positive(message = "[Quantity] must be positive ")
    private int quantity;
}