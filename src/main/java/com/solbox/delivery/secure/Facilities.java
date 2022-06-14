package com.solbox.delivery.secure;

import java.util.Random;

class Output {
	String result;
	String encodedUrl;

	Output(String result, String encodedUrl) throws Exception {
		this.result = result;
		this.encodedUrl = encodedUrl;
	}
}

class DataSet {
	String jsonString;
	String skippedPath;
	String fileName;

	DataSet(String jsonString, String skippedPath, String fileName) throws Exception {
		this.jsonString = jsonString;
		this.skippedPath = skippedPath;
		this.fileName = fileName;
	}
}

class Facilities {
	DataSet inputParser(String url, int timeout, int skipDepth, boolean isFileNameExcepted) throws Exception {
		// exception handling
		if (timeout <= 0) {
			throw new Exception("timeout must be greater than 0");
		}
		if (url.charAt(0) != '/') {
			url = "/" + url;
		}

		// seq
		Random random = new Random();
		int seq = random.nextInt(1000);
		// path
		String path = "/";
		String skippedPath = "/";
		String[] urlArray = url.split("/");
		String fileName = "";
		int fileNameExcepted = 0;
		if (isFileNameExcepted) {
			fileNameExcepted = 1;
		}

		if (skipDepth < 0 || urlArray.length - 2 < skipDepth) {
			throw new Exception("skipDepth must be more than 0 and less than path depth ");
		}

		for (int i = 1; i < skipDepth + 1; i++) {
			skippedPath += urlArray[i] + "/";
		}
		for (int i = 1 + skipDepth; i < urlArray.length - 1; i++) {
			path += urlArray[i] + "/";
		}
		path = path.substring(0, path.length() - 1);

		if (fileNameExcepted != 0) {
			fileName = "/" + urlArray[urlArray.length - 1];
		}else {
			int index = urlArray[urlArray.length - 1].lastIndexOf(".");
			if (index== -1) {
				throw new Exception("there is no file extension");
			}
			String extension = urlArray[urlArray.length - 1].substring(index);
			fileName = extension;
			path += ( "/" + urlArray[urlArray.length - 1].substring(0,index) );
		}

		// exp
		// second
		long exp = System.currentTimeMillis() / 1000;
		exp += timeout;

		String jsonString = "{ \"seq\": " + Integer.toString(seq) + ", \"path\": \"" + path + "\", \"exp\": "
				+ Long.toString(exp) + " }";

		return new DataSet(jsonString, skippedPath, fileName);
	}

	void printResult(String cipherText, DataSet dataSet) throws Exception {
		String result = dataSet.skippedPath + cipherText + dataSet.fileName;
		System.out.println(dataSet.jsonString);
		System.out.println(dataSet.jsonString.length());
		System.out.println(result);
	}
}