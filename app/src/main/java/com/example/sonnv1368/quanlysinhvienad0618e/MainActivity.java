package com.example.sonnv1368.quanlysinhvienad0618e;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void quanLyLop(View view) {
        Intent intent = new Intent(this, QuanLyLopActivity.class);
        startActivity(intent);
    }

    public void quanLySV(View view) {
        Intent intent = new Intent(this, QuanLySVActivity.class);
        startActivity(intent);
    }
}
