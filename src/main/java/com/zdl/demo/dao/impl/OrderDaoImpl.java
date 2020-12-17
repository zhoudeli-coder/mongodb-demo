package com.zdl.demo.dao.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.zdl.demo.dao.OrderDao;
import com.zdl.demo.entity.Order;
import org.bson.BSONObject;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDaoImpl extends MongoBasicRepositoryImpl<Order> implements OrderDao {

    @Override
    public List<BasicDBObject> aggregation() {
/*        DBObject $group = new BasicDBObject("$group", JSON.parse("{\n" +
                "            \"_id\": {\n" +
                "                $dateToString: {\n" +
                "                    format: \"%Y-%m-%d\",\n" +
                "                    date: {\n" +
                "                        \"$convert\": {\n" +
                "                            input: \"$created_at\",\n" +
                "                            to: \"date\"\n" +
                "                        }\n" +
                "                    },\n" +
                "\t\t\t\t\t\t\t\t\t\ttimezone: \"Asia/Shanghai\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"count\": {\n" +
                "                \"$sum\": 1\n" +
                "            }\n" +
                "        }"));*/
        DateOperators.DateToString toString = DateOperators.DateToString.dateOf(ConvertOperators.Convert.convertValueOf("created_at").to("date"))
                .toString("%Y-%m-%d").withTimezone(DateOperators.Timezone.valueOf("Asia/Shanghai"));
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project().and(toString).as("date_time"),
                Aggregation.group("date_time").count().as("count")
        );
        AggregationResults<BasicDBObject> list = mongoTemplate.aggregate(aggregation, "order", BasicDBObject.class);
        return list.getMappedResults();
    }
}
