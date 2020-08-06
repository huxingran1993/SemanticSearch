package com.interview.cognizone.service;

import com.interview.cognizone.model.Relation;

import java.util.List;
import java.util.Optional;

public interface RelationService {
    List<Relation> getAllRelation();

    Relation createRelation(Relation relation);

    List<Relation> getByR(String R);

    Optional<Relation> getById(String id);
}
