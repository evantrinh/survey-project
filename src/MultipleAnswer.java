import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultipleAnswer extends Answer {
    private static final long serialVersionUID = 1L;
    
    private List<Object> answerValues;
    
    /* default constructor */
    public MultipleAnswer() {
        super();
        this.answerValues = new ArrayList<>();
    }
    
    /* takes in question, constructs multiple answer */
    public MultipleAnswer(Question question) {
        super(question);
        this.answerValues = new ArrayList<>();
    }
    
    @Override
    public Object getAnswerValue() {
        return answerValues;
    }
    
    @Override
    public void setAnswerValue(Object value) {
        if (value instanceof List) {
            this.answerValues = new ArrayList<>((List<?>) value);
        } else {
            this.answerValues = new ArrayList<>();
            this.answerValues.add(value);
        }
    }
    
    /* takes in value, adds to answer values */
    public void addAnswerValue(Object value) {
        this.answerValues.add(value);
    }
    
    /* takes in index, removes answer value */
    public void removeAnswerValue(int index) {
        if (index >= 0 && index < answerValues.size()) {
            answerValues.remove(index);
        }
    }
    
    /* returns all answer values */
    public Object[] getAnswerValues() {
        return answerValues.toArray();
    }
    
    @Override
    public void modifyAnswer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Current answers: " + answerValues);
        System.out.print("Enter number of new answers: ");
        int count = scanner.nextInt();
        scanner.nextLine();
        
        answerValues.clear();
        for (int i = 0; i < count; i++) {
            System.out.print("Enter answer " + (i + 1) + ": ");
            String answer = scanner.nextLine();
            answerValues.add(answer);
        }
    }
    
    @Override
    public void displayAnswer() {
        if (getQuestion() != null) {
            System.out.println("Q" + getQuestion().getQuestionNumber() + ": " + getQuestion().getPrompt());
        }
        System.out.println("Answers:");
        for (int i = 0; i < answerValues.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + answerValues.get(i));
        }
    }
    
    @Override
    public boolean isValid() {
        return answerValues != null && !answerValues.isEmpty();
    }
    
    @Override
    public boolean compare(Answer other) {
        if (other == null || !(other instanceof MultipleAnswer)) {
            return false;
        }
        
        MultipleAnswer otherMA = (MultipleAnswer) other;
        Object[] otherValues = otherMA.getAnswerValues();
        
        if (answerValues.size() != otherValues.length) {
            return false;
        }
        
        for (int i = 0; i < answerValues.size(); i++) {
            String thisValue = answerValues.get(i).toString().trim();
            String otherValue = otherValues[i].toString().trim();
            if (!thisValue.equalsIgnoreCase(otherValue)) {
                return false;
            }
        }
        
        return true;
    }
}
