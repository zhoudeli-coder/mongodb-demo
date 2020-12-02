package com.zdl.demo.dao;

import com.mongodb.client.result.UpdateResult;
import com.zdl.demo.entity.MongoBasicEntity;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

/**
 * @author zhoudeli
 */
public interface MongoBasicRepository<T extends MongoBasicEntity> {
    /**
     * 增加
     */
    T insert(T bean);

    /**
     * 批量增加
     */
    List<T> batchInsert(List<T> bean);

    /**
     * 修改
     */
    T update(T bean);

    /**
     * 查询一条
     */
    T findOne(Criteria criteria);

    /**
     * 查询多条
     */
    List<T> list(Criteria criteria);

    /**
     * 取消激活
     */
    UpdateResult deactive(String... beanIdList);
}
