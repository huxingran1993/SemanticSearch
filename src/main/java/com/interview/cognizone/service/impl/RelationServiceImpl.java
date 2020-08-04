package com.interview.cognizone.service.impl;

import com.interview.cognizone.model.Relation;
import com.interview.cognizone.repository.RelationRepository;
import com.interview.cognizone.service.RelationService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RelationServiceImpl implements RelationService {
    private List<Relation> relationList;
    private final RelationRepository relationRepository;

    public RelationServiceImpl(RelationRepository relationRepository) {
        this.relationRepository = relationRepository;
    }

    @Override
    public List<Relation> getAllRelation() {
        return relationRepository.findAll();
    }

    @Override
    public Relation createRelation(Relation relation){
        return relationRepository.save(relation);
    }

//    @PostConstruct
//    public void initData(){
//        relationList = new ArrayList<>();
//        relationList.add(new Relation(1L, "son", "daughter","antonym" ));
//        relationList.add(new Relation(2L, "road", "street","synonym" ));
//        relationList.add(new Relation(3L, "road", "avenue","related" ));
//        relationList.add(new Relation(4L, "synonym", "match","related" ));
//        relationList.add(new Relation(5L, "antonym  ", "synonym","antonym" ));
//
//    }
}
