package com.zdl.demo.controller;

import com.google.common.collect.Lists;
import com.zdl.demo.dao.OrderDao;
import com.zdl.demo.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    @Autowired
    private OrderDao orderDao;

    @PostMapping("insert")
    public String insert(String orderNum){
        Order order = new Order();
        order.setOrderNum(orderNum);
        orderDao.insert(order);
        return "成功";
    }

    @PostMapping("insertBatch")
    public String insertBatch(String orderNum){
        Order order = new Order();
        order.setOrderNum(orderNum);
        Order order2 = new Order();
        order2.setOrderNum(orderNum + 1);
        orderDao.batchInsert(Lists.newArrayList(order, order2));
        return "成功";
    }

    @GetMapping("list")
    public List<Order> list(){
        return orderDao.list(new Criteria());
    }

    @GetMapping("deactive")
    public String deactive(String orderId){
        orderDao.deactive(orderId);
        return "成功";
    }
}
