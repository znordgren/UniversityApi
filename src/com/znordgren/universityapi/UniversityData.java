package com.znordgren.universityapi;

import java.util.ArrayList;

class UniversityData {
	
	private String name;
	private int tuition, outStateTuition, roomAndBoard;

	public UniversityData(ArrayList<String> line) {

		assert(line.size() == 4);
		
		name = line.get(0).toLowerCase();
		tuition = parseInt(line.get(1));
		outStateTuition = parseInt(line.get(2));
		roomAndBoard = parseInt(line.get(3));
		
		if (tuition == 0) tuition = outStateTuition;

	}
	
	public String getName() {
		return name;
	}

	public String toString(boolean returnCombined) {
		return returnCombined ? Integer.toString(tuition + roomAndBoard) : Integer.toString(tuition);
	}
	
	public boolean isEmpty() {
		return name.isEmpty();
	}
	
	private int parseInt(String n) {
		return n.isEmpty() ? 0 : Integer.parseInt(n);
	}



}
