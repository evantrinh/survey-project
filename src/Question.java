import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String prompt;
    private int questionNumber;
    private List<Answer> answers;
    
    /* default constructor */
    public Question() {
        this.answers = new ArrayList<>();
    }
    
    /* takes in prompt, constructs question */
    public Question(String prompt) {
        this.prompt = prompt;
        this.answers = new ArrayList<>();
    }
    
    /* returns prompt */
    public String getPrompt() {
        return prompt;
    }
    
    /* takes in prompt, sets prompt */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
    
    /* returns question number */
    public int getQuestionNumber() {
        return questionNumber;
    }
    
    /* takes in num, sets question number */
    public void setQuestionNumber(int num) {
        this.questionNumber = num;
    }
    
    /* takes in answer, adds answer */
    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }
    
    /* returns all answers */
    public Answer[] getAnswers() {
        return answers.toArray(new Answer[0]);
    }
    
    /* displays question */
    public abstract void displayQuestion();
    
    /* modifies question */
    public abstract void modifyQuestion();
    
    /* takes answer from user, returns answer */
    public abstract Answer takeAnswer();
    
    /* returns if accepts multiple answers */
    public abstract boolean acceptsMultipleAnswers();
}
