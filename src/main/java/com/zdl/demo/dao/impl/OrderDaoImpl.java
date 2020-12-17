package com.zdl.demo.dao.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.mongodb.BasicDBObject;
import com.mongodb.client.AggregateIterable;
import com.zdl.demo.dao.OrderDao;
import com.zdl.demo.entity.Order;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ConvertOperators;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDaoImpl extends MongoBasicRepositoryImpl<Order> implements OrderDao {

    @Override
    public List<BasicDBObject> aggregation() {
        DateOperators.DateToString toString = DateOperators.DateToString.dateOf(ConvertOperators.Convert.convertValueOf("created_at").to("date"))
                .toString("%Y-%m-%d").withTimezone(DateOperators.Timezone.valueOf("Asia/Shanghai"));
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project().and(toString).as("date_time"),
                Aggregation.group("date_time").count().as("count")
        );
        AggregationResults<BasicDBObject> list = mongoTemplate.aggregate(aggregation, "order", BasicDBObject.class);
        return list.getMappedResults();
    }


    @Override
    public List<BasicDBObject> aggregation2() {
        BasicDBObject $group = new BasicDBObject("$group", JSON.parse("{" +
                "            \"_id\": {" +
                "                $dateToString: {" +
                "                    format: \"%Y-%m-%d\"," +
                "                    date: {" +
                "                        \"$convert\": {" +
                "                            input: \"$created_at\"," +
                "                            to: \"date\"" +
                "                        }" +
                "                    }," +
                "timezone: \"Asia/Shanghai\"" +
                "                }" +
                "            }," +
                "            \"count\": {" +
                "                \"$sum\": 1" +
                "            }" +
                "        }"));
        List<BasicDBObject> pipeline = Lists.newArrayList();
        pipeline.add($group);

        AggregateIterable<BasicDBObject> list = mongoTemplate.getCollection("order").aggregate(pipeline, BasicDBObject.class);
        List<BasicDBObject> result = Lists.newArrayList(list);
        return result;
    }
}
