package com.interview.cognizone.service;

import com.interview.cognizone.model.Relation;

import java.util.List;

public interface RelationService {
    List<Relation> getAllRelation();
    Relation createRelation(Relation relation);
}
