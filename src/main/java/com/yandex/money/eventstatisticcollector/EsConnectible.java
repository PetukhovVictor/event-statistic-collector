package com.yandex.money.eventstatisticcollector;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

abstract class EsConnectible {
    private static final String ES_HOST = "localhost";
    private static final Integer ES_PORT = 9300;

    private static final String ES_CLUSTER_NAME = "test";
    private static final String ES_NODE_NAME = "main";

    protected TransportClient client;

    EsConnectible() {
        try {
            Settings settings = Settings.builder().put("cluster.name", ES_CLUSTER_NAME).put("node.name", ES_NODE_NAME).build();
            TransportAddress transport = new TransportAddress(InetAddress.getByName(ES_HOST), ES_PORT);

            client = new PreBuiltTransportClient(settings).addTransportAddress(transport);
        } catch (UnknownHostException e) {

        }
    }
}
