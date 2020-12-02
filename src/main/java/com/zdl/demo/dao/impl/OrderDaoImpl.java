package com.zdl.demo.dao.impl;

import com.zdl.demo.dao.OrderDao;
import com.zdl.demo.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderDaoImpl extends MongoBasicRepositoryImpl<Order> implements OrderDao {

}
