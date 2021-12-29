package com.example.cuahangthietbionline.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuahangthietbionline.R;
import com.example.cuahangthietbionline.activity.MainActivity;
import com.example.cuahangthietbionline.model.Giohang;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GiohangAdapter extends BaseAdapter {
    Context context;
    ArrayList<Giohang> arraygiohang;

    public GiohangAdapter(Context context, ArrayList<Giohang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }

    @Override
    public int getCount() {
        return arraygiohang.size();
    }

    @Override
    public Object getItem(int i) {
        return arraygiohang.get(i);
    }

    @Override
    public long getItemId(int i) {

        return i;
    }

    public class ViewHoler {
        public TextView txttengiohang, txtgiagiohang;
        public ImageView imggiohang;
        public Button btnminus, btnvalues, btnplus;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHoler viewHoler = null;
        if (view == null) {
            viewHoler = new ViewHoler();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_giohang, null);
            viewHoler.txttengiohang = (TextView) view.findViewById(R.id.textviewtengiohang);
            viewHoler.txtgiagiohang = (TextView) view.findViewById(R.id.textviewgiagiohang);
            viewHoler.imggiohang = view.findViewById(R.id.imgviewgiohang);
            viewHoler.btnminus = view.findViewById(R.id.buttomminus);
            viewHoler.btnvalues = view.findViewById(R.id.buttovalues);
            viewHoler.btnplus = view.findViewById(R.id.buttoplus);
            view.setTag(viewHoler);
        } else {
            viewHoler = (ViewHoler) view.getTag();
        }
        Giohang giohang = (Giohang) getItem(i);
        viewHoler.txttengiohang.setText(giohang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHoler.txtgiagiohang.setText(decimalFormat.format(giohang.getGiasp()) + "Đ");
        Picasso.get().load(giohang.getHinhsp())
                .placeholder(R.drawable.anhloi)
                .error(R.drawable.loading)
                .into(viewHoler.imggiohang);
        viewHoler.btnvalues.setText(giohang.getSoluongsp() + "");
        int sl = Integer.parseInt(viewHoler.btnvalues.getText().toString());
        if (sl >= 10) {
            viewHoler.btnplus.setVisibility(View.INVISIBLE);
            viewHoler.btnminus.setVisibility(View.VISIBLE);
        } else if (sl >= 1) {
            viewHoler.btnminus.setVisibility(View.VISIBLE);
            viewHoler.btnplus.setVisibility(View.VISIBLE);
        } else if (sl <= 1) {
            viewHoler.btnminus.setVisibility(View.INVISIBLE);
        }
        ViewHoler finalViewHoler = viewHoler;
        viewHoler.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(finalViewHoler.btnvalues.getText().toString()) + 1;
                int slht = MainActivity.manggiohang.get(i).getSoluongsp();
                long giaht = MainActivity.manggiohang.get(i).getGiasp();
                MainActivity.manggiohang.get(i).setSoluongsp(slmoinhat);
                long giamoinhat = (giaht * slmoinhat) / slht;
                MainActivity.manggiohang.get(i).setGiasp(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHoler.txtgiagiohang.setText(decimalFormat.format(giamoinhat) + "Đ");
                com.example.cuahangthietbionline.activity.Giohang.EventUltil();
                if (slmoinhat > 9) {
                    finalViewHoler.btnplus.setVisibility(View.INVISIBLE);
                    finalViewHoler.btnminus.setVisibility(View.VISIBLE);
                    finalViewHoler.btnvalues.setText(String.valueOf(slmoinhat));

                } else {
                    finalViewHoler.btnminus.setVisibility(View.VISIBLE);
                    finalViewHoler.btnplus.setVisibility(View.VISIBLE);
                    finalViewHoler.btnvalues.setText(String.valueOf(slmoinhat));
                }


            }
        });
        ViewHoler finalViewHoler1 = viewHoler;
        viewHoler.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(finalViewHoler1.btnvalues.getText().toString()) - 1;
                int slht = MainActivity.manggiohang.get(i).getSoluongsp();
                long giaht = MainActivity.manggiohang.get(i).getGiasp();
                MainActivity.manggiohang.get(i).setSoluongsp(slmoinhat);
                long giamoinhat = (giaht * slmoinhat) / slht;
                MainActivity.manggiohang.get(i).setGiasp(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHoler1.txtgiagiohang.setText(decimalFormat.format(giamoinhat) + "Đ");
                com.example.cuahangthietbionline.activity.Giohang.EventUltil();
                if (slmoinhat < 2) {
                    finalViewHoler1.btnminus.setVisibility(View.INVISIBLE);
                    finalViewHoler1.btnplus.setVisibility(View.VISIBLE);
                    finalViewHoler1.btnvalues.setText(String.valueOf(slmoinhat));

                } else {
                    finalViewHoler1.btnminus.setVisibility(View.VISIBLE);
                    finalViewHoler1.btnplus.setVisibility(View.VISIBLE);
                    finalViewHoler1.btnvalues.setText(String.valueOf(slmoinhat));
                }
            }
        });
        return view;
    }
}
