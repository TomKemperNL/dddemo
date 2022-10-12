package nl.tomkemper.dddemo.survey;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SurveyTest {

    @Test
    public void canAddAnswerToQuestions(){
        Question q1 = new Question("Hoe gaat het?");
        Answer a1 = new Answer("Goed");
        q1.addAnswer(a1);
        q1.addAnswer(new Answer("Medium"));
        q1.addAnswer(new Answer("Slecht"));

        assertTrue(q1.getAnswers().contains(a1));
        assertEquals(q1, a1.getQuestion());
    }

    @Test
    public void canMoveAnswerBetweenQuestions(){
        Question q1 = new Question("Hoe gaat het?");
        Answer a1 = new Answer("Goed");
        q1.addAnswer(a1);

        Question q2 = new Question("Wat is een ander naam voor spul in de economie?");
        a1.setQuestion(q2);

        assertTrue(q2.getAnswers().contains(a1));
        assertFalse(q1.getAnswers().contains(a1));
        assertEquals(q2, a1.getQuestion());
    }
}
