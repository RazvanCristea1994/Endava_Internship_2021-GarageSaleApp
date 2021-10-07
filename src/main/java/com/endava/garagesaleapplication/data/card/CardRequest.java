package com.endava.garagesaleapplication.data.card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CardRequest {

    @NotNull(message = "This is a required field ")
    @Pattern(regexp = "^[a-zA-Z '-]+$", message = "Wrong name format. Please use only letters and special characters: ' - ")
    private final String cardHolderName;

    @NotNull(message = "This is a required field ")
    @Pattern(regexp = "[\\d]{16}", message = "Card number length invalid ")
    private final String cardNumber;

    @NotNull(message = "This is a required field ")
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private final LocalDate expiry;

    @NotNull(message = "This is a required field ")
    @Pattern(regexp = "[//d]{3}", message = "CIV length must be 3 ")
    private final String civ;
}