package com.calow.tool.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendHttpServer {

	public static SendHttpServer sendHttpServer;

	private SendHttpServer() {

	}

	public static SendHttpServer getInstance() {
		if (sendHttpServer == null) {
			synchronized (SendHttpServer.class) {
				if (sendHttpServer == null) {
					sendHttpServer = new SendHttpServer();
				}
			}
		}
		return sendHttpServer;
	}

	public String sendWithHttpUrlConnectionGet(String urlString) {
		HttpURLConnection conn = null; // 连接对象
		String resultData = "";// 获取到的结果
		BufferedReader bufferReader = null;
		try {
			URL url = new URL(urlString);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("GET");
			InputStreamReader isr = new InputStreamReader(
					conn.getInputStream(), "UTF-8");
			bufferReader = new BufferedReader(isr);
			String inputLine = "";
			while ((inputLine = bufferReader.readLine()) != null) {
				resultData = resultData + inputLine + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferReader != null) {
				try {
					bufferReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return resultData;
	}

	public String sendWithHttpUrlConnectionPost(String urlString,
			String keyWords) {
		HttpURLConnection conn = null; // 连接对象
		PrintWriter out = null;
		BufferedReader in = null;
		String resultData = "";// 获取到的结果
		try {
			URL url = new URL(urlString);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			out = new PrintWriter(conn.getOutputStream());
			out.println(keyWords);
			out.flush();
			InputStreamReader isr = new InputStreamReader(
					conn.getInputStream(), "UTF-8");
			in = new BufferedReader(isr);
			String inputLine = "";
			while ((inputLine = in.readLine()) != null) {
				resultData = resultData + inputLine + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				conn.disconnect();
			}
		}

		return resultData;
	}
	
	
	// 发送一个GET请求,参数形式key1=value1&key2=value2...
		public String post(String path, String params){
			HttpURLConnection httpConn = null;
			BufferedReader in = null;
			PrintWriter out = null;
			try {
				URL url = new URL(path);
				httpConn = (HttpURLConnection) url.openConnection();
				httpConn.setRequestMethod("POST");
				httpConn.setDoInput(true);
				httpConn.setDoOutput(true);

				// 发送post请求参数
				out = new PrintWriter(httpConn.getOutputStream());
				out.println(params);
				out.flush();

				// 读取响应
				if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
					StringBuffer content = new StringBuffer();
					String tempStr = "";
					in = new BufferedReader(new InputStreamReader(
							httpConn.getInputStream(), "utf-8"));
					while ((tempStr = in.readLine()) != null) {
						content.append(tempStr + "\n");
					}
					return content.toString();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				out.close();
				httpConn.disconnect();
			}
			return null;
		}
}
