package com.example.cuahangthietbionline.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuahangthietbionline.R;
import com.example.cuahangthietbionline.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LaptopAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> sanphamArrayList;

    public LaptopAdapter(Context context, ArrayList<Sanpham> sanphamArrayList) {
        this.context = context;
        this.sanphamArrayList = sanphamArrayList;
    }

    @Override
    public int getCount() {
        return sanphamArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return sanphamArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHoldel {

        public TextView txttenlaptop, txtgialaptop, txtmotalaptop ;
        public ImageView imglaptop;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoldel viewHoldel = null;
        if (convertView == null) {
            viewHoldel = new ViewHoldel();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_laptop, null);
            viewHoldel.txttenlaptop = (TextView) convertView.findViewById((R.id.textviewtenlaptop));
            viewHoldel.txtgialaptop = (TextView) convertView.findViewById((R.id.textviewgialaptop));
            viewHoldel.txtmotalaptop = (TextView) convertView.findViewById((R.id.textviewmotalaptop));
            viewHoldel.imglaptop = (ImageView) convertView.findViewById((R.id.imageviewlaptop));
            convertView.setTag(viewHoldel);
        }else {
            viewHoldel = (LaptopAdapter.ViewHoldel) convertView.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(position);
        viewHoldel.txttenlaptop.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHoldel.txtgialaptop.setText("Giá : " + decimalFormat.format(sanpham.getGiasanpham())+" Đ");
        viewHoldel.txtmotalaptop.setMaxLines(2);
        viewHoldel.txtmotalaptop.setEllipsize(TextUtils.TruncateAt.END);
        viewHoldel.txtmotalaptop.setText(sanpham.getMotasanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.loading)
                .error(R.drawable.anhloi)
                .into(viewHoldel.imglaptop);
        return convertView;
    }
}
