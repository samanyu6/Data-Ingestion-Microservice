package com.microservices.demo.elastic.query.util;

import com.microservices.demo.elastic.model.index.IndexModel;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class ElasticQueryUtil<T extends IndexModel> {
        public Query getSearchQueryById(String id) {
            return new NativeSearchQueryBuilder()
                    .withIds(Collections.singleton(id))
                    .build();
        }

        public Query getSearchQueryByFieldText(String fields, String text) {
            return new NativeSearchQueryBuilder()
                    .withQuery(new BoolQueryBuilder()
                        .must(QueryBuilders.matchQuery(fields, text)))
                        .build();
        }

        public Query getSearchQueryForAll() {
            return new NativeSearchQueryBuilder()
                    .withQuery(new BoolQueryBuilder()
                        .must(QueryBuilders.matchAllQuery()))
                        .build();
        }
}
