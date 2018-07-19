package com.boxoffice.entity;

import java.io.Serializable;

import com.baidu.mapapi.model.LatLng;

public class LocationData implements Serializable{
	private LatLng latlng;

	public LocationData(LatLng latlng){
		super();
		this.latlng = latlng;
	}

	public LatLng getLatlng() {
		return latlng;
	}

	public void setLatlng(LatLng latlng) {
		this.latlng = latlng;
	}
	
}
