package com.example.dell.myapplication;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class Teacher1 extends AsyncTask<String,Void,String> {
    AlertDialog alertDialog;
    Context ctx;

    Teacher1(Context ctx) {
        this.ctx = ctx;
    }
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(ctx).create();
            alertDialog.setTitle("insert Information");
        }

    protected String doInBackground(String... params) {
        String reg_url = "http://10.0.2.2/webapp/register.php";
        String login_url = "http://10.0.2.2/webapp/login.php";
        String method = params[0];
        if (method.equals("save")) {
            String Tname = params[1];
            String TPhone = params[2];
            String Tdof = params[3];
            String Tcode = params[4];


            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("Tname", "UTF-8") + "=" + URLEncoder.encode(Tname, "UTF-8") + "&" +
                        URLEncoder.encode("TPhone", "UTF-8") + "=" + URLEncoder.encode(TPhone, "UTF-8") + "&" +
                        URLEncoder.encode("Tdof", "UTF-8") + "=" + URLEncoder.encode(Tdof, "UTF-8") + "&" +
                        URLEncoder.encode("Tcode", "UTF-8") + "=" + URLEncoder.encode(Tcode, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                //httpURLConnection.connect();
                httpURLConnection.disconnect();
                return "Registration Success...";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
     else if(method.equals("update"))
    {

        String Temail = params[1];
        String TPhone = params[2];
        String Tcode = params[3];

        try {
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String data = URLEncoder.encode("save_email","UTF-8")+"="+URLEncoder.encode(Temail,"UTF-8")+"&"+
                    URLEncoder.encode("save_phone","UTF-8")+"="+URLEncoder.encode(TPhone,"UTF-8")+"&"+
                    URLEncoder.encode("save_code","UTF-8")+"="+URLEncoder.encode(Tcode,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String response = "";
            String line = "";
            while ((line = bufferedReader.readLine())!=null)
            {
                response+= line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return response;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     return null;
}
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result) {
        if(result.equals("Registration Success..."))
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else
        {   alertDialog.setMessage(result);
            alertDialog.show();
        }
    }
}