package cn.edu.zjicm.nba.model;

public class Game {

	private int id;							//比赛编号
	private String name = null;				//比赛名称
	private String away_team_name = null;			//客队
	private String away_team_english_name = null;	//客队
	private String home_team_name = null;			//主队
	private String home_team_english_name = null;	//主队
	private int home_team_score;			//主队得分
	private int away_team_score;			//客队得分
	private long start_time;				//开始时间，到1970-1-1凌晨的毫秒数
	private int status;					//状态，0未赛，1进行中，2已结束

	public Game(int id, String name, String away_team_name, String away_team_english_name, String home_team_name, String home_team_english_name,
			    int home_team_score, int away_team_score, long start_time, int status) {
		this.id = id;
		this.name = name;
		this.away_team_name = away_team_name;
		this.away_team_english_name = away_team_english_name;
		this.home_team_name = home_team_name;
		this.home_team_english_name = home_team_english_name;
		this.home_team_score = home_team_score;
		this.away_team_score = away_team_score;
		this.start_time = start_time;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHome_team_name() {
		return home_team_name;
	}

	public void setHome_team_name(String home_team_name) {
		this.home_team_name = home_team_name;
	}

	public String getHome_team_english_name() {
		return home_team_english_name;
	}

	public void setHome_team_english_name(String home_team_english_name) {
		this.home_team_english_name = home_team_english_name;
	}

	public String getAway_team_name() {
		return away_team_name;
	}

	public void setAway_team_name(String away_team_name) {
		this.away_team_name = away_team_name;
	}

	public String getAway_team_english_name() {
		return away_team_english_name;
	}

	public void setAway_team_english_name(String away_team_english_name) {
		this.away_team_english_name = away_team_english_name;
	}

	public int getHome_team_score() {
		return home_team_score;
	}

	public void setHome_team_score(int home_team_score) {
		this.home_team_score = home_team_score;
	}

	public int getAway_team_score() {
		return away_team_score;
	}

	public void setAway_team_score(int away_team_score) {
		this.away_team_score = away_team_score;
	}

	public long getStart_time() {
		return start_time;
	}

	public void setStart_time(long start_time) {
		this.start_time = start_time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


}
