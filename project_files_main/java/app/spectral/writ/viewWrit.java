package app.spectral.writ;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class viewWrit extends AppCompatActivity {

    Button button;
    int page = 1;
    boolean isLast = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_writ);

        Button home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });

        readFile();

        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numPages = numPages();
                if(page != numPages) {
                    page++;
                    flushTextViews();
                    readFile();
                    Button prev = (Button) findViewById(R.id.prev);
                    prev.setVisibility(View.VISIBLE);
                }
                    if(page == numPages) {
                        Button next = (Button) findViewById(R.id.next);
                        next.setVisibility(View.INVISIBLE);
                    }
                }
        });

        Button prev = (Button) findViewById(R.id.prev);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numPages = numPages();
                if(page != 1){
                    page--;
                    flushTextViews();
                    readFile();
                    Button next = (Button) findViewById(R.id.next);
                    next.setVisibility(View.VISIBLE);
                }
                if(page == 1) {
                    Button prev = (Button) findViewById(R.id.prev);
                    prev.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public int numPages(){
        int numPages = 1;
        try {
            FileInputStream is;
            BufferedReader reader;
            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            File directory = cw.getDir("wData", Context.MODE_PRIVATE);
            final File file = new File(directory, "writ" + ".wrt");

            if (file.exists()) {
                is = new FileInputStream(file);
                reader = new BufferedReader(new InputStreamReader(is));

                boolean isnull = false;
                while (!isnull) {
                    //this skips 1 page
                    for (int x = 0; x <  10; x++) {
                        for (int y = 0; y < 7; y++) {
                            if(reader.readLine()==null) {
                                isnull = true;
                                break;
                            }
                        }
                    }
                    if(!isnull) {
                        numPages++;
                    }
                }
                reader.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return numPages;
    }



    public void openNewActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void flushTextViews(){

        TextView entry1 = findViewById(R.id.entry1);
        TextView entry2 = findViewById(R.id.entry2);
        TextView entry3 = findViewById(R.id.entry3);
        TextView entry4 = findViewById(R.id.entry4);
        TextView entry5 = findViewById(R.id.entry5);
        TextView entry6 = findViewById(R.id.entry6);
        TextView entry7 = findViewById(R.id.entry7);
        TextView entry8 = findViewById(R.id.entry8);
        TextView entry9 = findViewById(R.id.entry9);
        TextView entry10 = findViewById(R.id.entry10);

        TextView[] readEntry = {entry1, entry2, entry3, entry4, entry5, entry6, entry7, entry8, entry9, entry10};

        for(int x = 0; x < 10; x++){
            readEntry[x].setText("");
        }
    }

    public void readFile(){
        try {
            FileInputStream is;
            BufferedReader reader;
            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            File directory = cw.getDir("wData", Context.MODE_PRIVATE);
            final File file = new File(directory, "writ" + ".wrt");

            TextView entry1 = findViewById(R.id.entry1);
            TextView entry2 = findViewById(R.id.entry2);
            TextView entry3 = findViewById(R.id.entry3);
            TextView entry4 = findViewById(R.id.entry4);
            TextView entry5 = findViewById(R.id.entry5);
            TextView entry6 = findViewById(R.id.entry6);
            TextView entry7 = findViewById(R.id.entry7);
            TextView entry8 = findViewById(R.id.entry8);
            TextView entry9 = findViewById(R.id.entry9);
            TextView entry10 = findViewById(R.id.entry10);


            TextView[] readEntry = {entry1, entry2, entry3, entry4, entry5, entry6, entry7, entry8, entry9, entry10};

            if (file.exists()) {
                is = new FileInputStream(file);
                reader = new BufferedReader(new InputStreamReader(is));

                List<String> reverseWrit = new ArrayList<String>();
                String curLine;


                while ( (curLine = reader.readLine()) != null) {
                    reverseWrit.add(curLine);
                    //Log.d("This", reverseWrit.get(a));
                }

                Collections.reverse(reverseWrit);
                reader.close();


                int readHead = 0;
                if(page > 1){
                    readHead += (page-1)*70;
                    readHead--;
                    Log.d("Read Head", Integer.toString(readHead));
                    Log.d("Array Size", Integer.toString(reverseWrit.size()));
                }

                if(page == 1){
                    readHead = -1;
                }

                //Log.d("bullshit", Integer.toString(reverseWrit.size()));
                    for(int x = 0; x < 10; x += 1 ) {
                        readHead += 7;
                        for(int y = 0; y < 7; y++) {
                            String string =  reverseWrit.get(readHead);
                            readEntry[x].append(string);
                            readEntry[x].append("\n");
                            readHead--;
                            //if (reverseWrit.get(readHead) == null) { break; }
                        }
                        if((reverseWrit.size() - 1) != readHead){
                            readHead += 7;
                        }
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}