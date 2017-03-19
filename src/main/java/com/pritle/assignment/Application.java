package com.pritle.assignment;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pritle.assignment.filesystem.FileSystem;
import com.pritle.assignment.postcode.PostCodePattern;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws IOException {
		// SpringApplication.run(Application.class, args);
		// Step 1 - get list of postCode which need to search
		PostCodePattern postCodePattern = new PostCodePattern();
		List<String> postCodeList = postCodePattern.getPostcodeList();

		// Step 2 - get list files with the absolute path from file system
		FileSystem fileSystem = new FileSystem();
		List<Path> listOfFiles = fileSystem.getListFiles();
		System.out.println(listOfFiles);
		// Step 3 - search the post code in all the files present in the file
		// system and store the file path and lines having post code in a map.
		Map<Path, List<String>> fileHavingPostCode = fileSystem.searchText(postCodeList, listOfFiles);
		System.out.println(fileHavingPostCode);
	}
}
