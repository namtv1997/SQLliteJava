package com.example.sonnv1368.quanlysinhvienad0618e;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class QuanLySVActivity extends AppCompatActivity {
    private EditText edTenSV;
    private EditText edDiaChi;

    private Spinner spLop;
    private Cursor cursorLop;
    private SimpleCursorAdapter adapterLop;

    private ListView lvSV;
    private Cursor cursorSV;
    private SimpleCursorAdapter adapterSV;

    private DatabaseManager databaseManager;
    private String tenLop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_sv);

        edTenSV = findViewById(R.id.edTenSV);
        edDiaChi = findViewById(R.id.edDiaChi);
        spLop = findViewById(R.id.spLop);
        lvSV = findViewById(R.id.lvSV);

        databaseManager = new DatabaseManager(this);
        hienThiLop();

        hienSV();

        spLop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //lay hang ma dang ket noi voi item nay
                Cursor c = (Cursor) parent.getItemAtPosition(position);
                tenLop = c.getString(2);
                Toast.makeText(QuanLySVActivity.this, tenLop, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void themSV(View view) {
        //lay thong tin
        String tenSV = edTenSV.getText().toString().trim();
        String diaChi = edDiaChi.getText().toString().trim();

        //kiem tra thong tin
        if (tenSV.equals("") || diaChi.equals("")) {
            Toast.makeText(this, "Nhap thong tin!", Toast.LENGTH_SHORT).show();
        } else {
            //insert vao database
            databaseManager.insertSV(tenSV, diaChi, tenLop);

            //cap nhat lai danh sach sv
            hienSV();
        }
    }

    public void huyThemSV(View view) {
        edDiaChi.setText("");
        edTenSV.setText("");
    }

    private void hienThiLop() {
        cursorLop = databaseManager.selectLop();
        if (cursorLop != null) {
            String[] columns = {"tenLop"};
            //id cua textview hien thi cho item cua spinner
            int[] ids = {android.R.id.text1};
            adapterLop = new SimpleCursorAdapter(
                    this,
                    android.R.layout.simple_spinner_item,
                    cursorLop,
                    columns,
                    ids);
            spLop.setAdapter(adapterLop);
        }
    }

    private void hienSV() {
        cursorSV = databaseManager.selectSV();
        if (cursorSV != null) {
            String[] columns = {"_id", "tenSV", "diaChi", "tenLop"};
            int[] ids = {R.id.tvID, R.id.tvTenSV, R.id.tvDiaChi, R.id.tvTenLop};
            adapterSV = new SimpleCursorAdapter(this,
                    R.layout.item_sv,
                    cursorSV,
                    columns,
                    ids);
            lvSV.setAdapter(adapterSV);
        }
    }
}
