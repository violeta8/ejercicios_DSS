package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public Knight knight() {
        return new KnightOfTheRoundTable("Bedivere" , quest());
    }

    @Bean
    public Quest<T> quest() {
        return new HolyGrailQuest();
    }

    @Bean
    public HolyGrailQuest quest() {
        return new HolyGrailQuest();
    }
}
