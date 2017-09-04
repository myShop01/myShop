package com.sofrecom.myshop.common;

import java.util.List;

public class ListUtils {
	
	public static List paginate(List list, int page, int limit){
		
		int first;
		int last;
		
		if(limit*(page-1)>list.size() || limit*(page-1)<0){
			first=0;
		}
		else{
			first=limit*(page-1);
		}
		
		if(first+limit>list.size()){
			last=list.size();
		}
		else{
			last=first+limit;
		}
		return list.subList(first, last);
	}
}
