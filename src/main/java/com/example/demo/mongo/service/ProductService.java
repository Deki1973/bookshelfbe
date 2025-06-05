package com.example.demo.mongo.service;

import com.example.demo.mongo.model.GroceryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void updateProduct(int newQuantity){
        Query query=new Query(Criteria.where("name").is("XYZ Kodo Millet healthy"));
        Update update=new Update().set("quantity",newQuantity);
        mongoTemplate.updateFirst(query,update, GroceryItem.class);
    }

}
