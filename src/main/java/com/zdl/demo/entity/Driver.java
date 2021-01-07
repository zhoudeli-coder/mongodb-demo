package com.zdl.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 购票订单
 *
 * @author zhoudeli
 */
@Data
@NoArgsConstructor
@Document(collection = "driver")
public class Driver extends MongoBasicEntity {
    private static final long serialVersionUID = -2151089832520399018L;

    /**
     * 订单编号
     */
    @Field("name")
    @Indexed(unique = true)
    private String name;
}
