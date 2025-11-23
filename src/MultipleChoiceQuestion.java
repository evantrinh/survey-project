import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultipleChoiceQuestion extends Question {
    private static final long serialVersionUID = 1L;
    
    private List<String> choices;
    private int maxSelections;
    
    /* default constructor */
    public MultipleChoiceQuestion() {
        super();
        this.choices = new ArrayList<>();
        this.maxSelections = 1;
    }
    
    /* takes in prompt, constructs multiple choice question */
    public MultipleChoiceQuestion(String prompt) {
        super(prompt);
        this.choices = new ArrayList<>();
        this.maxSelections = 1;
    }
    
    /* takes in choice, adds choice */
    public void addChoice(String choice) {
        this.choices.add(choice);
    }
    
    /* takes in index, removes choice */
    public void removeChoice(int index) {
        if (index >= 0 && index < choices.size()) {
            choices.remove(index);
        }
    }
    
    /* returns all choices */
    public String[] getChoices() {
        return choices.toArray(new String[0]);
    }
    
    /* takes in max, sets maximum selections */
    public void setMaxSelections(int max) {
        this.maxSelections = max;
    }
    
    /* returns maximum selections */
    public int getMaxSelections() {
        return maxSelections;
    }
    
    @Override
    public void displayQuestion() {
        System.out.print("Q" + getQuestionNumber() + ": " + getPrompt());
        if (maxSelections > 1) {
            System.out.println(" Please give " + maxSelections + " choices.");
        } else {
            System.out.println();
        }
        
        // build string output from choices
        StringBuilder choicesLine = new StringBuilder();
        for (int i = 0; i < choices.size(); i++) {
            if (i > 0) {
                choicesLine.append(" ");
            }
            choicesLine.append((char)('A' + i)).append(") ").append(choices.get(i));
        }
        System.out.println(choicesLine.toString());
    }
    
    @Override
    public void modifyQuestion() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new question prompt: ");
        String newPrompt = scanner.nextLine();
        setPrompt(newPrompt);
        
        System.out.print("Enter number of choices: ");
        int numChoices = scanner.nextInt();
        scanner.nextLine();
        
        choices.clear();
        for (int i = 0; i < numChoices; i++) {
            System.out.print("Enter choice " + (i + 1) + ": ");
            String choice = scanner.nextLine();
            addChoice(choice);
        }
        
        System.out.print("Enter maximum number of selections: ");
        maxSelections = scanner.nextInt();
        scanner.nextLine();
    }
    
    @Override
    public Answer takeAnswer() {
        Scanner scanner = new Scanner(System.in);
        displayQuestion();
        
        if (maxSelections == 1) {
            String choice = "";
            int index = -1;
            
            // prompt user for choice (validates 'A' through whatever # of choice)
            while (index < 0 || index >= choices.size()) {
                System.out.print("Enter your choice (A-" + (char)('A' + choices.size() - 1) + "): ");
                choice = scanner.nextLine().trim().toUpperCase();
                
                if (choice.length() > 0) {
                    index = choice.charAt(0) - 'A';
                    if (index < 0 || index >= choices.size()) {
                        System.out.println("Invalid choice.");
                    }
                } else {
                    System.out.println("Invalid choice.");
                }
            }
            
            SingleAnswer answer = new SingleAnswer(this);
            answer.setAnswerValue(choice);
            addAnswer(answer);
            return answer;
        } else { // maxSelections > 1
            MultipleAnswer answer = new MultipleAnswer(this);
            
            for (int i = 0; i < maxSelections; i++) {
                String choice = "";
                int index = -1;

                // prompt user for choicse (validates 'A' through whatever # of choice)
                while (index < 0 || index >= choices.size()) {
                    System.out.print("Enter selection " + (i + 1) + " (A-" + (char)('A' + choices.size() - 1) + "): ");
                    choice = scanner.nextLine().trim().toUpperCase();
                    
                    if (choice.length() > 0) {
                        index = choice.charAt(0) - 'A';
                        if (index < 0 || index >= choices.size()) {
                            System.out.println("Invalid choice.");
                        }
                    } else {
                        System.out.println("Invalid choice.");
                    }
                }
                
                answer.addAnswerValue(choice);
            }
            addAnswer(answer);
            return answer;
        }
    }
    
    @Override
    public boolean acceptsMultipleAnswers() {
        return maxSelections > 1;
    }
}
