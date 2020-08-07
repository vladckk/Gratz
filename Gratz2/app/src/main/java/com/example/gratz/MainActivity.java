package com.example.gratz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView sample1, sample2;
    Typeface font1;
    ImageButton imgButton;
    MediaPlayer mediaPlayer;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        sample1 = findViewById(R.id.tv1);
        font1 = Typeface.createFromAsset(this.getAssets(), "fonts/xiomara.ttf");
        sample1.setTypeface(font1);

        sample2 = findViewById(R.id.tv2);
        font1 = Typeface.createFromAsset(this.getAssets(), "fonts/xiomara.ttf");
        sample2.setTypeface(font1);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#FFB6C1"));

        mediaPlayer = MediaPlayer.create(this, R.raw.ruslana);
        imgButton = findViewById(R.id.play);
        imgButton.setOnClickListener(imgButtonHandler);

    }

    View.OnClickListener imgButtonHandler = new View.OnClickListener() {

        boolean isPlaying = false;

        public void onClick(View v) {
            if (!isPlaying){
                imgButton.setImageResource(R.drawable.pause1);
                isPlaying = true;
                mediaPlayer.start();
            } else {
                imgButton.setImageResource(R.drawable.play1);
                isPlaying = false;
                mediaPlayer.pause();
            }
        }
    };

    public void openActivity1(View view){
        Intent intent = new Intent(this, Activity1.class);
        startActivity(intent);
    }

    public void openActivity2(View view){
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

    public void open(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(Html.fromHtml("<font color='#DA1962'> Something</font>"));
                alertDialogBuilder.setPositiveButton("Хорошо", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                finish();
                                mediaPlayer.release();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
