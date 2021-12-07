import java.io.*;
import java.util.Scanner;

public class readFile {

	public String readPoem(String path) {
		StringBuilder poem = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;
            while ((line = br.readLine()) != null) {
                poem.append(line).append("\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return poem.toString();
	}

	public static void mergeFiles() throws IOException {
		// create file object for directory
		File catsDir = new File("C://Users//matth//git//AlgoHw3//Homework3//src//data");
		// list of all files in the above directory
		File[] filesList = catsDir.listFiles();
		// create file object for new file
		File catFile = new File("C://Users//matth//git//AlgoHw3//Homework3//src//data//allCatPoems.txt");

		if (catFile.createNewFile()) {
			System.out.println("allCatPoems.txt created\n");
			System.out.println("Adding all cat data...");
			try {
				// writer for writing to the new file
				FileWriter writer = new FileWriter("C://Users//matth//git//AlgoHw3//Homework3//src//data//allCatPoems.txt");

				Scanner sc;
				// take each file from the directory mentioned above
				for (File file : filesList) {
					sc = new Scanner(file);
					// go through each line of a file
					while (sc.hasNextLine()) {
						String line = sc.nextLine();
						// append each new line to the file the writer is pointing at
						writer.append(line + "\n");
					}
					writer.flush();
				}
				System.out.println("cat data added\n");
			} catch (IOException e) {
				System.out.println("Something went wrong...");
				e.printStackTrace();
			}
		} else {
			System.out.println("allCatPoems.txt already exists... \n");
		}
	}
}
