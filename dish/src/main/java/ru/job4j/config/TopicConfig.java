package ru.job4j.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {
    @Bean
    public NewTopic dishUpdatesTopic(@Value("${kafka.topic.dish-updates}") String name) {
        return TopicBuilder
                .name(name)
                .partitions(1)
                .replicas(1)
                .build();
    }

}
