package nl.tomkemper.dddemo.survey;

public class Answer {
    protected Question question;
    private String label;

    public Answer(String label) { //Mag een antwoord zonder vraag bestaan?
        this.label = label;
    }

    public Question getQuestion() {
        return this.question;
    }

    public void setQuestion(Question question){ //Mag een antwoord zonder vraag bestaan?
        if(this.question != null){
            this.question.removeAnswer(this);
        }
        question.addAnswer(this);
    }

}
