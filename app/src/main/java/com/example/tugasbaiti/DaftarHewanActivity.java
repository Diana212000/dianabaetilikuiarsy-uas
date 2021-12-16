package com.example.tugasbaiti;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import com.example.tugasbaiti.model.Angsa;
import com.example.tugasbaiti.model.Anjing;
import com.example.tugasbaiti.model.Hewan;
import com.example.tugasbaiti.model.Kucing;

public class DaftarHewanActivity extends AppCompatActivity {
    public final static String HEWAN_TERPILIH ="hewan_obj_key";
    List<Hewan> hewans;
    ListView listView;
    String jenisHewan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_hewan);
        Intent intent = getIntent();
        jenisHewan = intent.getStringExtra(MainActivity.JENIS_GALERI_KEY);
        hewans = DataProvider.getHewansByTipe(this,jenisHewan);
        // sesuaikan judul
        TextView txJudullist = findViewById(R.id.text_title_daftar);
        String judul="";
        if (hewans.get(0) instanceof Kucing) {
            judul = getString(R.string.kucing_list_title);
        }  else if (hewans.get(0) instanceof Anjing) {
            judul = getString(R.string.anjing_list_title);
        }   else if (hewans.get(0) instanceof Angsa) {
                judul = getString(R.string.angsa_list_title);
        }
        txJudullist.setText(judul);
        setupListview();
        }

    private void setupListview() {
            listView = findViewById(R.id.listview_daftar_hewan);
            DaftarHewanAdapter adapter = new DaftarHewanAdapter( this,hewans);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(onListClick);

        }

    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> adapterView, View view, int position,long l) {
             Hewan terpilih = hewans.get(position);
             bukaProfilhewan(terpilih);


        }
    };
    private void bukaProfilhewan(Hewan hewanTerpilih) {
            Log.d("MAIN","Buka activity galeri");
            Intent intent = new Intent(this,ProfilActivity.class);
            intent.putExtra(HEWAN_TERPILIH, hewanTerpilih);
            startActivity(intent);

        }


}