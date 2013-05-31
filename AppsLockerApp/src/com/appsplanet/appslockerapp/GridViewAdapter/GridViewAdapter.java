package com.appsplanet.appslockerapp.GridViewAdapter;

import java.util.ArrayList;
import java.util.List;

import com.appsplanet.appslockerapp.R;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewAdapter extends BaseAdapter {
	private LayoutInflater mLayoutInflater;
	private ArrayList<ResolveInfo> mResolveInfos;
	private Context context;

	public GridViewAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		init();
	}

	public void init() {
		mLayoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void setData(ArrayList<ResolveInfo> couponList) {
		mResolveInfos = new ArrayList<ResolveInfo>();
		this.mResolveInfos.addAll(couponList);
	}

	@Override
	public int getCount() {

		if (mResolveInfos != null && mResolveInfos.size() > 0) {
			return mResolveInfos.size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mResolveInfos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		PackageManager pm = context.getPackageManager();
		ViewHolder viewHolder;
		View view = convertView;
		ResolveInfo info = (ResolveInfo) getItem(position);
		if (view == null) {
			view = mLayoutInflater.inflate(R.layout.applicationlist_item, null);

			viewHolder = new ViewHolder();
			viewHolder.applicationIcon = (ImageView) view
					.findViewById(R.id.imgApplicationIcon);
			viewHolder.applicationTitle = (TextView) view
					.findViewById(R.id.txtApplicationName);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();

		}

		viewHolder.applicationIcon.setImageDrawable(info.loadIcon(pm));
		viewHolder.applicationTitle.setText(info.loadLabel(pm));
		view.setTag(viewHolder);

		return view;
	}
}
