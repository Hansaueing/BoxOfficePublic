package com.boxoffice.entity;

/**
 * 封装周末票房的数据
 * @author Han
 *			"name": "奇幻森林",
            "weekendPeriod": "2016-05-13至2016-05-15",
            "weekendSum": 951
 */

public class WeekendBO {
	private String name;
	private String weekendPeriod;
	private String weekendSum;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWeekendPeriod() {
		return weekendPeriod;
	}
	public void setWeekendPeriod(String weekendPeriod) {
		this.weekendPeriod = weekendPeriod;
	}
	public String getWeekendSum() {
		return weekendSum;
	}
	public void setWeekendSum(String weekendSum) {
		this.weekendSum = weekendSum;
	}
	
}
