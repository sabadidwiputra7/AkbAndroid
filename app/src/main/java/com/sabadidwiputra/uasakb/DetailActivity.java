package com.sabadidwiputra.uasakb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sabadidwiputra.uasakb.Helper.RealmHelper;

import io.realm.Realm;
import io.realm.RealmConfiguration;
/**8-08-2019
 10116314
 Sabadi Dwiputra
 AKB7*/

public class DetailActivity extends AppCompatActivity {
    EditText etNim, etNama, etKelas, etTelepon, etEmail, etMedsos;
    Integer id;
    String nim, nama, kelas, telepon, email, medsos;
    Button btn_ubah, btn_hapus, btn_kembali;
    RealmHelper realmHelper;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // Set up
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        // Inisialisasi
        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        etKelas = findViewById(R.id.etKelas);
        etTelepon = findViewById(R.id.etTelepon);
        etEmail = findViewById(R.id.etEmail);
        etMedsos = findViewById(R.id.etMedsos);

        btn_ubah = findViewById(R.id.btnUpdate);
        btn_hapus = findViewById(R.id.btnHapus);
        btn_kembali = findViewById(R.id.btnCancel);

        id = getIntent().getIntExtra("id", 0);
        nim = getIntent().getStringExtra("nim");
        nama = getIntent().getStringExtra("nama");
        kelas = getIntent().getStringExtra("kelas");
        telepon = getIntent().getStringExtra("telepon");
        email = getIntent().getStringExtra("email");
        medsos = getIntent().getStringExtra("medsos");

        etNim.setText(nim);
        etNama.setText(nama);
        etKelas.setText(kelas);
        etTelepon.setText(telepon);
        etEmail.setText(email);
        etMedsos.setText(medsos);



        btn_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realmHelper.update(id, etNim.getText().toString(),etNama.getText().toString()
                        ,etKelas.getText().toString(),etTelepon.getText().toString(),etEmail.getText().toString()
                        ,etMedsos.getText().toString());
                Toast.makeText(DetailActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
                etNim.setText("");
                etNama.setText("");
                etKelas.setText("");
                etTelepon.setText("");
                etEmail.setText("");
                etMedsos.setText("");
                finish();
            }
        });
        btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realmHelper.delete(id);
                Toast.makeText(DetailActivity.this, "Delete Success", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btn_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
