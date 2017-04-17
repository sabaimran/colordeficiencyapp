package computationalphotography.recolorize;

import android.app.Activity;
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

    public void startCatActivity(View view) {
        startCameraActivity(0);
    }

    public void startDogActivity(View view) {
        startCameraActivity(1);
    }

    public void startSnakeActivity(View view) {
        startCameraActivity(2);
    }

    public void startCameraActivity(int mode) {
        String name;
        switch (mode){
            case 0:
                name = "Cat Vision";
                break;
            case 1:
                name = "Dog Vision";
                break;
            case 2:
                name = "Snake Vision";
                break;
            default:
                name = "--unknown--";
                break;
        }

        String errorMessage = name + " is not implemented yet.";
        Toast.makeText(AnimalActivity.this, errorMessage, Toast.LENGTH_LONG).show();
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
