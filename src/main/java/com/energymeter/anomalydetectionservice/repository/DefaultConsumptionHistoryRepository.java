package com.energymeter.anomalydetectionservice.repository;

import com.energymeter.anomalydetectionservice.entity.ConsumptionHistory;
import com.mongodb.client.DistinctIterable;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DefaultConsumptionHistoryRepository implements ConsumptionHistoryRepository {

    private final MongoTemplate mongoTemplate;
    private final String collectionName = "consumption_history";

    @Override
    public List<Double> findConsumptionByUserId(long userId) {
        DistinctIterable<Double> iterableObject = mongoTemplate.getCollection(collectionName).distinct("userId", Double.class);
        Iterator<Double> iterator = iterableObject.iterator();
        return IteratorUtils.toList(iterator);
    }

    @Override
    public ConsumptionHistory save(ConsumptionHistory consumptionHistory) {
        return mongoTemplate.insert(consumptionHistory, collectionName);
    }


}
