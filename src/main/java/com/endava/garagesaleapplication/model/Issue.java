package com.endava.garagesaleapplication.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table
public class Issue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String assetIssue;

    @ManyToOne(fetch = FetchType.LAZY)
    private Asset asset;

    public static final class IssueBuilder {
        private Integer id;
        private String assetIssue;

        private IssueBuilder() {
        }

        public static IssueBuilder anIssue() {
            return new IssueBuilder();
        }

        public IssueBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public IssueBuilder withAssetIssue(String assetIssue) {
            this.assetIssue = assetIssue;
            return this;
        }

        public Issue build() {
            Issue issue = new Issue();
            issue.setId(id);
            issue.setAssetIssue(assetIssue);
            return issue;
        }
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Issue)) return false;
        return id != null && id.equals(((Issue) obj).getId());
    }
}