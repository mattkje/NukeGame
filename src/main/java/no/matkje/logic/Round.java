package no.matkje.logic;

import java.util.HashMap;

public class Round {

    private final HashMap<Link, Question> questions;
    private final Question openingQuestion;

    /**
     * Creates an instance of round.
     */
    public Round(Question openingQuestion) {

        this.questions = new HashMap<>();
        this.openingQuestion = openingQuestion;
        this.addQuestion(openingQuestion);
    }

    /**
     * Adds a new question to the round.
     *
     * @param question The passage to add to the story.
     */
    public void addQuestion(Question question) {
        if (question == null) {
            throw new IllegalArgumentException("Question can not be null");
        }
        //Link link = new Link(question.getTitle(), question.getTitle());
        //this.questions.put(link, question);
    }
}
