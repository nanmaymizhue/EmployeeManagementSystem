package com.ems.framework;

import com.ems.framework.MrBean;
import com.ems.framework.ServerSession;

public class ServerSession {
	
	public static String serverPath = "";
	public static String email = "";
	public static long userSK = 0;
	
	private static MrBean mrbean = new MrBean();
	
	public static MrBean getMrbean() {return mrbean;}
	public static void setMrbean(MrBean mrbean) {ServerSession.mrbean = mrbean;}
	
}
