package com.znordgren.universityapi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Zachary Nordgren
 * @version 1.0
 * 
 * Package designed to allow easy access to data about the cost of tuition for universities in the US
 * 
 */
public class UniversityApi {
	
	private final String m_fileName = "college_costs.csv";
	private HashMap<String,UniversityData> m_map;
	private ArrayList<String> m_keyNames;

	/**
	 * Sets up the HasMap used to store the data and Loads the data from a csv file
	 */
	public UniversityApi() {
		
		m_map = new HashMap<String,UniversityData>();
		
		try {
			this.loadData();
		} catch (Exception e) {
			e.printStackTrace();
			new Error("Problem initializing UniversityApi");
		}
		
	}
	
	/**
	 * Function to access the data. 
	 * @param collegeName - the name of the college that data is needed for
	 * @param roomAndBoard - a boolean toggle if the room and board cost should be added to the tuition value
	 * @return the requested data as a string
	 */
	public String get(String collegeName, boolean roomAndBoard) {
		
		collegeName = collegeName.toLowerCase(); // all colleges are read in as lower case for ease of use
		
		if (collegeName.isEmpty()) {
			
			return "Error: College name is required";
			
		} if (!m_map.containsKey(collegeName)) {
			
			return "Error: College not found";
			
		} else {
			
			UniversityData data = m_map.get(collegeName);
			return data.toString(constructQuery(roomAndBoard));
			
		}

	}
	
	/**
	 * Function to Access the data with room and board defaulted to true
	 * @param collegeName
	 * @return The value of the sum of the tuition and the room and board values for the given college or an error
	 */
	public String get(String collegeName) {
		
		return this.get(collegeName, true);
		
	}
	
	/**
	 * Opens the csv file and loads the data into a HashMap
	 * @throws Exception
	 */
	private void loadData() throws Exception {
		
		BufferedReader br = new BufferedReader(new FileReader(m_fileName));
		
		ArrayList<String> line =  new ArrayList<String>();
		
		m_keyNames = (ArrayList<String>) CSVHelper.parseLine(br); // Read in column headers
		
		while((line = (ArrayList<String>) CSVHelper.parseLine(br)) != null) {
			
			UniversityData data = new UniversityData(m_keyNames, line);
			if (data.isEmpty()) continue; // skip empty lines
			assert(!m_map.containsKey(data.getName())); // don't allow adding duplicate colleges
			m_map.put(data.getName(), data);

		}

		br.close();
		
	}
	
	/**
	 * Selects which columns should be added together. Only this function needs to change if new columns are added
	 * @param roomAndBoard - boolean switch to include or not include the room and board data
	 * @return an array list containing the column names that should be added together for the api request
	 */
	private ArrayList<String> constructQuery(boolean roomAndBoard) {
		
		ArrayList<String> query = new ArrayList<String>();
		
		query.add(m_keyNames.get(1));
		
		if (roomAndBoard) {
			query.add(m_keyNames.get(3));
		}
		
		return query;
		
	}
	

}
