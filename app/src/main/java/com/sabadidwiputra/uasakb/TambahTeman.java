package com.sabadidwiputra.uasakb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.sabadidwiputra.uasakb.Helper.RealmHelper;
import com.sabadidwiputra.uasakb.Model.MahasiswaModel;

import io.realm.Realm;
import io.realm.RealmConfiguration;
/**11-08-2019
 10116314
 Sabadi Dwiputra
 AKB7*/
public class TambahTeman extends AppCompatActivity {

    Button btnSimpan, btnTampil;
    EditText nim, nama, kelas, telepon, email, medsos;
    String sNama, sNim, sKelas, sTelepon, sEmail, sMedsos;
    Realm realm;
    RealmHelper realmHelper;
    MahasiswaModel mahasiswaModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahteman);

        //Inisialisasi
        btnSimpan = findViewById(R.id.btnSubmit);
        btnTampil = findViewById(R.id.btnTampil);
        nim = findViewById(R.id.nimmhs);
        nama = findViewById(R.id.namamhs);
        kelas = findViewById(R.id.kelas);
        telepon = findViewById(R.id.telepon);
        email = findViewById(R.id.email);
        medsos = findViewById(R.id.medsos);

        //Set up Realm
        Realm.init(TambahTeman.this);
        //clear alldata
        //.deleteRealmIfMigrationNeeded()
        RealmConfiguration configuration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        realm = Realm.getInstance(configuration);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sNim = nim.getText().toString();
                sNama = nama.getText().toString();
                sKelas = kelas.getText().toString();
                sTelepon = telepon.getText().toString();
                sEmail = email.getText().toString();
                sMedsos = medsos.getText().toString();

                if (!sNim.equals("") && !sNama.isEmpty()){
                    mahasiswaModel = new MahasiswaModel();
                    mahasiswaModel.setNim(sNim);
                    mahasiswaModel.setNama(sNama);
                    mahasiswaModel.setKelas(sKelas);
                    mahasiswaModel.setTelepon(sTelepon);
                    mahasiswaModel.setEmail(sEmail);
                    mahasiswaModel.setMedsos(sMedsos);

                    realmHelper = new RealmHelper(realm);
                    realmHelper.save(mahasiswaModel);

                    Toast.makeText(TambahTeman.this, "Berhasil Disimpan!", Toast.LENGTH_SHORT).show();

                    nim.setText("");
                    nama.setText("");
                    kelas.setText("");
                    telepon.setText("");
                    email.setText("");
                    medsos.setText("");
                }else {
                    Toast.makeText(TambahTeman.this, "Terdapat inputan yang kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnTampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}
