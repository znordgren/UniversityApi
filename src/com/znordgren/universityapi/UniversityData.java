package com.znordgren.universityapi;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * Class to store the data about a university
 * @author zdnordgren
 * @version 1.0
 */
class UniversityData {
	
	private String m_name;
	private Dictionary<String, Integer> values = new Hashtable<String, Integer>();
	
	/**
	 * Creates a dictionary of key value pairs to store all of the data about a university
	 * @param valuekeys
	 * @param line
	 */
	public UniversityData(ArrayList<String> keyNames, ArrayList<String> line) {
		
		m_name = line.get(0).toLowerCase();
		for (int i = 1; i < line.size(); i++) {
			values.put(keyNames.get(i), parseInt(line.get(i)));
		}
		
	}
	
	/**
	 * get college name
	 * @return A string containing the name of the college
	 */
	public String getName() {
		return m_name;
	}
	
	/**
	 * Adds and returns the requested columns as a string
	 * @param valuesToPrint
	 * @return a string containing the sum of the requested columns
	 */
	public String toString(ArrayList<String> valuesToPrint) {
		
		int valueToReturn = 0;
		
		for (Iterator<String> i = valuesToPrint.iterator(); i.hasNext();) {
			valueToReturn += values.get(i.next());
		}
		
		return Integer.toString(valueToReturn);
	}
	
	/**
	 * 
	 * @return a boolean value indicating if the object has any data
	 */
	public boolean isEmpty() {
		return m_name.isEmpty();
	}
	
	/**
	 * Function to convert strings to numbers. 
	 * @param n - a numeric string or an empty string
	 * @return an integer of the value of the string or zero if the string is empty
	 */
	private int parseInt(String n) {
		return n.isEmpty() ? 0 : Integer.parseInt(n);
	}



}
