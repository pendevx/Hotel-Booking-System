package com.group5.system;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataBackup {
	public static void backupFile(String sourcePath) {
		try {
			Files.copy(Paths.get(sourcePath), Paths.get(createBackupPath(sourcePath)), StandardCopyOption.REPLACE_EXISTING);
//			System.out.println("File backup successful.\n");
		}
		catch (IOException ex) { System.out.println("File backup failed."); };
	}

	private static String createBackupPath(String sourcePath) {
		String rootPath = Paths.get(sourcePath).getParent().toString() + "/logs/";
		String originalName = Paths.get(sourcePath).getFileName().toString();
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd_HHmm"));
		return rootPath + timestamp + "_" + originalName;
    }
}
