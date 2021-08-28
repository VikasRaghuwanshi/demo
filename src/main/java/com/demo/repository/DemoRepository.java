package com.demo.repository;

import com.demo.entity.DataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class DemoRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public void createSingleBhav(DataEntity dataEntity, String collection){
        mongoTemplate.insert(dataEntity,collection);
    }

    public long getCount(String collection) {
        Query query = new Query();
        return mongoTemplate.count(query,collection);
    }

}
