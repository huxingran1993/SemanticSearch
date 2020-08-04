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
import java.util.Optional;

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
    /**
     * Task 4: Checkbox to also show inverse relations
     * */
    @GetMapping("/showInverse")
    public ResponseEntity<List<Relation>> getInverseRelation(){
        try {
            List<Relation> relations = new ArrayList<>();
            List<Relation> relations_copy = new ArrayList<>();

            relationService.getAllRelation().forEach(relations::add);
            relationService.getAllRelation().forEach(relations_copy::add);

            relations_copy.forEach(e -> {
                String w3 = e.getW1();
                e.setW1(e.getW2());
                e.setW2(w3);
            });

            relations.addAll(relations_copy);

            if (relations.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(relations, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Task 5: Lowercase all words
     * */
    @GetMapping("/getAllInLowercase")
    public ResponseEntity<List<Relation>> getRelationInLowercase(){
        try {
            List<Relation> relations = new ArrayList<>();
            relationService.getAllRelation().forEach(relations::add);
            relations.forEach(e ->{
                e.setW1(e.getW1().toLowerCase());
                e.setW2(e.getW2().toLowerCase());
                e.setR(e.getR().toLowerCase());
                e.getW1().trim();
                e.getW2().trim();
                e.getR().trim();
            });

            if (relations.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(relations, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Task 7: Adding another relation between two word will fail.
     * */
    @PutMapping("/addRelation/{id}")
    public Relation addRelation(@PathVariable String id, @RequestBody Relation relation) {
        Optional<Relation> optionalRelation = relationService.getById(id);
        Relation rel = optionalRelation.get();
        if (relation.getR().equals(rel.getR())){
            rel.setR(relation.getR());
            LOGGER.info("Its ok not change the relation");
        }else {
            LOGGER.error("Can not add different relation!!");
        }
        relationService.createRelation(rel);
        return rel;
    }
}
