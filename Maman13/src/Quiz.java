/*
 * Quiz class describes a quiz made of different amount of questions.
 * The questions don't have a specific order and can be chosen in a random order.
 */
import java.util.ArrayList;
import java.util.Random;

public class Quiz {
	private ArrayList<Question> questions;
	private int currRandIndex;
	//private ArrayList<Integer> availableQuestions;
	
	public Quiz(Database db) {
		this.questions = new ArrayList<>();
		//this.availableQuestions = new ArrayList<>();
		for(int i = 0; i < db.getQuestionsAmount(); i++) {
			this.questions.add(new Question(db, i));
			//this.availableQuestions.add(i);
		}		
	}
	/*
	 * Generates a random question
	 */
	public Question getRandomQuestion() {
		Random random = new Random();
		int index = random.nextInt(questions.size());
		this.currRandIndex = index;
		
		return this.getQuestion(index);
	}
	//Getters of the different attributes:
	
	public Question getQuestion(int index) {
		return this.questions.get(index);
	}
	
	public int getRandomIndex() {
		return currRandIndex;
	}
	
	/*
	 * Delete the question in the current's index from the bank.
	 * The use for this will be understood later.
	 */
	public void deleteQuestion() {
		
			this.questions.remove(currRandIndex);
	}
	/*
	 * Checks if the question's bank is empty
	 */
	public boolean isEmpty() {
		
		return questions.isEmpty();
	}
}
