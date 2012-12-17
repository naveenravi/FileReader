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

		String criteria = input.nextLine();
		String criterion[];
		if (criteria.contains("*")) {
			criterion = criteria.split("[*]");
			System.out.println("printing the criterias");
			int i = 0;
			for (String criter : criterion) {
				System.out.println("i:" + i + "criter" + criter);
				i++;
				rop_obj.filter(criter, ext);
			}
		} else
			rop_obj.filter(criteria, ext);

		input.close();
		System.out.println("bye");

	}

}
