package com.naveen.reader;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import sun.management.FileSystem;
import sun.org.mozilla.javascript.internal.regexp.SubString;

public class FileLocator {

	public void hello() {
		
		Path paths = Paths.get("C:","Users","Naveen","Desktop","Testing");
		
		try (DirectoryStream<Path> dir = Files.newDirectoryStream(paths)) {
           for(Path pat : dir){       	   
        	   
        	   String file_name = pat.getFileName().toString();
        	   System.out.println(pat.getParent());
//        	   Path pat2 = pat.getFileSystem().getPath(pat.toFile().getParent(), more)
        	   
        	   
        	   if(file_name.endsWith("mp3")){        		   
        		   String name_part[] = file_name.split(".");
        		   for(String str : name_part){
        			   System.out.println(str);
        		   }
        	   }
        	   
           }
		} catch (Exception e) {

		}
	}

}
