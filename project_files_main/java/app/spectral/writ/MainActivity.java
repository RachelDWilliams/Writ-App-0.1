package app.spectral.writ;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    SeekBar sBar;
    TextView tView;
    String moodCat;
    String moodSpef;
    Button switcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sBar = findViewById(R.id.simpleSeekBar);
        tView = findViewById(R.id.imood_output);
        tView.setText(String.valueOf(sBar.getProgress()));


        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pval = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tView.setText(String.valueOf(pval));
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tView.setText(String.valueOf(pval));
            }
        });

        ////////////////
        final Spinner base_spinner =  findViewById(R.id.base_spinner);
        ArrayAdapter<CharSequence> base_adapter = ArrayAdapter.createFromResource(this,
                R.array.mood_base, android.R.layout.simple_spinner_item);
        base_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        base_spinner.setAdapter(base_adapter);
        ////////////////

        base_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (!"Choose Category".equals(base_spinner.getItemAtPosition(i))) {
                    if ("Anger".equals(base_spinner.getItemAtPosition(i))) {
                        Spinner s101 = findViewById(R.id.anger_spinner);
                        s101.setVisibility(View.VISIBLE);
                        Spinner s102 = findViewById(R.id.sadness_spinner);
                        s102.setVisibility(View.INVISIBLE);
                        Spinner s103 = findViewById(R.id.surprise_spinner);
                        s103.setVisibility(View.INVISIBLE);
                        Spinner s104 = findViewById(R.id.joy_spinner);
                        s104.setVisibility(View.INVISIBLE);
                        Spinner s105 = findViewById(R.id.love_spinner);
                        s105.setVisibility(View.INVISIBLE);
                        Spinner s106 = findViewById(R.id.fear_spinner);
                        s106.setVisibility(View.INVISIBLE);
                        moodCat = "Anger";
                    }
                    if ("Sadness".equals(base_spinner.getItemAtPosition(i))) {
                        Spinner s101 = findViewById(R.id.anger_spinner);
                        s101.setVisibility(View.INVISIBLE);
                        Spinner s102 = findViewById(R.id.sadness_spinner);
                        s102.setVisibility(View.VISIBLE);
                        Spinner s103 = findViewById(R.id.surprise_spinner);
                        s103.setVisibility(View.INVISIBLE);
                        Spinner s104 = findViewById(R.id.joy_spinner);
                        s104.setVisibility(View.INVISIBLE);
                        Spinner s105 = findViewById(R.id.love_spinner);
                        s105.setVisibility(View.INVISIBLE);
                        Spinner s106 = findViewById(R.id.fear_spinner);
                        s106.setVisibility(View.INVISIBLE);
                        moodCat = "Sadness";
                    }
                    if ("Surprise".equals(base_spinner.getItemAtPosition(i))) {
                        Spinner s101 = findViewById(R.id.anger_spinner);
                        s101.setVisibility(View.INVISIBLE);
                        Spinner s102 = findViewById(R.id.sadness_spinner);
                        s102.setVisibility(View.INVISIBLE);
                        Spinner s103 = findViewById(R.id.surprise_spinner);
                        s103.setVisibility(View.VISIBLE);
                        Spinner s104 = findViewById(R.id.joy_spinner);
                        s104.setVisibility(View.INVISIBLE);
                        Spinner s105 = findViewById(R.id.love_spinner);
                        s105.setVisibility(View.INVISIBLE);
                        Spinner s106 = findViewById(R.id.fear_spinner);
                        s106.setVisibility(View.INVISIBLE);
                        moodCat = "Surprise";
                    }
                    if ("Joy".equals(base_spinner.getItemAtPosition(i))) {
                        Spinner s101 = findViewById(R.id.anger_spinner);
                        s101.setVisibility(View.INVISIBLE);
                        Spinner s102 = findViewById(R.id.sadness_spinner);
                        s102.setVisibility(View.INVISIBLE);
                        Spinner s103 = findViewById(R.id.surprise_spinner);
                        s103.setVisibility(View.INVISIBLE);
                        Spinner s104 = findViewById(R.id.joy_spinner);
                        s104.setVisibility(View.VISIBLE);
                        Spinner s105 = findViewById(R.id.love_spinner);
                        s105.setVisibility(View.INVISIBLE);
                        Spinner s106 = findViewById(R.id.fear_spinner);
                        s106.setVisibility(View.INVISIBLE);
                        moodCat = "Joy";
                    }
                    if ("Love".equals(base_spinner.getItemAtPosition(i))) {
                        Spinner s101 = findViewById(R.id.anger_spinner);
                        s101.setVisibility(View.INVISIBLE);
                        Spinner s102 = findViewById(R.id.sadness_spinner);
                        s102.setVisibility(View.INVISIBLE);
                        Spinner s103 = findViewById(R.id.surprise_spinner);
                        s103.setVisibility(View.INVISIBLE);
                        Spinner s104 = findViewById(R.id.joy_spinner);
                        s104.setVisibility(View.INVISIBLE);
                        Spinner s105 = findViewById(R.id.love_spinner);
                        s105.setVisibility(View.VISIBLE);
                        Spinner s106 = findViewById(R.id.fear_spinner);
                        s106.setVisibility(View.INVISIBLE);
                        moodCat = "Love";
                    }
                    if ("Fear".equals(base_spinner.getItemAtPosition(i))) {
                        Spinner s101 = findViewById(R.id.anger_spinner);
                        s101.setVisibility(View.INVISIBLE);
                        Spinner s102 = findViewById(R.id.sadness_spinner);
                        s102.setVisibility(View.INVISIBLE);
                        Spinner s103 = findViewById(R.id.surprise_spinner);
                        s103.setVisibility(View.INVISIBLE);
                        Spinner s104 = findViewById(R.id.joy_spinner);
                        s104.setVisibility(View.INVISIBLE);
                        Spinner s105 = findViewById(R.id.love_spinner);
                        s105.setVisibility(View.INVISIBLE);
                        Spinner s106 = findViewById(R.id.fear_spinner);
                        s106.setVisibility(View.VISIBLE);
                        moodCat = "Fear";
                    }
                }
                else {
                    Spinner s101 = findViewById(R.id.anger_spinner);
                    s101.setVisibility(View.INVISIBLE);
                    Spinner s102 = findViewById(R.id.sadness_spinner);
                    s102.setVisibility(View.INVISIBLE);
                    Spinner s103 = findViewById(R.id.surprise_spinner);
                    s103.setVisibility(View.INVISIBLE);
                    Spinner s104 = findViewById(R.id.joy_spinner);
                    s104.setVisibility(View.INVISIBLE);
                    Spinner s105 = findViewById(R.id.love_spinner);
                    s105.setVisibility(View.INVISIBLE);
                    Spinner s106 = findViewById(R.id.fear_spinner);
                    s106.setVisibility(View.INVISIBLE);
                    moodCat = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ////////////////
        final Spinner anger_spinner = findViewById(R.id.anger_spinner);
        ArrayAdapter<CharSequence> anger_adapter = ArrayAdapter.createFromResource(this,
                R.array.mood_anger, android.R.layout.simple_spinner_item);
        anger_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        anger_spinner.setAdapter(anger_adapter);
        ////////////////

        ////////////////
        final Spinner sadness_spinner = findViewById(R.id.sadness_spinner);
        ArrayAdapter<CharSequence> sadness_adapter = ArrayAdapter.createFromResource(this,
                R.array.mood_sadness, android.R.layout.simple_spinner_item);
        sadness_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sadness_spinner.setAdapter(sadness_adapter);
        ////////////////

        ////////////////
        final Spinner surprise_spinner = findViewById(R.id.surprise_spinner);
        ArrayAdapter<CharSequence> surprise_adapter = ArrayAdapter.createFromResource(this,
                R.array.mood_surprise, android.R.layout.simple_spinner_item);
        surprise_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        surprise_spinner.setAdapter(surprise_adapter);
        ////////////////

        ////////////////
        final Spinner joy_spinner = findViewById(R.id.joy_spinner);
        ArrayAdapter<CharSequence> joy_adapter = ArrayAdapter.createFromResource(this,
                R.array.mood_joy, android.R.layout.simple_spinner_item);
        joy_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        joy_spinner.setAdapter(joy_adapter);
        ////////////////

        ////////////////
        final Spinner love_spinner = findViewById(R.id.love_spinner);
        ArrayAdapter<CharSequence> love_adapter = ArrayAdapter.createFromResource(this,
                R.array.mood_love, android.R.layout.simple_spinner_item);
        love_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        love_spinner.setAdapter(love_adapter);
        ////////////////

        ////////////////
        final Spinner fear_spinner = findViewById(R.id.fear_spinner);
        ArrayAdapter<CharSequence> fear_adapter = ArrayAdapter.createFromResource(this,
                R.array.mood_fear, android.R.layout.simple_spinner_item);
        fear_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fear_spinner.setAdapter(fear_adapter);
        ////////////////

        ////////////////
        final Spinner focus_spinner = findViewById(R.id.focus_spinner);
        ArrayAdapter<CharSequence> focus_adapter = ArrayAdapter.createFromResource(this,
                R.array.focus_array, android.R.layout.simple_spinner_item);
        focus_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        focus_spinner.setAdapter(focus_adapter);
        ////////////////

        ////////////////
        final Spinner energy_spinner = findViewById(R.id.energy_spinner);
        ArrayAdapter<CharSequence> energy_adapter = ArrayAdapter.createFromResource(this,
                R.array.energy_array, android.R.layout.simple_spinner_item);
        energy_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        energy_spinner.setAdapter(energy_adapter);
        ////////////////



        findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String text = mySpinner.getSelectedItem().toString();
                if (!"Choose Category".equals(base_spinner.getSelectedItem().toString()) && !"Choose One".equals(focus_spinner.getSelectedItem().toString()) && !"Choose One".equals(energy_spinner.getSelectedItem().toString())) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Confirm?");
                    builder.setMessage("Confirm Committing to your Writ?");
                    builder.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    DateFormat df = new SimpleDateFormat("d MMM yyyy HH:mm", Locale.US);
                                    String date = df.format(Calendar.getInstance().getTime());

                                    if (moodCat.equals("Anger")) {
                                        moodSpef = anger_spinner.getSelectedItem().toString();
                                    }
                                    if (moodCat.equals("Sadness")) {
                                        moodSpef = sadness_spinner.getSelectedItem().toString();
                                    }
                                    if (moodCat.equals("Surprise")) {
                                        moodSpef = surprise_spinner.getSelectedItem().toString();
                                    }
                                    if (moodCat.equals("Joy")) {
                                        moodSpef = joy_spinner.getSelectedItem().toString();
                                    }
                                    if (moodCat.equals("Love")) {
                                        moodSpef = love_spinner.getSelectedItem().toString();
                                    }
                                    if (moodCat.equals("Fear")) {
                                        moodSpef = fear_spinner.getSelectedItem().toString();
                                    }

                                    SeekBar simpleSeekBar = findViewById(R.id.simpleSeekBar);
                                    String imood = Integer.toString(simpleSeekBar.getProgress());
                                    String focus = focus_spinner.getSelectedItem().toString();
                                    String energy = energy_spinner.getSelectedItem().toString();
                                    EditText inputBox = findViewById(R.id.thoughts_input);
                                    String thoughts = inputBox.getText().toString();

                                    String output = date + "\nMood: " + moodSpef + "\nMood Intensity: " + imood + "\nFocus: " + focus + "\nEnergy: " + energy + "\nThoughts:\n" + thoughts + "\n";
                                    //7 lines//

                                    ////////////////

                                    ContextWrapper cw = new ContextWrapper(getApplicationContext());
                                    File directory = cw.getDir("wData", Context.MODE_PRIVATE);
                                    File file = new File(directory, "writ" + ".wrt");
                                    if (!file.exists()) {
                                        Log.d("path", file.toString());

                                        try {
                                            FileWriter writer = new FileWriter(file);
                                            writer.append(output);
                                            writer.flush();
                                            writer.close();
                                            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                                        } catch (java.io.IOException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        Log.d("path", file.toString());
                                        try {
                                            FileWriter writer = new FileWriter(file, true);
                                            writer.append(output);
                                            writer.flush();
                                            writer.close();
                                            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                                        } catch (java.io.IOException e) {
                                            e.printStackTrace();
                                        }
                                    }


                                    //Log.i("myTag", output);

                                    ////////////////

                                    Spinner resetSpinnerBase = findViewById(R.id.base_spinner);
                                    Spinner resetSpinnerFocus = findViewById(R.id.focus_spinner);
                                    Spinner resetSpinnerEnergy = findViewById(R.id.energy_spinner);
                                    simpleSeekBar.setProgress(50);
                                    tView.setText(String.valueOf(50));
                                    inputBox.setText("");
                                    resetSpinnerBase.setSelection(0);
                                    resetSpinnerFocus.setSelection(0);
                                    resetSpinnerEnergy.setSelection(0);

                                }
                            });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    TextView warning = findViewById(R.id.warning);
                    warning.setVisibility(View.VISIBLE);

                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            new Handler(Looper.getMainLooper()).post(new Runnable(){
                                @Override
                                public void run() {
                                    TextView warning = findViewById(R.id.warning);
                                    warning.setVisibility(View.INVISIBLE);
                                }
                            });
                        }
                    }, 4000);
                }
            }
        });
        EditText thoughts_input = findViewById(R.id.thoughts_input);
        thoughts_input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("focus", "focus lost");
                    // Do whatever you want here
                } else {
                    Log.d("focus", "focused");
                }
            }
        });



        switcher = (Button) findViewById(R.id.switcher);
        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });
        }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    Log.d("focus", "touchevent");
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }


    public void openNewActivity(){
        Intent intent = new Intent(this, viewWrit.class);
        startActivity(intent);
    }
}