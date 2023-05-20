package com.example.demo;

public class KnightOfTheRoundTable  implements Knight<T>{
    private String name;
    private Quest<HolyGrail> quest;

    public KnightOfTheRoundTable(String name, Quest<HolyGrail> quest) {
        this.name = name;
        this.quest = quest;
    }

    public T embarkOnQuest() throws QuestFailedException {
        return quest.embark();
    }

    public void setQuest(Quest<T> quest) {
        this.quest = quest;
    }
}
