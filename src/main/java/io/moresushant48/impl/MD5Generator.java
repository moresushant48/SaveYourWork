package io.moresushant48.impl;

import java.io.UnsupportedEncodingException;

import com.twmacinta.util.MD5;

public class MD5Generator {

	public static String MD5(String pswd) {
		MD5 md = new MD5();
		try {
			md.Update(pswd,null);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return md.asHex();
	}
}