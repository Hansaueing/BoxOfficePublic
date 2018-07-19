package com.boxoffice.adapter;

import java.util.List;

import com.boxoffice.R;
import com.boxoffice.entity.BoxOfficeInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RealTimeBOAdadapter extends BaseAdapter{

	private Context context;
	private List<BoxOfficeInfo> data;
	
	public RealTimeBOAdadapter(Context context, List<BoxOfficeInfo> data) {
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

	private class ViewHolder{
		TextView tvBoxOfficeToday;
		TextView tvBeOnDays;
		TextView tvMovieName;
		TextView tvBoxOfficeTotal;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BoxOfficeInfo info = data.get(position);
		ViewHolder holder;
		if(convertView==null){
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.boxoffice_item, parent,false);
			holder = new ViewHolder();
			holder.tvMovieName = (TextView) convertView.findViewById(R.id.tv_f1_moviename);
			holder.tvBoxOfficeToday = (TextView) convertView.findViewById(R.id.tv_f1_today_bo);
			holder.tvBoxOfficeTotal = (TextView) convertView.findViewById(R.id.tv_f1_sum_bo);
			holder.tvBeOnDays = (TextView) convertView.findViewById(R.id.tv_f1_days);
			convertView.setTag(holder);
		}else{
			holder= (ViewHolder) convertView.getTag();
		}
		
		holder.tvMovieName.setText(info.getMovieName());
		holder.tvBoxOfficeToday.setText("今天票房:"+info.getBoxOfficeToday()+"万");
		holder.tvBoxOfficeTotal.setText("总票房:"+info.getBoxOfficeTotal()+"万");
		holder.tvBeOnDays.setText("上映"+info.getBeOnDays()+"天");
		
		return convertView;
	}

}
