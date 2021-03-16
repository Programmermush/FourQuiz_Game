package com.example.fourquiz_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlayActivity extends AppCompatActivity {

    //どのメソッドからも使えるための準備
    private TextView tvCount;
    private TextView tvQuestion;
    private Button ansBtn1;
    private Button ansBtn2;
    private Button ansBtn3;
    private Button ansBtn4;
    private Button nextBtn;
    private Button titleBtn;

    private int i = 0;

    //クイズデータを準備
    String quizDate[][] = {
            //{"問題", "正解", "選択肢１", "選択肢２", "選択肢３"}
            {"問題1 : このなかで仲間外れはどれ？", "氷", "木", "月", "土"},
            {"問題2 : 「海賊王に俺はなる！」と言ったジャンプのキャラクターは？", "モンキー・D・ルフィ", "マーシャル・D・ティーチ", "エドワード・ニューゲート", "エイブラハム・リンカーン"},
            {"問題3 : 中国史「三国志」の中では、魏、呉、○という三つの国が出てくる。○に入るのは？", "蜀", "燭", "嘱", "食"},
            {"問題4 : 三国志で有名な言葉の中で「泣いて馬謖を斬る」という言葉があるが、なぜ馬謖は斬られた？", "大将である諸葛亮の命令に従わず魏に大敗したため", "大将である諸葛勤の命令に従わず魏に大敗したため", "大将である諸葛亮の命令に従わず呉に大敗したため",
                    "大将である猪八戒の命令に従わず魏に大敗したため"},
            {"問題5 : ドラゴンボールZのZ戦士の中で一番戦闘力が高い人物は？（フュージョンなどの合体は考えない）"
                    , "孫悟飯", "孫悟空", "ベジータ", "トランクス"},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //(2)idを取得
        getId();

        //(3)クイズを表示
        showquiz();
    }

    public void getId(){
        tvCount = findViewById(R.id.tvCount);
        tvQuestion = findViewById(R.id.tvQuestion);
        ansBtn1 = findViewById(R.id.ansBtn1);
        ansBtn2 = findViewById(R.id.ansBtn2);
        ansBtn3 = findViewById(R.id.ansBtn3);
        ansBtn4 = findViewById(R.id.ansBtn4);
        nextBtn = findViewById(R.id.nextBtn);
        titleBtn = findViewById(R.id.titleBtn);
    }

    public void showquiz(){
        //(4)シャッフル
        List<Integer> num = Arrays.asList(1,2,3,4);
        Collections.shuffle(num);

        tvCount.setText("残り" + (5 - i) + "問");

        tvQuestion.setText(quizDate[i][0]);

        ansBtn1.setText(quizDate[i][num.get(0)]);
        ansBtn2.setText(quizDate[i][num.get(1)]);
        ansBtn3.setText(quizDate[i][num.get(2)]);
        ansBtn4.setText(quizDate[i][num.get(3)]);
    }

    //(5)ボタンが押された時の正誤判定
    public void onButton(View view){
        //押されたボタン
        Button clickedBtn = (Button)view;
        String clickedAns = clickedBtn.getText().toString();

        if(clickedAns.equals(quizDate[i][1])) {
            clickedBtn.setText("正解！");

            //ボタンを無効化、ネクストボタンを有効化
            ansBtn1.setEnabled(false);
            ansBtn2.setEnabled(false);
            ansBtn3.setEnabled(false);
            ansBtn4.setEnabled(false);
            nextBtn.setEnabled(true);
            titleBtn.setEnabled(false);

            //i++;
            if (i == 4) {
                //(7)アクティビティ画面を作成
                //(8)画面推移
                Intent intent = new Intent(this, ResultActivity.class);
                startActivity(intent);
                finish();
            } else {
                i++;
            }

        }else{
            clickedBtn.setText("不正解");
            tvQuestion.setText("Game over");

            //ボタンを無効化
            ansBtn1.setEnabled(false);
            ansBtn2.setEnabled(false);
            ansBtn3.setEnabled(false);
            ansBtn4.setEnabled(false);
            nextBtn.setEnabled(false);
            titleBtn.setEnabled(true);
        }
    }

    //(6)Nextボタンが押された時の処理
    public void onNext(View view){
        showquiz();

        ansBtn1.setEnabled(true);
        ansBtn2.setEnabled(true);
        ansBtn3.setEnabled(true);
        ansBtn4.setEnabled(true);
    }

    //Titleボタンは押された時の処理
    public void onTitle(View view){
        showquiz();

        ansBtn1.setEnabled(false);
        ansBtn2.setEnabled(false);
        ansBtn3.setEnabled(false);
        ansBtn4.setEnabled(false);

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
