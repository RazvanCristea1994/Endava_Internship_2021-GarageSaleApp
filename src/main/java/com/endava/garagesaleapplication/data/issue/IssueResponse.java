package com.endava.garagesaleapplication.data.issue;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class IssueResponse implements Serializable {

    private String issue;

    @Override
    public String toString() {
        return "\t-" + issue + "\n";
    }
}