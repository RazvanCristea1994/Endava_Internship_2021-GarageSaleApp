package com.endava.garagesaleapplication.facade.converter.issue;

import com.endava.garagesaleapplication.data.issue.IssueResponse;
import com.endava.garagesaleapplication.facade.converter.Converter;
import com.endava.garagesaleapplication.model.Issue;
import org.springframework.stereotype.Component;

@Component
public class IssueResponseConverter implements Converter<IssueResponse, Issue> {

    @Override
    public IssueResponse convert(Issue issue) {
        return new IssueResponse(issue.getAssetIssue());
    }
}