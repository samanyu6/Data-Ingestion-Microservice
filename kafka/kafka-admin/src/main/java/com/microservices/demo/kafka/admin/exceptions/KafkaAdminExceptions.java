package com.microservices.demo.kafka.admin.exceptions;

public class KafkaAdminExceptions extends RuntimeException{

    public KafkaAdminExceptions() {
    }

    public KafkaAdminExceptions(String msg){
        super(msg);
    }

    public KafkaAdminExceptions(String msg, Throwable cause){
        super(msg, cause);
    }
}
