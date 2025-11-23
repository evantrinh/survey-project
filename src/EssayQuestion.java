import java.util.Scanner;

public class EssayQuestion extends Question {
    private static final long serialVersionUID = 1L;
    
    private int minWords;
    private int maxWords;
    private int numResponsesRequired;
    
    /* default constructor */
    public EssayQuestion() {
        super();
        this.minWords = 50;
        this.maxWords = 500;
        this.numResponsesRequired = 1;
    }
    
    /* takes in prompt, constructs essay question */
    public EssayQuestion(String prompt) {
        super(prompt);
        this.minWords = 50;
        this.maxWords = 500;
        this.numResponsesRequired = 1;
    }
    
    /* returns minimum words */
    public int getMinWords() {
        return minWords;
    }
    
    /* takes in min, sets minimum words */
    public void setMinWords(int min) {
        this.minWords = min;
    }
    
    /* returns maximum words */
    public int getMaxWords() {
        return maxWords;
    }
    
    /* takes in max, sets maximum words */
    public void setMaxWords(int max) {
        this.maxWords = max;
    }
    
    /* returns number of responses required */
    public int getNumResponsesRequired() {
        return numResponsesRequired;
    }
    
    /* takes in num, sets number of responses required */
    public void setNumResponsesRequired(int num) {
        this.numResponsesRequired = num;
    }
    
    @Override
    public void displayQuestion() {
        System.out.println("Q" + getQuestionNumber() + ": " + getPrompt());
        System.out.println("(Essay - " + minWords + " to " + maxWords + " words)");
    }
    
    @Override
    public void modifyQuestion() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new question prompt: ");
        String newPrompt = scanner.nextLine();
        setPrompt(newPrompt);
        
        System.out.print("Enter minimum words: ");
        minWords = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter maximum words: ");
        maxWords = scanner.nextInt();
        scanner.nextLine();
    }
    
    @Override
    public Answer takeAnswer() {
        Scanner scanner = new Scanner(System.in);
        displayQuestion();
        
        if (numResponsesRequired > 1) {
            MultipleAnswer answer = new MultipleAnswer(this);
            
            for (int i = 0; i < numResponsesRequired; i++) {
                String answerText = "";
                int wordCount = 0;
                
                while (wordCount < minWords || wordCount > maxWords) {
                    System.out.print("Enter essay response " + (char)('A' + i) + " (press Enter when done): ");
                    answerText = scanner.nextLine();
                    
                    if (answerText.isEmpty()) {
                        System.out.println("Answer cannot be empty.");
                        continue;
                    }
                    
                    wordCount = answerText.trim().split("\\s+").length;
                    
                    if (wordCount < minWords) {
                        System.out.println("Essay must have at least " + minWords + " words. You have " + wordCount + " words.");
                    } else if (wordCount > maxWords) {
                        System.out.println("Essay must have at most " + maxWords + " words. You have " + wordCount + " words.");
                    }
                }
                
                answer.addAnswerValue(answerText);
            }
            addAnswer(answer);
            return answer;
        } else {
            String answerText = "";
            int wordCount = 0;
            
            while (wordCount < minWords || wordCount > maxWords) {
                System.out.print("Enter your essay (press Enter when done): ");
                answerText = scanner.nextLine();
                
                if (answerText.isEmpty()) {
                    System.out.println("Answer cannot be empty.");
                    continue;
                }
                
                wordCount = answerText.trim().split("\\s+").length;
                
                if (wordCount < minWords) {
                    System.out.println("Essay must have at least " + minWords + " words. You have " + wordCount + " words.");
                } else if (wordCount > maxWords) {
                    System.out.println("Essay must have at most " + maxWords + " words. You have " + wordCount + " words.");
                }
            }
            
            SingleAnswer answer = new SingleAnswer(this);
            answer.setAnswerValue(answerText);
            addAnswer(answer);
            return answer;
        }
    }
    
    @Override
    public boolean acceptsMultipleAnswers() {
        return numResponsesRequired > 1;
    }
}
