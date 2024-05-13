import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileDatabase implements Database {
	private ArrayList<String> _questions;
	private ArrayList<String> _answers;
	private ArrayList<String[]> _wrong;
	
	public FileDatabase(String filepath) {
		ArrayList<String> lines = new ArrayList<>();
		File file = new File(filepath);
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				lines.add(scanner.nextLine());
			}
			
			_questions = new ArrayList<>();
			_answers = new ArrayList<>();
			_wrong = new ArrayList<>();
			for (int i = 0; i < lines.size(); i += 5) {
				if (lines.get(i).trim().isEmpty()) {
					break;
				}
				
				_questions.add(lines.get(i));
				_answers.add(lines.get(i + 1));
				_wrong.add(new String[] {
					lines.get(i + 2),
					lines.get(i + 3),
					lines.get(i + 4)
				});
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}
	
	public String getQuestion(int i) {
		return _questions.get(i);
	}
	
	public String getCorrectAnswer(int i) {
		return _answers.get(i);
	}
	
	public String[] getIncorrectAnswers(int i) {
		return _wrong.get(i);
	}
	
	public int getQuestionsAmount() {
		return _questions.size();
	}

}
