package com.example.idc06.layout.test;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText ed;
    private final int BUTTONSIZE = 20;
    private String temp = "", val = "";
    private double inum; // 먼저들어온 수를 저장하는 변수
    private int pin; // result를 구분하기 위한 변수
    private int id[] = {R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6, R.id.bt7, R.id.bt8, R.id.bt9, R.id.bt10,
            R.id.bt11, R.id.bt12, R.id.bt13, R.id.bt14, R.id.bt15, R.id.bt16, R.id.bt17, R.id.bt18, R.id.bt19, R.id.bt20};
    private Button[] button;
    private Button back, result_log;
    private AnimationDrawable kakao_run;
    private ImageView kakao_run_img;
    private RelativeLayout relativeLayout;
    private TranslateAnimation trans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, Splash.class));
        ed = (EditText) findViewById(R.id.edit);
        ed.setClickable(false);
        button = new Button[BUTTONSIZE];
        back = (Button) findViewById(R.id.bimg);
        back.setOnClickListener(this);
        result_log = (Button) findViewById(R.id.result_log);
        result_log.setOnClickListener(this);
        kakao_run_img = new ImageView(this);
        kakao_run_img.setBackgroundResource(R.drawable.ani);
        kakao_run = (AnimationDrawable) kakao_run_img.getBackground();
        trans = new TranslateAnimation(
                -10, -1000, 0, 0);
        trans.setDuration(2000);

        relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);

        RelativeLayout.LayoutParams imgParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        imgParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        kakao_run_img.setLayoutParams(imgParams);


        ed.setText("0");
        ed.setTextSize(35f);

        ed.setGravity(Gravity.RIGHT);
        result_log.setBackgroundColor(Color.argb(0, 255, 255, 255));
        result_log.setTextSize(25f);
        result_log.setTextColor(Color.parseColor("#FF69B4"));
        result_log.setText("계산 기록");

        back.setBackgroundColor(Color.argb(0, 255, 255, 255));
        back.setTextSize(25f);
        back.setTextColor(Color.parseColor("#FF69B4"));
        back.setText("＜");

        for (int i = 0; i < BUTTONSIZE; i++) {
            button[i] = (Button) findViewById(id[i]);
            button[i].setOnClickListener(this);
            button[i].setBackgroundColor(Color.argb(0, 255, 255, 255));
            button[i].setTextColor(Color.parseColor("#FF69B4"));
            button[i].setTextSize(25f);
        }

        button[0].setText("C");
        button[1].setText("()");
        button[2].setText("%");
        button[3].setText("÷");
        button[4].setText("7");
        button[5].setText("8");
        button[6].setText("9");
        button[7].setText("X");
        button[8].setText("4");
        button[9].setText("5");
        button[10].setText("6");
        button[11].setText("-");
        button[12].setText("1");
        button[13].setText("2");
        button[14].setText("3");
        button[15].setText("+");
        button[16].setText(".");
        button[17].setText("0");
        button[18].setText("+/-");
        button[19].setText("=");

    }

    public void number(String setnum) { // 숫자 구하는 메소드
        String num = ed.getText().toString();
        if (num.equals("0")) num = "";

        num = num + setnum;
        ed.setText(num);
    }

    public void calculation(int num) { // 계산 메소드
            String val = ed.getText().toString();
            String val2 = ed.getText().toString();
            if(val.contains("(")) {
                val.replace("(", " ");
                inum = Double.parseDouble(val);
            }
                inum = Double.parseDouble(val);
        Log.d("String", val);
        switch (num) {
            case 1: //나누기
                ed.setText(val2 + "÷");
                pin = 1;
                break;
            case 2: // 곱하기
                ed.setText(val2 + "x");
                pin = 2;
                break;
            case 3: // 빼기
                ed.setText(val2 + "-");
                pin = 3;
                break;
            case 4: // 더하기
                ed.setText(val2 + "+");
                pin = 4;
                break;
        }
        temp = ed.getText().toString();
        cur();

    }

    public void garo() { // 괄호
        String aaa = ed.getText().toString();
        if (aaa.equals("0")) aaa = "";

        if (aaa.contains("(")) ed.setText(aaa + ")");
        else ed.setText(aaa+"(");
    }

    public void change(){
        String val = ed.getText().toString();
        if(val.contains("-"))val.replace("-","");
        else val.replace("","-");
        ed.setText(val);
    }


    public void result(){ // 결과값 출력
        String temp2 = "";

        if(temp2.equals("")) {
            temp2 = ed.getText().toString();
            if(temp2.contains(")")) temp2.replace(")"," ");
            temp2 = temp2.replace(temp, "");
        }
        double num;
        switch (pin){
            case 1:  //나누기
                num = inum / Double.parseDouble(temp2);
                ed.setText(String.valueOf(num));
                break;
                case 2: //곱하기
                 num = inum * Double.parseDouble(temp2);
                ed.setText(String.valueOf(num));
                    break;
                case 3: //빼기
                 num = inum - Double.parseDouble(temp2);
                ed.setText(String.valueOf(num));
                    break;
                case 4: //더하기
                num = inum + Double.parseDouble(temp2);
                ed.setText(String.valueOf(num));
                    break;
            }
        cur();

        relativeLayout.addView(kakao_run_img);
        kakao_run_img.setAnimation(trans);
        kakao_run.start();
        trans.start();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                kakao_run.stop();
                relativeLayout.removeView(kakao_run_img);
            }
        },2000);
        }

public void cur(){
    ed.setSelection(ed.getText().length());
}

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bt1:
                ed.setText("0"); // 초기화
                cur();
                break;

            case R.id.bt2:
                garo();
                cur();
                break;

            case R.id.bt3:
                cur();
                break;

            case R.id.bt4:
                calculation(1); // 나누기
                break;

            case R.id.bt5:
                number("7");
                break;

            case R.id.bt6:
                number("8");
                break;

            case R.id.bt7:
                number("9");
                break;

            case R.id.bt8:
                calculation(2); // 곱하기
                break;

            case R.id.bt9:
                number("4");
                break;

            case R.id.bt10:
                number("5");
                break;

            case R.id.bt11:
                number("6");
                break;

            case R.id.bt12:
                calculation(3); // 빼기
                break;

            case R.id.bt13:
                number("1");
                break;

            case R.id.bt14:
                number("2");
                break;

            case R.id.bt15:
                number("3");
                break;

            case R.id.bt16:
                calculation(4); // 더하기
                break;

            case R.id.bt17:
                number(".");
                break;

            case R.id.bt18:
                number("0");
                break;

            case R.id.bt19:// +/- 바꾸기
                change();
                cur();
                break;

            case R.id.bt20: // result
                result();
                break;
            case R.id.bimg: // 한글자씩 지우기
                String strtemp = ed.getText().toString();
                if(strtemp.length() == 0) ed.setText("0");
                else ed.setText(strtemp.substring(0,strtemp.length()-1));
                cur();
                break;
            case R.id.result_log: //계산 기록
                break;
            default:
                break;

        }
    }
}

