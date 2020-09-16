package com.hyperion.nextide.settings;

import android.app.Activity;
import android.os.Bundle;
import com.hyperion.nextide.R;

public class Codestyle extends Activity 
{ 
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.codestyle); 
        //  getActionBar().setTitle("Settings");
        getActionBar().setDisplayShowTitleEnabled(true); 




    }
} 
