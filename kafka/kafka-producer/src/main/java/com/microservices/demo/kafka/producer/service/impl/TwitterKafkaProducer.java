package com.microservices.demo.kafka.producer.service.impl;

import com.microservices.demo.kafka.avro.model.TwitterAvroModel;
import com.microservices.demo.kafka.producer.service.KafkaProducer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.PreDestroy;

@Service
public class TwitterKafkaProducer implements KafkaProducer<Long, TwitterAvroModel> {

    private final Logger LOG = LoggerFactory.getLogger(TwitterKafkaProducer.class);

    private KafkaTemplate<Long, TwitterAvroModel> kafkaTemplate;

    public TwitterKafkaProducer(KafkaTemplate<Long, TwitterAvroModel> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String topicName, Long key, TwitterAvroModel message) {
        LOG.info("Sending Message '{}' to topic '{}'", message, topicName);
        ListenableFuture<SendResult<Long, TwitterAvroModel>> kafkaResult = kafkaTemplate.send(topicName, key, message);

        kafkaResult.addCallback(new ListenableFutureCallback<SendResult<Long, TwitterAvroModel>>() {
            @Override
            public void onFailure(Throwable throwable) {
                LOG.error("Error while sending message : {}, to topic: {}", message.toString(), topicName, throwable);
            }

            @Override
            public void onSuccess(SendResult<Long, TwitterAvroModel> longTwitterAvroModelSendResult) {
                RecordMetadata record = longTwitterAvroModelSendResult.getRecordMetadata();

                LOG.debug("Received metadata, Topic: {}; Partition: {}; Offset: {}; TimeStamp: {} at time {}",
                        record.topic(),
                        record.partition(),
                        record.offset(),
                        record.timestamp(),
                        System.nanoTime()
                );
            }
        });
    }

    @PreDestroy
    public void close() {
        if(kafkaTemplate != null){
            LOG.info("Closing kafka producer");
            kafkaTemplate.destroy();
        }
    }
}
