package com.interview.cognizone.controller;

import com.interview.cognizone.model.Relation;
import com.interview.cognizone.service.RelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RelationController {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RelationService relationService;

    /**
     * Task 1: Create relations between words
     * */
    @PostMapping("/createRelation")
    public void addRelation(@Validated @RequestBody Relation relation){
        relationService.createRelation(relation);
    }

    /**
     * Task 2: List entries
     * */
    @GetMapping("/getAllRelation")
    public ResponseEntity<List<Relation>> getAllRelation(){
        try {
            List<Relation> relations = new ArrayList<>();
            relationService.getAllRelation().forEach(relations::add);
            if (relations.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(relations, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Task 3: Filter on a type of relation
     * */
    @GetMapping("/getByR")
    public ResponseEntity<List<Relation>> getRelationByR(@RequestParam String R){
        try{
            List<Relation> relations = new ArrayList<>();
            relationService.getByR(R).forEach(relations::add);
            return new ResponseEntity<>(relations, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@PathVariable String id){
        try {
            return ResponseEntity.ok(relationService.getById(id).get());
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }


}
