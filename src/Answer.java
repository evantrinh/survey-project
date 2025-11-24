import java.io.Serial;
import java.io.Serializable;

public abstract class Answer implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private Question question;

	/* default constructor */
	public Answer() {
	}

	/* takes in question, constructs answer */
	public Answer(Question question) {
		this.question = question;
	}

	/* takes nothing, returns question */
	public Question getQuestion() {
		return question;
	}

	/* takes in question, sets question */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/* takes nothing, returns answer value */
	public abstract Object getAnswerValue();

	/* takes in value, sets answer value */
	public abstract void setAnswerValue(Object value);

	/* takes nothing, modifies answer */
	public abstract void modifyAnswer();

	/* takes nothing, displays answer */
	public abstract void displayAnswer();

	/* takes nothing, returns if answer is valid */
	public abstract boolean isValid();

	/* takes in other answer, returns if answers match */
	public abstract boolean compare(Answer other);
}
