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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RelationController {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private List<Relation> allRelations = new ArrayList<>();

    @Autowired
    private RelationService relationService;

    /**
     * Task 1: Create relations between words
     *        &
     * Task 8: Inverse relation check
     * */
    @PostMapping("/createRelation")
    public void addRelation(@Validated @RequestBody Relation relation){
        boolean s = true;
        List<Relation> relations = new ArrayList<>();
        relationService.getAllRelation().forEach(relations::add);

        for (int i =0; i < relations.size();i++){
            if (relation.getW1().equals(relations.get(i).getW2()) && relation.getW2().equals(relations.get(i).getW1())) {
                LOGGER.error("Error!!!Adding the inverse relation!");
                s = false;
                }
        }
        if (s) relationService.createRelation(relation);

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
            relations.forEach(allRelations::add);

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

    /**
     * Task 9  Create UI for path search
     * */
    @GetMapping("/pathSearch/{source}/{target}")
    public void pathSearch(@PathVariable String source, @PathVariable String target){
        String next, path;
        List<Relation> relations = new ArrayList<>();
        relationService.getAllRelation().forEach(relations::add);
        getInverseRelation();
        LOGGER.info("Size of allRelation: " + allRelations.size());

        for (int i = 0; i<allRelations.size();i++){
            if (source.equals(allRelations.get(i).getW1())){
                next = allRelations.get(i).getW2();
                path = source + " ==("+ allRelations.get(i).getR()+")=> "+ next;

                while (getNumber(next)==2){
                    String medium = next;
                    next = getNextWord(source, next);
                    path = path + " ==("+getRelation(medium,source)+")=> "+next;
                    if (next.equals(target)) break;
                    source = medium;
                }
                LOGGER.info("Path: "+ path);
                allRelations.clear();
            }
        }
    }

    public List<Relation> getPairs(String w){
        return allRelations.stream().filter(e -> e.getW1().equals(w)).collect(Collectors.toList());
    }

    public int getNumber(String w){
        List<Relation> nextPair = getPairs(w);
        return nextPair.size();
    }

    public String getNextWord(String pre, String w){
        return getPairs(w).stream().filter(e -> !e.getW2().equals(pre)).collect(Collectors.toList()).get(0).getW2();
    }

    public String getRelation(String pre, String w){
        return getPairs(pre).stream().filter(e -> !e.getW2().equals(w)).collect(Collectors.toList()).get(0).getR();
    }

}
