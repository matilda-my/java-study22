package com.kakaoent.matilda.controller;

import com.kakaoent.matilda.service.Consumer;
import com.kakaoent.matilda.service.Producer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
    private final Producer producer;
    private final Consumer consumer;

    public KafkaController(Producer producer, Consumer consumer) {
        this.producer = producer;
        this.consumer = consumer;
    }

    @GetMapping("/send")
    public void send(@RequestParam String msg) {
        producer.sendMessage(msg);
    }

    @GetMapping("/printAll")
    public void get() {
        consumer.printMessages();
    }
}
