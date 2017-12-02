package com.example.dell.sbi;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by DELL on 11/8/2017.
 */

public class HttpCaller implements Runnable{
    public StringBuffer response=new StringBuffer();
    public String urls="";
    public String api_key="";
    public String TEAM_ID="";
    public String CHQ_NUM= "";
    public String MIME_TYPE="";
    public String AMOUNT_WORDS="";
    public String AMOUNT_DIGIT="";
    public String CHQ_DATE="";
    public String MICR_CODE="";
    public String ACT_TYPE="";
    public String SAN_NO="";
    public String BEN_NAME ="";
    public String PAYEE_AC_NO="";
    public String AMT_MATCH="";
    public String CHQ_STALE="";
    public String ENCODING="";
    public String IMG_SIZE="";
    public String image11="";
    // HttpCaller c=new HttpCaller();
    public HttpCaller(String Urls,String Api_key, String team_id, String chq_num,String mime_type,String amount_words,String amount_digit,String chq_date,String micr_code,String act_type,String san_no,String ben_name,String payee_ac_no,String amt_match,String chq_stale,String encoding,String img_size,String image11) {
     /*   this.urls = Urls;
        this.api_key = Api_key;
        this.TEAM_ID = team_id;
        this.CHQ_NUM = chq_num;
        this.MIME_TYPE = mime_type;
        this.AMOUNT_WORDS = amount_words;
        this.AMOUNT_DIGIT = amount_digit;
        this.CHQ_DATE = chq_date;
        this.MICR_CODE = micr_code;
        this.ACT_TYPE = act_type;
        this.SAN_NO = san_no;
        this.BEN_NAME = ben_name;
        this.PAYEE_AC_NO = payee_ac_no;
        this.AMT_MATCH = amt_match;
        this.CHQ_STALE = chq_stale;
        this.ENCODING = encoding;
        this.IMG_SIZE = img_size;
        this.image11=image11; */

        this.urls = Urls;
        this.api_key = Api_key;
        this.TEAM_ID = team_id;
        this.CHQ_NUM = chq_num;
        this.MIME_TYPE = mime_type;
        this.AMOUNT_WORDS = "";
        this.AMOUNT_DIGIT = "";
        this.CHQ_DATE = "";
        this.MICR_CODE = "";
        this.ACT_TYPE = "";
        this.SAN_NO = "";
        this.BEN_NAME = "";
        this.PAYEE_AC_NO = "";
        this.AMT_MATCH = "";
        this.CHQ_STALE = "";
        this.ENCODING = "";
        this.IMG_SIZE = "";
        this.image11=image11;


    }
    Context context;
    HttpCaller(Context context)
    {
        this.context=context;
    }
    @Override
    public void run()
    {
        byte[] data = this.image11.getBytes(Charset.forName("UTF-8"));
        int data_length = data.length;
        URL url=null;
        HttpURLConnection urlConnection = null;
        try
        {
            url = new URL(this.urls);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("api-key", api_key);
            urlConnection.setRequestProperty("TEAM_ID", TEAM_ID);
            urlConnection.setRequestProperty("CHQ_NUM", CHQ_NUM);
            urlConnection.setRequestProperty("MIME_TYPE", MIME_TYPE);
            urlConnection.setRequestProperty("AMOUNT_WORDS", AMOUNT_WORDS);
            urlConnection.setRequestProperty("AMOUNT_DIGIT", AMOUNT_DIGIT);
            urlConnection.setRequestProperty("CHQ_DATE", null);
            urlConnection.setRequestProperty("MICR_CODE", MICR_CODE);
            urlConnection.setRequestProperty("ACT_TYPE", ACT_TYPE);
            urlConnection.setRequestProperty("SAN_NO", SAN_NO);
            urlConnection.setRequestProperty("BEN_NAME", BEN_NAME);
            urlConnection.setRequestProperty("PAYEE_AC_NO", PAYEE_AC_NO);
            urlConnection.setRequestProperty("AMT_MATCH", AMT_MATCH);
            urlConnection.setRequestProperty("CHQ_STALE", CHQ_STALE);
            urlConnection.setRequestProperty("ENCODING", ENCODING);
            urlConnection.setRequestProperty("IMG_SIZE", IMG_SIZE);


            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            byte[] image_data = image11.getBytes();

            DataOutputStream out = new DataOutputStream(urlConnection.getOutputStream());
            out.write(image_data);

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
            String line;
            while((line=in.readLine())!=null)
            {
                response.append(line);
                Log.e("check","+"+response.toString());
            }
            out.flush();
            out.close();
        } catch(Exception e)
        {
            e.printStackTrace();
        } finally
        {
            urlConnection.disconnect();
        }
    }
    public void starter()
    {
        try {
            Thread runs = new Thread(this);
            runs.start();
            runs.join();

        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
