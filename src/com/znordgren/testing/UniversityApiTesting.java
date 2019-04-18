package com.znordgren.testing;

import com.znordgren.universityapi.*;

/**
 * @author zdnordgren
 * 
 * Program written to demonstrate features of the universityAPI
 * 
 */
public class UniversityApiTesting {

	public static void main(String[] args) {
		
		UniversityApi api = new UniversityApi();
		
		System.out.println("Blank String Test:                       " + api.get(""));
		System.out.println("Blank String Test with R&B set to false: " + api.get("",false));
		System.out.println();
		
		System.out.println("Non Existing School Test: not a school = " + api.get("not a school"));
		System.out.println();
		
		System.out.println("Existing School Test: Columbia College (Chicago) = " + api.get("Columbia College (Chicago)"));
		System.out.println("Existing School Test: Wheaton College, Norton    = " + api.get("Wheaton College, Norton"));
		System.out.println();
		
		System.out.println("School with R&B Test: Barton Collage    = " + api.get("Barton College", true));
		System.out.println("School without R&B Test: Barton Collage = " + api.get("Barton College", false));
		System.out.println();
		
		System.out.println("Lower Case Test: marquette university = " + api.get("marquette university"));
		System.out.println("Upper Case Test: MARQUETTE UNIVERSITY = " + api.get("MARQUETTE UNIVERSITY"));
	}

}
