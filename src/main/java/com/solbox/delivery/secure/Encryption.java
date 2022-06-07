package com.solbox.delivery.secure;

public class Encryption {
	  static public String urlEncorder(String url, String key, int timeout, int
	  skipDepth, boolean isFileNameExcepted) 
	  { 
		  try {
			  byte[] cipherKey; 
			  Facilities facilities = new Facilities(); 
			  DataSet dataSet = facilities.inputParser(url, timeout, skipDepth, isFileNameExcepted);

			  SHA256 sha256 = new SHA256(); 
			  cipherKey = sha256.encrypt(key);

			  AES256 aes256 = new AES256(); 
			  String cipherText = aes256.encrypt(dataSet.jsonString, cipherKey);

			  String encodedUrl = dataSet.skippedPath + cipherText + dataSet.fileName;
			  String jsonString = "{ \"result\" : \"success\", \"url\" : \"" + encodedUrl +"\" }" ;

			  return jsonString; 
		  } catch (Exception e) 
		  { 
			  e.printStackTrace();
			  return "{ \"result\" : \"fail\" }"; 
			  } 
		  }
}
