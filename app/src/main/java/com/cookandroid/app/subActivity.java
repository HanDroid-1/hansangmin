package com.cookandroid.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class subActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        setTitle("메뉴 리스트");
        EditText editText_listname = (EditText)findViewById(R.id.editText_listname);
        EditText editText_food = (EditText)findViewById(R.id.editText);
        Button btn_Add = (Button)findViewById(R.id.btn_Add);
        Button btn_remove = (Button)findViewById(R.id.btn_remove);
        Button btn_listAdd = (Button)findViewById(R.id.btn_listAdd);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String food = intent.getStringExtra("food");
        int position = intent.getIntExtra("position", 0);

        editText_listname.setText(name);
        editText_food.setText(food);



       ArrayList<String> items = new ArrayList<String>() ;
       ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, items) ;
        // ArrayAdapter 생성. 아이템 View를 선택(multiple choice)가능하도록 만듦

        // listview 생성 및 adapter 지정.
        ListView listview = (ListView) findViewById(R.id.listview1) ;
        listview.setAdapter(adapter) ;

        //items.add("햄버거");




        //(TextWatcher : edittext안의 값의 변화 감지)editText안에 값이 있을때
        editText_food.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0 && editText_listname.getText().length() > 0 && editText_listname.getText().toString() != "") {
                    btn_Add.setEnabled(true);
                    btn_Add.setBackgroundColor(getResources().getColor(R.color.orange));
                } else {
                    btn_Add.setEnabled(false);
                    btn_Add.setBackgroundColor(Color.GRAY);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) { //값을 넣어야만 ADD버튼 활성화되게 설정

            }
        });
        editText_listname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    if(s.length()>0){
                        editText_food.setEnabled(true);
                    }else{
                        editText_food.setEnabled(false);
                    }
            }
        });
        btn_listAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _name = editText_listname.getText().toString();
                String _food = editText_food.getText().toString();

                Intent outintent = new Intent(getApplicationContext(), MainActivity.class);
                outintent.putExtra("name",_name);
                outintent.putExtra("food",_food);
                outintent.putExtra("position",position);
                setResult(RESULT_OK, outintent);
                finish();


            }
        });

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String menuAdd = editText_food.getText().toString(); //editText에 있는값 받아와서 items에 추가
                    items.add(menuAdd);
                    adapter.notifyDataSetChanged();
                    editText_food.setText(null);

            }
        });

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray checkedItems = listview.getCheckedItemPositions(); //다중선택된 아이템들을 체크값을 식별하기위한 객체
                int count = adapter.getCount();
                for (int i = count-1; i >= 0; i--) {
                    if (checkedItems.get(i)) { //체크값이 true면
                        items.remove(i) ; //해당값에 위치한 값 삭제
                    }
                }
                // 모든 선택 상태 초기화.
                listview.clearChoices() ; //선택된값들을 초기화 해줘야 다시 아이템을 추가해도 체크가 초기화됨
                adapter.notifyDataSetChanged();
            }
        });









    }
}