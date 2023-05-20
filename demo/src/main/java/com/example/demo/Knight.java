package com.example.demo;

public interface Knight<T> {
    T embarkOnQuest() throws QuestFailedException;
}
