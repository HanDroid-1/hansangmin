package com.cookandroid.app;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    static SQLiteDatabase database;
    static String tableName;

    public static ActivityResultLauncher<Intent> launcher;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("오늘 뭐 먹지?");
        Button btn_random = (Button)findViewById(R.id.btn_random);
        Button btn1 = (Button)findViewById(R.id.button);



        dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();
        tableName = DatabaseHelper.TABLE_FOOD_INFO;

        ArrayList<MainData> arrayList = new ArrayList<>();

        Cursor cursor = database.rawQuery("select DATE, CONTENT, IMAGE from " + tableName, null );
        for(int i = 0; i <cursor.getCount(); i++){
            cursor.moveToNext();
            String name = cursor.getString(0);
            String food = cursor.getString(1);

            MainData info = new MainData(name,food);
            arrayList.add(info);
        }
        cursor.close();
       // arrayList.add(new MainData("리스트1","짜장면")); //임시데이터추가

        MainAdapter mainAdapter = new MainAdapter(arrayList);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mainAdapter);



        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
        result -> {
            if(result.getResultCode() == Activity.RESULT_OK){
                Intent data = result.getData();
                String name = data.getStringExtra("name");
                String food = data.getStringExtra("food");
                int position = data.getIntExtra("position",0);

                //데이터베이스 삽입
                if(position == arrayList.size()){
                    mainAdapter.addItem(new MainData(name,food));
                    try {
                        String INSERT_SQL = "INSERT INTO " + tableName +
                                " VALUES ( "

                                + "'" + name + "',"
                                + "'" + food
                                + "')";
                        database.execSQL(INSERT_SQL);
                        Log.d("DatabaseHelper","insert db");
                    } catch (Exception e){
                        Log.e("DatabaseHelper","INSERT ERROR");
                    }



                }


            }

        });

        btn_random.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(),subActivity2.class);
               startActivity(intent);
           }
       });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),subActivity.class);
                intent.putExtra("name","");
                intent.putExtra("food","");
                intent.putExtra("position",arrayList.size());
                launcher.launch(intent);

            }
        });

        ArrayList<String> items = new ArrayList<String>() ;
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items) ;




    }
}