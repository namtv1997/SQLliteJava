package com.example.sonnv1368.quanlysinhvienad0618e;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class QuanLyLopActivity extends AppCompatActivity {
    //khai bao
    private EditText edMaLop;
    private EditText edTenLop;

    //lv voi database
    private DatabaseManager databaseManager;

    private ListView lvLop;
    private Cursor cursor; //bang
    private SimpleCursorAdapter adapter; //ket noi tung hang voi tung item

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_lop);

        //khoi tao
        edMaLop = findViewById(R.id.edMaLop);
        edTenLop = findViewById(R.id.edTenLop);
        lvLop = findViewById(R.id.lvLop);

        databaseManager = new DatabaseManager(this);
        hienThiLop();
    }

    public void themLop(View view) {
        //lay thong tin nhap vao
        String maLop = edMaLop.getText().toString().trim();
        String tenLop = edTenLop.getText().toString().trim();

        //kiem tra thong tin
        if (maLop.equals("") || tenLop.equals("")) {
            Toast.makeText(this, "Nhap thong tin!", Toast.LENGTH_SHORT).show();
        } else {
            //TODO insert vao database
            databaseManager.insertLop(maLop, tenLop);

            //TODO cap nhat lai danh sach khi thay doi
            hienThiLop();
        }
    }

    public void huyThemLop(View view) {
        //xoa trang noi dung de nhap moi
        edTenLop.setText("");
        edMaLop.setText("");
    }

    public void hienThiLop() {
        //lay cac lop trong bang lop
        cursor = databaseManager.selectLop();

        //cap nhat cho listview
        if (cursor != null) {
            //khai bao ten cac cot se lay ra
            String[] columns = {"_id", "maLop", "tenLop"};
            //kb id cac textview cua item
            int[] ids = {R.id.tvID, R.id.tvMaLop, R.id.tvTenLop};

            adapter = new SimpleCursorAdapter(this,
                    R.layout.item_lop,
                    cursor,
                    columns,
                    ids);
            lvLop.setAdapter(adapter);
        }
    }
}
