package com.kakaoent.matilda.service.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Service
public class Consumer {
    private final String TOPIC_NAME = "source";

    private KafkaConsumer<String, String> consumer;

    public Consumer(@Qualifier("kafkaProperties") Properties properties) {
        consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList(TOPIC_NAME));
    }

    public void printMessages() {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
        for (ConsumerRecord<String, String> record : records) {
            System.out.println("------------------------" + record.value());
        }
    }
}
