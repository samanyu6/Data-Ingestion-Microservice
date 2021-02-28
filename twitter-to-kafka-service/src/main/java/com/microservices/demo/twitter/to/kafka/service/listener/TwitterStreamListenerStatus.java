package com.microservices.demo.twitter.to.kafka.service.listener;

import com.microservices.demo.config.KafkaConfigData;
import com.microservices.demo.kafka.avro.model.TwitterAvroModel;
import com.microservices.demo.kafka.producer.service.KafkaProducer;
import com.microservices.demo.twitter.to.kafka.service.transformer.TwitterStatusToAvroTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.StatusAdapter;

@Component
public class TwitterStreamListenerStatus extends StatusAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(TwitterStreamListenerStatus.class);

    private final KafkaConfigData kafkaConfigData;
    private final TwitterStatusToAvroTransformer twitterStatusToAvroTransformer;
    private final KafkaProducer<Long, TwitterAvroModel> kafkaProducer;

    public TwitterStreamListenerStatus(KafkaConfigData kafkaConfigData, TwitterStatusToAvroTransformer twitterStatusToAvroTransformer, KafkaProducer<Long, TwitterAvroModel> kafkaProducer) {
        this.kafkaConfigData = kafkaConfigData;
        this.twitterStatusToAvroTransformer = twitterStatusToAvroTransformer;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void onStatus(Status status) {
        LOG.info("Twitter Listener Status {}", status.getText());

        TwitterAvroModel twitterAvroModel = twitterStatusToAvroTransformer.getTwitterAvroModelFromStatus(status);
        kafkaProducer.send(kafkaConfigData.getTopicName(), twitterAvroModel.getUserId(), twitterAvroModel);
    }
}
