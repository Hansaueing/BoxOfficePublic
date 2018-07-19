package com.boxoffice.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.boxoffice.R;
import com.boxoffice.entity.WeekBO;

public class WeekBOAdadapter extends BaseAdapter {

	private Context context;
	private List<WeekBO> data;

	public WeekBOAdadapter(Context context, List<WeekBO> data) {
		super();
		this.context = context;
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	private class ViewHolder {
		TextView tvWeekSum;
		TextView tvMovieName;
		TextView tvWeekPeriod;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		WeekBO info = data.get(position);
		ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.boxoffice_item, parent,
					false);
			holder = new ViewHolder();
			holder.tvMovieName = (TextView) convertView
					.findViewById(R.id.tv_f1_moviename);
			holder.tvWeekSum = (TextView) convertView
					.findViewById(R.id.tv_f1_today_bo);
			holder.tvWeekPeriod = (TextView) convertView
					.findViewById(R.id.tv_f1_sum_bo);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvMovieName.setText(info.getName());
		holder.tvWeekSum.setText("周末票房:" + info.getWeekSum() + "	万");
		holder.tvWeekPeriod.setText("统计日期:" + info.getWeekPeriod());

		return convertView;
	}

}
