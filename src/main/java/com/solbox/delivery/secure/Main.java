package com.solbox.delivery.secure;


public class Main {
	public static void main(String[] args) {
		try {
			
			String url = "/a/b/c/d/file_name.mp4";
			String key = "abcdefghi";
			int timeout = 3600;
			int skipDepth = 0;
			boolean isFileNameExcepted = false;
			byte[] cipherKey;
			Facilities facilities = new Facilities();
			
			DataSet dataSet = facilities.inputParser(url, timeout, skipDepth, isFileNameExcepted);

			SHA256 sha256 = new SHA256();
			cipherKey = sha256.encrypt(key);

			AES256 aes256 = new AES256();
			String cipherText = aes256.encrypt(dataSet.jsonString, cipherKey);

			facilities.printResult(cipherText, dataSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
