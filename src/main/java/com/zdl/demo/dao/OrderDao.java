package com.zdl.demo.dao;

import com.mongodb.BasicDBObject;
import com.zdl.demo.entity.Order;

import java.util.List;

/**
 * @author zhoudeli
 */
public interface OrderDao extends MongoBasicRepository<Order> {

    List<BasicDBObject> aggregation();

    List<BasicDBObject> aggregation2();
}
