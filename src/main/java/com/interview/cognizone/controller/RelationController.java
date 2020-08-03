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
import java.util.List;

@RestController
@RequestMapping("/api/relation")
public class RelationController {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RelationService relationService;

    @PostMapping("/createRelation")
    public void createRelation(@Validated @RequestBody Relation relation){
        relationService.create(relation);

    }

    @GetMapping("/getAllRelation")
    public ResponseEntity<List<Relation>> getAllRelation(){
        List<Relation> relations = new ArrayList<>();
        relationService.getAllRelation().forEach(relations::add);
        return new ResponseEntity<>(relations, HttpStatus.OK);
    }

}
