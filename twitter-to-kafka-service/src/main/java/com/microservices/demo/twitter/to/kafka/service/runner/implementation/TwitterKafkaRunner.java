package com.microservices.demo.twitter.to.kafka.service.runner.implementation;

import com.microservices.demo.twitter.to.kafka.service.config.TwitterToKafkaConfigData;
import com.microservices.demo.twitter.to.kafka.service.listener.TwitterStreamListenerStatus;
import com.microservices.demo.twitter.to.kafka.service.runner.TwitterKafkaRunnerInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import twitter4j.FilterQuery;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.annotation.PreDestroy;
import java.util.Arrays;

@Component
public class TwitterKafkaRunner implements TwitterKafkaRunnerInterface {

    private final TwitterToKafkaConfigData twitterToKafkaConfigData;
    private final TwitterStreamListenerStatus twitterStreamListenerStatus;
    private TwitterStream twitterStream;
    private static final Logger LOG = LoggerFactory.getLogger(TwitterKafkaRunner.class);

    public TwitterKafkaRunner(TwitterToKafkaConfigData twitterToKafkaConfigData, TwitterStreamListenerStatus twitterStreamListenerStatus) {
        this.twitterToKafkaConfigData = twitterToKafkaConfigData;
        this.twitterStreamListenerStatus = twitterStreamListenerStatus;
    }

    @Override
    public void start() throws TwitterException {
        twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(twitterStreamListenerStatus);
        addFilter();
    }

    @PreDestroy
    public void preDestroy() {
        if(twitterStream != null){
            LOG.info("Twitter stream shutdown");
            twitterStream.shutdown();
        }
    }

    private void addFilter() {
        String []keywords = twitterToKafkaConfigData.getTwitterTopics().toArray(new String[0]);
        FilterQuery filterQuery = new FilterQuery(keywords);
        twitterStream.filter(filterQuery);
        LOG.info("Twitter Adding filter for {}", Arrays.toString(keywords));
    }
}
