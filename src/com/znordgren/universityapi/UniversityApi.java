/**
 * API to return the cost of tuition for a given university
 */
package com.znordgren.universityapi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Zachary Nordgren
 * @version 1.0
 * 
 */
public class UniversityApi {
	
	private final String fileName = "college_costs.csv";
	private HashMap<String,UniversityData> map;

	public UniversityApi() {
		
		map = new HashMap<String,UniversityData>();
		
		try {
			this.loadData();
		} catch (Exception e) {
			e.printStackTrace();
			new Error("Problem initializing UniversityApi");
		}
		
	}

	public String get(String collegeName, boolean roomAndBoard) {
		
		collegeName = collegeName.toLowerCase();
		
		if (collegeName.isEmpty()) {
			
			return "Error: College name is required";
			
		} if (!map.containsKey(collegeName)) {
			
			return "Error: College not found";
			
		} else {
			
			UniversityData data = map.get(collegeName);
			return data.toString(roomAndBoard);
			
		}

	}

	public String get(String collegeName) {
		return this.get(collegeName, true);
	}
	
	private void loadData() throws Exception {
		
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		ArrayList<String> line =  new ArrayList<String>();
		
		CSVHelper.parseLine(br); // Skip column headers
		
		while((line=(ArrayList<String>) CSVHelper.parseLine(br))!=null){
			
			UniversityData data = new UniversityData(line);
			if (data.isEmpty()) continue;
			map.put(data.getName(), data);

		}

		br.close();
		
	}
	

}
