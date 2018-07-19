package com.boxoffice.fragment;

import java.io.Serializable;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.utils.DistanceUtil;
import com.boxoffice.R;
import com.boxoffice.activity.PoiResultActivity;

public class CinemaNearbyFragment extends Fragment implements OnClickListener,OnMapClickListener {

	private MapView mMapView = null;
	private BaiduMap mBaiduMap;
	private LocationClient client;
	private BDLocationListener listener = new BDLocationListener();
	private LocationMode mode;
	private BitmapDescriptor marker;
	private MyLocationData locData;
	private LatLng point;
	boolean isFirstLoc = true;
	// 检索功能控件
	private Button btnSearch;
	private PoiSearch poiSearch;
	// maker监听
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = View.inflate(getActivity(), R.layout.fragment_map, null);
		btnSearch = (Button) v.findViewById(R.id.btn_search);
		btnSearch.setOnClickListener(this);
		// 地图初始化
		mMapView = (MapView) v.findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		// 定位
		mode = LocationMode.NORMAL;
		marker = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
		mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(mode,
				true, marker));

		mBaiduMap.setMyLocationEnabled(true);

		client = new LocationClient(getActivity().getApplicationContext());
		client.registerLocationListener(listener);

		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(com.baidu.location.LocationClientOption.LocationMode.Hight_Accuracy);
		option.setOpenGps(true); // 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000);
		option.setIsNeedAddress(true);
		option.setLocationNotify(true);

		client.setLocOption(option);
		client.start();
		// 添加地图标记点击监听
		mBaiduMap.setOnMapClickListener(this);

		return v;
	}

	public class BDLocationListener implements
			com.baidu.location.BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null || mMapView == null) {
				return;
			}
			locData = new MyLocationData.Builder()
					.accuracy(location.getRadius()).direction(0)
					.latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			mBaiduMap.setMyLocationData(locData);

			// mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
			// mode, true, marker));
			// mBaiduMap.clear();

			// 定义Maker坐标点
			point = new LatLng(locData.latitude, locData.longitude);
			// 构建Marker图标
			// BitmapDescriptor bitmap =
			// BitmapDescriptorFactory.fromResource(R.drawable.icon_geo);
			// 构建MarkerOption，用于在地图上添加Marker
			// OverlayOptions options = new MarkerOptions()
			// .position(point)
			// .icon(marker);
			// 在地图上添加Marker，并显示
			// mBaiduMap.addOverlay(options);

			if (isFirstLoc) {
				isFirstLoc = false;
				//LatLng ll = new LatLng(location.getLatitude(),location.getLongitude());
				MapStatus.Builder builder = new MapStatus.Builder();
				builder.target(point).zoom(17.0f);
				mBaiduMap.animateMapStatus(MapStatusUpdateFactory
						.newMapStatus(builder.build()));
			}
		}

	}

	@Override
	public void onDestroy() {
		// 停止定位
		client.stop();
		// 关闭定位图层
		mBaiduMap.setMyLocationEnabled(false);
		mMapView.onDestroy();
		mMapView = null;
		super.onDestroy();

	}

	@Override
	public void onResume() {
		mMapView.onResume();
		super.onResume();

	}

	@Override
	public void onPause() {
		mMapView.onPause();
		super.onPause();

	}
	

	private List<PoiInfo> results;

	@Override
	public void onClick(View v) {
		Log.i("text", "click button");
		poiSearch = PoiSearch.newInstance();

		// poiSearch.searchInCity(new
		// PoiCitySearchOption().city("成都").keyword("电影院"));
		poiSearch.searchNearby(new PoiNearbySearchOption().location(point).keyword("电影院").radius(10000));

		poiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {

					@Override
					public void onGetPoiResult(PoiResult poiResult) {
						Log.i("text", "onGetPoiResult");
						Log.i("text", "total item = "+ poiResult.getAllPoi().size());
						results = poiResult.getAllPoi();

						String poiname = poiResult.getAllPoi().get(0).name;
						String poiadd = poiResult.getAllPoi().get(0).address;
						String idString = poiResult.getAllPoi().get(0).uid;

						for (int i = 0; i < poiResult.getAllPoi().size(); i++) {
							// 获取位置集合的经纬度信息
							LatLng jwd = poiResult.getAllPoi().get(i).location;
							// 构建Marker图标
							BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_geo);
							// 构建MarkerOption，用于在地图上添加Marker
							OverlayOptions option = new MarkerOptions().position(jwd).icon(bitmap);
							// 在地图上添加Marker，并显示
							mBaiduMap.addOverlay(option);
							// 重置缩放比
							MapStatus.Builder builder = new MapStatus.Builder();
							builder.target(point).zoom(16.0f);
							mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
							// 给标记位置添加监听
							
							
							
						}

						Log.i("text", "地点:" + poiname + ",详细地址:" + poiadd
								+ ",id编号:" + idString);

//						Intent intent = new Intent(getActivity(),
//								PoiResultActivity.class);
//						intent.putExtra("poiResult", (Serializable) results);
//						Log.i("text", "trans activity");
						// startActivity(intent);

					}

					@Override
					public void onGetPoiIndoorResult(PoiIndoorResult arg0) {
						Log.i("text", "onGetPoiIndoorResult");
					}

					@Override
					public void onGetPoiDetailResult(PoiDetailResult arg0) {
						Log.i("text", "onGetPoiDetailResult");
					}
				});
	}

	@Override
	public void onMapClick(LatLng arg0) {
		Log.i("han", System.currentTimeMillis()+"	click a position");
		if( results != null){
			for(int n=0; n<results.size(); n++){
				double distanceClick = DistanceUtil.getDistance(arg0, results.get(n).location);
				double distance = DistanceUtil.getDistance(point, results.get(n).location);
				if(distanceClick<300.0){
					Log.i("han",System.currentTimeMillis()+ "	click maked position");
					Log.i("han", "n="+n);
					Intent intent = new Intent(getActivity(),PoiResultActivity.class);
					intent.putExtra("poiResult", (Serializable) results);
					intent.putExtra("position", n);
					intent.putExtra("distance", distance);
//					LocationData locationData = new LocationData(point);
//					intent.putExtra("point", (Serializable)locationData);
					Log.i("text", "trans activity");
					startActivity(intent);
					break;
				}else{
					Log.i("han",System.currentTimeMillis()+ "	click unmaked position");
				}
			}
			Log.i("han", "===============");
		}
		
	}

	@Override
	public boolean onMapPoiClick(MapPoi arg0) {
		// TODO Auto-generated method stub
		return true;
	}





}
