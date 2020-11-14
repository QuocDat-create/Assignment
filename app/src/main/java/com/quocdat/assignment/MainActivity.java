package com.quocdat.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    CheckBox cbOne, cbTwo, cbThree;
    SeekBar skOne, skTwo, skThree;
    TextView txtPoint;
    ImageButton ibtnPlay;
    int soDiem = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        skOne.setEnabled(false);
        skTwo.setEnabled(false);
        skThree.setEnabled(false);
        
        txtPoint.setText(soDiem + "");
        final CountDownTimer countDownTimer = new CountDownTimer(60000, 500) {
            @Override
            public void onTick(long millisUntilFinished) {

                //cho các seekbar countdown một cách random
                int number = 5;
                Random random = new Random(number);
                int one = random.nextInt(number);
                int two = random.nextInt(number);
                int three = random.nextInt(number);

                skOne.setProgress(skOne.getProgress() + one );
                skTwo.setProgress(skTwo.getProgress() + two);
                skThree.setProgress(skThree.getProgress() + three);

                //kiểm tra win
                if (skOne.getProgress() >= skOne.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "ONE WIN", Toast.LENGTH_SHORT).show();
                    if (cbOne.isChecked()){
                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "You selected correct!", Toast.LENGTH_SHORT).show();
                    }else{
                        soDiem -= 10;
                        Toast.makeText(MainActivity.this, "You selected incorrect!", Toast.LENGTH_SHORT).show();
                    }
                    txtPoint.setText(soDiem + "");
                    EnableCheckBox();
                }
                if (skTwo.getProgress() >= skTwo.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "TWO WIN", Toast.LENGTH_SHORT).show();
                    if (cbTwo.isChecked()){
                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "You selected correct!", Toast.LENGTH_SHORT).show();
                    }else{
                        soDiem -= 10;
                        Toast.makeText(MainActivity.this, "You selected incorrect!", Toast.LENGTH_SHORT).show();
                    }
                    txtPoint.setText(soDiem + "");
                    EnableCheckBox();
                }
                if (skThree.getProgress() >= skThree.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "THREE WIN", Toast.LENGTH_SHORT).show();
                    if (cbThree.isChecked()){
                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "You selected correct!", Toast.LENGTH_SHORT).show();
                    }else{
                        soDiem -= 10;
                        Toast.makeText(MainActivity.this, "You selected incorrect!", Toast.LENGTH_SHORT).show();
                    }
                    txtPoint.setText(soDiem + "");
                    EnableCheckBox();
                }

            }

            @Override
            public void onFinish() {

            }
        };
        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cbThree.isChecked() || cbTwo.isChecked() || cbOne.isChecked()){
                    skOne.setProgress(0);
                    skTwo.setProgress(0);
                    skThree.setProgress(0);

                    //click vào button ẩn đi
                    ibtnPlay.setVisibility(View.INVISIBLE);
                    DisableCheckBox();

                    //Chạy câu lệnh countdowntimer
                    countDownTimer.start();
                }else{
                    Toast.makeText(MainActivity.this, "Vui lòng đặt cược!", Toast.LENGTH_SHORT).show();
                }





            }
        });

        //kiểm tra checkbox
        cbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    cbTwo.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });
        cbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    cbThree.setChecked(false);
                    cbOne.setChecked(false);
                }
            }
        });
        cbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    cbOne.setChecked(false);
                    cbTwo.setChecked(false);
                }
            }
        });
    }
    private void EnableCheckBox(){
        cbThree.setEnabled(true);
        cbTwo.setEnabled(true);
        cbThree.setEnabled(true);
    }

    private void DisableCheckBox(){
        cbThree.setEnabled(false);
        cbTwo.setEnabled(false);
        cbOne.setEnabled(false);
    }
    private void AnhXa(){
        cbOne = (CheckBox) findViewById(R.id.checkboxOne);
        cbTwo = (CheckBox) findViewById(R.id.checkboxTwo);
        cbThree = (CheckBox) findViewById(R.id.checkboxThree);
        skOne = (SeekBar) findViewById( R.id.seekbarOne);
        skTwo = (SeekBar) findViewById(R.id.seekbarTwo);
        skThree = (SeekBar) findViewById(R.id.seekbarThree);
        txtPoint = (TextView) findViewById(R.id.textviewPoint);
        ibtnPlay = (ImageButton) findViewById(R.id.imagebuttonPlay);
    }
}