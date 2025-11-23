import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ValidDateQuestion extends Question {
    private static final long serialVersionUID = 1L;
    
    private String dateFormat;
    private Date minDate;
    private Date maxDate;
    private int numResponsesRequired;
    
    /* default constructor */
    public ValidDateQuestion() {
        super();
        this.dateFormat = "YYYY-MM-DD";
        this.numResponsesRequired = 1;
    }
    
    /* takes in prompt, constructs date question */
    public ValidDateQuestion(String prompt) {
        super(prompt);
        this.dateFormat = "YYYY-MM-DD";
        this.numResponsesRequired = 1;
    }
    
    /* takes in format, sets date format */
    public void setDateFormat(String format) {
        this.dateFormat = format;
    }
    
    /* returns date format */
    public String getDateFormat() {
        return dateFormat;
    }
    
    /* takes in date, sets minimum date */
    public void setMinDate(Date date) {
        this.minDate = date;
    }
    
    /* takes in date, sets maximum date */
    public void setMaxDate(Date date) {
        this.maxDate = date;
    }
    
    /* returns minimum date */
    public Date getMinDate() {
        return minDate;
    }
    
    /* returns maximum date */
    public Date getMaxDate() {
        return maxDate;
    }
    
    /* returns number of responses required */
    public int getNumResponsesRequired() {
        return numResponsesRequired;
    }
    
    /* takes in num, sets number of responses required */
    public void setNumResponsesRequired(int num) {
        this.numResponsesRequired = num;
    }
    
    /* takes in input string, validates strict format match */
    private boolean isValidDateFormat(String input) {
        if (!Pattern.matches("\\d{4}-\\d{2}-\\d{2}", input)) {
            return false;
        }
        
        String[] parts = input.split("-");
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        
        return month >= 1 && month <= 12 && day >= 1 && day <= 31;
    }
    
    @Override
    public void displayQuestion() {
        System.out.println("Q" + getQuestionNumber() + ": " + getPrompt());
        System.out.println("A date should be entered in the following format: " + dateFormat);
        if (minDate != null) {
            System.out.println("Minimum date: " + new SimpleDateFormat(dateFormat).format(minDate));
        }
        if (maxDate != null) {
            System.out.println("Maximum date: " + new SimpleDateFormat(dateFormat).format(maxDate));
        }
    }
    
    @Override
    public void modifyQuestion() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        
        // prompt
        System.out.print("Enter new question prompt: ");
        String newPrompt = scanner.nextLine();
        setPrompt(newPrompt);
        
        // date format
        System.out.print("Enter date format (e.g., MM/dd/yyyy): ");
        dateFormat = scanner.nextLine();
        sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        
        // set min date??
        System.out.print("Set minimum date? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            boolean valid = false;
            while (!valid) {
                System.out.print("Enter minimum date (" + dateFormat + "): ");
                try {
                    minDate = sdf.parse(scanner.nextLine());
                    valid = true;
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please try again.");
                }
            }
        }
        
        // set max date??
        System.out.print("Set maximum date? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            boolean valid = false;
            while (!valid) {
                System.out.print("Enter maximum date (" + dateFormat + "): ");
                try {
                    maxDate = sdf.parse(scanner.nextLine());
                    valid = true;
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please try again.");
                }
            }
        }
    }
    
    @Override
    public Answer takeAnswer() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        
        displayQuestion();
        
        if (numResponsesRequired > 1) {
            MultipleAnswer answer = new MultipleAnswer(this);
            
            for (int i = 0; i < numResponsesRequired; i++) {
                Date dateAnswer = null;
                boolean valid = false;
                
                while (!valid) {
                    System.out.print("Enter date " + (char)('A' + i) + ": ");
                    String input = scanner.nextLine();
                    
                    if (!isValidDateFormat(input)) {
                        System.out.println("Invalid date format. Please use exactly: " + dateFormat);
                        continue;
                    }
                    
                    try {
                        dateAnswer = sdf.parse(input);
                        
                        if (minDate != null && dateAnswer.before(minDate)) {
                            System.out.println("Date must be on or after " + sdf.format(minDate));
                            continue;
                        }
                        
                        if (maxDate != null && dateAnswer.after(maxDate)) {
                            System.out.println("Date must be on or before " + sdf.format(maxDate));
                            continue;
                        }
                        
                        valid = true;
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use: " + dateFormat);
                    }
                }
                
                answer.addAnswerValue(sdf.format(dateAnswer));
            }
            
            addAnswer(answer);
            return answer;
        } else {
            Date dateAnswer = null;
            boolean valid = false;
            
            while (!valid) {
                System.out.print("Enter date: ");
                String input = scanner.nextLine();
                
                if (!isValidDateFormat(input)) {
                    System.out.println("Invalid date format. Please use exactly: " + dateFormat);
                    continue;
                }
                
                try {
                    dateAnswer = sdf.parse(input);
                    
                    if (minDate != null && dateAnswer.before(minDate)) {
                        System.out.println("Date must be on or after " + sdf.format(minDate));
                        continue;
                    }
                    
                    if (maxDate != null && dateAnswer.after(maxDate)) {
                        System.out.println("Date must be on or before " + sdf.format(maxDate));
                        continue;
                    }
                    
                    valid = true;
                } catch (ParseException e) {
                    System.out.println("Invalid date format. Please use: " + dateFormat);
                }
            }
            
            SingleAnswer answer = new SingleAnswer(this);
            answer.setAnswerValue(sdf.format(dateAnswer));
            addAnswer(answer);
            return answer;
        }
    }
    
    @Override
    public boolean acceptsMultipleAnswers() {
        return numResponsesRequired > 1;
    }
}
