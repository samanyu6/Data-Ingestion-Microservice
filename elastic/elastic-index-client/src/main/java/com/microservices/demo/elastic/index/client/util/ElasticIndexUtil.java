package com.microservices.demo.elastic.index.client.util;

import com.microservices.demo.elastic.model.index.IndexModel;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ElasticIndexUtil<T extends IndexModel>{
    public List<IndexQuery> getIndexQueries(List<T> documents) {
        return documents.stream()
                .map(document -> new IndexQueryBuilder()
                                .withId(document.getId())
                                .withObject(document)
                                .build()
                ).collect(Collectors.toList());
    }
}
