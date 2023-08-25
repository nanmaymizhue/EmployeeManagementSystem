package com.ems.framework;

import java.sql.Connection;
import java.util.ArrayList;

import com.nirvasoft.database.ConnMgr;

import password.DESedeEncryption;

import com.ems.framework.FileUtil;

public class ConnAdmin {

	public ConnAdmin() {
		super();
	}

	public static String servername = "";
	public static String port = "";
	public static String instance = "";
	public static String dbname = "";
	public static String dbUsr = "";
	public static String dbPwd = "";
	public static String connType = "";
	static String path = "";
	static String url="";

	public static Connection getConn(String oId) {

		Connection conn = null;
		if (!oId.equals("")) {
			readConnectionString(oId);
			conn = (new ConnMgr(servername, Integer.parseInt(port), instance, dbname, dbUsr, dbPwd,
					Integer.parseInt(connType))).getConn();
		}
		return conn;
	}

	public static Connection getAnotherConn(String fileName, String oId) {

		Connection conn = null;
		if (!oId.equals("")) {
			readAnotherConnectionString(fileName, oId);
			conn = (new ConnMgr(servername, Integer.parseInt(port), instance, dbname, dbUsr, dbPwd,
					Integer.parseInt(connType))).getConn();
		}
		return conn;
	}

	public static Connection getOracleConn(String oId) {

		Connection conn = null;
		if (!oId.equals("")) {
			readOracleConnectionString(oId);
			conn = (new ConnMgr(servername, Integer.parseInt(port), instance, dbname, dbUsr, dbPwd,
					Integer.parseInt(connType))).getConn();
		}
		return conn;
	}

	private static void readConnectionString(String pOID) {
		String l_ret = "";
		ArrayList<String> arl = new ArrayList<String>();
		path = ServerSession.serverPath + "WEB-INF//data//ConnectionConfig.txt";

		try {
			arl = FileUtil.readFile(path);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < arl.size(); i++) {
			if (!arl.get(i).equals("")) {
				if (arl.get(i).startsWith(pOID)) {
					l_ret = arl.get(i);
					break;
				}
			}
		}
		String[] l_split = l_ret.split(",");
		servername = l_split[1];
		port = l_split[2];
		instance = l_split[3];
		dbname = l_split[4];
		dbUsr = l_split[5];
		dbPwd =decryptPIN(l_split[6]);
		connType = l_split[7];
	}
	public static String decryptPIN(String p) {
		String ret = "";
		try {
			DESedeEncryption myEncryptor = new DESedeEncryption();
			ret = myEncryptor.decrypt(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	private static void readAnotherConnectionString(String fileName, String pOID) {
		String l_ret = "";
		ArrayList<String> arl = new ArrayList<String>();
		path = ServerSession.serverPath + "data//" + fileName;

		try {
			arl = FileUtil.readFile(path);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < arl.size(); i++) {
			if (!arl.get(i).equals("")) {
				if (arl.get(i).startsWith(pOID)) {
					l_ret = arl.get(i);
					break;
				}
			}
		}
		String[] l_split = l_ret.split(",");
		servername = l_split[1];
		port = l_split[2];
		instance = l_split[3];
		dbname = l_split[4];
		dbUsr = l_split[5];
		dbPwd = serverUtil.decryptPIN(l_split[6]);
		connType = l_split[7];
	}

	private static void readOracleConnectionString(String pOID) {
		String l_ret = "";
		ArrayList<String> arl = new ArrayList<String>();
		path = ServerSession.serverPath + "data//OracleConnectionConfig.txt";

		try {
			arl = FileUtil.readFile(path);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < arl.size(); i++) {
			if (!arl.get(i).equals("")) {
				if (arl.get(i).startsWith(pOID)) {
					l_ret = arl.get(i);
					break;
				}
			}
		}
		String[] l_split = l_ret.split(",");
		servername = l_split[1];
		port = l_split[2];
		instance = l_split[3];
		dbname = l_split[4];
		dbUsr = l_split[5];
		dbPwd = serverUtil.decryptPIN(l_split[6]);
		connType = l_split[7];
	}

	public static String readExternalUrl(String pOID) {
		String l_ret = "";
		ArrayList<String> arl = new ArrayList<String>();

		String path = ServerSession.serverPath + "WEB-INF//data//ExternalConnectionConfig.txt";
		System.out.println(path);
		try {
			arl = FileUtil.readFile(path);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < arl.size(); i++) {
			if (!arl.get(i).equals("")) {
				if (arl.get(i).startsWith(pOID)) {
					l_ret = arl.get(i);
					break;
				}
			}
		}
		String[] l_split = l_ret.split("_");
		return url = l_split[1];
	}

}
