package com.example.dell.sbi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.ByteArrayOutputStream;

import static com.example.dell.sbi.R.layout.activity_retrieve;

public class retrieve extends AppCompatActivity {
    ImageView imageView;
    Button button,ocrbutton;
    EditText editText;
    Bitmap bitimage,bitmap;
    TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_retrieve);
        imageView = (ImageView) findViewById(R.id.ImageView1);
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.edittext);
        ocrbutton = (Button) findViewById(R.id.ocrbutton);
        txtResult = (TextView) findViewById(R.id.txtResult);//http://apiplatformcloudse-gseapicssbisecond-uqlpluu8.srv.ravcloud.com:8001/ChequeInfo/695004887/900/CHEQUE_IMAGE
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String http = "http://apiplatformcloudse-gseapicssbisecond-uqlpluu8.srv.ravcloud.com:8001/ChequeInfo/695004887/"+editText.getText().toString()+"/CHEQUE_IMAGE";
                String Api_key ="40222dff-d253-47d0-8dd3-c918df0b8444";
                HttpCaller1 o = new HttpCaller1(http,Api_key);
                o.starter();
                String s = o.response.toString();
                Toast.makeText(getApplicationContext(),"=="+s,Toast.LENGTH_LONG).show();
                Log.e("check","=="+s);
//              byte[] array = s.getBytes();
                bitimage = BitmapFactory.decodeByteArray(o.response,0,o.response.length);
                imageView.setImageBitmap(bitimage);
            }
        });
        ocrbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
                if(!textRecognizer.isOperational())
                    Log.e("ERROR","Detector dependencies are not yet available");
                else {
                    Frame frame = new Frame.Builder().setBitmap(bitimage).build();
                    SparseArray<TextBlock> items = textRecognizer.detect(frame);
                    StringBuilder stringBuilder = new StringBuilder();
                    for(int i=0; i < items.size(); i++){
                        TextBlock item = items.valueAt(i);
                        stringBuilder.append(item.getValue());
                        stringBuilder.append("\n");


                    }
                    Toast.makeText(getApplicationContext(),stringBuilder.toString(),Toast.LENGTH_LONG).show();
                    txtResult.setText(stringBuilder.toString());

                }
            }
        });


    }
}
