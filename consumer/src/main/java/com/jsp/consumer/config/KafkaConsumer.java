package com.jsp.consumer.config;

import com.jsp.consumer.model.Student;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConsumer {

    @KafkaListener(topics = "add-student", groupId = "0123")
    public void consumeAddStudent(String student) {
        System.out.println("Consumed message from add-student topic: " + student);
    }

    @KafkaListener(topics = "delete-student", groupId = "0123")
    public void consumeDeleteStudent(Student student) {
        System.out.println("Consumed message from delete-student topic: " + student);
    }

    @KafkaListener(topics = "get-student", groupId = "0123")
    public void consumeGetStudent(String student) {
        System.out.println("Consumed message from get-student topic: " + student);
    }

    @KafkaListener(topics = "update-student", groupId = "0123")
    public void consumeUpdateStudent(Student student) {
        System.out.println("Consumed message from get-student topic: " + student);
    }

}