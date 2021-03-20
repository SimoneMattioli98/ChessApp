package com.example.chessapp;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import org.opencv.android.OpenCVLoader;

public class MainActivity extends AppCompatActivity {

    EditText e1, e2;
    TextView tv;
    Button btn;
    private static  String TAG = "MainActivity";

    static {
        if(OpenCVLoader.initDebug()){
            Log.d(TAG,"OpenCV is Configured or Connected successfully");
        }else{
            Log.d(TAG, "OpenCV not Working");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!Python.isStarted()){
            Python.start(new AndroidPlatform((this)));
        }
        e1 = (EditText) findViewById(R.id.firstNum);
        e2 = (EditText) findViewById(R.id.secondNum);
        tv = (TextView)findViewById(R.id.result);
        btn =(Button) findViewById(R.id.calculate);

        Python py = Python.getInstance();
        final PyObject pyObject = py.getModule("test");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PyObject obj = pyObject.callAttr("main", e1.getText().toString(), e2.getText().toString());
                tv.setText(obj.toString());
            }
        });
    }
}
