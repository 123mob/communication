package com.example.communication;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.StringBuffer;
public class CallActivity extends AppCompatActivity {
    private Button number0Button;
    private Button number1Button;
    private Button number2Button;
    private Button number3Button;
    private Button number4Button;
    private Button number5Button;
    private Button number6Button;
    private Button number7Button;
    private Button number8Button;
    private Button number9Button;
    private Button numberStarButton;
    private Button numberSharpButton;
    private Button callButton;
    private Button clearButton;
    private TextView callNumberTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        initialize();
    }
    private void initialize() {
        bindViews();
        setOnClickListeners();
        callNumberTextView.setText("");
    }
    private void bindViews() {
        number0Button = (Button) findViewById(R.id.number0Button);
        number1Button = (Button) findViewById(R.id.number1Button);
        number2Button = (Button) findViewById(R.id.number2Button);
        number3Button = (Button) findViewById(R.id.number3Button);
        number4Button = (Button) findViewById(R.id.number4Button);
        number5Button = (Button) findViewById(R.id.number5Button);
        number6Button = (Button) findViewById(R.id.number6Button);
        number7Button = (Button) findViewById(R.id.number7Button);
        number8Button = (Button) findViewById(R.id.number8Button);
        number9Button = (Button) findViewById(R.id.number9Button);
        numberStarButton = (Button) findViewById(R.id.numberStarButton);
        numberSharpButton = (Button) findViewById(R.id.numberSharpButton);
        callButton = (Button) findViewById(R.id.callButton);
        clearButton = (Button) findViewById(R.id.clearButton);
        callNumberTextView = (TextView) findViewById(R.id.callNumberTextView);
    }
    private void setOnClickListeners() {
        OnClickListener onClickListener = new OnClickListener();
        number0Button.setOnClickListener(onClickListener);
        number1Button.setOnClickListener(onClickListener);
        number2Button.setOnClickListener(onClickListener);
        number3Button.setOnClickListener(onClickListener);
        number4Button.setOnClickListener(onClickListener);
        number5Button.setOnClickListener(onClickListener);
        number6Button.setOnClickListener(onClickListener);
        number7Button.setOnClickListener(onClickListener);
        number8Button.setOnClickListener(onClickListener);
        number9Button.setOnClickListener(onClickListener);
        numberStarButton.setOnClickListener(onClickListener);
        numberSharpButton.setOnClickListener(onClickListener);
        callButton.setOnClickListener(onClickListener);
        clearButton.setOnClickListener(onClickListener);
    }
   private class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.number0Button:
                case R.id.number1Button:
                case R.id.number2Button:
                case R.id.number3Button:
                case R.id.number4Button:
                case R.id.number5Button:
                case R.id.number6Button:
                case R.id.number7Button:
                case R.id.number8Button:
                case R.id.number9Button:
                case R.id.numberStarButton:
                case R.id.numberSharpButton:
                    callNumberTextView.append(((Button) view).getText());
                    break;
                case R.id.clearButton:
//                    callNumberTextView.setText("");
                    StringBuffer st_callNumber=new StringBuffer(callNumberTextView.getText().toString());
                    if(!(callNumberTextView.getText().toString().equals(""))) {
                        st_callNumber.deleteCharAt(callNumberTextView.getText().length() - 1);
                        callNumberTextView.setText(st_callNumber.toString());
//                        callNumberTextView.setSelection(callNumberTextView.getText().length());
                }
                    break;
                case R.id.callButton:
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(CallActivity.this, new String[] {Manifest.permission.CALL_PHONE}, 1);
                    } else {
                        Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + callNumberTextView.getText()));
                        startActivity(dialIntent);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
