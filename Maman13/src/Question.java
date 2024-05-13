/*
 * Question class describes an object of a question.
 * Describes that every question also has a right answer and wrong answers.
 */
import java.util.ArrayList;
import java.util.Collections;

public class Question{
	String question;
	String correctAnswer;
	String[] incorrectAnswers;
	
	/*
	 * Question constructor which gets 3 parameters of the given question, correct answer and wrong answers.
	 * the wrong\incorrect answers will be a String array since there are few of them and not only 1.
	 */
	public Question(Database db, int index) {
		this.question = db.getQuestion(index);
		this.correctAnswer = db.getCorrectAnswer(index);
		this.incorrectAnswers = db.getIncorrectAnswers(index);
	}
	
	//Getters for the different attributes:
	public String getQuestion() {
		return this.question;
	}
	
	public String getCorrectAnswer() {
		return this.correctAnswer;
	}
	
	public ArrayList<String> getIncorrectAnswers(){
		
		ArrayList<String> temp = new ArrayList<>();
		Collections.addAll(temp, this.incorrectAnswers);
		
		return temp;
	}
	
	/*
	 * Make the game in case we want a text version of the game (not in use in this project)
	 * but still decided to keep it.
	 */
	public String getForm() {
		ArrayList<String> answers = new ArrayList<>();
		Collections.addAll(answers, this.correctAnswer);
		Collections.addAll(answers, this.incorrectAnswers);
		Collections.shuffle(answers);
		
		StringBuilder builder = new StringBuilder();
		for (String answer : answers) {
			builder.append(answer).append("\n");
		}
		
		return builder.toString();
	}
	
	/*
	 * Checks if the given answer is the right answer (equals to the attribute of the correct answer)
	 */
	public boolean isCorrect(String word) {
		return word.equals(this.correctAnswer);
	}
	
}
