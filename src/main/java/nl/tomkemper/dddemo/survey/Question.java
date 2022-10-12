package nl.tomkemper.dddemo.survey;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Question {
    private final String label;
    private List<Answer> answers = new ArrayList<>();


    public Question(String label) {
        this.label = label;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
        answer.question = this;
    }

    public Collection<Answer> getAnswers() {
        return Collections.unmodifiableCollection(this.answers);
    }

    public void removeAnswer(Answer answer) {
        this.answers.remove(answer);
    }
}
