package com.luismiguelcotinez.formulariocedulajava;

import android.app.Application;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BDHelper extends Application {
    private DBHandler dbHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        dbHandler = new DBHandler(this);
    }

    public DBHandler getDBHandler() {
        return dbHandler;
    }
}
