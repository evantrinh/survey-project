import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Survey implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String surveyName;
    private List<Question> questions;
    private List<SurveyResponse> responses;
    
    /* default constructor */
    public Survey() {
        this.questions = new ArrayList<>();
        this.responses = new ArrayList<>();
    }
    
    /* takes in name, constructs survey */
    public Survey(String name) {
        this.surveyName = name;
        this.questions = new ArrayList<>();
        this.responses = new ArrayList<>();
    }
    
    /* returns survey name */
    public String getSurveyName() {
        return surveyName;
    }
    
    /* takes in name, sets survey name */
    public void setSurveyName(String name) {
        this.surveyName = name;
    }
    
    /* takes in question, adds question */
    public void addQuestion(Question question) {
        question.setQuestionNumber(questions.size() + 1);
        this.questions.add(question);
    }
    
    /* takes in index, removes question */
    public void removeQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            questions.remove(index);
            for (int i = index; i < questions.size(); i++) {
                questions.get(i).setQuestionNumber(i + 1);
            }
        }
    }
    
    /* takes in index and question, modifies question */
    public void modifyQuestion(int index, Question question) {
        if (index >= 0 && index < questions.size()) {
            question.setQuestionNumber(index + 1);
            questions.set(index, question);
        }
    }
    

    /* takes in index, returns question */
    public Question getQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            return questions.get(index);
        }
        return null;
    }
    
    /* returns all questions */
    public Question[] getAllQuestions() {
        return questions.toArray(new Question[0]);
    }
    
    /* returns number of questions */
    public int getNumQuestions() {
        return questions.size();
    }
    
    /* displays survey */
    public void displaySurvey() {
        OutputHandler outputHandler = new OutputHandler();
        outputHandler.displaySurvey(this);
    }
    

    /* takes survey and returns response */
    public SurveyResponse takeSurvey() {
        SurveyResponse response = new SurveyResponse();
        
        System.out.println("\nTaking Survey: " + surveyName);
        
        for (Question question : questions) {
            question.takeAnswer();
            Answer[] answers = question.getAnswers();
            if (answers.length > 0) {
                response.addAnswer(answers[answers.length - 1]);
            }
        }
        
        addResponse(response);
        
        System.out.println("\nSurvey completed! Thank you for your responses.");
        return response;
    }
    

    /* takes in response, adds response */
    public void addResponse(SurveyResponse response) {
        this.responses.add(response);
    }
    
    /* returns all responses */
    public SurveyResponse[] getResponses() {
        return responses.toArray(new SurveyResponse[0]);
    }
    
    /* takes in index, returns response */
    public SurveyResponse getResponse(int index) {
        if (index >= 0 && index < responses.size()) {
            return responses.get(index);
        }
        return null;
    }
}
