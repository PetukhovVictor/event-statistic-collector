package com.yandex.money.eventstatisticcollector;

import com.google.gson.Gson;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.joda.time.DateTime;

/**
 * Elasticsearch event manager.
 * Класс, предоставляющий функционал для учета и подсчета событий с использованием Elasticsearch.
 */
public final class EsEventManager extends EsConnectible implements EventManager {
    /**
     * Название поля в Elasticsearch с датой события.
     */
    private static final String ES_DATE_FILED_NAME = "date";

    /**
     * Иднекс в Elasticsearch, в котором будут храниться события.
     */
    private String esIndex = "events";

    /**
     * Тип в Elasticsearch для записей событий.
     */
    private String esType = "time_point";

    /**
     * Сериализация объекта события в JSON.
     *
     * @param event Объект события.
     * @return Сериализованный объект события (JSON-строка).
     */
    private String eventObjectSerialize(EventDTO event) {
        return new Gson().toJson(event);
    }

    /**
     * Установка индекса в Elasticsearch, который будет использовать для записи событий.
     *
     * @param indexName Название индекса в Elasticsearch.
     */
    public void setEsIndex(String indexName) {
        esIndex = indexName;
    }

    /**
     * Установка типа в Elasticsearch для записей событий.
     *
     * @param typeName Название типа для записей в Elasticsearch.
     */
    private void setEsType(String typeName) {
        esType = typeName;
    }

    /**
     * Учет события.
     *
     * @param time Время наступления события.
     */
    public void considerEvent(DateTime time) {
        IndexRequest request = new IndexRequest(esIndex, esType);

        EventDTO event = new EventDTO(time);

        request.source(eventObjectSerialize(event), XContentType.JSON);
        client.index(request).actionGet();
    }

    /**
     * Подсчет колечества событий, произошедших с заданного времени.
     *
     * @param time Время, начиная с которого нужно подсчитать количество событий.
     */
    private Long countEvents(DateTime time) {
        QueryBuilder qb = QueryBuilders
                .rangeQuery(ES_DATE_FILED_NAME)
                .from(time.getMillis());

        SearchResponse response = client.prepareSearch(esIndex)
                .setTypes(esType)
                .setQuery(qb)
                .get();

        return response.getHits().getTotalHits();
    }

    /**
     * Подсчет колечества событий, произошедших за последнюю минуту.
     */
    public Long countEventsInLastMinute() {
        return countEvents(new DateTime().minusMinutes(1));
    }

    /**
     * Подсчет колечества событий, произошедших за последний час.
     */
    public Long countEventsInLastHour() {
        return countEvents(new DateTime().minusHours(1));
    }

    /**
     * Подсчет колечества событий, произошедших за последний день.
     */
    public Long countEventsInLastDay() {
        return countEvents(new DateTime().minusDays(1));
    }
}
