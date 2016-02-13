package edu.gatech.cs6310.projectOne.filereader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class ResourceFileReader<T> {
	
	public static String DELIMITER = ",";
	
	public List<T> getContents(){
		String fileLocation = getFileLocation();
		return getContents(fileLocation);
	}
	
	public List<T> getContents(String fileLoc){
		List<T> contents = new ArrayList<T>();
		BufferedReader br = null;
		String line = "";
		try{
			br = new BufferedReader(new FileReader(fileLoc));
			readFirstLine(br);
			while((line = br.readLine()) != null){
				contents.add(parseLine(line));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return contents;
	}

	private void readFirstLine(BufferedReader br) throws IOException {
		br.readLine();
	}

	public abstract T parseLine(String line);
	
	public abstract String getFileLocation();
}
