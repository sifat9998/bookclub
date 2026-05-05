package com.bookclub.service.impl;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

@Repository
public class MongoWishlistDao implements WishlistDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    // ✅ ADD NEW ITEM
    @Override
    public void add(WishlistItem entity) {
        mongoTemplate.save(entity);
    }

    // ✅ UPDATE ITEM
    @Override
    public void update(WishlistItem entity) {

        WishlistItem item = mongoTemplate.findById(entity.getId(), WishlistItem.class);

        if (item != null) {
            item.setIsbn(entity.getIsbn());
            item.setTitle(entity.getTitle());
            item.setUsername(entity.getUsername());

            mongoTemplate.save(item);
        }
    }

    // ✅ REMOVE ITEM
    @Override
    public boolean remove(String key) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(key));

        mongoTemplate.remove(query, WishlistItem.class);

        return true;
    }

    // ✅ LIST BY USERNAME (IMPORTANT FOR ASSIGNMENT)
    @Override
    public List<WishlistItem> list(String username) {

        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));

        return mongoTemplate.find(query, WishlistItem.class);
    }

    // ✅ FIND BY ID
    @Override
    public WishlistItem find(String key) {
        return mongoTemplate.findById(key, WishlistItem.class);
    }
}