import java.util.Scanner;

public class SurveyMenuInterface {
    private SurveyManager manager;
    private Scanner scanner;
    private InputHandler inputHandler;
    private OutputHandler outputHandler;
    
    /* takes in manager, constructs menu interface */
    public SurveyMenuInterface(SurveyManager manager) {
        this.manager = manager;
        this.scanner = new Scanner(System.in);
        this.inputHandler = new InputHandler();
        this.outputHandler = new OutputHandler();
    }
    
    /* displays main menu */
    public void displayMainMenu() {
        boolean running = true;
        
        while (running) {
            System.out.println("\n=== SURVEY SYSTEM MENU ===");
            System.out.println("1) Create a new Survey");
            System.out.println("2) Display an existing Survey");
            System.out.println("3) Load an existing Survey");
            System.out.println("4) Save the current Survey");
            System.out.println("5) Take the current Survey");
            System.out.println("6) Modifying the current Survey");
            System.out.println("7) Quit");
            
            int choice = getIntInput("Enter your choice (1-7): ");
            
            switch (choice) {
                case 1:
                    handleCreateSurvey();
                    break;
                case 2:
                    handleDisplaySurvey();
                    break;
                case 3:
                    handleLoadSurvey();
                    break;
                case 4:
                    handleSaveSurvey();
                    break;
                case 5:
                    handleTakeSurvey();
                    break;
                case 6:
                    handleModifySurvey();
                    break;
                case 7:
                    handleExit();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }
    }
    
    /* handles creating new survey */
    public void handleCreateSurvey() {
        System.out.println("\n=== Create New Survey ===");
        
        Survey survey = manager.createSurvey("");
        
        // add questions using Menu 2
        boolean addingQuestions = true;
        while (addingQuestions) {
            addingQuestions = displayAddQuestionMenu(survey);
        }
        
        outputHandler.displayMessage("Survey created successfully!");
    }
    
    /* takes in survey, displays add question menu, and returns if should continue */
    private boolean displayAddQuestionMenu(Survey survey) {
        System.out.println("1) Add a new T/F question");
        System.out.println("2) Add a new multiple-choice question");
        System.out.println("3) Add a new short answer question");
        System.out.println("4) Add a new essay question");
        System.out.println("5) Add a new date question");
        System.out.println("6) Add a new matching question");
        System.out.println("7) Return to previous menu");
        
        int choice = getIntInput("Enter your choice (1-7): ");
        
        switch (choice) {
            case 1:
                addTrueFalseQuestion(survey);
                return true;
            case 2:
                addMultipleChoiceQuestion(survey);
                return true;
            case 3:
                addShortAnswerQuestion(survey);
                return true;
            case 4:
                addEssayQuestion(survey);
                return true;
            case 5:
                addDateQuestion(survey);
                return true;
            case 6:
                addMatchingQuestion(survey);
                return true;
            case 7:
                return false;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                return true;
        }
    }
    
    /* takes in survey, adds true/false question */
    private void addTrueFalseQuestion(Survey survey) {
        System.out.println("\nEnter the prompt for your True/False question:");

        String prompt = getStringInput("");
        TrueFalseQuestion question = new TrueFalseQuestion(prompt);

        survey.addQuestion(question);
        System.out.println("True/False question added.");
    }
    
    /* takes in survey, adds multiple choice question */
    private void addMultipleChoiceQuestion(Survey survey) {
        System.out.println("\nEnter the prompt for your multiple-choice question:");
        String prompt = getStringInput("");
        
        int numChoices = getIntInput("Enter the number of choices for your multiple-choice question: ");
        
        // get answer choices
        MultipleChoiceQuestion question = new MultipleChoiceQuestion(prompt);
        for (int i = 0; i < numChoices; i++) {
            System.out.println("Enter choice #" + (i + 1) + ":");
            String choice = getStringInput("");
            question.addChoice(choice);
        }
        
        // get if multiple responses, no. of responses
        System.out.println("Does this question allow multiple responses? (y/n)");
        String multipleResponse = getStringInput("");
        if (multipleResponse.equalsIgnoreCase("y")) {
            int maxSelections = getIntInput("Enter the maximum number of selections allowed: ");
            question.setMaxSelections(maxSelections);
        } else {
            question.setMaxSelections(1);
        }
        
        survey.addQuestion(question);
        System.out.println("Multiple choice question added.");
    }
    
    /* takes in survey, adds short answer question */
    private void addShortAnswerQuestion(Survey survey) {
        System.out.println("\nEnter the prompt for your short answer question:");
        String prompt = getStringInput("");
        
        ShortAnswerQuestion question = new ShortAnswerQuestion(prompt);
        
        System.out.println("Does this question allow multiple short answers? (y/n)");
        String multipleResponse = getStringInput("");
        if (multipleResponse.equalsIgnoreCase("y")) {
            int numResponses = getIntInput("How many short answers are required? ");
            question.setNumResponsesRequired(numResponses);
        }
        
        int maxLength = getIntInput("Enter maximum character length: ");
        question.setMaxLength(maxLength);
        
        survey.addQuestion(question);
        System.out.println("Short answer question added.");
    }
    
    /* takes in survey, adds essay question */
    private void addEssayQuestion(Survey survey) {
        System.out.println("\nEnter the prompt for your essay question:");
        String prompt = getStringInput("");
        
        EssayQuestion question = new EssayQuestion(prompt);
        
        System.out.println("Does this question allow multiple essay responses? (y/n)");
        String multipleResponse = getStringInput("");
        if (multipleResponse.equalsIgnoreCase("y")) {
            int numResponses = getIntInput("How many essay responses are required? ");
            question.setNumResponsesRequired(numResponses);
        }
        
        int minWords = getIntInput("Enter minimum words: ");
        int maxWords = getIntInput("Enter maximum words: ");
        question.setMinWords(minWords);
        question.setMaxWords(maxWords);
        
        survey.addQuestion(question);
        System.out.println("Essay question added.");
    }
    
    /* takes in survey, adds date question */
    private void addDateQuestion(Survey survey) {
        System.out.println("\nEnter the prompt for your date question:");
        String prompt = getStringInput("");
        
        ValidDateQuestion question = new ValidDateQuestion(prompt);
        
        System.out.println("Does this question allow multiple dates? (y/n)");
        String multipleResponse = getStringInput("");
        if (multipleResponse.equalsIgnoreCase("y")) {
            int numResponses = getIntInput("How many dates are required? ");
            question.setNumResponsesRequired(numResponses);
        }
        
        survey.addQuestion(question);
        System.out.println("Date question added.");
    }
    
    /* takes in survey, adds matching question */
    private void addMatchingQuestion(Survey survey) {
        System.out.println("\nEnter the prompt for your matching question:");
        String prompt = getStringInput("");
        
        MatchingQuestion question = new MatchingQuestion(prompt);
        
        int numPairs = getIntInput("Enter the number of items to match: ");
        
        System.out.println("\nEnter the left column items:");
        for (int i = 0; i < numPairs; i++) {
            System.out.println("Enter item " + (char)('A' + i) + ":");
            String item = getStringInput("");
            question.addLeftItem(item);
        }
        
        System.out.println("\nEnter the right column items:");
        for (int i = 0; i < numPairs; i++) {
            System.out.println("Enter item " + (i + 1) + ":");
            String item = getStringInput("");
            question.addRightItem(item);
        }
        
        survey.addQuestion(question);
        System.out.println("Matching question added.");
    }
    
    /* handles modifying survey */
    public void handleModifySurvey() {
        System.out.println("\n=== Modify Survey ===");
        Survey currentSurvey = manager.getCurrentSurvey();
        
        if (currentSurvey == null) {
            System.out.println("You must have a survey loaded in order to modify it.");
            return;
        }
        
        manager.displaySurvey(currentSurvey);
        
        System.out.println("\nWhat question do you wish to modify?");
        int questionNum = getIntInput("Enter question number: ");
        
        Question question = currentSurvey.getQuestion(questionNum - 1);
        if (question == null) {
            System.out.println("Invalid question number.");
            return;
        }
        
        modifyQuestionInteractive(question);
    }
    
    /* handles loading survey */
    public void handleLoadSurvey() {
        System.out.println("\n=== Load Survey ===");
        
        // load serialization
        java.io.File surveyDir = new java.io.File("survey_data");
        if (!surveyDir.exists()) {
            System.out.println("No survey_data directory found.");
            return;
        }
        
        java.io.File[] files = surveyDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".ser"));
        
