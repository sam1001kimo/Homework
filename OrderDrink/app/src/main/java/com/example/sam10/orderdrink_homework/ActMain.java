package com.example.sam10.orderdrink_homework;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ActMain extends AppCompatActivity {

    public static String number;
    public static String select = "紅茶20元";
    public static int count;
    public static int countall;
    public static String str;
    ArrayList numberlist = new ArrayList();
    ArrayList selectlist = new ArrayList();
    ArrayList countlist = new ArrayList();
    ArrayList mList = new ArrayList();
    String[] first = new String[]{"暫無資料"};
    ArrayAdapter adapter;
    ArrayAdapter adapter2;


    private AdapterView.OnItemSelectedListener spSelect_select = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            select = spSelect.getSelectedItem().toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            select = "紅茶20元";
        }
    };


    private NumberPicker.OnValueChangeListener numberPicker_change = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            etNumber.setText(String.valueOf(newVal));
            number = etNumber.getText().toString();
        }
    };


    private View.OnClickListener btnAdd_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            if (etNumber.getText().toString().equals("")) {
                Toast.makeText(ActMain.this, "請輸入數量", Toast.LENGTH_SHORT).show();

            } else {
                select = spSelect.getSelectedItem().toString();
                selectlist.add(select);
                numberlist.add(etNumber.getText().toString());
                switch (select) {
                    case "紅茶20元":
                        count = 20 * Integer.parseInt(etNumber.getText().toString());
                        break;
                    case "綠茶20元":
                        count = 20 * Integer.parseInt(etNumber.getText().toString());
                        break;
                    case "烏龍茶20元":
                        count = 20 * Integer.parseInt(etNumber.getText().toString());
                        break;
                    case "花茶35元":
                        count = 35 * Integer.parseInt(etNumber.getText().toString());
                        break;
                    case "奶茶35元":
                        count = 35 * Integer.parseInt(etNumber.getText().toString());
                        break;
                    default:
                        break;
                }
                countlist.add(count);
                str = select + " x " + etNumber.getText().toString() + "杯 = " + String.valueOf(count) + "元";
                mList.add(str);
                lvList.setAdapter(adapter2);
                countall = 0;
                for (int i = 0; i < countlist.size(); i++) {
                    countall += (int) countlist.get(i);
                }
                tvAll.setText("總價:" + String.valueOf(countall) + "元");
            }
        }
    };


    private View.OnClickListener btnCheckout_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(countlist.size()==0){
                Toast t= Toast.makeText(ActMain.this, "您還沒有下訂單", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER,0,0);
                t.show();

            }else {


            selectlist.clear();
            numberlist.clear();
            countlist.clear();
            mList.clear();
            number = "";
            select = "紅茶20元";
            count = 0;
            etNumber.setText("");
            lvList.setAdapter(adapter);
            tvAll.setText("");
            Toast t=Toast.makeText(ActMain.this, "結帳完成", Toast.LENGTH_SHORT);
            t.setGravity(Gravity.CENTER,0,0);
            t.show();
            }
        }
    };


    private AdapterView.OnItemClickListener lvList_click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


            AlertDialog.Builder dialog = new AlertDialog.Builder(ActMain.this);
            dialog.setTitle("刪除");
            dialog.setMessage("是否確定刪除此項目");
            dialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    selectlist.remove(position);
                    numberlist.remove(position);
                    countlist.remove(position);
                    mList.remove(position);
                    countall = 0;
                    for (int i = 0; i < countlist.size(); i++) {
                        countall += (int) countlist.get(i);
                    }

                    if (countlist.size() == 0) {
                        tvAll.setText("");
                        lvList.setAdapter(adapter);
                    } else {
                        tvAll.setText("總價:" + String.valueOf(countall) + "元");
                        lvList.setAdapter(adapter2);
                    }
                }
            });
            Dialog message = dialog.create();
            message.show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actmain);
        InitialComponent();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, first);
        adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mList);
        lvList.setAdapter(adapter);

        tvAll.setText(etNumber.getText().toString());
    }

    private void InitialComponent() {
        spSelect = findViewById(R.id.spSelect);
        spSelect.setOnItemSelectedListener(spSelect_select);
        etNumber = findViewById(R.id.etNumber);
        numberPicker = findViewById(R.id.numberPicker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(200);
        numberPicker.setValue(0);
        numberPicker.setOnValueChangedListener(numberPicker_change);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(btnAdd_click);
        btnCheckout = findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(btnCheckout_click);
        lvList = findViewById(R.id.lvList);
        lvList.setOnItemClickListener(lvList_click);
        tvAll = findViewById(R.id.tvAll);

    }


    Spinner spSelect;
    EditText etNumber;
    NumberPicker numberPicker;
    Button btnAdd;
    Button btnCheckout;
    ListView lvList;
    TextView tvAll;

}
