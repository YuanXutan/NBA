package cn.edu.zjicm.nba.model;

import java.util.List;

public class Group {

	private String name = null;		//分组名
	private List<Team> teams = null;	//球队列表

	public Group(String name, List<Team> teams) {
		this.name = name;
		this.teams = teams;
	}

	public Group() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

}
