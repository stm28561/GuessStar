package com.example.guessstar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.guessstar.Stars.starsList;
import static com.example.guessstar.Stars.starsNames;

public class TheGuess extends AppCompatActivity {

    private ImageView imageViewStar;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private String randomStar;

    private ArrayList<String> makeStringArrayForRound () {
        ArrayList<String> arrayOfFourStars = new ArrayList<>();

        int counter = 0;
        while (counter < 4) {

            String name = Stars.getRandomName(Stars.getStarsNames());
            if (!arrayOfFourStars.contains(name)) {
                arrayOfFourStars.add(name);
                counter++;
            }

        }
        return arrayOfFourStars;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_guess3);
        imageViewStar = findViewById(R.id.imageViewStar);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        startTheGame();

    }

    public void startTheGame () {
        ArrayList<String> arrayOfFourStars = makeStringArrayForRound();
        button1.setText(arrayOfFourStars.get(0));
        button2.setText(arrayOfFourStars.get(1));
        button3.setText(arrayOfFourStars.get(2));
        button4.setText(arrayOfFourStars.get(3));
        randomStar = arrayOfFourStars.get((int) (Math.random() * 4));
        imageViewStar.setImageBitmap( starsList.get(randomStar));
    }

    public void onClickButton1(View view) {
        if (button1.getText().equals(randomStar)) {
            Toast.makeText(this, "This is the Right answer", Toast.LENGTH_SHORT).show();
            startTheGame();
        } else {
            Toast.makeText(this, "This is the Wrong answer", Toast.LENGTH_SHORT).show();
            startTheGame();
        }
    }

    public void onClickButton2(View view) {
        if (button2.getText().equals(randomStar)) {
            Toast.makeText(this, "This is the Right answer", Toast.LENGTH_SHORT).show();
            startTheGame();
        } else {
            Toast.makeText(this, "This is the Wrong answer", Toast.LENGTH_SHORT).show();
            startTheGame();
        }
    }

    public void onClickButton3(View view) {
        if (button3.getText().equals(randomStar)) {
            Toast.makeText(this, "This is the Right answer", Toast.LENGTH_SHORT).show();
            startTheGame();
        } else {
            Toast.makeText(this, "This is the Wrong answer", Toast.LENGTH_SHORT).show();
            startTheGame();
        }
    }

    public void onClickButton4(View view) {
        if (button4.getText().equals(randomStar)) {
            Toast.makeText(this, "This is the Right answer", Toast.LENGTH_SHORT).show();
            startTheGame();
        } else {
            Toast.makeText(this, "This is the Wrong answer", Toast.LENGTH_SHORT).show();
            startTheGame();
        }
    }
}