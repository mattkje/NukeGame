package no.matkje.logic;

public class Question {

    private String question;

    private String imagePath;

    private String answer;

    public Question(String question, String imagePath, String answer){
        this.question = question;
        this.imagePath = imagePath;
        this.answer = answer;
    }

    public String getQuestion(String question){
        return question;
    }
    public String getImagePath(String imagePath){
        return imagePath;
    }
    public String getAnswer(String answer){
        return answer;
    }
}