        if (files == null || files.length == 0) {
            System.out.println("No survey files found in the survey_data directory.");
            return;
        }
        
        System.out.println("Please select a file to load:");
        for (int i = 0; i < files.length; i++) {
            System.out.println((i + 1) + ") " + files[i].getName().replace(".ser", ""));
        }
        
        int choice = getIntInput("Enter your choice: ");
        
        if (choice < 1 || choice > files.length) {
            System.out.println("Invalid choice.");
            return;
        }
        
        String filename = files[choice - 1].getName();
        Survey loadedSurvey = Survey.load(filename);
        if (loadedSurvey != null) {
            manager.setSurvey(loadedSurvey);
            // outputHandler.displayMessage("Survey loaded successfully!");
        } else {
            // outputHandler.displayError("Failed to load survey.");
        }
    }
    
    /* handles saving survey */
    public void handleSaveSurvey() {
        System.out.println("\n=== Save Survey ===");
        Survey currentSurvey = manager.getCurrentSurvey();
        
        if (currentSurvey == null) {
            System.out.println("You must have a survey loaded in order to save it.");
            return;
        }
        
        String filename = getStringInput("Enter filename to save: ");
        
        if (!filename.endsWith(".ser")) {
            filename += ".ser";
        }
        
        currentSurvey.save(filename);
    }
    
    /* handles taking survey */
    public void handleTakeSurvey() {
        System.out.println("\n=== Take Survey ===");
        Survey currentSurvey = manager.getCurrentSurvey();
        
        if (currentSurvey == null) {
            System.out.println("You must have a survey loaded in order to take it.");
            return;
        }
        
        if (currentSurvey.getNumQuestions() == 0) {
            outputHandler.displayError("This survey has no questions.");
            return;
        }
        
        // useless??? why is this included in the instructions
        // System.out.println("Enter the name of the survey you wish to take:");
        // String surveyName = getStringInput("");
        
        SurveyResponse response = currentSurvey.takeSurvey();
        
        String responseFilename = "response_" + System.currentTimeMillis() + ".ser";
        response.save(responseFilename);
        // System.out.println("Your response has been saved to: " + responseFilename);
    }
    
    /* handles displaying survey */
    public void handleDisplaySurvey() {
        System.out.println("\n=== Display Survey ===");
        Survey currentSurvey = manager.getCurrentSurvey();
        
        if (currentSurvey == null) {
            System.out.println("You must have a survey loaded in order to display it.");
            return;
        }
        
        manager.displaySurvey(currentSurvey);
    }
    
    /* takes in question, modifies question interactively */
    private void modifyQuestionInteractive(Question question) {
        // display current prompt
        System.out.println("\nCurrent prompt: " + question.getPrompt());
        System.out.println("Do you wish to modify the prompt? (y/n)");
        String modifyPrompt = getStringInput("");
        
        if (modifyPrompt.equalsIgnoreCase("y")) {
            System.out.println("Enter a new prompt:");
            String newPrompt = getStringInput("");
            question.setPrompt(newPrompt);
            System.out.println("Prompt updated.");
        }
        
        // handle specific question types
        if (question instanceof MultipleChoiceQuestion && !(question instanceof TrueFalseQuestion)) {
            modifyMultipleChoiceQuestion((MultipleChoiceQuestion) question);
        } else if (question instanceof TrueFalseQuestion) {
            System.out.println("True/False question prompt modified.");
        } else if (question instanceof ShortAnswerQuestion) {
            modifyShortAnswerQuestion((ShortAnswerQuestion) question);
        } else if (question instanceof EssayQuestion) {
            modifyEssayQuestion((EssayQuestion) question);
        } else if (question instanceof MatchingQuestion) {
            modifyMatchingQuestion((MatchingQuestion) question);
        } else if (question instanceof ValidDateQuestion) {
            modifyDateQuestion((ValidDateQuestion) question);
        }
    }
    
    /* takes in question, modifies multiple choice question */
    private void modifyMultipleChoiceQuestion(MultipleChoiceQuestion question) {
        System.out.println("\nDo you wish to modify choices? (y/n)");
        String modifyChoices = getStringInput("");
        
        if (modifyChoices.equalsIgnoreCase("y")) {
            // show choices to modify
            String[] choices = question.getChoices();
            System.out.println("\nCurrent choices:");
            for (int i = 0; i < choices.length; i++) {
                System.out.println((char)('A' + i) + ") " + choices[i]);
            }
            
            System.out.println("\nWhich choice do you want to modify?");
            String choiceLetter = getStringInput("").toUpperCase();
            
            // modify choice by prompting user
            if (choiceLetter.length() > 0) {
                int index = choiceLetter.charAt(0) - 'A';
                if (index >= 0 && index < choices.length) {
                    System.out.println("Enter new value for choice " + choiceLetter + ":");
                    String newValue = getStringInput("");
                    
                    // rebuild choices list with the updated value
                    question.removeChoice(index);
                    
                    // insert new value at the same position
                    String[] tempChoices = new String[choices.length];
                    for (int i = 0; i < choices.length; i++) {
                        if (i == index) {
                            tempChoices[i] = newValue;
                        } else if (i < index) {
                            tempChoices[i] = choices[i];
                        } else {
                            tempChoices[i] = choices[i];
                        }
                    }
                    
                    // clear and re-add all choices in correct order
                    String[] currentChoices = question.getChoices();
                    for (int i = currentChoices.length - 1; i >= 0; i--) {
                        question.removeChoice(i);
                    }
                    
                    for (int i = 0; i < tempChoices.length; i++) {
                        question.addChoice(tempChoices[i]);
                    }
                    
                    System.out.println("Choice updated.");
                } else {
                    System.out.println("Invalid choice letter.");
                }
            }
        }
    }
    
    /* takes in question, modifies short answer question */
    private void modifyShortAnswerQuestion(ShortAnswerQuestion question) {
        System.out.println("\nCurrent max length: " + question.getMaxLength());
        System.out.println("Do you wish to modify the max length? (y/n)");
        String modify = getStringInput("");
        
        if (modify.equalsIgnoreCase("y")) {
            int newMax = getIntInput("Enter new max length: ");
            question.setMaxLength(newMax);
            System.out.println("Max length updated.");
        }
    }
    
    /* takes in question, modifies essay question */
    private void modifyEssayQuestion(EssayQuestion question) {
        System.out.println("\nCurrent min words: " + question.getMinWords());
        System.out.println("Current max words: " + question.getMaxWords());
        System.out.println("Do you wish to modify word limits? (y/n)");
        String modify = getStringInput("");
        
        if (modify.equalsIgnoreCase("y")) {
            int newMin = getIntInput("Enter new min words: ");
            int newMax = getIntInput("Enter new max words: ");
            question.setMinWords(newMin);
            question.setMaxWords(newMax);
            System.out.println("Word limits updated.");
        }
    }
    
    /* takes in question, modifies matching question */
    private void modifyMatchingQuestion(MatchingQuestion question) {
        System.out.println("\nDo you wish to modify matching items? (y/n)");
        String modify = getStringInput("");
        
        if (modify.equalsIgnoreCase("y")) {
            question.modifyQuestion();
        }
    }
    
    /* takes in question, modifies date question */
    private void modifyDateQuestion(ValidDateQuestion question) {
        System.out.println("\nCurrent date format: " + question.getDateFormat());
        System.out.println("Do you wish to modify the date format? (y/n)");
        String modify = getStringInput("");
        
        if (modify.equalsIgnoreCase("y")) {
            String newFormat = getStringInput("Enter new date format: ");
            question.setDateFormat(newFormat);
            System.out.println("Date format updated.");
        }
    }
    
    /* handles exiting application */
    public void handleExit() {
        System.out.println("\nExiting the survey system.");
        
        Survey currentSurvey = manager.getCurrentSurvey();
        if (currentSurvey != null) {
            String save = getStringInput("Save current survey before exiting? (y/n): ");
            if (save.equalsIgnoreCase("y") || save.equalsIgnoreCase("yes")) {
                handleSaveSurvey();
            }
        }
    }
    
    /* takes in prompt, returns integer input */
    private int getIntInput(String prompt) {
        return inputHandler.getIntInput(prompt);
    }
    
    /* takes in prompt, returns string input */
    private String getStringInput(String prompt) {
        return inputHandler.getStringInput(prompt);
    }
}
