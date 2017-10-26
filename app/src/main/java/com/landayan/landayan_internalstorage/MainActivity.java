package com.landayan.landayan_internalstorage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText et_message;
    Button btn_save, btn_display;
    TextView tv_output;
    FileOutputStream fos;
    FileInputStream fis;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_message = (EditText) findViewById(R.id.et_message);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_display = (Button) findViewById(R.id.btn_display);
        tv_output = (TextView) findViewById(R.id.tv_output);
    }

    public void saveMessage (View view) {
        String message = et_message.getText().toString();
        try{
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(message.getBytes());
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Message Saved!", Toast.LENGTH_SHORT).show();

    }
    public void displayMessage (View view){
        StringBuffer buffer = new StringBuffer();
        int read=0;
        try{
            fis = openFileInput("output.txt");
            while((read=fis.read()) != -1){
                buffer.append((char)read);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tv_output.setText(buffer.toString());
    }

}