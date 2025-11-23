import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SurveyResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<Answer> answers;
    
    /* default constructor */
    public SurveyResponse() {
        this.answers = new ArrayList<>();
    }
    
    /* takes in answer, adds answer */
    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }
    
    /* takes in question number, returns answer */
    public Answer getAnswer(int questionNum) {
        for (Answer answer : answers) {
            if (answer.getQuestion() != null && answer.getQuestion().getQuestionNumber() == questionNum) {
                return answer;
            }
        }
        return null;
    }
    
    /* returns all answers */
    public Answer[] getAllAnswers() {
        return answers.toArray(new Answer[0]);
    }
    
    /* displays response */
    public void displayResponse() {
        System.out.println("=== Survey Response ===");
        
        for (Answer answer : answers) {
            answer.displayAnswer();
            // System.out.println("----------------------------------------");
        }
    }
    

    /* returns if response is complete */
    public boolean isComplete() {
        if (answers.isEmpty()) {
            return false;
        }
        
        for (Answer answer : answers) {
            if (!answer.isValid()) {
                return false;
            }
        }
        
        return true;
    }
}
