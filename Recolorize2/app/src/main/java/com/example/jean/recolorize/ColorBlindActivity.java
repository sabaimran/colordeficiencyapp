package com.example.jean.recolorize;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Yannick on 17/04/2017.
 */

public class ColorBlindActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_blind);
    }

    public void startProtanopiaActivity(View view) {
        startCameraActivity(0);
    }

    public void startDeuteranopiaActivity(View view) {
        startCameraActivity(1);
    }

    public void startTritanopiaActivity(View view) {
        startCameraActivity(2);
    }

    public void startCameraActivity(int mode) {
        String name;
        switch (mode){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                String errorMessage = "--unknown--" + " is not implemented yet.";
                Toast.makeText(ColorBlindActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                break;
        }

        Intent colorBlindActivityIntent = new Intent(ColorBlindActivity.this,
                CameraActivity.class);
        startActivity(colorBlindActivityIntent);

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
