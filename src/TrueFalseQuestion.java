import java.util.Scanner;

public class TrueFalseQuestion extends MultipleChoiceQuestion {
    private static final long serialVersionUID = 1L;
    
    /* default constructor */
    public TrueFalseQuestion() {
        super();
        initializeChoices();
    }
    
    /* takes in prompt, constructs true/false question */
    public TrueFalseQuestion(String prompt) {
        super(prompt);
        initializeChoices();
    }
    
    /* initializes true/false choices */
    private void initializeChoices() {
        addChoice("True");
        addChoice("False");
        setMaxSelections(1);
    }
    
    @Override
    public void displayQuestion() {
        System.out.println("Q" + getQuestionNumber() + ": " + getPrompt());
        System.out.println("T/F");
    }
    
    @Override
    public void modifyQuestion() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new question prompt: ");
        String newPrompt = scanner.nextLine();
        
        setPrompt(newPrompt);
        System.out.println("True/False question updated.");
    }
    
    @Override
    public void takeAnswer() {
        Scanner scanner = new Scanner(System.in);
        displayQuestion();
        
        // get user t/f or true/false
        String choice = "";
        while (!choice.equalsIgnoreCase("T") && !choice.equalsIgnoreCase("F") &&
               !choice.equalsIgnoreCase("True") && !choice.equalsIgnoreCase("False")) {
            System.out.print("Enter your answer (T/F): ");
            choice = scanner.nextLine().trim();

            if (!choice.equalsIgnoreCase("T") && !choice.equalsIgnoreCase("F") &&
                !choice.equalsIgnoreCase("True") && !choice.equalsIgnoreCase("False")) {
                System.out.println("Invalid choice. Please enter T or F.");
            }
        }
        
        SingleAnswer answer = new SingleAnswer(this);
        answer.setAnswerValue(choice.toUpperCase().startsWith("T") ? "True" : "False");
        addAnswer(answer);
    }
    
    @Override
    public boolean acceptsMultipleAnswers() {
        return false;
    }
}
