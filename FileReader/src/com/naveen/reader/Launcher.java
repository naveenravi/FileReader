package com.naveen.reader;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Launcher {

	/**
	 * Prepares the application for operation by collecting the inputs and
	 * calling the respective methods
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Welcome");
		System.out.println("enter the folder name");
		Scanner input = new Scanner(System.in);
		// Path path = Paths.get(input.nextLine());
		Path path = Paths.get("C:\\Users\\Naveen\\Desktop\\Testing");
		ReaderOperations rop_obj = new ReaderOperations(path);
		System.out.println("Enter the extension of the files");
		String ext = input.nextLine();
		System.out.println("Enter your criteria for filtering:");
		
		// get the criteria
        // replace the * with .
		
		String regex = input.nextLine().replaceAll("[*]", ".*");
//		System.out.println("regex");
				
		rop_obj.filter(regex, ext);
		input.close();
		System.out.println("bye");

	}

}
