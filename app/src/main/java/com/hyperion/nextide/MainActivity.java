package com.hyperion.nextide;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

// AndroidX Libraries များသို့ ပြောင်းလဲမွမ်းမံထားပါသည်
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import com.dengxiao.scroll_viewgroup_library.ScrollGroup;
import com.gigamole.navigationtabstrip.NavigationTabStrip;

public class MainActivity extends Activity
{
    private static final String shareStr[] = {
        "MyApp1","MyApp2","MyApp3","MyApp4","MyApp5","MyApp6","MyApp7","NextIDE"
    };

    public static final String[] reservedWords = { "abstract", "assert",
        "boolean", "break", "byte", "case", "catch", "char", "class",
        "const", "continue", "default", "do", "double", "else", "enum",
        "extends", "false", "final", "finally", "float", "for", "goto",
        "if", "implements", "import", "instanceof", "int", "interface",
        "long", "native", "new", "null", "package", "private", "protected",
        "public", "return", "short", "static", "strictfp", "super",
        "switch", "synchronized", "this", "throw", "throws", "transient",
        "true", "try", "void", "volatile", "while" };

    private EditText LineNumberedEditText = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState); 
        
        // Battery Optimization Ignore Request
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent();
            String packageName = getPackageName();
            PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + packageName)); 
                startActivity(intent);
            }
        }
        
        // Storage Permission စနစ်ကို ခေတ်မီအောင် ပြင်ဆင်ခြင်း
        checkStoragePermissions();
        
        setContentView(R.layout.main); 
        
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setDisplayShowTitleEnabled(false); 
            getActionBar().setHomeAsUpIndicator(R.drawable.image_11);
            getActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getActionBar().setElevation(0); 
        }

        final NavigationTabStrip navigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts);
        navigationTabStrip.setTitles("main.xml", "main.xml", "main.xml", "main.xml", "main.xml", "main.xml");
        navigationTabStrip.setTabIndex(0, true);
        navigationTabStrip.setTitleSize(30);
        navigationTabStrip.setStripColor(Color.parseColor("#0197F6"));
        navigationTabStrip.setStripWeight(12);
        navigationTabStrip.setStripFactor(2);
        navigationTabStrip.setStripType(NavigationTabStrip.StripType.LINE);
        navigationTabStrip.setStripGravity(NavigationTabStrip.StripGravity.BOTTOM);
        navigationTabStrip.setTypeface("fonts/typeface.ttf");
        navigationTabStrip.setCornersRadius(3);
        navigationTabStrip.setAnimationDuration(300);
        navigationTabStrip.setInactiveColor(Color.BLUE);
        navigationTabStrip.setActiveColor(Color.BLACK); 
        
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     

        ScrollGroup mScrollGroup = (ScrollGroup) findViewById(R.id.mScrollGroup);
        mScrollGroup.setHorizontalOrVertical(true)
            .setStartEndScroll(true)
            .setScrollEdge(width/2)
            .setDuration(1000)
            .setInvalidate();
      
        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));

        LineNumberedEditText = (EditText) findViewById(R.id.editor_area);
        SyntaxTextWatcher watcher = new SyntaxTextWatcher();
        LineNumberedEditText.addTextChangedListener(watcher); 

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                { 
                    showBSDialog(); 
                }
            });
    }

    // Android 11+ ဖုန်းများတွင်ပါ ဖိုင်ပတ်လမ်းကြောင်းများ အလုပ်လုပ်စေရန် ပြင်ဆင်ထားသော စနစ်
    private void checkStoragePermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                try {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.setData(Uri.parse(String.format("package:%s", getPackageName())));
                    startActivity(intent);
                } catch (Exception e) {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                    startActivity(intent);
                }
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) 
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) 
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE, 
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, 1);
            }
        }
    }

    private void showBSDialog()
    {
        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.bs_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); 

        SimpleStringRecyclerViewAdapter adapter = new SimpleStringRecyclerViewAdapter(this);
        adapter.setItemClickListener(new SimpleStringRecyclerViewAdapter.ItemClickListener() {
                @Override
                public void onItemClick(int pos)
                {
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this, "MyApp" + (pos + 1) + " " + "Folder", Toast.LENGTH_LONG).show();
                }
            });
        recyclerView.setAdapter(adapter);
        dialog.setContentView(view); 
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);  
        dialog.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        dialog.getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));

        BottomSheetBehavior<View> mBehavior = BottomSheetBehavior.from((View) view.getParent());
        mBehavior.setPeekHeight(1400);

        dialog.show(); 
    }

    public static class SimpleStringRecyclerViewAdapter
    extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder>
    {
        public ItemClickListener mItemClickListener;

        public void setItemClickListener(ItemClickListener listener)
        {
            mItemClickListener = listener;
        }

        public interface ItemClickListener
        {
            public void onItemClick(int pos); 
        }

        private Context mContext;

        public static class ViewHolder extends RecyclerView.ViewHolder
        {
            public final ImageView mImageView;
            public final TextView mTextView;

            public ViewHolder(View view)
            {
                super(view);
                mImageView = (ImageView) view.findViewById(R.id.avatar);
                mTextView = (TextView) view.findViewById(R.id.tv);
            }
        }

        public SimpleStringRecyclerViewAdapter(Context context)
        {
            super();
            mContext = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position)
        {
            holder.mTextView.setText(shareStr[position]);
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        if (mItemClickListener != null) {
                            mItemClickListener.onItemClick(position);
                        }
                    }
                });
        }

        @Override
        public int getItemCount()
        {
            return shareStr.length;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true; 
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.one || id == R.id.two || id == R.id.three)
        {
            return true;
        }
        if (id == R.id.files)
        {
            Intent manager = new Intent(MainActivity.this, FileManager.class);
            MainActivity.this.startActivity(manager);
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            return true;
        }
        if (id == R.id.iconlib)
        {
            Intent myIntent = new Intent(MainActivity.this, IconPicker.class);
            MainActivity.this.startActivity(myIntent);
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            return true;
        }
        if (id == R.id.settings)
        {
            Intent myIntent = new Intent(MainActivity.this, SettingsActivity.class);
            MainActivity.this.startActivity(myIntent);
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            return true;
        }
        if (id == R.id.mergeproj)
        {
            ProjMergeDialog cdd = new ProjMergeDialog(this);
            cdd.show();
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            return true;
        } 
        if (id == R.id.exit)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
