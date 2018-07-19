package com.boxoffice.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.boxoffice.R;
import com.boxoffice.fragment.BoxOfficeInfoFragment;
import com.boxoffice.fragment.CinemaNearbyFragment;

public class BoxOfficeActivity extends FragmentActivity {

	private List<Fragment> list=new ArrayList<Fragment>();
	private FragmentPagerAdapter adapter;
	private CinemaNearbyFragment cinemaNearbyFragment;
//	private ShowRecentlyFragment showRecentlyFragment;
	private BoxOfficeInfoFragment boxOfficeInfoFragment;
//	private PersonalInfoFragment personalInfoFragment;

	//����ؼ�
	private RadioGroup rg;
	private ViewPager viewPager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.box_office_activity);
		//��ʼ������
		init();
		//adapter
		setAdapter();
		//������ť����
		setListener();

	}
	/**
	 * ���viewpager����
	 */
	@SuppressWarnings("unused")
	private void setListenerViewPager(){
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {
				case 0:
					rg.check(R.id.rd_real_time);
					break;
				case 1:
					rg.check(R.id.rd_week);
					break;
				case 2:
					rg.check(R.id.rd_week_over);
					break;
				case 3:
					rg.check(R.id.rd_personal);
					break;
				}
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}
	/**
	 * ��Ӱ�ť����
	 */
	private void setListener(){
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rd_real_time:
					viewPager.setCurrentItem(0);
					break;

				case R.id.rd_week:
					viewPager.setCurrentItem(2);
					break;
				case R.id.rd_week_over:
					viewPager.setCurrentItem(1);
					break;
				case R.id.rd_personal:
					viewPager.setCurrentItem(3);
				}
			}
		});
	}

	/**
	 * Adapter
	 */
	private void setAdapter(){
		adapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
			@Override
			public int getCount() {
				return list.size();
			}
			@Override
			public Fragment getItem(int arg0) {
				return list.get(arg0);
			}
		};
		viewPager.setAdapter(adapter);
	}

	/*
	 * ��ʼ������
	 */
	private void init() {
		cinemaNearbyFragment=new CinemaNearbyFragment();
//		showRecentlyFragment=new ShowRecentlyFragment();
		boxOfficeInfoFragment=new BoxOfficeInfoFragment();
//		personalInfoFragment=new PersonalInfoFragment();
		list.add(boxOfficeInfoFragment);
		list.add(cinemaNearbyFragment);
//		list.add(showRecentlyFragment);
//		list.add(personalInfoFragment);
		rg=(RadioGroup) findViewById(R.id.rg_boxOffice);
		viewPager=(ViewPager) findViewById(R.id.vp);
	}

}
