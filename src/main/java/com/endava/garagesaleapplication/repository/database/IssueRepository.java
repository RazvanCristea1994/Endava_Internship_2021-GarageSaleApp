package com.endava.garagesaleapplication.repository.database;

import com.endava.garagesaleapplication.model.Issue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends CrudRepository<Issue, Integer> {
}