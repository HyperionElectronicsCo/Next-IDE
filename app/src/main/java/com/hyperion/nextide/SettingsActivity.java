package com.hyperion.nextide;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.hyperion.nextide.settings.AdvancedSettings;
import com.hyperion.nextide.settings.Application;
import com.hyperion.nextide.settings.Buildrun;
import com.hyperion.nextide.settings.Codestyle;
import com.hyperion.nextide.settings.Dropbox;
import com.hyperion.nextide.settings.Editor;
import com.hyperion.nextide.settings.Gsc;
import com.hyperion.nextide.settings.Keybindings;
import com.hyperion.nextide.settings.Legal;
import com.hyperion.nextide.settings.Syntax;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsActivity extends Activity 
{ 
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings); 
      //  getActionBar().setTitle("Settings");
        getActionBar().setDisplayShowTitleEnabled(true); 
      //  final ListView settingsLV = (ListView) findViewById(R.id.settingsListView);
        
      
        // Initializing a new String Array
        String[] settings = new String[] {
            "Application",
            "Editor",
            "Code Style",
            "Syntax",
            "Build & Run",
            "Dropbox",
            "Git Source Control",
            "Keybindings",
            "Advanced Settings",
            "Donate",
            "Legal"
        };
// Initializing a new String Array
        String[] hints = new String[] {
            "Configure global application settings.",
            "Configure the editor.",
            "Configure the code Style",
            "Syntax settings in the editor.",
            "Configure build & execution settings.",
            "Configure Dropbox settings.",
            "Configure Git Source Control settings.",
            "Configure Keybindings.",
            "Next-IDE Advanced Settings.",
            "Feed a hungry coder 😜 (£5)",
            "Show Legal Information."
        };
        List<Map<String, String>> listArray = new ArrayList<>();

        for(int i=0; i< settings.length; i++)
        {
            Map<String, String> listItem = new HashMap<>();
            listItem.put("titleKey", settings[i]);
            listItem.put("detailKey", hints[i]);
            listArray.add(listItem);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listArray,
                                                        android.R.layout.simple_list_item_2,
                                                        new String[] {"titleKey", "detailKey" },
                                                        new int[] {android.R.id.text1, android.R.id.text2 });

        ListView listView = findViewById(R.id.settingsListView);
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    switch( position )
                    {
                        case 0:  Intent newActivity = new Intent(SettingsActivity.this, Application.class);     
                            startActivity(newActivity);
                            break;
                        case 1:  Intent newActivity1 = new Intent(SettingsActivity.this, Editor.class);     
                            startActivity(newActivity1);
                            break;
                        case 2:  Intent newActivity2 = new Intent(SettingsActivity.this, Codestyle.class);     
                            startActivity(newActivity2);
                            break;
                        case 3:  Intent newActivity3 = new Intent(SettingsActivity.this, Syntax.class);     
                            startActivity(newActivity3);
                            break;
                        case 4:  Intent newActivity4 = new Intent(SettingsActivity.this, Buildrun.class);     
                            startActivity(newActivity4);
                            break;
                        case 5:  Intent newActivity5 = new Intent(SettingsActivity.this, Dropbox.class);     
                            startActivity(newActivity5);
                            break;
                        case 6:  Intent newActivity6 = new Intent(SettingsActivity.this, Gsc.class);     
                            startActivity(newActivity6);
                            break;
                        case 7:  Intent newActivity7 = new Intent(SettingsActivity.this, Keybindings.class);     
                            startActivity(newActivity7);
                            break;
                        case 8:  Intent newActivity8 = new Intent(SettingsActivity.this, AdvancedSettings.class);     
                            startActivity(newActivity8);
                            break;
                        case 9:  Uri uri = Uri.parse("https://www.paypal.com/paypalme/liquid8visuals/5"); // missing 'http://' will cause crashed
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            break;
                        case 10:  Intent newActivity10 = new Intent(SettingsActivity.this, Legal.class);     
                            startActivity(newActivity10); 
                            overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                            break;
                        
                    }
                }
        });
    }} 

