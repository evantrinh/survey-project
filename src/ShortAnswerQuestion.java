import java.util.Scanner;

public class ShortAnswerQuestion extends EssayQuestion {
    private static final long serialVersionUID = 1L;
    
    private int maxLength;
    
    /* default constructor */
    public ShortAnswerQuestion() {
        super();
        this.maxLength = 100;
    }
    
    /* takes in prompt, constructs short answer question */
    public ShortAnswerQuestion(String prompt) {
        super(prompt);
        this.maxLength = 100;
    }
    
    /* returns max length */
    public int getMaxLength() {
        return maxLength;
    }
    
    /* takes in length, sets max length */
    public void setMaxLength(int length) {
        this.maxLength = length;
    }
    
    @Override
    public void displayQuestion() {
        System.out.println("Q" + getQuestionNumber() + ": " + getPrompt());
        System.out.println("(Short answer - max " + maxLength + " characters)");
    }
    
    @Override
    public void modifyQuestion() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new question prompt: ");
        String newPrompt = scanner.nextLine();
        setPrompt(newPrompt);
        
        System.out.print("Enter maximum character length: ");
        maxLength = scanner.nextInt();
        scanner.nextLine();
    }
    
    @Override
    public void takeAnswer() {
        Scanner scanner = new Scanner(System.in);
        displayQuestion();
        
        int numResponses = getNumResponsesRequired();
        
        if (numResponses > 1) {
            MultipleAnswer answer = new MultipleAnswer(this);
            
            for (int i = 0; i < numResponses; i++) {
                String answerText = "";
                while (answerText.isEmpty() || answerText.length() > maxLength) {
                    System.out.print("Enter answer " + (char)('A' + i) + ": ");
                    answerText = scanner.nextLine();
                    
                    if (answerText.isEmpty()) {
                        System.out.println("Answer cannot be empty.");
                    } else if (answerText.length() > maxLength) {
                        System.out.println("Answer exceeds maximum length of " + maxLength + " characters.");
                    }
                }
                answer.addAnswerValue(answerText);
            }
            addAnswer(answer);
        } else {
            String answerText = "";
            while (answerText.isEmpty() || answerText.length() > maxLength) {
                System.out.print("Enter your answer: ");
                answerText = scanner.nextLine();
                
                if (answerText.isEmpty()) {
                    System.out.println("Answer cannot be empty.");
                } else if (answerText.length() > maxLength) {
                    System.out.println("Answer exceeds maximum length of " + maxLength + " characters.");
                }
            }
            
            SingleAnswer answer = new SingleAnswer(this);
            answer.setAnswerValue(answerText);
            addAnswer(answer);
        }
    }
    
    @Override
    public boolean acceptsMultipleAnswers() {
        return getNumResponsesRequired() > 1;
    }
}
