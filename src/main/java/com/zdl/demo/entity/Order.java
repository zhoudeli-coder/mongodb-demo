package com.zdl.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 购票订单
 * @author zhoudeli
 */
@Data
@NoArgsConstructor
@Document(collection = "order")
public class Order extends MongoBasicEntity{
	private static final long serialVersionUID = 5035937904320562316L;

	/**
	 * 订单编号
	 */
	@Field("order_num")
	@Indexed(unique = true)
	private String orderNum;
}
