import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Survey implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String DATA_DIRECTORY = "survey_data";
    
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
    
    /* modifies survey interactively */
    public void modifySurvey() {
        Scanner scanner = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler();
        
        System.out.println("\n=== Modify Survey ===");
        System.out.println("1. Change survey name");
        System.out.println("2. Add question");
        System.out.println("3. Remove question");
        System.out.println("4. Modify question");
        System.out.println("5. Return to main menu");
        
        int choice = inputHandler.getIntInput("Enter choice: ");
        
        switch (choice) {
            // change survey name
            case 1:
                String newName = inputHandler.getStringInput("Enter new survey name: ");
                setSurveyName(newName);
                System.out.println("Survey name updated.");
                break;

            // add question
            case 2:
                addQuestionInteractive();
                break;
                
            // remove question
            case 3:
                if (questions.isEmpty()) {
                    System.out.println("No questions to remove.");
                } else {
                    displaySurvey();
                    int index = inputHandler.getIntInput("Enter question number to remove: ") - 1;
                    removeQuestion(index);
                    System.out.println("Question removed.");
                }
                break;
            
            // modify question
            case 4:
                if (questions.isEmpty()) {
                    System.out.println("No questions to modify.");
                } else {
                    displaySurvey();
                    int index = inputHandler.getIntInput("Enter question number to modify: ") - 1;
                    if (index >= 0 && index < questions.size()) {
                        questions.get(index).modifyQuestion();
                        System.out.println("Question modified.");
                    } else {
                        System.out.println("Invalid question number.");
                    }
                }
                break;
            
            // return to menu
            case 5:
                break;
                
            default:
                System.out.println("Invalid choice.");
        }
    }
    
    /* adds question interactively */
    private void addQuestionInteractive() {
        Scanner scanner = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler();
        
        System.out.println("\nSelect question type:");
        System.out.println("1. True/False");
        System.out.println("2. Multiple Choice");
        System.out.println("3. Short Answer");
        System.out.println("4. Essay");
        System.out.println("5. Matching");
        System.out.println("6. Valid Date");
        
        int type = inputHandler.getIntInput("Enter choice: ");
        String prompt = inputHandler.getStringInput("Enter question prompt: ");
        
        Question question = null;
        
        switch (type) {
            case 1:
                question = new TrueFalseQuestion(prompt);
                break;
                
            case 2:
                MultipleChoiceQuestion mcq = new MultipleChoiceQuestion(prompt);
                int numChoices = inputHandler.getIntInput("Enter number of choices: ");
                for (int i = 0; i < numChoices; i++) {
                    String choice = inputHandler.getStringInput("Enter choice " + (i + 1) + ": ");
                    mcq.addChoice(choice);
                }
                int maxSelections = inputHandler.getIntInput("Enter maximum selections allowed: ");
                mcq.setMaxSelections(maxSelections);
                question = mcq;
                break;
                
            case 3:
                ShortAnswerQuestion saq = new ShortAnswerQuestion(prompt);
                String allowMultipleSA = inputHandler.getStringInput("Does this question allow multiple short answers? (y/n): ");
                if (allowMultipleSA.equalsIgnoreCase("y")) {
                    int numResponsesSA = inputHandler.getIntInput("How many short answers are required? ");
                    saq.setNumResponsesRequired(numResponsesSA);
                }
                int maxLength = inputHandler.getIntInput("Enter maximum character length: ");
                saq.setMaxLength(maxLength);
                question = saq;
                break;
                
            case 4:
                EssayQuestion eq = new EssayQuestion(prompt);
                String allowMultiple = inputHandler.getStringInput("Does this question allow multiple essay responses? (y/n): ");
                if (allowMultiple.equalsIgnoreCase("y")) {
                    int numResponses = inputHandler.getIntInput("How many essay responses are required? ");
                    eq.setNumResponsesRequired(numResponses);
                }
                int minWords = inputHandler.getIntInput("Enter minimum words: ");
                int maxWords = inputHandler.getIntInput("Enter maximum words: ");
                eq.setMinWords(minWords);
                eq.setMaxWords(maxWords);
                question = eq;
                break;
                
            case 5:
                MatchingQuestion mq = new MatchingQuestion(prompt);
                int leftCount = inputHandler.getIntInput("Enter number of items in left column: ");
                for (int i = 0; i < leftCount; i++) {
                    String item = inputHandler.getStringInput("Enter left item " + (i + 1) + ": ");
                    mq.addLeftItem(item);
                }
                int rightCount = inputHandler.getIntInput("Enter number of items in right column: ");
                for (int i = 0; i < rightCount; i++) {
                    String item = inputHandler.getStringInput("Enter right item " + (i + 1) + ": ");
                    mq.addRightItem(item);
                }
                question = mq;
                break;
                
            case 6:
                ValidDateQuestion vdq = new ValidDateQuestion(prompt);
                String allowMultipleDate = inputHandler.getStringInput("Does this question allow multiple dates? (y/n): ");
                if (allowMultipleDate.equalsIgnoreCase("y")) {
                    int numResponsesDate = inputHandler.getIntInput("How many dates are required? ");
                    vdq.setNumResponsesRequired(numResponsesDate);
                }
                String format = inputHandler.getStringInput("Enter date format (e.g., MM/dd/yyyy): ");
                vdq.setDateFormat(format);
                question = vdq;
                break;
                
            default:
                System.out.println("Invalid question type.");
                return;
        }
        
        if (question != null) {
            addQuestion(question);
            System.out.println("Question added successfully.");
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
    
    /* takes in filename, saves survey */
    public void save(String filename) {
        File dataDir = new File(DATA_DIRECTORY);
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        
        String filepath = DATA_DIRECTORY + File.separator + filename;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            oos.writeObject(this);
            System.out.println("Survey saved successfully to " + filepath);
        } catch (IOException e) {
            System.err.println("Error saving survey: " + e.getMessage());
        }
    }
    
    /* takes in filename, loads and returns survey */
    public static Survey load(String filename) {
        String filepath = DATA_DIRECTORY + File.separator + filename;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath))) {
            Survey survey = (Survey) ois.readObject();
            System.out.println("Survey loaded successfully from " + filepath);
            return survey;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading survey: " + e.getMessage());
            return null;
        }
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
