package model;

import view.EndView;

public class DictModel {
	
	Dict row = Dict.getDict();
	public static row[] allVoca = new row[100];
	public static int index;
	
	static {
    allVoca[0] = row("vindication", "지지, 옹호", 20190702);
    allVoca[1] = row("indignity", "수모, 치욕", 20190702);
    allVoca[2] = row("eject", "내쫓다, 쫓아내다", 20190701);
    index = 3;
	}
	
	//1.입력된 모든 단어 불러오기(read.1)
	public static Dict[] getAll() {
		return allVoca;
  }

	//2.개별 단어 검색(read.2)
	public static boolean search(String newVoca) {
		Dict v = null;
		for(int i=0; i<allVoca.length; i++) {
			v = allVoca[i];
			if(v !=null && v.getVoca().equals(newVoca)) {
				System.out.println(v.toString());
				return true;
			}	
		}
		return false;	  	
	}
 
  
  
  //3.신규 단어 입력(create)
	  public static void insert(String newVoca, String newMean, int newDate) {
		  Dict v = null;
		  allVoca[index] = new Dict(newVoca, newMean, newDate);
		  v = allVoca[index++];
		  v.setVoca(newVoca);
		  v.setMean(newMean);
		  v.setDate(newDate);
		  EndView.printSuccess("새단어가 저장 되었습니다.");
	  }
	

	
  //4.의미 수정(update)
  public static boolean update(String newVoca, String newMean, int newDate) {
	  Dict v = null;
	  for(int i=0; i<allVoca.length; i++) {
		  v = allVoca[i];
		  if(v !=null && v.getVoca().equals(newVoca)) {
			  v.setMean(newMean);
			  v.setDate(newDate);
			  return true;
		  }
	  }
	  return false;
  }
	
  //5.기존 단어 삭제(delete)
  public static boolean delete(String newVoca) {
	  Dict v = null;
	  for(int i=0; i<allVoca.length; i++) {
		  v = allVoca[i];
		  if(v != null && v.getVoca().equals(newVoca)) {
			  allVoca[i] = null;
			  return true;
		  }
	  }
	  return false;
  }

}
