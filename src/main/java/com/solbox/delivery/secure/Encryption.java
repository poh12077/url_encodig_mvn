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

			  //facilities.printResult(cipherText, dataSet); 
			  String encodedUrl = dataSet.skippedPath + cipherText + dataSet.fileName;

			  String jsonString = "{ \"result\" : \"success\", \"url\" : \"" + encodedUrl +"\" }" ;

			  return jsonString; 
		  } catch (Exception e) 
		  { 
			  e.printStackTrace();

			  return "{ \"result\" : \"fail\" }"; 
			  } 
		  }
	 

	/*
	 * static public String urlEncorder(String url, String key, int timeout, int
	 * skipDepth, boolean isFileNameExcepted) { try { byte[] cipherKey; Facilities
	 * facilities = new Facilities(); DataSet dataSet = facilities.inputParser(url,
	 * timeout, skipDepth, isFileNameExcepted);
	 * 
	 * SHA256 sha256 = new SHA256(); cipherKey = sha256.encrypt(key);
	 * 
	 * AES256 aes256 = new AES256(); String cipherText =
	 * aes256.encrypt(dataSet.jsonString, cipherKey);
	 * 
	 * // facilities.printResult(cipherText, dataSet); String encodedUrl =
	 * dataSet.skippedPath + cipherText + dataSet.fileName;
	 * 
	 * Output output = new Output("success",encodedUrl); //output.result =
	 * "success"; //output.encodedUrl = encodedUrl; ObjectMapper mapper = new
	 * ObjectMapper();
	 * 
	 * return mapper.writeValueAsString(output); } catch (Exception e) { try {
	 * e.printStackTrace(); Output output = new Output("fail","" ); //output.result
	 * = "fail"; //output.encodedUrl = ""; ObjectMapper mapper = new ObjectMapper();
	 * 
	 * return mapper.writeValueAsString(output); } catch (Exception err) {
	 * e.printStackTrace(); return ""; } } }
	 */

}
