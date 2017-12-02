package com.example.dell.sbi;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;

import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.DatePicker;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;


public class insert extends AppCompatActivity {
    Button bcamera,btnsubmit;
    ImageView imageView1,im;
    //EditText date;
    String key="40222dff-d253-47d0-8dd3-c918df0b8444";
    String t_id="695004887";
    String mime_type="image/jpeg";
    String image11;
    String urls="http://apiplatformcloudse-gseapicssbisecond-uqlpluu8.srv.ravcloud.com:8001/InsertChqDetails";
    String tt1,tt2,tt3,tt4,tt5,tt6,tt7,tt8,tt9,tt10;
    String ss1;
    EditText cheque_no,amt_words,amt_digit,micr,act_type,san_no,bank_name,date,act_no;
    RadioButton rb11,rb22;
    String amt_match="y", cheque_stale="", encoding="",  img_size="50";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        im = (ImageView) findViewById(R.id.IImageView1);
        bcamera = (Button) findViewById(R.id.btncamera);
        btnsubmit = (Button) findViewById(R.id.text11);
        imageView1 = (ImageView) findViewById(R.id.IImageView);
        cheque_no = (EditText)findViewById(R.id.text1);
        amt_words = (EditText)findViewById(R.id.text2);
        amt_digit = (EditText)findViewById(R.id.text3);
        micr = (EditText)findViewById(R.id.text4);

        act_type = (EditText)findViewById(R.id.text6);
        san_no= (EditText)findViewById(R.id.text7);
        bank_name = (EditText)findViewById(R.id.text8);
        act_no = (EditText)findViewById(R.id.text9);
        rb11 = (RadioButton)findViewById(R.id.rb1);
        rb22 = (RadioButton)findViewById(R.id.rb2);
        date = (EditText) findViewById(R.id.date);



        btnsubmit .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tt1 = cheque_no.getText().toString();
                tt2 = amt_words.getText().toString();
                tt3 = amt_digit.getText().toString();
                tt4 = micr.getText().toString();
                tt5 = act_type.getText().toString();
                tt6 = san_no.getText().toString();
                tt7 = bank_name.getText().toString();
                tt8 = act_no.getText().toString();
                // encoding=image11;

                tt10=date.getText().toString();
//                Toast.makeText(getApplicationContext(),tt10,Toast.LENGTH_LONG).show();
                //   Toast.makeText(getApplicationContext(),tt1+tt2+tt3+tt4+tt5+tt6+tt7+tt8,Toast.LENGTH_SHORT).show();
                // Toast.makeText(getApplicationContext(),ss1,Toast.LENGTH_LONG).show();
                HttpCaller caller=new HttpCaller(urls,key, t_id, tt1,mime_type,tt2,tt3,tt10,tt4,tt5,tt6,tt7,tt8,amt_match,ss1,encoding,img_size,image11);
                caller.starter();
                Toast.makeText(getApplicationContext(),caller.response.toString(),Toast.LENGTH_LONG).show();
                Log.e("Check","===="+caller.response.toString());
                Log.e("Check",image11);
            }
        });






        // perform click event on edit text
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(insert.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });



        bcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rb1:
                if (checked)
                    // Pirates are the best
                    ss1="Y";
                break;
            case R.id.rb2:
                if (checked)
                    // Ninjas rule
                    ss1="N";
                break;
        }
    }


    public void onActivityResult(int requestcode, int resultcode, Intent data) {

        super.onActivityResult(requestcode, resultcode, data);

        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        imageView1.setVisibility(View.VISIBLE);
        imageView1.setImageBitmap(bitmap);
        image11 =bitmapToBase64(bitmap);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        im.setImageBitmap(bmp);
        Toast.makeText(getApplicationContext(),image11,Toast.LENGTH_LONG).show();

    }



    //find the string of the image
//    public static String BitmapToString(Bitmap bitmap) {
//        try {
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//            byte[] b = baos.toByteArray();
//            String temp = Base64.encodeToString(b, Base64.DEFAULT);
//            return temp;
//        } catch (NullPointerException e) {
//            return null;
//        } catch (OutOfMemoryError e) {
//            return null;
//        }
//    }
    private String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        StringBuilder sb = new StringBuilder();
        for (byte by : byteArray)
            sb.append(Integer.toBinaryString(by & 0xFF));

        return sb.toString();
    }




}




