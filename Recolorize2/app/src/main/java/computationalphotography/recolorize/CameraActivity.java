package computationalphotography.recolorize;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ViewAnimator;
import android.util.Log;
import android.view.SurfaceView;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CameraActivity extends Activity implements CameraBridgeViewBase.CvCameraViewListener2{
    private static String TAG="CameraActivity";
    JavaCameraView javaCameraView;
    Mat mRgba,imgGray;

    String s = "CAMERA";
    private static final int REQUEST_CAMERA = 0;

    /**
     * Id to identify a contacts permission request.
     */
    private static final int REQUEST_CONTACTS = 1;

    /**
     */
    private static String[] PERMISSIONS_CONTACT = {Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS};

    private boolean mLogShown;
    private View mLayout;

    BaseLoaderCallback mLoaderCallBack= new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch(status) {
                case BaseLoaderCallback.SUCCESS:
                    javaCameraView.enableView();
                    break;
                default:
                    super.onManagerConnected(status);
                    break;
            }
        }
    };

    static {
        System.loadLibrary("opencv_java");
        if(OpenCVLoader.initDebug()) {
            Log.i(TAG,"OpenCV Loaded suceessfully");
        } else {
            Log.i(TAG,"OpenCV not Loaded suceessfully");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        javaCameraView =(JavaCameraView)findViewById(R.id.java_camera_view);
        javaCameraView.setVisibility(SurfaceView.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(javaCameraView!=null) {
            javaCameraView.disableView();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(javaCameraView!=null) {
            javaCameraView.disableView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(OpenCVLoader.initDebug()) {
            Log.i(TAG,"OpenCV Loaded suceessfully");
            mLoaderCallBack.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        } else {
            Log.i(TAG,"OpenCV not Loaded suceessfully");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_13,this,mLoaderCallBack);
        }
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mRgba= new Mat(height,width,CvType.CV_8UC4);
        imgGray= new Mat(height,width,CvType.CV_8UC4);
    }

    @Override
    public void onCameraViewStopped() {
        mRgba.release();
        imgGray.release();
    }

    private Mat featuresVectorBinarization(Mat fv){
        int size = (int) fv.total() * fv.channels();
        double[] buff = new double[size];
        fv.get(0, 0, buff);

        for(int i = 0; i < size; i++) {
            buff[i] = (buff[i] >= 0) ? 1 : 0;
        }

        Mat bv = new Mat(fv.size(), CvType.CV_8U);
        bv.put(0, 0, buff);
        return bv;
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        Mat cameraframe=inputFrame.rgba();
        double [] pixelValue =cameraframe.get(0,0);
        double redChannel=pixelValue[0];
        double greenChannel=pixelValue[1];
        double blueChannel=pixelValue[2];

        mRgba=inputFrame.rgba();
        Imgproc.cvtColor(mRgba,imgGray, Imgproc.COLOR_RGB2Lab);
        return imgGray;
    }
}
