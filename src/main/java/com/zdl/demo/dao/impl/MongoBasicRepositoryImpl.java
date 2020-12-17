package com.zdl.demo.dao.impl;

import com.mongodb.client.result.UpdateResult;
import com.zdl.demo.dao.MongoBasicRepository;
import com.zdl.demo.entity.MongoBasicEntity;
import com.zdl.demo.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

public class MongoBasicRepositoryImpl<T extends MongoBasicEntity> implements MongoBasicRepository<T> {
    @Resource
    protected MongoTemplate mongoTemplate;
    @Autowired
    private SnowFlake snowFlake;

    private Class<T> clazz;

    public MongoBasicRepositoryImpl() {
        clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T insert(T bean) {
        bean.setId(snowFlake.nextId());
        bean.setCreatedAt(new Date().getTime());
        return mongoTemplate.insert(bean);
    }

    @Override
    public List<T> batchInsert(List<T> beanList) {
        beanList.forEach(item -> {
            item.setId(snowFlake.nextId());
            item.setCreatedAt(new Date().getTime());
        });
        mongoTemplate.insertAll(beanList);
        return beanList;
    }

    @Override
    public T update(T bean) {
        return mongoTemplate.save(bean);
    }

    @Override
    public T findOne(Criteria criteria) {
        Query query = Query.query(criteria).limit(1);
        return mongoTemplate.findOne(query, clazz);
    }

    @Override
    public List<T> list(Criteria criteria) {
        Query query = new Query(criteria);
        return mongoTemplate.find(query, clazz);
    }

    @Override
    public UpdateResult deactive(String... beanIdList) {
        Criteria criteria = Criteria.where("_id").in(beanIdList);
        Update update = Update.update("is_active", false);
        return mongoTemplate.updateMulti(Query.query(criteria), update, clazz);
    }
}
