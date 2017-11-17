package com.example.d2a.texteditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {

    protected class SaveListener implements View.OnClickListener{
        private EditText TextArea;
        public SaveListener (EditText txt){
            TextArea = txt;
        }

        @Override
        public void onClick(View v) {
            try {
                File myFile = new File(v.getContext().getExternalFilesDir(null),"myFile.txt");
                myFile.createNewFile();

                FileOutputStream fOut = new FileOutputStream(myFile);
                OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);

                myOutWriter.append(TextArea.getText());
                myOutWriter.close();
                fOut.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    protected class OpenListener implements View.OnClickListener{
        private EditText TextArea;
        public OpenListener(EditText txt){
            TextArea = txt;
        }

        @Override
        public void onClick(View v) {
            try {
                File myFile = new File(v.getContext().getExternalFilesDir(null), "myFile.txt");
                FileInputStream fIn = new FileInputStream(myFile);
                BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
                String aDataRow = "";
                String aBuffer = "";
                while ((aDataRow = myReader.readLine()) !=null){
                    aBuffer += aDataRow + "\n";
                }
                TextArea.setText(aBuffer);
                myReader.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    protected class ClearScreenListener implements View.OnClickListener{
        private EditText TextArea;
        public ClearScreenListener(EditText txt){
            TextArea = txt;
        }

        @Override
        public void onClick(View v) {
            TextArea.setText("");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText TextArea = (EditText)findViewById(R.id.textArea);

        Button btnSave = (Button)this.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new SaveListener(TextArea));

        Button btnOpen = (Button) this.findViewById(R.id.btnOpen);
        btnOpen.setOnClickListener(new OpenListener(TextArea));

        Button btnClearScreen = (Button) this.findViewById(R.id.btnClearScreen);
        btnClearScreen.setOnClickListener(new ClearScreenListener(TextArea));
    }
}
