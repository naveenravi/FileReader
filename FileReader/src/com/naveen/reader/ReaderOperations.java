package com.naveen.reader;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ReaderOperations {
	
	private String[] criterias;
	private String extension;
	private ArrayList<Path> filteredPaths;
	private String criterion;

	public ReaderOperations() {
	}

	/**
	 * filters the files according to the criteria and the extension
	 * 
	 * @param criteria
	 * @param criter_parts
	 * @param ext
	 * @return results
	 */
	public void filter(Path folder_path) {		
		ArrayList<Path> results = new ArrayList<>();		
		try (DirectoryStream<Path> search_dir = Files
				.newDirectoryStream(folder_path);) {
			String fileName;
			for (Path path : search_dir) {
				fileName = path.getFileName().toString();
				// checking for extension and overall criteria
				if (fileName.endsWith(extension)
						&& Pattern.matches(criterion, fileName)) {
					results.add(path);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		filteredPaths = results;
	}

	/**
	 * renames all the files in the filtered paths
	 * 
	 * @param criter_parts
	 * @param filteredPaths
	 */
	public void renameFiles() {
        ArrayList<Path> modifiedPaths = new ArrayList<>();
		for (Path filePath : filteredPaths) {			
			for (String criterion : criterias) {
				if (!criterion.contentEquals(extension)) {
					Pattern myPattern = Pattern.compile(criterion);
					String parts[] = myPattern.split(filePath.getFileName()
							.toString());
					StringBuilder rebuilder = new StringBuilder();
					for (String part : parts) {
						rebuilder.append(part);
					}
					try {						
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
			modifiedPaths.add(filePath);						
		}
		filteredPaths.clear();
		filteredPaths = modifiedPaths;
	}

	public void setCriterias(String[] criterias) {
		this.criterias = criterias;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public void setCriterion(String criterion) {
		this.criterion = criterion;
	}

	public ArrayList<Path> getFilteredPaths() {
		return filteredPaths;
	}
}
