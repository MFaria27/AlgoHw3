import java.io.IOException;
import java.util.ArrayList;

import edu.princeton.cs.algs4.Stopwatch;

public class Hw3Runner {
	public static void main(String[] args) {
		
		System.out.printf("-----Task Set 1 Start... -----\n\n");
		TDIDF calc = new TDIDF();
		int docs = 14;
		
		String[] paths = new String[docs];
		String[] pathNames = new String[docs];
		
		paths[0] = "C://Users//matth//git//AlgoHw3//Homework3//src//data//bustopher-jones-the-cat-about-town.txt";
		pathNames[0] = "bustopher-jones-the-cat-about-town.txt";
		paths[1] = "C://Users//matth//git//AlgoHw3//Homework3//src//data//growltigers-last-stand.txt";
		pathNames[1] = "growltigers-last-stand.txt";
		paths[2] = "C://Users//matth//git//AlgoHw3//Homework3//src//data//gus-the-theater-cat.txt";
		pathNames[2] = "gus-the-theater-cat.txt";
		paths[3] = "C://Users//matth//git//AlgoHw3//Homework3//src//data//macavity-the-mystery-cat.txt";
		pathNames[3] = "macavity-the-mystery-cat.txt";
		paths[4] = "C://Users//matth//git//AlgoHw3//Homework3//src//data//mr-mistoffelees.txt";
		pathNames[4] = "mr-mistoffelees.txt";
		paths[5] = "C://Users//matth//git//AlgoHw3//Homework3//src//data//mungojerrie-and-rumpelteazer.txt";
		pathNames[5] = "mungojerrie-and-rumpelteazer.txt";
		paths[6] = "C://Users//matth//git//AlgoHw3//Homework3//src//data//of-the-awefull-battle-of-the-pekes-and-the-pollicles.txt";
		pathNames[6] = "of-the-awefull-battle-of-the-pekes-and-the-pollicles.txt";
		paths[7] = "C://Users//matth//git//AlgoHw3//Homework3//src//data//old-deuteronomy.txt";
		pathNames[7] = "old-deuteronomy.txt";
		paths[8] = "C://Users//matth//git//AlgoHw3//Homework3//src//data//skimbleshanks-the-railway-cat.txt";
		pathNames[8] = "skimbleshanks-the-railway-cat.txt";
		paths[9] = "C://Users//matth//git//AlgoHw3//Homework3//src//data//the-ad-dressing-of-cats.txt";
		pathNames[9] = "the-ad-dressing-of-cats.txt";
		paths[10] = "C://Users//matth//git//AlgoHw3//Homework3//src//data//the-naming-of-cats.txt";
		pathNames[10] = "the-naming-of-cats.txt";
		paths[11] = "C://Users//matth//git//AlgoHw3//Homework3//src//data//the-old-gumbie-cat.txt";
		pathNames[11] = "the-old-gumbie-cat.txt";
		paths[12] = "C://Users//matth//git//AlgoHw3//Homework3//src//data//the-rum-tum-tugger.txt";
		pathNames[12] = "the-rum-tum-tugger.txt";
		paths[13] = "C://Users//matth//git//AlgoHw3//Homework3//src//data//the-song-of-the-jellicles.txt";
		pathNames[13] = "the-song-of-the-jellicles.txt";
		
		ReadFile merger = new ReadFile();
		try {
			merger.mergeFiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String allCatPoems = "C://Users//matth//git//AlgoHw3//Homework3//src//data//allCatPoems.txt";
		String allCatPoemName = "allCatPoems.txt";
		
		LinearProbingHashST<String, Term>[] hashTables = new LinearProbingHashST[docs];
		int[] totalWordsPerDoc = new int[docs];
		
		ReadFile docFreqReader = new ReadFile();
		LinearProbingHashST<String, Term> docFreq = new LinearProbingHashST<String, Term>();
		String allRead = docFreqReader.readPoem(allCatPoems);
		String[] allWords = allRead.replaceAll("[^a-zA-Z ]", " ").toLowerCase().split("\\s+");
		for (String word : allWords) {
			Term tempTerm = (Term) docFreq.get(word);
			if (tempTerm != null) {
				int j = tempTerm.getFrequency();
				docFreq.put(word, new Term(word, allCatPoemName, j + 1, 0));
			} else docFreq.put(word, new Term(word, allCatPoemName, 1, 0));
			
		}
		System.out.printf("Document Frequency Hash Complete!\n\n");
		
		Stopwatch constructionTimer = new Stopwatch();
		
		for (int i = 0; i < docs; i++) {
			ReadFile fileReader = new ReadFile();
			String tempPoem = fileReader.readPoem(paths[i]);
			String[] tempWords = tempPoem.replaceAll("[^a-zA-Z ]", " ").toLowerCase().split("\\s+");
			hashTables[i] = new LinearProbingHashST<String, Term>();
			//System.out.println("Hash table " + i + " created");
			
			for (String word : tempWords) {
				Term tempTerm = (Term) hashTables[i].get(word);
				if (tempTerm != null) {
					int j = tempTerm.getFrequency();
					hashTables[i].put(word, new Term(word, pathNames[i], j + 1, 0));
				} else hashTables[i].put(word, new Term(word, pathNames[i], 1, 0));
				
			}
			totalWordsPerDoc[i] = tempWords.length;
			System.out.println("Hash " + (i+1) + " Complete!");
		}
		
		System.out.println();
		
		for (int i = 0; i < docs; i++) {
			for (String term : hashTables[i].keys()) {
				double tf = calc.tf(hashTables[i], totalWordsPerDoc[i], term);
				double idf = calc.idf(docFreq, term, docs);
				double tfidf = calc.calculateTFIDF(tf, idf);
				hashTables[i].get(term).setTFIDF(tfidf);
			}
		}
		double constructTime = constructionTimer.elapsedTime();
		
		System.out.printf("TF_IDF's set!\n\n");
		System.out.printf("-----Task Set 1 Complete!-----\n\n");
		System.out.printf("-----Task Set 2 Start... -----\n\n");
		
		Search searcher = new Search();
		
		
		
		String toBeSearched = "cat";
		System.out.println("Searching for \""+ toBeSearched + "\"");
		ArrayList<String> searchResults = searcher.search("cat", hashTables);
		System.out.println("Search complete! Printing results for \""+ toBeSearched + "\"...");
		for(String s : searchResults) System.out.println(s);
		System.out.printf("Search Results Printed!\n\n");
		System.out.printf("Printing Top 10 terms in Document \""+ pathNames[3]+ "\"...\n\n");
		ArrayList<String> top10Results = searcher.Top10(pathNames[3], hashTables);
		for(String s : top10Results) System.out.printf("\t%s\n", s);
		System.out.printf("\nTop 10 Printed!\n\n");
		System.out.printf("-----Task Set 2 Complete!-----\n\n");
		System.out.printf("-----Task Set 3 Start... -----\n\n");
		
		BST<String, Term>[] BSTs = new BST[docs];
		
		Stopwatch bSTConstructionTimer = new Stopwatch();
		
		for (int i = 0; i < docs; i++) {
			ReadFile fileReader = new ReadFile();
			String tempPoem = fileReader.readPoem(paths[i]);
			String[] tempWords = tempPoem.replaceAll("[^a-zA-Z ]", " ").toLowerCase().split("\\s+");
			BSTs[i] = new BST<String, Term>();
			//System.out.println("Hash table " + i + " created");
			
			for (String word : tempWords) {
				Term tempTerm = (Term) BSTs[i].get(word);
				if (tempTerm != null) {
					int j = tempTerm.getFrequency();
					BSTs[i].put(word, new Term(word, pathNames[i], j + 1, 0));
				} else BSTs[i].put(word, new Term(word, pathNames[i], 1, 0));
				
			}
			System.out.println("BST " + (i+1) + " Complete!");
		}
		
		for (int i = 0; i < docs; i++) {
			for (String term : BSTs[i].keys()) {
				double tf = calc.tf(BSTs[i], totalWordsPerDoc[i], term);
				double idf = calc.idf(docFreq, term, docs);
				double tfidf = calc.calculateTFIDF(tf, idf);
				BSTs[i].get(term).setTFIDF(tfidf);
			}
		}
		
		double bSTConstructTime = bSTConstructionTimer.elapsedTime();
		
		System.out.printf("\nConstruction time for LinearProbing: %.1f ms", constructTime*1000);
		System.out.printf("\nConstruction time for BST          : %.1f ms", bSTConstructTime*1000);

		System.out.printf("\n\n-----Task Set 3 Complete!-----\n\n");
	}
}
