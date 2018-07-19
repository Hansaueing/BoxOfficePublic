package com.boxoffice.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.boxoffice.R;
import com.boxoffice.entity.WeekendBO;

public class WeekendsBOAdadapter extends BaseAdapter {

	private Context context;
	private List<WeekendBO> data;

	public WeekendsBOAdadapter(Context context, List<WeekendBO> data) {
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
		TextView tvWeekendSum;
		TextView tvMovieName;
		TextView tvWeekendPeriod;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		WeekendBO info = data.get(position);
		ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.boxoffice_item, parent,
					false);
			holder = new ViewHolder();
			holder.tvMovieName = (TextView) convertView
					.findViewById(R.id.tv_f1_moviename);
			holder.tvWeekendSum = (TextView) convertView
					.findViewById(R.id.tv_f1_today_bo);
			holder.tvWeekendPeriod = (TextView) convertView
					.findViewById(R.id.tv_f1_sum_bo);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.tvMovieName.setText(info.getName());
		holder.tvWeekendSum.setText("本周票房:" + info.getWeekendSum() + "	万");
		holder.tvWeekendPeriod.setText("统计日期:" + info.getWeekendPeriod());

		return convertView;
	}

}
