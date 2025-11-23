import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test extends Survey {
    private static final long serialVersionUID = 1L;
    
    private List<Answer> correctAnswers;
    
    /* default constructor */
    public Test() {
        super();
        this.correctAnswers = new ArrayList<>();
    }
    
    /* takes in name, constructs test */
    public Test(String name) {
        super(name);
        this.correctAnswers = new ArrayList<>();
    }
    
    /* takes in answer, adds correct answer */
    public void addCorrectAnswer(Answer answer) {
        this.correctAnswers.add(answer);
    }
    
    /* takes in index, returns correct answer */
    public Answer getCorrectAnswer(int index) {
        if (index >= 0 && index < correctAnswers.size()) {
            return correctAnswers.get(index);
        }
        return null;
    }
    
    /* returns all correct answers */
    public Answer[] getAllCorrectAnswers() {
        return correctAnswers.toArray(new Answer[0]);
    }
    
    /* takes in index and answer, sets correct answer */
    public void setCorrectAnswer(int index, Answer answer) {
        if (index >= 0 && index < correctAnswers.size()) {
            correctAnswers.set(index, answer);
        } else if (index == correctAnswers.size()) {
            correctAnswers.add(answer);
        }
    }
    
    /* displays test with correct answers */
    public void displayTestWithAnswers() {
        System.out.println("\nTest: " + getSurveyName());
        System.out.println("Number of questions: " + getNumQuestions());
        System.out.println();
        
        Question[] questions = getAllQuestions();
        for (int i = 0; i < questions.length; i++) {
            questions[i].displayQuestion();
            
            if (i < correctAnswers.size() && correctAnswers.get(i) != null) {
                System.out.print("The correct answer is: ");
                
                Object value = correctAnswers.get(i).getAnswerValue();
                if (value != null) {
                    System.out.println(value.toString());
                } else {
                    System.out.println("Not set");
                }
            }
            
            System.out.println();
        }
    }
}
