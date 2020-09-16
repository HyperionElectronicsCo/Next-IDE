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
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.dengxiao.scroll_viewgroup_library.ScrollGroup;
import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.hyperion.nextide.MainActivity;

/**
 * Created by cjj on 2016/2/29.
 */
public class MainActivity extends Activity
{
    private static final String shareStr[] = {
        "MyApp1","MyApp2","MyApp3","MyApp4","MyApp5","MyApp6","MyApp7","NextIDE"
    };

 

    //LineNumberedEditText LineNumberedEditText;

    /** This list might be useful for initializing your hash set */
    public static final String[] reservedWords = { "abstract", "assert",
        "boolean", "break", "byte", "case", "catch", "char", "class",
        "const", "continue", "default", "do", "double", "else", "enum",
        "extends", "false", "final", "finally", "float", "for", "goto",
        "if", "implements", "import", "instanceof", "int", "interface",
        "long", "native", "new", "null", "package", "private", "protected",
        "public", "return", "short", "static", "strictfp", "super",
        "switch", "synchronized", "this", "throw", "throws", "transient",
        "true", "try", "void", "volatile", "while" };

    /** a reference to the main text field.  Init'd in onCreate */
    private EditText LineNumberedEditText = null;

    

    
    

    //  private static final int READ_EXTERNAL_STORAGE_PERMISSION_CODE = 0;

    // private static final int WRITE_EXTERNAL_STORAGE_PERMISSION_CODE = 0;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState); 
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
        
        if (ContextCompat. checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) 
            != PackageManager.PERMISSION_GRANTED)
        {
// permission is not granted
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1); 

		}
        setContentView(R.layout.main); 
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowTitleEnabled(false); 
        getActionBar().setHomeAsUpIndicator(R.drawable.image_11);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getActionBar().setElevation(0); 

        
        
        
        
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
        
      //  navigationTabStrip.setOnPageChangeListener(...);
      //  navigationTabStrip.setOnTabStripSelectedIndexListener(...);
        
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）

        ScrollGroup mScrollGroup = (ScrollGroup) findViewById(R.id.mScrollGroup);
        mScrollGroup.setHorizontalOrVertical(true)//设置滚动方向true:横向滚动 false: 纵向滚动
            .setStartEndScroll(true)//设置边缘是否可以滚动true: 可以滚动 false :不可以滚动
            .setScrollEdge(width/2)//设置滚动下一页标记
            .setDuration(1000)//设置滚动时间
            .setInvalidate();//设置重绘

        /* mScrollGroup.setOnPageChangeListener(new ScrollGroup.onPageChangeListener() {
         @Override
         public void onPageChange(int currentPage) {
         Toast.makeText(MainActivity.this, "第" + currentPage + "页", Toast.LENGTH_SHORT).show();
         }
         });*/
      
      
         
         
      
      
        Window window = this.getWindow();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(this.getResources().getColor(R.color.white));


        //check for permission
        /*    if(ContextCompat.checkSelfPermission(this,
         Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
         //ask for permission
         requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_PERMISSION_CODE);
         }
         //check for permission
         if(ContextCompat.checkSelfPermission(this,
         Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
         //ask for permission
         requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_PERMISSION_CODE);
         } */
         
         

        LineNumberedEditText = (LineNumberedEditText) findViewById(R.id.editor_area);
        //Setup the text watcher

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
// clear FLAG_TRANSLUCENT_STATUS flag:
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        dialog.getWindow().setStatusBarColor(this.getResources().getColor(R.color.white));


        BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) view.getParent());
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
                        mItemClickListener.onItemClick(position);
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
        // return true so that the menu pop up is opened
        return true; 
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.one)
        {
            // your code
            return true;
        }
        if (id == R.id.two)
        {
            // your code
            return true;
        }
        if (id == R.id.three)
        {
            // your code
            return true;
        }
        if (id == R.id.files)
        {
            // your code 
            Intent manager = new Intent(MainActivity.this, FileManager.class);
            //myIntent.putExtra("key", value); //Optional parameters
            MainActivity.this.startActivity(manager);
            LayoutParams params=new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams. FILL_PARENT);
            addContentView(manager , params);  
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            return true;
        }









//Tools offshoot 
        if (id == R.id.iconlib)
        {
            // your code 
            Intent myIntent = new Intent(MainActivity.this, IconPicker.class);
            //myIntent.putExtra("key", value); //Optional parameters
            MainActivity.this.startActivity(myIntent);
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);

            return true;
        }

        if (id == R.id.settings)
        {
            // your code 
            Intent myIntent = new Intent(MainActivity.this, SettingsActivity.class);
            //myIntent.putExtra("key", value); //Optional parameters
            MainActivity.this.startActivity(myIntent);
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);

            return true;
        }

        if (id == R.id.mergeproj)
        {
            // your code 
           // Intent myIntent = new Intent(MainActivity.this, SettingsActivity.class);
            //myIntent.putExtra("key", value); //Optional parameters
           // MainActivity.this.startActivity(myIntent); 
            ProjMergeDialog cdd=new ProjMergeDialog(this);
            cdd.show();
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);

            return true;
        } 
        
    /*    Button angryButton = (Button) findViewById(R.id.angry_btn);
        angryButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
// Click event trigger here
                }
            });
        Button angryButton2 = (Button) findViewById(R.id.angry_btn_2);
        angryButton2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
// Click event trigger here
                }
            });
        */
        
        
        
        
        
        
        
        if (id == R.id.exit)
        {
            // your code
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addContentView(Intent manager, WindowManager.LayoutParams params)
    {

    }
}

