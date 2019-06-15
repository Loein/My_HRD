package com.example.myhrd;

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

    public void first_game(View view) {
        Intent intent = new Intent(this, My_game.class);
        intent.putExtra("type","0");
        startActivity(intent);
    }

    public void second_game(View view) {
        Intent intent = new Intent(this, My_game.class);
        intent.putExtra("type","1");
        startActivity(intent);
    }

    public void third_game(View view) {
        Intent intent = new Intent(this, My_game.class);
        intent.putExtra("type","2");
        startActivity(intent);
    }

    public void fourth_game(View view) {
        Intent intent = new Intent(this, My_game.class);
        intent.putExtra("type","3");
        startActivity(intent);
    }
}
