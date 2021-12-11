package com.example.cuahangthietbionline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.ImageView;
import android.widget.ListView;

import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangthietbionline.R;
import com.example.cuahangthietbionline.adapter.LoaispAdapter;
import com.example.cuahangthietbionline.adapter.SanphamAdapter;
import com.example.cuahangthietbionline.model.Loaisp;
import com.example.cuahangthietbionline.model.Sanpham;
import com.example.cuahangthietbionline.ultil.CheckConnection;
import com.example.cuahangthietbionline.ultil.Server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerviewmanhinhchinh;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    ArrayList<Loaisp> mangloaisp;
    ArrayList<Sanpham> mangsanpham;
    SanphamAdapter sanphamAdapter;
    LoaispAdapter loaispAdapter;
    int id = 0;
    String tenloaisp = "";
    String hinhanhloaisp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            ActionBar();
            ActionViewFlipper();
            GetDuLieuLoaisp();
            GetDuLieuSPMoiNhat();
            CatchOnItemListView();
        } else {
            CheckConnection.showToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
            finish();
        }


    }

    private void CatchOnItemListView() {
        listViewmanhinhchinh.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        CheckConnection.showToast_Short(getApplicationContext(), "Kiểm tra lại kết nối");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case 1:
                    if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                        Intent intent = new Intent(MainActivity.this, DienThoaiActivity.class);
                        intent.putExtra("idloaisanpham", mangloaisp.get(position).getId());
                        startActivity(intent);
                    } else {
                        CheckConnection.showToast_Short(getApplicationContext(), "Kiểm tra lại kết nối");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case 2:
                    if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                        Intent intent = new Intent(MainActivity.this, LapTopActivity.class);
                        intent.putExtra("idloaisanpham", mangloaisp.get(position).getId());
                        startActivity(intent);
                    } else {
                        CheckConnection.showToast_Short(getApplicationContext(), "Kiểm tra lại kết nối");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case 3:
                    if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                        Intent intent = new Intent(MainActivity.this, LienHeActivity.class);
                        startActivity(intent);
                    } else {
                        CheckConnection.showToast_Short(getApplicationContext(), "Kiểm tra lại kết nối");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case 4:
                    if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                        Intent intent = new Intent(MainActivity.this, ThongTinActivity.class);
                        startActivity(intent);
                    } else {
                        CheckConnection.showToast_Short(getApplicationContext(), "Kiểm tra lại kết nối");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
            }
        });
    }

    private void GetDuLieuSPMoiNhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duongdansanphammoinhat, response -> {
            if (response != null) {
                int ID = 0;
                String Tensanpham = "";
                Integer Giasanpham = 0;
                String Hinhanhsanpham = "";
                String Motasanpham = "";
                int IDsanpham = 0;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        ID = jsonObject.getInt("id");
                        Tensanpham = jsonObject.getString("tensp");
                        Giasanpham = jsonObject.getInt("giasp");
                        Hinhanhsanpham = jsonObject.getString("hinhanhsp");
                        Motasanpham = jsonObject.getString("motasp");
                        IDsanpham = jsonObject.getInt("idsanpham");

                        mangsanpham.add(new Sanpham(ID, Tensanpham, Giasanpham, Hinhanhsanpham, Motasanpham, IDsanpham));
                        sanphamAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, error -> {

        });
        requestQueue.add(jsonArrayRequest);
    }

    private void GetDuLieuLoaisp() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duongdanloaisp, response -> {
            if (response != null) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        id = jsonObject.getInt("id");
                        tenloaisp = jsonObject.getString("tenloaisp");
                        hinhanhloaisp = jsonObject.getString("hinhanhloaisp");
                        mangloaisp.add(new Loaisp(id, tenloaisp, hinhanhloaisp));
                        loaispAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mangloaisp.add(3, new Loaisp(0, "Liên hệ", "https://voip24h.vn/wp-content/uploads/2019/03/phone-png-mb-phone-icon-256.png"));
                mangloaisp.add(4, new Loaisp(0, "Thông tin", "https://png.pngtree.com/png-vector/20190830/ourlarge/pngtree-user-info-icon-png-image_1708293.jpg"));

            }
        }, error -> CheckConnection.showToast_Short(getApplicationContext(), error.toString()));
        requestQueue.add(jsonArrayRequest);

    }

    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://cdn.tgdd.vn/Files/2015/08/05/679589/hybrids.jpg");
        mangquangcao.add("https://kienthucmoi.net/img/2019/10/08/dpi-to.jpg");
        mangquangcao.add("https://www.tncstore.vn/image/catalog/BAI%20VIET/th%C3%B4ng%20s%E1%BB%91%20b%C3%A0n%20ph%C3%ADm%20c%C6%A1/razer-blackwidow-x-chroma-group-1_1024x1024%20(1).jpg");
        mangquangcao.add("https://amoi.tech/wp-content/uploads/2021/01/Tai-nghe-gaming-PC-co-Mic-LED-AMOI-PC68-Tai-nghe-chuyen-game-Tai-nghe-sieu-bass-jack-3.5-cong-USB-co-den-LED-mic-dung-cho-PCLaptop-Goodshop4u-22.jpg");
        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);


        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void Anhxa() {
        toolbar = (Toolbar) findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        recyclerviewmanhinhchinh = (RecyclerView) findViewById(R.id.recyclerview);
        navigationView = (NavigationView) findViewById(R.id.navigationview);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        listViewmanhinhchinh = (ListView) findViewById(R.id.listviewmanhinhchinh);
        mangloaisp = new ArrayList<>();
        mangloaisp.add(0, new Loaisp(0, "Trang chính", "https://cdn.pixabay.com/photo/2015/12/28/02/58/home-1110868_960_720.png"));
        loaispAdapter = new LoaispAdapter(mangloaisp, getApplicationContext());
        listViewmanhinhchinh.setAdapter(loaispAdapter);

        mangsanpham = new ArrayList<>();
        sanphamAdapter = new SanphamAdapter(getApplicationContext(), mangsanpham);
        recyclerviewmanhinhchinh.setHasFixedSize(true);
        recyclerviewmanhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerviewmanhinhchinh.setAdapter(sanphamAdapter);
    }
}