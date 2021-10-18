package com.endava.garagesaleapplication.facade.converter.issue;

import com.endava.garagesaleapplication.data.issue.IssueRequest;
import com.endava.garagesaleapplication.facade.converter.Converter;
import com.endava.garagesaleapplication.model.Issue;
import org.springframework.stereotype.Component;

@Component
public class IssueConverter implements Converter<Issue, IssueRequest> {

    @Override
    public Issue convert(IssueRequest issueRequest) {
        return Issue.IssueBuilder.anIssue().withAssetIssue(issueRequest.getIssue()).build();
    }
}