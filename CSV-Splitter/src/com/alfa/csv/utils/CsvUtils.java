package com.alfa.csv.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {
	private static final char DEFAULT_SEPARATOR = ',';

    public static void writeLine(Writer w, List<String> values) throws IOException {
        writeLine(w, values, DEFAULT_SEPARATOR, ' ');
    }

    public static void writeLine(Writer w, List<String> values, char separators) throws IOException {
        writeLine(w, values, separators, ' ');
    }

    //https://tools.ietf.org/html/rfc4180
    private static String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }

    public static void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {

        boolean first = true;

        //default customQuote is empty

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(separators);
            }
            if (customQuote == ' ') {
                sb.append(followCVSformat(value));
            } else {
                sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
            }

            first = false;
        }
        sb.append("\n");
        w.append(sb.toString());


    }
	public static ArrayList<ArrayList<String>> readLine(String csvInFile) throws IOException {
		ArrayList<ArrayList<String>> allLines = new ArrayList<ArrayList<String>>();
		
		BufferedReader br = new BufferedReader(new FileReader(csvInFile));
		String line = "";
        String cvsSplitBy = ",";
        
		while ((line = br.readLine()) != null) 
		{
			ArrayList<String> oneLine = new ArrayList<String>();
			String[] items = line.split(cvsSplitBy);
			for (String item : items)
			{
				oneLine.add(item);
			}
			allLines.add(oneLine);
		}
		br.close();
		return allLines;
    }	
}
