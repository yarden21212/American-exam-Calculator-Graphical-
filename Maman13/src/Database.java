/*
 * An interface so we can use an external independent text file 
 * for the different quiz's questions and answers. 
 */
public interface Database {
	public String getQuestion(int i);
	public String getCorrectAnswer(int i);
	public String[] getIncorrectAnswers(int i);
	public int getQuestionsAmount();
}
