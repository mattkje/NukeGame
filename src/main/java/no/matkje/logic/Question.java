package no.matkje.logic;

import javafx.scene.image.Image;


/**
 * The Question class represents a question in the game.
 *
 * @author Matti Kjellstadli
 * @version 2023-09-11
 */
public class Question {

    private int id;
    private String question;

    private String answer;

    private Image image;

    private String category;

    /**
     * Creates an instance of Question.
     * @param id The question id.
     * @param question The question text.
     * @param answer The answer for the question.
     * @param image The question image.
     * @param category The question category.
     */
    public Question(int id, String question, String answer, Image image, String category){
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.image = image;
        this.category = category;
    }
    public int getId(int id){
        return id;
    }

    public String getQuestion(String question){
        return question;
    }

    public String getAnswer(String answer){
        return answer;
    }

    public Image getImage(Image image){
        return image;
    }

    public String getCategory(String category){
        return category;
    }
}
