package com.kakaoent.matilda.service.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.Properties;

@Service
public class Producer {
    private final String TOPIC_NAME = "source";

    private KafkaProducer<String, String> producer;

    public Producer(@Qualifier("kafkaProperties") Properties properties) {
        producer = new KafkaProducer<String, String>(properties);
    }

    public void sendMessage(String msg) {
        producer.send(new ProducerRecord(TOPIC_NAME, msg));
    }

    public void flush() {
        producer.flush();
    }

    @PreDestroy
    public void close() {
        producer.close();
    }
}
