package com.example.fhernandez.webviewcustomfontsexample;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private WebView myBowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        myBowser = (WebView) findViewById(R.id.myBowser);

        // Load our page
        loadData();


    }

    private void loadData() {

        // read the file
        String myPage = "";
        try {
            myPage = getStringFromFile(this, R.raw.our_page);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Complete the page
        myPage = myPage + "This is a part added from code <b>awesome!</b></body></html>";

        //myBowser.loadData(myPage, "text/html; charset=UTF-8", null);
        myBowser.loadDataWithBaseURL("file:///android_asset/", myPage, "text/html", "utf-8",null);
    }

    // Read from res/raw
    public String getStringFromFile (Context context, int resId) throws IOException {

        String result;
        Resources res = context.getResources();
        InputStream in_s = res.openRawResource(resId);

        byte[] b = new byte[in_s.available()];
        in_s.read(b);
        result = new String(b);

        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
