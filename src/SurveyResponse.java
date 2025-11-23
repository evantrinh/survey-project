import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SurveyResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String DATA_DIRECTORY = "survey_data";
    
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
    
    /* takes in filename, saves response */
    public void save(String filename) {
        File dataDir = new File(DATA_DIRECTORY);
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        
        String filepath = DATA_DIRECTORY + File.separator + filename;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            oos.writeObject(this);
            System.out.println("Response saved successfully to " + filepath);
        } catch (IOException e) {
            System.err.println("Error saving response: " + e.getMessage());
        }
    }
    
    /* takes in filename, loads and returns response */
    public static SurveyResponse load(String filename) {
        String filepath = DATA_DIRECTORY + File.separator + filename;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath))) {
            SurveyResponse response = (SurveyResponse) ois.readObject();
            System.out.println("Response loaded successfully from " + filepath);
            return response;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading response: " + e.getMessage());
            return null;
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
