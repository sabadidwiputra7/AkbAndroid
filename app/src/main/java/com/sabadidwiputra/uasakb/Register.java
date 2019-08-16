package com.sabadidwiputra.uasakb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
/**11-08-2019
 10116314
 Sabadi Dwiputra
 AKB7*/
public class Register extends AppCompatActivity {
    EditText etnama, etusername, etpassword;
    Button btnSimpan, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etnama = (EditText) findViewById(R.id.etNama);
        etusername = (EditText) findViewById(R.id.etUsername);
        etpassword = (EditText) findViewById(R.id.etPassword);


        btnSimpan = (Button) findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regis();
            }
        });
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void regis(){
        etnama.setError(null);
        etusername.setError(null);
        etpassword.setError(null);
        View fokus = null;
        boolean cancel = false;

        /* Mengambil text dari Form User, Password, Repassword dengan variable baru bertipe String*/
        String nama = etnama.getText().toString();
        String username = etusername.getText().toString();
        String password = etpassword.getText().toString();

        /* Jika form user kosong atau MEMENUHI kriteria di Method cekUser() maka, Set error di Form-
         * User dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
        if (TextUtils.isEmpty(nama)){
            etnama.setError("This field is required");
            fokus = etnama;
            cancel = true;
        }

        if (TextUtils.isEmpty(username)){
            etusername.setError("This field is required");
            fokus = etusername;
            cancel = true;
        }else if(cekUser(username)){
            etusername.setError("This Username is already exist");
            fokus = etusername;
            cancel = true;
        }

        /* Jika form password kosong dan MEMENUHI kriteria di Method cekPassword maka,
         * Reaksinya sama dengan percabangan User di atas. Bedanya untuk Password dan Repassword*/
        if (TextUtils.isEmpty(password)){
            etpassword.setError("This field is required");
            fokus = etpassword;
            cancel = true;
        }

        /** Jika cancel true, variable fokus mendapatkan fokus. Jika false, maka
         *  Kembali ke LoginActivity dan Set User dan Password untuk data yang terdaftar */
        if (cancel){
            fokus.requestFocus();
        }else{
            Preferences.setRegisteredNama(getBaseContext(),nama);
            Preferences.setRegisteredUser(getBaseContext(),username);
            Preferences.setRegisteredPass(getBaseContext(),password);
            Intent login = new Intent(this, Login.class);
            startActivity(login);
            finish();
        }


    }
    /** True jika parameter user sama dengan data user yang terdaftar dari Preferences */
    private boolean cekUser(String user){
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }
}
