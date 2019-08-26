package com.ecristobale.zip;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFiles {

	private static final String STRFOLDER = "C:\\folder";
	private static final String STRZIPFILE = "\\texts.zip";
	
	private static final String STRFILE1 = Paths.get("").toAbsolutePath() + "\\src\\text1.txt";
	private static final String STRFILE2 = Paths.get("").toAbsolutePath() + "\\src\\text2.txt";

	public static void main(String[] args) throws IOException {
		
		File file1 = new File(STRFILE1);
		File file2 = new File(STRFILE2);
		List<File> files = new ArrayList<File>();
		files.add(file1);
		files.add(file2);
		
		int step = 1;
		
		// Obtain an array of bytes from a List<File>
		byte[] zipBytes = createZipFromFiles(files);
		System.out.println(step++ + ". Created an array of bytes from the files.");
		
		File folder = new File(STRFOLDER);
		boolean folderCreated = true;
		if(!folder.exists() || !folder.isDirectory()) {
			System.out.println(step++ + ". The folder provided: \""+ STRFOLDER + "\" doesn't exists.");
			folderCreated =  folder.mkdir();
			System.out.println(step++ + ". The folder provided: \""+ STRFOLDER + "\" was " + (folderCreated ? "" :"not ") + "created.");
		}
		if(folderCreated) {
			// Create a zip in a path
			createZipfromBytesArray(zipBytes, STRFOLDER.concat(STRZIPFILE));
			System.out.println(step + ". Created .zip file in the path: \"" + STRFOLDER.concat(STRZIPFILE) + "\".");
		}
	}

	public static byte[] createZipFromFiles(List<File> files) throws IOException {
		// Files to an array of bytes --> zip
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ZipOutputStream zipOutputStream = new ZipOutputStream(bos);
		for(File file: files){
			ZipEntry zipEntry = new ZipEntry(file.getName());
			zipOutputStream.putNextEntry(zipEntry);
			zipOutputStream.write(Files.readAllBytes(file.toPath()));
		}
		zipOutputStream.close();
		byte[] zipBytes = bos.toByteArray();
		return zipBytes;
	}

	private static void createZipfromBytesArray(byte[] zipBytes, final String path) throws FileNotFoundException, IOException {
		FileOutputStream fout = new FileOutputStream(path);
		fout.write(zipBytes);
		fout.close();
	}

}
