package com.example.carolina.practicetimethree;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.textView2)
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private String readFileFromAssets(String filename) {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();

        try {
            reader = new BufferedReader(new InputStreamReader(
                    getAssets().open(filename)));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            return builder.toString();

        } catch (FileNotFoundException e){
            addText("File not found:" + e.getMessage());
        }
        catch (Exception e) {
            addText(e.getMessage());
        } finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (Exception e) {
                    addText(e.getMessage());
                }
            }


        }

        return null;
    }
    @OnClick(R.id.button)
    public void onViewClicked() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = readFileFromAssets("lore_ipsum.txt");
                if (texto != null){
                    text.setText(texto);}
            }
        });
    }

    public void addText(String message){
        message = textView2.getText().toString().trim() + message;
        text.setText(message);
    }
}
