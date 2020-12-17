package com.zdl.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * 购票订单
 *
 * @author zhoudeli
 */
@Data
@NoArgsConstructor
public class MongoBasicEntity implements Serializable {
    private static final long serialVersionUID = -6242794032148489926L;
    /**
     * id
     */
    @Id
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String id;

    /**
     * 有效数据
     */
    @Field("is_active")
    private Boolean isActive = true;

    /**
     * 有效数据
     */
    @Field("created_at")
    private Long createdAt;
}
