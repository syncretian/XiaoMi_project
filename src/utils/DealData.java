package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DealData {
	public static Date parseDate(String d) {
		SimpleDateFormat fd =  new SimpleDateFormat ("yyyy-MM-dd HH:MM:ss");
		
		try {
			return fd.parse(d);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return null;
	}


	public static String getParam(Map<String, String> chaxun) {
		
		String st = "";
		for(Entry<String, String> e :  chaxun.entrySet()) {

			String str = e.getKey();
			
			switch(str) {
			case"current_page":
			case"name":
			case"state":
			case"start_time":
			case"end_time": 
				st=st+"&"+str+"="+e.getValue();
				System.out.println(str+"--na--"+e.getValue());
				break;
			default:break;
			}
			
		}
		System.out.println(st);
		
		return st;	
	}


	public static String getParam1(Map<String, String[]> chaxun) {

		String st = "";
		for(Entry<String, String[]> e :  chaxun.entrySet()) {

			String str = e.getKey();
			
			switch(str) {
			case"current_page":
			case"name":
			case"state":
			case"start_time":
			case"end_time": 
				st=st+"&"+str+"="+e.getValue()[0];
				System.out.println(str+"--na--"+e.getValue()[0]);
				break;
			default:break;
			}
			
		}
		System.out.println(st);
		
		return st;	
	}

}
