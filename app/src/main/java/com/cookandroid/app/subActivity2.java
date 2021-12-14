package com.cookandroid.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class subActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);
        setTitle("오늘 뭐 먹지?");
        Intent intent = getIntent();

        EditText editText_random = (EditText)findViewById(R.id.Et_random);
        EditText Et_add = (EditText)findViewById(R.id.Et_add);
        Button Menu_Add = (Button)findViewById(R.id.btn_add2);
        Button Random_button = (Button)findViewById(R.id.btn_random2);
        TextView txt_food = (TextView)findViewById(R.id.txt_food);

        HashMap<Integer,String> hashMap = new HashMap<Integer, String>();
        ArrayList<String> foodlist = new ArrayList<String>();
        foodlist.add("햄버거");
        foodlist.add("피자");
        foodlist.add("치킨");
        foodlist.add("족발");
        foodlist.add("샌드위치");
        foodlist.add("라면");
        foodlist.add("떡볶이");
        foodlist.add("김밥");
        foodlist.add("파스타");
        foodlist.add("돈까스");
        foodlist.add("생선까스");
        Collections.shuffle(foodlist); //foodlist의 목록들을 섞어준다
        for(int i = 0; i<foodlist.size(); i++) { //
            hashMap.put(i, foodlist.get(i));
            editText_random.setHint("1~"+foodlist.size()+"의 숫자를 입력하세요");
        }

        Menu_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String menu_add = Et_add.getText().toString();
                Et_add.setText(null);
                foodlist.add(menu_add);//입력한 메뉴 추가
                Collections.shuffle(foodlist);//입력한메뉴추가된 상태로 또 셔플시켜줌
                for(int i = 0; i<foodlist.size(); i++) {
                    hashMap.put(i, foodlist.get(i)); //입력한 메뉴에 키값부여
                    editText_random.setHint("1~"+foodlist.size()+"의 숫자를 입력하세요"); //추가된 매뉴의 개수포함 총 개수
                }
            }
        });

        Random_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int i = Integer.parseInt(editText_random.getText().toString()) - 1;//1부터입력이여서 -1 해줘야 0번인덱스에 있는 값부터 나옴
                if (i <= hashMap.size()-1) {
                    txt_food.setText(hashMap.get(i)); //텍스트듀에 입력된 key값의 value를 setText
                } else {
                    txt_food.setText("1~"+foodlist.size()+"의 숫자를 입력하세요!!");
                }
            }

        });
        Et_add.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    Menu_Add.setEnabled(true);
                    Menu_Add.setBackgroundColor(getResources().getColor(R.color.orange));
                } else {
                    Menu_Add.setEnabled(false);
                    Menu_Add.setBackgroundColor(Color.GRAY);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText_random.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    Random_button.setEnabled(true);
                    Random_button.setBackgroundColor(getResources().getColor(R.color.orange));
                } else {
                    Random_button.setEnabled(false);
                    Random_button.setBackgroundColor(Color.GRAY);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}