
public class TDIDF {
	public TDIDF() {

	}

	public double tf(LinearProbingHashST<String, Term> hash, int totalWords, String term) {
		double freq = hash.get(term).getFrequency();
		double tf = freq / totalWords;
		return tf;
	}
	
	public double tf(BST<String, Term> hash, int totalWords, String term) {
		double freq = hash.get(term).getFrequency();
		double tf = freq / totalWords;
		return tf;
	}

	public double idf(LinearProbingHashST<String, Term> allHash, String term, double totalDocs) {
		double idf = 0;
		double df = allHash.get(term).getFrequency();
		return Math.log10(totalDocs / (df));
	}
	
	public double idf(BST<String, Term> allHash, String term, double totalDocs) {
		double idf = 0;
		double df = allHash.get(term).getFrequency();
		return Math.log10(totalDocs / (df));
	}

	public double calculateTFIDF(double tf, double idf) {
		return tf*idf;
	}
}
