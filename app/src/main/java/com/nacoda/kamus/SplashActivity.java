package com.nacoda.kamus;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.nacoda.kamus.db.KamusHelper;
import com.nacoda.kamus.model.Kamus;
import com.nacoda.kamus.prefs.AppPreference;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import butterknife.BindView;

public class SplashActivity extends AppCompatActivity {

    final String TAG = LoadKamus.class.getSimpleName();

    KamusHelper helper;
    AppPreference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        helper = new KamusHelper(SplashActivity.this);
        preference = new AppPreference(SplashActivity.this);
//
//        Boolean firstRun = preference.getFirstRun();
//        if (firstRun) {
//
//            ArrayList<Kamus> indonesia = preLoadRaw(getString(R.string.indonesia));
//            ArrayList<Kamus> english = preLoadRaw(getString(R.string.english));
//
//            helper.open();
//            helper.beginTransaction();
//
//            try {
//                for (Kamus model : indonesia) {
//                    helper.insertIndonesiaEnglish(model);
//                }
//                for (Kamus model : english) {
//                    helper.insertEnglishIndonesia(model);
//                }
//
//                helper.setTransactionSuccess();
//            } catch (Exception e) {
//                Log.e(TAG, "doInBackground: Exception");
//            }
//            helper.endTransaction();
//            helper.close();
//            preference.setFirstRun(false);
//        }

        new LoadKamus().execute();
    }

    private class LoadKamus extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            helper = new KamusHelper(SplashActivity.this);
            preference = new AppPreference(SplashActivity.this);
        }

        @Override
        protected Void doInBackground(Void... params) {
            Boolean firstRun = preference.getFirstRun();
            if (firstRun) {
                ArrayList<Kamus> indonesia = preLoadRaw(getString(R.string.indonesia));
                ArrayList<Kamus> english = preLoadRaw(getString(R.string.english));
                helper.open();
                helper.beginTransaction();
                try {
                    for (Kamus model : indonesia) {
                        helper.insertIndonesiaEnglish(model);
                    }
                    for (Kamus model : english) {
                        helper.insertEnglishIndonesia(model);
                    }

                    helper.setTransactionSuccess();
                } catch (Exception e) {
                    Log.e(TAG, "doInBackground: Exception");
                }
                helper.endTransaction();
                helper.close();
                preference.setFirstRun(false);
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(Void result) {
            Intent i = new Intent(SplashActivity.this, SearchActivity.class);
            startActivity(i);
            finish();
        }
    }

    public ArrayList<Kamus> preLoadRaw(String type) {
        ArrayList<Kamus> kamusModel = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_dict;

            if (type.equals(getString(R.string.english))) {
                raw_dict = res.openRawResource(R.raw.english_indonesia);
            } else if (type.equals(getString(R.string.indonesia))) {
                raw_dict = res.openRawResource(R.raw.indonesia_english);
            } else {
                raw_dict = null;
            }

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");

                Kamus kamus;

                kamus = new Kamus(splitstr[0], splitstr[1]);
                kamusModel.add(kamus);
                count++;
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kamusModel;
    }
}
