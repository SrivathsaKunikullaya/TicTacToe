package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<int[]> combinationList=new ArrayList<>();

    private int[] boxpositions = {0,0,0,0,0,0,0,0,0};

    private int playerTurn=1;

    private int totalSelectionBoxes=1;

    private long backPressedTime;

    private LinearLayout playerOneLayout,playerTwoLayout;
    private TextView playerOneName,playerTwoName;
    private ImageView image1,image2,image3,image4,image5,image6,image7,image8,image9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         playerOneName=findViewById(R.id.playerOneName);
         playerTwoName=findViewById(R.id.playerTwoName);

         playerOneLayout=findViewById(R.id.playerOneLayout);
         playerTwoLayout=findViewById(R.id.playerTwoLayout);

         image1=findViewById(R.id.image1);
         image2=findViewById(R.id.image2);
         image3=findViewById(R.id.image3);
         image4=findViewById(R.id.image4);
         image5=findViewById(R.id.image5);
         image6=findViewById(R.id.image6);
         image7=findViewById(R.id.image7);
         image8=findViewById(R.id.image8);
         image9=findViewById(R.id.image9);


        combinationList.add(new int[]{0, 1, 2});
        combinationList.add(new int[]{3, 4, 5});
        combinationList.add(new int[]{6, 7, 8});
        combinationList.add(new int[]{0, 3, 6});
        combinationList.add(new int[]{1, 4, 7});
        combinationList.add(new int[]{2, 5, 8});
        combinationList.add(new int[]{2, 4, 6});
        combinationList.add(new int[]{0, 4, 8});

        final String getPlayerOneName=getIntent().getStringExtra("playerOne");
        final String getPlayerTwoName=getIntent().getStringExtra("playerTwo");

        playerOneName.setText(getPlayerOneName);
        playerTwoName.setText(getPlayerTwoName);


        image1.setOnClickListener(v -> {
            if (isBoxSelectable(0)){
                performAction((ImageView)v,0);
            }
        });

        image2.setOnClickListener(v -> {
            if (isBoxSelectable(1)){
                performAction((ImageView)v,1);
            }

        });

        image3.setOnClickListener(v -> {
            if (isBoxSelectable(2)){
                performAction((ImageView)v,2);
            }

        });

        image4.setOnClickListener(v -> {
            if (isBoxSelectable(3)){
                performAction((ImageView)v,3);
            }

        });

        image5.setOnClickListener(v -> {
            if (isBoxSelectable(4)){
                performAction((ImageView)v,4);
            }

        });

        image6.setOnClickListener(v -> {
            if (isBoxSelectable(5)){
                performAction((ImageView)v,5);
            }

        });

        image7.setOnClickListener(v -> {
            if (isBoxSelectable(6)){
                performAction((ImageView)v,6);
            }

        });

        image8.setOnClickListener(v -> {
            if (isBoxSelectable(7)){
                performAction((ImageView)v,7);
            }

        });

        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectable(8)){

                    performAction((ImageView)v,8);
                }

            }
        });
    }

    private void performAction(ImageView imageView,int selectedBoxPosition){

        boxpositions[selectedBoxPosition]=playerTurn;

        if (playerTurn==1){
            imageView.setImageResource(R.drawable.cross_icon);

            if (checkPlayerWin()){

                WinDialog winDialog =new WinDialog(MainActivity.this,playerOneName.getText().toString() + " Has won the match",MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else if (totalSelectionBoxes==9){

                WinDialog winDialog =new WinDialog(MainActivity.this,"It is a draw",MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else{
                changePlayerTurn(2);

                totalSelectionBoxes++;

            }
        }
        else{

            imageView.setImageResource(R.drawable.zero_icon);

            if (checkPlayerWin()){

                WinDialog winDialog =new WinDialog(MainActivity.this,playerTwoName.getText().toString()+" Has won the match",MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }

            else if (totalSelectionBoxes==9){

                WinDialog winDialog =new WinDialog(MainActivity.this,"It is a draw",MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }

            else{
                changePlayerTurn(1);

                totalSelectionBoxes++;

            }

        }
    }

    private void changePlayerTurn(int currentPlayerTurn){

        playerTurn=currentPlayerTurn;

        if (playerTurn==1){
            playerOneLayout.setBackgroundResource(R.drawable.round_back_blue_border);
            playerTwoLayout.setBackgroundResource(R.drawable.round_back_dark_blue);
        }
        else{
            playerTwoLayout.setBackgroundResource(R.drawable.round_back_blue_border);
            playerOneLayout.setBackgroundResource(R.drawable.round_back_dark_blue);
        }
    }

    private boolean checkPlayerWin(){
        boolean response=false;
        for (int i=0;i<combinationList.size();++i){

            final int[] combination =combinationList.get(i);

            if (boxpositions[combination[0]]==playerTurn && boxpositions[combination[1]]==playerTurn && boxpositions[combination[2]]==playerTurn){

                response=true;
            }
        }
        return response;
    }

    private boolean isBoxSelectable(int boxPosition){

        return boxpositions[boxPosition] == 0;
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finish();
        } else {
            Toast.makeText(this, "Press back again to Exit the match", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    public void restartMatch(){

        boxpositions=new int[]{0,0,0,0,0,0,0,0,0};

        playerTurn=1;

        totalSelectionBoxes=1;

        image1.setImageResource(R.drawable.transparent_background);
        image2.setImageResource(R.drawable.transparent_background);
        image3.setImageResource(R.drawable.transparent_background);
        image4.setImageResource(R.drawable.transparent_background);
        image5.setImageResource(R.drawable.transparent_background);
        image6.setImageResource(R.drawable.transparent_background);
        image7.setImageResource(R.drawable.transparent_background);
        image8.setImageResource(R.drawable.transparent_background);
        image9.setImageResource(R.drawable.transparent_background);

    }
}