package cn.edu.zjicm.nba.model;

public class Team {

	private String name = null;			//队名
	private String englishName = null;	//队名
	private int win;					//胜
	private int lost;					//负


	public Team(String name, String englishName, int win, int lost) {
		this.name = name;
		this.englishName = englishName;
		this.win = win;
		this.lost = lost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLost() {
		return lost;
	}

	public void setLost(int lost) {
		this.lost = lost;
	}


}
