package com.global.globalonline.adapter.HistoricalRecord;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.global.globalonline.R;
import com.global.globalonline.bean.xuNiBi.CoinsExtractRecordItemBean;
import com.global.globalonline.tools.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijl on 16/6/5.
 */
public class ZhuanChuVirtualFlowAdapter extends BaseAdapter {

    LayoutInflater layoutInflater = null;
    List<CoinsExtractRecordItemBean> list = new ArrayList<CoinsExtractRecordItemBean>();
    Activity activity;


    public ZhuanChuVirtualFlowAdapter(Activity activity , List<CoinsExtractRecordItemBean> list) {
        layoutInflater = LayoutInflater.from(activity);
        this.list = list;
        this.activity = activity;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Mandatory  viewHolder = null;
        if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.act_item_zhuanchu_xunibi_flow, null);
                viewHolder = new Mandatory();

                viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
                viewHolder.tv_number = (TextView) convertView.findViewById(R.id.tv_number);
                viewHolder.tv_feel = (TextView) convertView.findViewById(R.id.tv_feel);
                viewHolder.tv_dizhi = (TextView) convertView.findViewById(R.id.tv_dizhi);
                viewHolder.tv_status = (TextView) convertView.findViewById(R.id.tv_status);


                convertView.setTag(viewHolder);

        }else {
            viewHolder = (Mandatory)convertView.getTag();
        }
        CoinsExtractRecordItemBean con = list.get(position);
        viewHolder.tv_time.setText(DateUtils.getDateString(con.getTime()));
        viewHolder.tv_number.setText(con.getQuantity());
        viewHolder.tv_feel.setText(con.getProcedures());
        viewHolder.tv_dizhi.setText(con.getExtract_address());
        viewHolder.tv_status.setText(con.getStatus_name());

        return convertView;
    }



    /*存放控件 的ViewHolder*/
    public final class Mandatory {

        TextView tv_time;
        TextView tv_number;
        TextView tv_feel;
        TextView tv_dizhi;
        TextView tv_status;

    }
}
