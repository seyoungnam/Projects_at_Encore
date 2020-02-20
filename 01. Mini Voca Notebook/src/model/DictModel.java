package model;

import view.EndView;

public class DictModel {
	
	Dict row = Dict.getDict();
	public static row[] allVoca = new row[100];
	public static int index;
	
	static {
    allVoca[0] = row("vindication", "����, ��ȣ", 20190702);
    allVoca[1] = row("indignity", "����, ġ��", 20190702);
    allVoca[2] = row("eject", "���Ѵ�, �ѾƳ���", 20190701);
    index = 3;
	}
	
	//1.�Էµ� ��� �ܾ� �ҷ�����(read.1)
	public static Dict[] getAll() {
		return allVoca;
  }

	//2.���� �ܾ� �˻�(read.2)
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
 
  
  
  //3.�ű� �ܾ� �Է�(create)
	  public static void insert(String newVoca, String newMean, int newDate) {
		  Dict v = null;
		  allVoca[index] = new Dict(newVoca, newMean, newDate);
		  v = allVoca[index++];
		  v.setVoca(newVoca);
		  v.setMean(newMean);
		  v.setDate(newDate);
		  EndView.printSuccess("���ܾ ���� �Ǿ����ϴ�.");
	  }
	

	
  //4.�ǹ� ����(update)
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
	
  //5.���� �ܾ� ����(delete)
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
