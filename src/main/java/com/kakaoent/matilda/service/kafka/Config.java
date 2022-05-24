package com.kakaoent.matilda.service.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class Config {
    @Bean("kafkaProperties")
    public Properties getProperties() {
        Properties properties = new Properties();

        properties.put("bootstrap.servers", "b-2.matilda-cluster-1.m77db2.c3.kafka.ap-northeast-2.amazonaws.com:9092,b-3.matilda-cluster-1.m77db2.c3.kafka.ap-northeast-2.amazonaws.com:9092,b-1.matilda-cluster-1.m77db2.c3.kafka.ap-northeast-2.amazonaws.com:9092");
        properties.put("acks", "all");
        properties.put("group.id", "test_group2");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        return properties;
    }
}
