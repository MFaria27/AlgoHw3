import java.io.IOException;

public class Hw3Runner {
	public static void main(String[] args) {
		
		readFile fileReader = new readFile();
		
		String path1 = "C://Users//matth//git//AlgoHw3//Homework3//src//data//bustopher-jones-the-cat-about-town.txt";
		String path2 = "C://Users//matth//git//AlgoHw3//Homework3//src//data//growltigers-last-stand.txt";
		String path3 = "C://Users//matth//git//AlgoHw3//Homework3//src//data//gus-the-theater-cat.txt";
		String path4 = "C://Users//matth//git//AlgoHw3//Homework3//src//data//macavity-the-mystery-cat.txt";
		String path5 = "C://Users//matth//git//AlgoHw3//Homework3//src//data//mr-mistoffelees.txt";
		String path6 = "C://Users//matth//git//AlgoHw3//Homework3//src//data//mungojerrie-and-rumpelteazer.txt";
		String path7 = "C://Users//matth//git//AlgoHw3//Homework3//src//data//of-the-awefull-battle-of-the-pekes-and-the-pollicles.txt";
		String path8 = "C://Users//matth//git//AlgoHw3//Homework3//src//data//old-deuteronomy.txt";
		String path9 = "C://Users//matth//git//AlgoHw3//Homework3//src//data//skimbleshanks-the-railway-cat.txt";
		String path10 = "C://Users//matth//git//AlgoHw3//Homework3//src//data//the-ad-dressing-of-cats.txt";
		String path11 = "C://Users//matth//git//AlgoHw3//Homework3//src//data//the-naming-of-cats.txt";
		String path12 = "C://Users//matth//git//AlgoHw3//Homework3//src//data//the-old-grumbie-cat.txt";
		String path13 = "C://Users//matth//git//AlgoHw3//Homework3//src//data//the-rum-tum-tugger.txt";
		String path14 = "C://Users//matth//git//AlgoHw3//Homework3//src//data//the-song-of-the-jellicles.txt";
		
		try {
			fileReader.mergeFiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
