package com.yandex.money.eventstatisticcollector;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Класс, предоставляющий функциональность для работы с Elasticsearch.
 */
abstract class EsConnectible {
    /**
     * Название хоста Elasticsearch.
     */
    private String esHost = "localhost";

    /**
     * Порт сервера Elasticsearch.
     */
    private Integer esPort = 9300;

    /**
     * Название кластера Elasticsearch.
     */
    private String esClusterName = "production";

    /**
     * Название узла Elasticsearch.
     */
    private String esNodeName = "main";

    /**
     * Клиент для общения с Elasticsearch.
     */
    protected TransportClient client;

    /**
     * Конструктор для параметров соединения по умолчанию.
     */
    EsConnectible() {
        connect();
    }

    /**
     * Конструктор с указание только хоста и порта.
     */
    EsConnectible(String host, Integer port) {
        esHost = host;
        esPort = port;

        connect();
    }

    /**
     * Конструктор.
     */
    EsConnectible(String host, Integer port, String cluster, String node) {
        esHost = host;
        esPort = port;
        esClusterName = cluster;
        esNodeName = node;

        connect();
    }

    /**
     * Инициализация соединения с Elasticsearch.
     */
    private void connect() {
        try {
            Settings settings = Settings.builder().put("cluster.name", esClusterName).put("node.name", esNodeName).build();
            TransportAddress transport = new TransportAddress(InetAddress.getByName(esHost), esPort);

            client = new PreBuiltTransportClient(settings).addTransportAddress(transport);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
