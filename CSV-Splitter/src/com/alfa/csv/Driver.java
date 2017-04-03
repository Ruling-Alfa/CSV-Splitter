package com.alfa.csv;

import java.io.FileWriter;
import java.util.ArrayList;
import com.alfa.csv.utils.CsvUtils;

public class Driver {

	public static void main(String[] args) {
		String csvInFile = "";
		String csvOutFile = "";
		long linesPerFile = 10000;
		int counter = 0;
		FileWriter writer = null;
		try {
			ArrayList<ArrayList<String>> data = CsvUtils.readLine(csvInFile);

			csvOutFile = counter+"";
			writer =  new FileWriter(csvOutFile+"_split.csv");
			
			for(ArrayList<String> lines : data){
				CsvUtils.writeLine(writer, lines, ',', '"');
				counter++;
				if(counter > linesPerFile){
					counter = 0;
					writer.flush();
					writer.close();
					writer = new FileWriter(csvOutFile+"_split.csv");
				}		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try{
				if(writer != null)
					writer.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
