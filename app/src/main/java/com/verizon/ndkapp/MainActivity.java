package com.verizon.ndkapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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
       type.check(R.id.type_fib_JR);
        Button button = (Button) findViewById(R.id.getoutput);
        button.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
        }
     String s = input.getText().toString();
        if(TextUtils.isEmpty(s)){
            return;
        }
        final ProgressDialog dialog = ProgressDialog.show(this, "", "calculating...",true);
        final long n = Long.parseLong(s);
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                long result = 0;
                String resulttext = "";
                long t = System.currentTimeMillis();
                switch (MainActivity.this.type.getCheckedRadioButtonId()) {
                    case R.id.type_fib_JR:
                        result = FibLib.fibJavaIterative(n);
                        resulttext = "  Using Java calculations";
                        break;

                    case R.id.type_fib_NR:
                        // Example of a call to a native method
                        result = FibLib.fibNativeIterative(n);
                        resulttext = " Using Native C++ calculations";
                        break;
                }
                t = System.currentTimeMillis() - t;
                return String.format("fib(%d) = %d in %d ms"+resulttext, n,result,t);
            }


            @Override
            protected void onPostExecute(String result) {
                dialog.dismiss();
                MainActivity.this.output.setText(result);
               // super.onPostExecute(result);
            }
        }.execute();



    }



}
