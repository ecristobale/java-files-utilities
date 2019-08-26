package com.ecristobale.b64;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class B64Encode {
	
	private static final String STRFILE1 = Paths.get("").toAbsolutePath() + "\\src\\text1.txt";

	public static void main(String[] args) {
		
		String sampleStr = "sample text";

		/********************    B64 - ENCODE    ************************************/
		String sampleStrB64Encoded = Base64.getEncoder().encodeToString(sampleStr.getBytes());
		System.out.println("------------- ENCODING TO B64 -------------");
		System.out.println("1a. Decoded text: \"" + sampleStr + "\"");
		System.out.println("1b. Encode text to B64: " + sampleStrB64Encoded);
		
		/********************    B64 - DECODE    ************************************/
		System.out.println("\n");
		System.out.println("------------- DECODING FROM B64 -------------");
		byte[] decodedBytes = Base64.getDecoder().decode(sampleStrB64Encoded);
		String sampleStrDecoded = new String(decodedBytes);
		
		System.out.println("2a. B64 Encoded text: " + sampleStrB64Encoded);
		System.out.println("2b. Decode from B64: \"" + sampleStrDecoded + "\"");
		
		File file1 = new File(STRFILE1);
		
		if(file1.exists()) {

			/********************    B64 - ENCODE    ************************************/
			System.out.println("\n");
			System.out.println("------------ FILE CONTENT TO B64 ------------");
			try {
				byte[] file1Content = Files.readAllBytes(file1.toPath());
				String file1ContentStr = new String(file1Content);
				String file1ContentStrB64 = Base64.getEncoder().encodeToString(file1Content);
				
				System.out.println("3a. Decoded text from file: " + file1ContentStr);
				System.out.println("3b. Encoded text to B64:    "+ file1ContentStrB64);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
