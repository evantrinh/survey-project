import java.io.Serial;
import java.util.Scanner;

public class SingleAnswer extends Answer {
	@Serial
	private static final long serialVersionUID = 1L;

	private Object answerValue;

	/* default constructor */
	public SingleAnswer() {
		super();
	}

	/* takes in question, constructs single answer */
	public SingleAnswer(Question question) {
		super(question);
	}

	@Override
	public Object getAnswerValue() {
		return answerValue;
	}

	@Override
	public void setAnswerValue(Object value) {
		this.answerValue = value;
	}

	@Override
	public void modifyAnswer() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Current answer: " + (answerValue != null ? answerValue.toString() : "None"));
		System.out.print("Enter new answer: ");
		this.answerValue = scanner.nextLine();
	}

	@Override
	public void displayAnswer() {
		if (getQuestion() != null) {
			System.out.println("Q" + getQuestion().getQuestionNumber() + ": " + getQuestion().getPrompt());
		}
		System.out.println("Answer: " + (answerValue != null ? answerValue.toString() : "No answer"));
	}

	@Override
	public boolean isValid() {
		return answerValue != null && !answerValue.toString().trim().isEmpty();
	}

	@Override
	public boolean compare(Answer other) {
		if (other == null || !(other instanceof SingleAnswer)) {
			return false;
		}

		Object otherValue = other.getAnswerValue();

		if (answerValue == null && otherValue == null) {
			return true;
		}

		if (answerValue == null || otherValue == null) {
			return false;
		}

		return answerValue.toString().trim().equalsIgnoreCase(otherValue.toString().trim());
	}
}
