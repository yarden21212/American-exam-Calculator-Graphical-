//
//public class SimpleDatabase implements Database {
//	private String[] _questions = {
//		"When was Rome founded?", 
//		"Who were the founders of Rome?", 
//		"When was Israel established?",
//    	"Who was the first prime minister of Israel?"
//	};
//	private String[] _answers = {
//		"753 B.C.", 
//		"Romulus and his twin brother", 
//		"1948", 
//		"David Ben-Gurion"
//	};
//	private String[][] _wrong = {
//		{ "1020 B.C.", "540 B.C.", "215 A.C." }, 
//		{ "Spartacus and Theotokos", "Joaquin Phoenix and Trifonas", "Medussa and her twin brother" },
//		{ "1944", "1967", "1946" }, 
//		{ "Benjain Netamyahu", "Yitzhak Ben-Zvi", "Menachem Begin" }
//	};
//	
//	public String getQuestion(int i) {
//		return _questions[i];
//	}
//	
//	public String getCorrectAnswer(int i) {
//		return _answers[i];
//	}
//	
//	public String[] getIncorrectAnswers(int i) {
//		return _wrong[i];
//	}
//	
//	public int getQuestionsAmount() {
//		return _questions.length;
//	}
//}
