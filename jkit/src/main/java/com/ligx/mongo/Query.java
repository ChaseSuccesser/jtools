package com.ligx.mongo;

import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: ligongxing.
 * Date: 2018年08月24日.
 */
public class Query {

    private List<String> includeFields;
    private String collectionName;
    private Bson whereCondition;
    private String orderField;
    private SortType sortType;

    public Query select(List<String> includeFields) {
        this.includeFields = includeFields;
        return this;
    }

    public Query select(String... includeFields) {
        this.includeFields = new ArrayList<>(Arrays.asList(includeFields));
        return this;
    }

    public Query from(String collectionName) {
        this.collectionName = collectionName;
        return this;
    }

    public Query where(Bson whereCondition) {
        this.whereCondition = whereCondition;
        return this;
    }

    public Query order_by(String orderField) {
        this.orderField = orderField;
        return this;
    }

    public Query asc() {
        this.sortType = SortType.ASC;
        return this;
    }

    public Query desc() {
        this.sortType = SortType.DESC;
        return this;
    }
}
