package com.naveen.reader;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ReaderOperations {

	private static Path folder_path;
	private static String extension;

	public ReaderOperations(Path f_path, String file_ext) {
		folder_path = f_path;
		extension = file_ext;
	}

	public void listFiles() {

	}

	/**
	 * filters the files according to the criteria and the extension
	 * 
	 * @param criteria
	 * @param criter_parts
	 * @param ext
	 * @return results
	 */
	public ArrayList<Path> filter(String criteria) {
		System.out.println(criteria);
		ArrayList<Path> results = new ArrayList<>();
		System.out.println("filteringgggg....." + folder_path);
		try (DirectoryStream<Path> search_dir = Files
				.newDirectoryStream(folder_path);) {
			String fileName;
			for (Path path : search_dir) {
				fileName = path.getFileName().toString();
				// checking for extension and overall criteria
				if (fileName.endsWith(extension)
						&& Pattern.matches(criteria, fileName)) {
					results.add(path);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * renames all the files in the filtered paths
	 * 
	 * @param criter_parts
	 * @param filteredPaths
	 */
	public void renameFiles(String[] criter_parts, ArrayList<Path> filteredPaths) {

		for (Path filePath : filteredPaths) {
			System.out.println("renaming for ::::" + filePath);
			for (String criterion : criter_parts) {
				if (!criterion.contentEquals(extension)) {
					Pattern myPattern = Pattern.compile(criterion);
					String parts[] = myPattern.split(filePath.getFileName()
							.toString());
					StringBuilder rebuilder = new StringBuilder();
					for (String part : parts) {
						rebuilder.append(part);
					}
					try {
						System.out.println("renaming for criter: " + criterion);
						filePath = Files.move(filePath,
								filePath.resolveSibling(rebuilder.toString()));
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					System.out
							.println("yet to implement for criteria same as extension");
				}
			}
			System.out.println("final modified name:::" + filePath);
		}
	}
}
