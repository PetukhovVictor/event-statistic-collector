package com.yandex.money.eventstatisticcollector;

import com.google.gson.Gson;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.Date;

public class EventManager extends EsConnectible {
    private static final String ES_INDEX = "events";
    private static final String ES_TYPE = "my_event_type";

    public EventManager() {

    }

    private String objectSerialize(Object object) {
        return new Gson().toJson(object);
    }

    void write(Date date) {
        IndexRequest request = new IndexRequest(ES_INDEX, ES_TYPE);
        Event event = new Event(date);

        request.source(objectSerialize(event), XContentType.JSON);
        client.index(request).actionGet();
    }

    public Integer countEvents(Date date) {
        QueryBuilder qb = QueryBuilders
                .rangeQuery("date")
                .to(new Date().getTime());

        SearchResponse response = client.prepareSearch(ES_INDEX)
                .setTypes(ES_TYPE)
                .setQuery(qb)
                .get();

        System.out.println(response);

        return 0;
    }

    Integer countEventsInLastMinute() {

        return 0;
    }

    Integer countEventsInLastHour() {

        return 0;
    }

    Integer countEventsInLastDay() {

        return 0;
    }
}
