package com.pritle.assignment.filesystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileSystem {
	public static final String BASEDIRECTORYPATH = "../Assingment/resources/";

	public List<Path> getListFiles() throws IOException {
		String baseDirectoryPath = FileSystem.BASEDIRECTORYPATH;

		List<Path> listOfFiles = getListofAllFiles(baseDirectoryPath);
		return listOfFiles;
	}

	public List<Path> getListofAllFiles(String directoryPath) throws IOException {
		List<Path> listOfFiles = new ArrayList<>();
		Files.walk(Paths.get(directoryPath)).filter(Files::isRegularFile).forEach(path -> listOfFiles.add(path));
		return listOfFiles;
	}

	public Map<Path, List<String>> searchText(List<String> postCodeList, List<Path> listOfFiles) throws IOException {
		Map<Path, List<String>> fileHavingPostCode = new HashMap<>();

		listOfFiles.stream().parallel().forEach((path) -> {
			System.out.println("File Name:" + path.getFileName() + " Path:" + path);
			List<String> lineHavingPostCode = new ArrayList<>();
			try (BufferedReader reader = Files.newBufferedReader(path)) {

				reader.lines().parallel().forEach(line -> {
					if (postCodeList.parallelStream().parallel().filter(line::contains).findAny().isPresent()) {
						lineHavingPostCode.add(line);
					}

				});
			} catch (IOException e) {
				e.printStackTrace();
				// Log the error with file name
			}
			System.out.println(lineHavingPostCode);
			if (lineHavingPostCode.size() > 0) {
				fileHavingPostCode.put(path, lineHavingPostCode);
			}
		});
		return fileHavingPostCode;
	}

}
