import java.util.ArrayList;
import java.util.Collections;

public class Search {
	public Search () {
		
	}
	
	public ArrayList<String> search(String Key, LinearProbingHashST<String, Term>[] hashTables) {
		ArrayList<String> SearchResults = new ArrayList<String>();
		for (LinearProbingHashST<String, Term> hash : hashTables) {
			if(hash.get(Key) != null) {
				SearchResults.add("Doc   : " + hash.get(Key).getDocument());
				SearchResults.add("Freq  : " + String.valueOf(hash.get(Key).getFrequency()));
				SearchResults.add("TF-IDF: " + String.valueOf(hash.get(Key).getTFIDF()));
				SearchResults.add(" ");
			}
		}
		return SearchResults;
	}
	
	public ArrayList<String> Top10(String document, LinearProbingHashST<String, Term>[] hashTables){
		ArrayList<String> top10 = new ArrayList<String>();
		LinearProbingHashST<String, Double> keyTOtfidf = new LinearProbingHashST<String, Double>();
		ArrayList<Double> toBeSorted = new ArrayList<Double>();
		for (LinearProbingHashST<String, Term> hash : hashTables) {
			for(String key : hash.keys()) {
				if (hash.get(key).getDocument() == document) {
					keyTOtfidf.put(key, hash.get(key).getTFIDF());
					toBeSorted.add(hash.get(key).getTFIDF());
				}
			}
		}
		
		Collections.sort(toBeSorted, Collections.reverseOrder());
		
		ArrayList<Double> top10Scores = new ArrayList<Double>();
		for (int i = 0; i < 10; i++) top10Scores.add(toBeSorted.get(i));
		
		for (Double top : top10Scores) {
			for (String key : keyTOtfidf.keys()) {
				if(keyTOtfidf.get(key).equals(top)) {
					top10.add(key);
					keyTOtfidf.delete(key);
				}
				if(top10.size() == 10) return top10;
			}
		}
		return null;
	}
	
	
}
