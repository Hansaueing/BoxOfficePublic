package com.boxoffice.entity;

/**
 * ������Ʊ����ʵ����
 * @author Han
 *			"name": "ħ����Ӱ",
            "weekPeriod": "2016-05-09��2016-05-15",
            "weekSum": 423
 */
public class WeekBO {
	private String name;
	private String weekPeriod;
	private String weekSum;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWeekPeriod() {
		return weekPeriod;
	}
	public void setWeekPeriod(String weekPeriod) {
		this.weekPeriod = weekPeriod;
	}
	public String getWeekSum() {
		return weekSum;
	}
	public void setWeekSum(String weekSum) {
		this.weekSum = weekSum;
	}
	

}
