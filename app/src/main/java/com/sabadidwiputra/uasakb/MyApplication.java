package com.sabadidwiputra.uasakb;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
/**9-08-2019
 10116314
 Sabadi Dwiputra
 AKB7*/
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("mahasiswa.db")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
