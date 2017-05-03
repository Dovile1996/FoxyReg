package com.example.dovile.foxy;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class SecondActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
    }
    public void Search (View v){
        Intent intent = new Intent (SecondActivity.this, Search.class);
        startActivity(intent);
    }

    public void NewPost (View v){
        Intent intent = new Intent (SecondActivity.this, NewPost.class);
        startActivity(intent);
    }

}
