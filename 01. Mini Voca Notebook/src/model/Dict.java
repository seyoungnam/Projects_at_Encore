package model;

public class Dict {
	
	private String voca;
	private String mean;
	private int date;
	
	//Singleton ¼³Á¤
	private static Dict instance = new Dict();
	private Dict() {}
	
	public static Dict getDict() {
		return instance;
	}
	

	public Dict(String voca, String mean, int date) {
		this.voca = voca;
		this.mean = mean;
		this.date = date;
	}

	public String getVoca() {
		return voca;
	}

	public void setVoca(String voca) {
		this.voca = voca;
	}

	public String getMean() {
		return mean;
	}

	public void setMean(String mean) {
		this.mean = mean;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}
	
	public String toString() {
		return voca+"   "+mean+"   "+date;
	}

}
