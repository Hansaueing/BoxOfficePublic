package com.boxoffice.httputils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;




/**
 * ��װһЩ��̬��������������� ex:get/post
 * 
 * @author Administrator
 * 
 */
public class HttpUtils {
	
	/**
	 * 
	 * @param is
	 *            �ӷ���˻�ȡ��������
	 * @return �����ַ���
	 */
	public static String getStr(InputStream is) {
		StringBuffer buff = new StringBuffer();
		// ���İ�װ
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line;
		try {
			line = br.readLine();
			while (line != null) {
				buff.append(line);
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buff.toString();
	}
	/**
	 * post��ʽ��ȡ������
	 * @return
	 */
	public static InputStream postIs(String path,String parma){
		InputStream is = null;
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			OutputStream os = conn.getOutputStream();
			os.write(parma.getBytes("utf-8"));
			os.flush();
//			conn.setRequestProperty("Content",
//					"application/x-www-form-urlencoded");
			is = conn.getInputStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return is;
	}
	
	/**
	 * get��ʽ��ȡ������
	 * @return
	 */
	public static InputStream getIs(String path){
		InputStream is = null;
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			is = conn.getInputStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return is;
	}
	
//	public static Music getJson(String str){
//		Music result = new Music();
//		try {
//			JSONObject object = new JSONObject(str);
//			String re=(String) (object.isNull("result")?"":object.get("result"));
//			String msg=(String) (object.isNull("msg")?"":object.get("msg"));
//			//result.setMsg(msg);
//			//result.setResult(re);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		
//		return result;
//	}
	/**
	 * @param path
	 * @param param
	 * @return
	 * ͨ��post�����ȡ������
	 */
	public static InputStream  postLogin(String path,String param,String cookie){
		InputStream is=null;
		try {
			//url
			URL url=new URL(path);
			//conn
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			//���󷽷�
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			//����cookid
			conn.setRequestProperty("Cookie", cookie);
			OutputStream os=conn.getOutputStream();
			os.write(param.getBytes("utf-8"));
			
			os.flush();
			is=conn.getInputStream();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return is;
	}
	
	
}
