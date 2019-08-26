package com.ecristobale.hash;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.ecristobale.zip.ZipFiles;

public class HashFile {

	private static final String STRFILE1 = Paths.get("").toAbsolutePath() + "\\src\\text1.txt";
	
	public static void main(String[] args) {
		File file1 = new File(STRFILE1);
		String text = "Sample text";
		int step = 1;
		
		// 1. Generate hash codes from text (String variable) and show them in console.
		System.out.println("------------- HASH FROM TEXT -------------");
		System.out.println(step + ". Text: \"" + text + "\"");
		System.out.println(step + "a. SHA256 hash from the text: " + getSHA256(text));
		System.out.println(step++ + "b. MD5 hash from the text: " + getMD5(text));
		
		// 2. Generate hash codes from a file and show them in console.
		if(file1.exists()) {
			try {
				byte[] file1Content = Files.readAllBytes(file1.toPath());
				System.out.println("\n");
				System.out.println("------------- HASH FROM FILE -------------");
				System.out.println(step + ". File path: " + STRFILE1);
				System.out.println(step + "a. SHA256: " + getSHA256(new String(file1Content)));
				System.out.println(step++ + "b. MD5: " + getMD5(new String(file1Content)));
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			
		} 
		
		// 3. Generate hash codes from a bytes of a zip generated and show them in console.
		try {
			List<File> files = new ArrayList<File>();
			files.add(file1);
			byte[] zipBytes = ZipFiles.createZipFromFiles(files);
			System.out.println("\n");
			System.out.println("------------- HASH FROM ZIP --------------");
			System.out.println(step + ". Zip bytes: " + zipBytes.toString());
			System.out.println(step + "a. SHA256: " + getSHA256(new String(zipBytes)));
			System.out.println(step + "b. MD5: " + getMD5(new String(zipBytes)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

	private static String getSHA256(String text) {
		try{
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(text.getBytes("UTF-8"));
	        StringBuffer hexStr = new StringBuffer();

	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) hexStr.append('0');
	            hexStr.append(hex);
	        }
	        return hexStr.toString();
	    } catch(NoSuchAlgorithmException ex){
	    	ex.printStackTrace();
	    	return ex.getMessage();
	    } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	private static String getMD5(String text) {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(text.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			String hashtext = bigInt.toString(16);
			// Now we need to zero pad it for the full 32 chars.
			while(hashtext.length() < 32 ){
			  hashtext = "0"+hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
	}

}
