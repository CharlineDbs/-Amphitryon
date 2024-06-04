package com.example.amphitryon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import java.util.Locale;

import com.example.amphitryon.dto.Plat;

import java.util.List;

public class PlatsListAdapter extends BaseAdapter {

    private List<Plat> listePlats;
    private LayoutInflater layoutInflater;
    private Context context;

    public PlatsListAdapter(Context aContext, List<Plat> listePlats) {
        this.context = aContext;
        this.listePlats = listePlats;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listePlats.size();
    }

    @Override
    public Object getItem(int position) {

        return listePlats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_item_plat, null);
            holder = new ViewHolder();
            holder.nomView = convertView.findViewById(R.id.textView_nom);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Plat plat = this.listePlats.get(position);
        holder.nomView.setText(plat.getNomPlat());

        return convertView;
    }

    static class ViewHolder {
        TextView nomView;
    }
}