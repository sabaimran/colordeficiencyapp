package computationalphotography.recolorize;

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

public class AnimalActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);
    }

    public void startBumblebeeActivity(View view) {
        startCameraActivity(0);
    }

    public void startDeerActivity(View view) {
        startCameraActivity(1);
    }

    public void startBirdActivity(View view) {
        startCameraActivity(2);
    }

    public void startDragonActivity(View view) {
        startCameraActivity(3);
    }

    public void startCameraActivity(int mode) {
        Intent cameraActivityIntent = new Intent(AnimalActivity.this,
                CameraActivity.class);
        cameraActivityIntent.putExtra("mode", "animal");

        switch (mode){
            case 0:
                // Bumblebee
                break;
            case 1:
                // Deer
                break;
            case 2:
                // Bird
                break;
            case 3:
                // Dragon
                break;
            default:
                String errorMessage = "--unknown-- is not implemented.";
                Toast.makeText(AnimalActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                break;
        }

        startActivity(cameraActivityIntent);
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
