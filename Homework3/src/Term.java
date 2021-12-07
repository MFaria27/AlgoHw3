
public class Term {
	public String word;
	public String document;
	public int word_frequency;
	public double td_idf;
	
	public Term (String word, String document, int word_frequency, double td_idf) {
		this.word = word;
		this.document = document;
		this.word_frequency = word_frequency;
		this.td_idf = td_idf;
	}
}
