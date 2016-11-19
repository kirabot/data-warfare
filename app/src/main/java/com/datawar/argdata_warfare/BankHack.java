package com.datawar.argdata_warfare;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Kill-o-bite on 11/17/2016.
 */

public class BankHack extends Activity {

    private TextView textView;
    private Button decryptButton;
    private Button startButton;
    private TextView textView4;

    private int progressStatus = 0;
    private int decryptingInt = 0;

    //privremeno ide u button
    // bankHacker bh = new bankHacker();

    private ProgressBar prg;
    private ProgressBar drp;
    private Handler handler = new Handler();
    private Handler handlerDec = new Handler();
    //for testint
    private TextView textView2;
    private TextView textView3;
    private Button close;

    public boolean decryptedData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bank_hack_layout);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        close = (Button) findViewById(R.id.button3);
        decryptButton = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.textView);
        prg = (ProgressBar) findViewById(R.id.progressBar);
        drp = (ProgressBar) findViewById(R.id.progressBar2);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView3 = (TextView) findViewById(R.id.textView3);
        startButton = (Button) findViewById(R.id.button);

        View.OnClickListener myListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bankHacker bh = new bankHacker(); // privremen
                if(true){
//                    textView.setText("Currently hours: " + bh.hourNow + "\n Mission is active: " + bh.start + "-" + bh.end);
                    progressStatus = 0;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while(progressStatus < 100){
                                progressStatus++;

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        prg.setProgress(progressStatus);
                                        textView2.setText(progressStatus + "/" + prg.getMax());
//                                        bh.setProgress(progressStatus); ovo je kada objekat bude public
                                        if(progressStatus == 100){
                                            decryptButton.setEnabled(true);
                                        }
                                    }
                                });
                                try{
                                    //change this to 3000
                                    Thread.sleep(30);
                                }
                                catch (InterruptedException e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
                    startButton.setEnabled(false);
                }
                else {
                    decryptButton.setEnabled(false);
                    textView.setText("This mission is currently unavaliable." + bh.hourNow + " TEST PURPOSE " + bh.start + " " + bh.end);
                }
            }
        };
        View.OnClickListener myLisetner2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DecryptStuff decrypt  = new DecryptStuff();
                decrypt.isConnected(getBaseContext());
                if(decrypt.isConnected(getBaseContext())){
                    textView3.setText("Now you are using your PC to decrypt data you have stolen from the bank.");
                    decryptingInt = 0;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while(decryptingInt < 100){
                                decryptingInt++;
                                if(DecryptStuff.isConnected(getBaseContext())){

                                }
                                else{
                                    drp.setProgress(0);
                                    decryptingInt = 0;
                                    textView3.setText("You are disconnected from source power, please start again!");
                                    break;
                                }
                                handlerDec.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        drp.setProgress(decryptingInt);

                                        if(decryptingInt == 100){
                                            Toast newMessage = Toast.makeText(getApplicationContext(), "DECRYPTED! Well done, hacker!",Toast.LENGTH_LONG);
                                            newMessage.show();
                                            bankHacker bhr = new bankHacker();
                                            textView4.setText("Money: " + bhr.reward()+"$");
                                            decryptedData = true;
                                            finish();
                                        }
                                    }
                                });
                                try{
                                    //random from 5 mins to 15 mins decrypt.howHardIsIt()
                                    Thread.sleep(30);

                                }
                                catch (InterruptedException e){
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
                    startButton.setEnabled(true);
                }
                else{
                    textView3.setText("Your device is low on processing power, pls find stronger source.");
                }
            }
        };

        View.OnClickListener closee = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };

        startButton.setOnClickListener(myListener);
        decryptButton.setOnClickListener(myLisetner2);
        close.setOnClickListener(closee);

        // part for decoding




    }
}
