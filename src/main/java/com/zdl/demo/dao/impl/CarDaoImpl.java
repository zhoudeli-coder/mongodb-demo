package com.zdl.demo.dao.impl;

import com.zdl.demo.dao.CarDao;
import com.zdl.demo.entity.Car;
import org.springframework.stereotype.Service;

@Service
public class CarDaoImpl extends MongoBasicRepositoryImpl<Car> implements CarDao {
}
