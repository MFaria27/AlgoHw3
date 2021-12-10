
public class Term {
	public String word;
	public String document;
	public int wordFrequency;
	public double tfidf;
	
	public Term (String word, String document, int wordFrequency, double tfidf) {
		this.word = word;
		this.document = document;
		this.wordFrequency = wordFrequency;
		this.tfidf = tfidf;
	}
	
	public int getFrequency () {
		return this.wordFrequency;
	}
	public String getDocument() {
		return this.document;
	}
	public double getTFIDF() {
		return this.tfidf;
	}
	
	public void setTFIDF(double temp) {
		this.tfidf = temp;
	}
}
