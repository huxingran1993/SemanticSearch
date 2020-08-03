package com.interview.cognizone.service;

import com.interview.cognizone.model.Relation;

import java.util.List;

public interface RelationService {
    void create(Relation relation);
    List<Relation> getAllRelation();
}
