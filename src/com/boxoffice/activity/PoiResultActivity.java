package com.boxoffice.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.utils.DistanceUtil;
import com.boxoffice.R;
import com.boxoffice.entity.LocationData;

public class PoiResultActivity extends Activity implements OnClickListener{
	
	private TextView tvCinemaName,tvAddress,tvTel,tvDistance;
	private ImageView ivGo;
	
	private List<PoiInfo> results;
	private int position;
	private double distance;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_poiinfo);
		results = (List<PoiInfo>) getIntent().getSerializableExtra("poiResult");
		position = getIntent().getIntExtra("position", 0);
		distance = getIntent().getDoubleExtra("distance", 0);
		Log.i("text", "PoiResultActivity results position="+position);
		Log.i("text", "PoiResultActivity results.size()="+results.size());
		
		init();
		loadContent();
		
		ivGo.setOnClickListener(this);
		
	}
	private void loadContent() {
		tvCinemaName.setText("影院名称:"+results.get(position).name);
		tvAddress.setText("详细地址:"+results.get(position).address);
		tvTel.setText("联系方式:"+results.get(position).phoneNum);
		tvDistance.setText("距离本地:"+String.valueOf((int)distance)+"米");
		
	}
	private void init() {
		tvCinemaName = (TextView) findViewById(R.id.tv_cinema_name);
		tvAddress = (TextView) findViewById(R.id.tv_cinema_address);
		tvTel = (TextView) findViewById(R.id.tv_cinema_tel);
		tvDistance = (TextView) findViewById(R.id.tv_distance);
		
		
		ivGo = (ImageView) findViewById(R.id.iv_go);
		
	}
	@Override
	public void onClick(View v) {
		Log.i("han", "去这里");
		
	}
}
