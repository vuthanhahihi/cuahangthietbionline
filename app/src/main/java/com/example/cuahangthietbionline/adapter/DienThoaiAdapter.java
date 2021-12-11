package com.example.cuahangthietbionline.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.TextureView;
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

public class DienThoaiAdapter extends BaseAdapter {

    Context context;
    ArrayList<Sanpham> sanphamArrayList;

    public DienThoaiAdapter(Context context, ArrayList<Sanpham> sanphamArrayList) {
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

        public TextView txtdienthoai, txtgia, txtmota;
        public ImageView imgdienthoai;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoldel viewHoldel = null;
        if (convertView == null) {
            viewHoldel = new ViewHoldel();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_dienthoai, null);
            viewHoldel.txtdienthoai = (TextView) convertView.findViewById((R.id.textviewdienthoai));
            viewHoldel.txtgia = (TextView) convertView.findViewById((R.id.textviewgiadienthoai));
            viewHoldel.txtmota = (TextView) convertView.findViewById((R.id.textviewmotadienthoai));
            viewHoldel.imgdienthoai = (ImageView) convertView.findViewById((R.id.imageviewdienthoai));
            convertView.setTag(viewHoldel);
        }else {
            viewHoldel = (ViewHoldel) convertView.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(position);
        viewHoldel.txtdienthoai.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHoldel.txtgia.setText("Giá : " + decimalFormat.format(sanpham.getGiasanpham())+" Đ");
        viewHoldel.txtmota.setMaxLines(2);
        viewHoldel.txtmota.setEllipsize(TextUtils.TruncateAt.END);
        viewHoldel.txtmota.setText(sanpham.getMotasanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.loading)
                .error(R.drawable.anhloi)
                .into(viewHoldel.imgdienthoai);
        return convertView;
    }
}
