package com.example.jack.myshopping.user.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jack.myshopping.R;
import com.example.jack.myshopping.user.Activity.MessageActivity;
import com.example.jack.myshopping.user.bean.MessageBean;

import java.util.List;

public class MesAdapter extends BaseAdapter {
    private final List<MessageBean> messageBeans;
    private final MessageActivity mContent;
    private MesViewHolder holder;
    private static final String TAG = "MesAdapter";

    public MesAdapter(MessageActivity messageActivity, List<MessageBean> messageBeans) {
        this.mContent = messageActivity;
        this.messageBeans = messageBeans;
    }

    @Override
    public int getCount() {
        return messageBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return messageBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContent).inflate(R.layout.mess_item, null);
            holder = new MesViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MesViewHolder) convertView.getTag();
        }
        holder.mesTitle.setText(messageBeans.get(position).getTitle());
        holder.mesUrlText.setText("内容："+messageBeans.get(position).getMes() +" ,\n链接： "+ messageBeans.get(position).getUrl());
        holder.mesDate.setText(messageBeans.get(position).getDate());

        return convertView;
    }

    public class MesViewHolder {

        private final TextView mesUrlText;
        private final TextView mesDate;
        private final TextView mesTitle;

        public MesViewHolder(View convertView) {
            mesTitle = convertView.findViewById(R.id.mes_title);
            mesUrlText = convertView.findViewById(R.id.mes_url_text);
            mesDate = convertView.findViewById(R.id.mes_date);
        }
    }
}
