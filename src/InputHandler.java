import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;
    
    /* default constructor */
    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }
    
    /* takes in prompt, returns integer input */
    public int getIntInput(String prompt) {
        int value = 0;
        boolean valid = false;
        
        while (!valid) {
            System.out.print(prompt);
            try {
                value = scanner.nextInt();
                scanner.nextLine();
                valid = true;
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        
        return value;
    }
    
    /* takes in prompt, returns string input */
    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    /* takes in prompt, returns boolean input */
    public boolean getBooleanInput(String prompt) {
        String input = "";
        
        while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n") &&
               !input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no")) {
            System.out.print(prompt + " (y/n): ");
            input = scanner.nextLine().trim();
            
            if (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n") &&
                !input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("no")) {
                System.out.println("Invalid input. Please enter y or n.");
            }
        }
        
        return input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes");
    }
    
    /* takes in prompt, returns date input */
    public Date getDateInput(String prompt) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(false);
        Date date = null;
        boolean valid = false;
        
        while (!valid) {
            System.out.print(prompt + " (MM/dd/yyyy): ");
            String input = scanner.nextLine();
            
            try {
                date = sdf.parse(input);
                valid = true;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use MM/dd/yyyy.");
            }
        }
        
        return date;
    }
    
    /* closes scanner */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
