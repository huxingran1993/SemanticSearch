package com.interview.cognizone.repository;

import com.interview.cognizone.model.Relation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationRepository extends MongoRepository<Relation, String> {

}
