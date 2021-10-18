package com.endava.garagesaleapplication.data.issue;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@AllArgsConstructor
public class IssueRequest implements Serializable {

    @NotNull(message = "[Issue] is a required field ")
    private String issue;

    public IssueRequest() {
    }
}