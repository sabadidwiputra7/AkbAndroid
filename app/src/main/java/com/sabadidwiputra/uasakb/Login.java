package com.sabadidwiputra.uasakb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/**9-08-2019
 10116314
 Sabadi Dwiputra
 AKB7*/

public class Login extends AppCompatActivity {
    EditText etusername, etpassword;
    Button btnLogin, btnRegister;
    TextView txtTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etusername = (EditText) findViewById(R.id.etUsername);
        etpassword = (EditText) findViewById(R.id.etPassword);

        btnLogin = (Button) findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /* Mereset semua Error dan fokus menjadi default */
                etusername.setError(null);
                etpassword.setError(null);
                View focus = null;
                boolean cancel = false;

                /* Mengambil text dari form User dan form Password dengan variable baru bertipe String*/
                String username = etusername.getText().toString();
                String password = etpassword.getText().toString();

                /* Jika form user kosong atau TIDAK memenuhi kriteria di Method cekUser() maka, Set error
                 *  di Form User dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
                if (TextUtils.isEmpty(username)){
                    etusername.setError("harus diisi");
                    focus = etusername;
                    cancel = true;
                }else if(!cekUser(username)){
                    etusername.setError("username salah");
                    focus = etusername;
                    cancel = true;
                }

                /* Jika form password kosong dan MEMENUHI kriteria di Method cekPassword maka,
                 * Reaksinya sama dengan percabangan User di atas. Bedanya untuk Password dan Repassword*/
                if (TextUtils.isEmpty(password)){
                    etpassword.setError("harus diisi");
                    focus = etpassword;
                    cancel = true;
                }else if (!cekPassword(password)){
                    etpassword.setError("password salah");
                    focus = etpassword;
                    cancel = true;
                }

                /* Jika cancel true, variable fokus mendapatkan fokus */
                if (cancel) focus.requestFocus();
                else masuk();

            }
        });
        btnRegister = (Button) findViewById(R.id.btndaftar);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(Login.this, Register.class);
                startActivity(register);
            }
        });

    }


    /** ke MainActivity jika data Status Login dari Data Preferences bernilai true */
    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.getLoggedInStatus(getBaseContext())){
            startActivity(new Intent(getBaseContext(),MainActivity.class));
            finish();
        }
    }

    /** Menuju ke MainActivity dan Set User dan Status sedang login, di Preferences */
    private void masuk(){
        Preferences.setLoggedInUser(getBaseContext(),Preferences.getRegisteredUser(getBaseContext()));
        Preferences.setLoggedInUser(getBaseContext(),Preferences.getRegisteredNama(getBaseContext()));
        Preferences.setLoggedInStatus(getBaseContext(),true);
        startActivity(new Intent(getBaseContext(),MainActivity.class));finish();
    }


    /** True jika parameter password sama dengan data password yang terdaftar dari Preferences */
    private boolean cekPassword(String password){
        return password.equals(Preferences.getRegisteredPass(getBaseContext()));
    }

    /** True jika parameter user sama dengan data user yang terdaftar dari Preferences */
    private boolean cekUser(String user){
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }
}

