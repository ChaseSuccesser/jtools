package com.ligx.mongo;

import lombok.Getter;
import lombok.ToString;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: ligongxing.
 * Date: 2018年08月24日.
 */
@Getter
@ToString
public class Query {

    private List<String> includeFields;
    private String collectionName;
    private Bson whereCondition;
    private List<String> orderFields = new ArrayList<>();
    private List<SortType> sortTypes = new ArrayList<>();
    private int skip;
    private int limit;

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
        this.orderFields.add(orderField);
        return this;
    }

    public Query asc() {
        this.sortTypes.add(SortType.ASC);
        return this;
    }

    public Query desc() {
        this.sortTypes.add(SortType.DESC);
        return this;
    }

    public Query skip(int skip) {
        this.skip = skip;
        return this;
    }

    public Query limit(int limit) {
        this.limit = limit;
        return this;
    }

}
