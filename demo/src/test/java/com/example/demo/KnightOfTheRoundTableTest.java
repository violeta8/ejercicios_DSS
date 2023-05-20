package com.example.demo.Knight;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KnightOfTheRoundTableTest {
    
        @Test
        void testEmbarkOnQuest() throws QuestFailedException {
            KnightOfTheRoundTable knight = new KnightOfTheRoundTable("Bedivere");
            knight.embarkOnQuest();
        }

        @Test
        void testEmbarkOnQuestWithMock() throws QuestFailedException {
            Quest<HolyGrail> mockQuest = mock(Quest.class);
            KnightOfTheRoundTable knight = new KnightOfTheRoundTable("Bedivere", mockQuest);
            knight.embarkOnQuest();
            verify(mockQuest, times(1)).embark();
        }

        @Test
        void testEmbarkOnQuestWithMockAndException() throws QuestFailedException {
            Quest<HolyGrail> mockQuest = mock(Quest.class);
            when(mockQuest.embark()).thenThrow(new QuestFailedException());
            KnightOfTheRoundTable knight = new KnightOfTheRoundTable("Bedivere", mockQuest);
            knight.embarkOnQuest();
            verify(mockQuest, times(1)).embark();
        }
}
