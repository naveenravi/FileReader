package com.naveen.reader;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;


public class ReaderOperations {

	private static Path folder_path;

	public ReaderOperations(Path f_path) {
		folder_path = f_path;
	}

	public void listFiles() {

	}

	/**
	 * this method filters the files in the folder according to the critereia of
	 * the user
	 * 
	 * @param criteria
	 */
	public void filter(String criteria, String ext) {		
		System.out.println("ext:"+ext + "crit" + criteria);
		Pattern pat = Pattern.compile("Wire");
		
		
		System.out.println("filteringgggg....." + folder_path);
		try (DirectoryStream<Path> search_dir = Files
				.newDirectoryStream(folder_path);) {
			for (Path p : search_dir) {				
				if(p.toFile().getName().endsWith(ext)){
					System.out.println("before split");
					System.out.println(p);
//					String[] name_parts = p.toString().split("Wire");
					String[] name_parts = pat.split(p.toString());
					System.out.println("after split");
					StringBuilder new_name = new StringBuilder();
					
					for(String s : name_parts){						
						new_name.append(s);
					}					
					System.out.println(new_name);
					p.toFile().renameTo(new File(new_name.toString()));
				}
			}
		} catch (Exception e) {

		}

	}

}
