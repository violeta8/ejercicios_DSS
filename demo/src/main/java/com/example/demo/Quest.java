package com.example.demo;

public interface Quest<T> {
    T embark() throws QuestFailedException;
}