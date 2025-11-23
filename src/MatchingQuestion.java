import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MatchingQuestion extends Question {
    private static final long serialVersionUID = 1L;
    
    private List<String> leftColumn;
    private List<String> rightColumn;
    
    /* default constructor */
    public MatchingQuestion() {
        super();
        this.leftColumn = new ArrayList<>();
        this.rightColumn = new ArrayList<>();
    }
    
    /* takes in prompt, constructs matching question */
    public MatchingQuestion(String prompt) {
        super(prompt);
        this.leftColumn = new ArrayList<>();
        this.rightColumn = new ArrayList<>();
    }
    
    /* takes in item, adds to left column */
    public void addLeftItem(String item) {
        this.leftColumn.add(item);
    }
    
    /* takes in item, adds to right column */
    public void addRightItem(String item) {
        this.rightColumn.add(item);
    }
    
    /* takes in index, removes from left column */
    public void removeLeftItem(int index) {
        if (index >= 0 && index < leftColumn.size()) {
            leftColumn.remove(index);
        }
    }
    
    /* takes in index, removes from right column */
    public void removeRightItem(int index) {
        if (index >= 0 && index < rightColumn.size()) {
            rightColumn.remove(index);
        }
    }
    
    /* returns left column items */
    public String[] getLeftColumn() {
        return leftColumn.toArray(new String[0]);
    }
    
    /* returns right column items */
    public String[] getRightColumn() {
        return rightColumn.toArray(new String[0]);
    }
    
    @Override
    public void displayQuestion() {
        System.out.println("Q" + getQuestionNumber() + ": " + getPrompt());
        
        // calculate left width for padding
        int maxLeftWidth = 0;
        for (String item : leftColumn) {
            int width = item.length() + 3;
            if (width > maxLeftWidth) {
                maxLeftWidth = width;
            }
        }
        
        // print matching rows formatted nicely
        int maxRows = Math.max(leftColumn.size(), rightColumn.size());
        for (int i = 0; i < maxRows; i++) {
            String leftPart = "";
            String rightPart = "";
            
            if (i < leftColumn.size()) {
                leftPart = (char)('A' + i) + ") " + leftColumn.get(i);
            }
            
            if (i < rightColumn.size()) {
                rightPart = (i + 1) + ") " + rightColumn.get(i);
            }
            
            System.out.printf("%-" + (maxLeftWidth + 5) + "s%s%n", leftPart, rightPart);
        }
    }
    
    @Override
    public void modifyQuestion() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new question prompt: ");
        String newPrompt = scanner.nextLine();
        setPrompt(newPrompt);
        
        System.out.print("Enter number of items in left column: ");
        int leftCount = scanner.nextInt();
        scanner.nextLine();
        
        leftColumn.clear();
        for (int i = 0; i < leftCount; i++) {
            System.out.print("Enter left item " + (i + 1) + ": ");
            String item = scanner.nextLine();
            addLeftItem(item);
        }
        
        System.out.print("Enter number of items in right column: ");
        int rightCount = scanner.nextInt();
        scanner.nextLine();
        
        rightColumn.clear();
        for (int i = 0; i < rightCount; i++) {
            System.out.print("Enter right item " + (i + 1) + ": ");
            String item = scanner.nextLine();
            addRightItem(item);
        }
    }
    
    @Override
    public void takeAnswer() {
        Scanner scanner = new Scanner(System.in);
        displayQuestion();
        
        MultipleAnswer answer = new MultipleAnswer(this);
        
        System.out.println("\nEnter your matches (e.g., A 2, B 1, C 3):");
        for (int i = 0; i < leftColumn.size(); i++) {
            String match = "";
            boolean valid = false;
            
            // read answers, validate for specific match format
            while (!valid) {
                System.out.print((char)('A' + i) + " ");
                match = scanner.nextLine().trim();
                
                try {
                    int num = Integer.parseInt(match);
                    if (num >= 1 && num <= rightColumn.size()) {
                        valid = true;
                        answer.addAnswerValue((char)('A' + i) + " " + num);
                    } else {
                        System.out.println("Invalid choice. Please enter a number from 1 to " + rightColumn.size());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
        }
        
        addAnswer(answer);
    }
    
    @Override
    public boolean acceptsMultipleAnswers() {
        return true;
    }
}
