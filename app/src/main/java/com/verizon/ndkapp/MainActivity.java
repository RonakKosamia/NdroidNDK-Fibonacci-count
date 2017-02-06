package com.verizon.ndkapp;

import android.content.Context;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText input;
    private RadioGroup type;
    private TextView output;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            this.input = (EditText)findViewById(R.id.input);
            this.type = (RadioGroup)findViewById(R.id.type);
            this.output = (TextView) findViewById(R.id.resultview);

        Button button = (Button) findViewById(R.id.getoutput);
        button.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

     String s = input.getText().toString();
        if(TextUtils.isEmpty(s)){
            return;
        }
        long n = Long.parseLong(s);
        long result = 0;
        long t = System.currentTimeMillis();
        switch(this.type.getCheckedRadioButtonId()){
            case R.id.type_fib_JR:
                result = FibLib.fibJavaRecursive(n);
                break;

            case R.id.type_fib_NR:
                // Example of a call to a native method
                result = FibLib.fibNativeRecursive(n);
                break;
        }
        t = System.currentTimeMillis() - t;
        this.output.setText(String.format("fib(%d) = %d in %d ms", n,result,t));
    }



}
