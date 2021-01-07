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
@Document(collection = "car")
@CompoundIndexes({
        @CompoundIndex(name = "license_no_idx", def = "{'license_no': 1}")
})
public class Car extends MongoBasicEntity {
    private static final long serialVersionUID = 382016627098620070L;

    /**
     * 订单编号
     */
    @Field("license_no")
    private String licenseNo;
}
