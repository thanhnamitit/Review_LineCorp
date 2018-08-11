package com.linecorp.reviewexam.rockpaperscissors;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import androidx.fragment.app.Fragment;

/**
 * A placeholder to keep hand buttons and a text view for results.
 */
public class GamePlayFragment extends Fragment implements View.OnClickListener {
    Hand opponentHand = HandManager.Object.ROCK;
    Hand userHand;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_game_play, container, false);
        initButton(fragmentView, R.id.btn_game_rock, HandManager.Object.ROCK);
        initButton(fragmentView, R.id.btn_game_scissors, HandManager.Object.SCISSORS);
        initButton(fragmentView, R.id.btn_game_paper, HandManager.Object.PAPER);
        return fragmentView;
    }

    public void initButton(View fragmentView, int id, Hand hand) {
        HandButton handButton = fragmentView.findViewById(id);
        handButton.setOnClickListener(this);
        handButton.setHand(hand);
    }

    @Override
    public void onClick(View v) {
        HandButton button = (HandButton) v;
        if (button.getRest() != 0) {
            button.play();
            button.updateText();
            userHand = button.getHand();
            getOpponentHand();
        }
    }

    public void onGetOpponentHandSuccess() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int result = userHand.compareTo(opponentHand);
                if (result < 0) {
                    printResult("LOSE");
                } else if (result > 0) {
                    printResult("WIN");
                } else {
                    printResult("DRAW");
                }
            }
        });
    }

    public void onGetOpponentHandError() {
        // do something
    }

    public void printResult(String result) {
        TextView resultTextView = getActivity().findViewById(R.id.tv_result);
        resultTextView.setText(resultTextView.getText() + result
                + "(You: " + userHand.toString() + ", Opponent: "
                + opponentHand.toString() + ")\n");
    }

    public void getOpponentHand() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://example.com/randhand");
                    URLConnection connection = url.openConnection();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(
                            new BufferedInputStream(connection.getInputStream())));
                    String str = reader.readLine();
                    while (!TextUtils.isEmpty(str)) {
                        opponentHand = HandManager.fromInt(Integer.valueOf(str.trim()));
                        str = reader.readLine();
                    }
                    onGetOpponentHandSuccess();
                } catch (IOException e) {
                    onGetOpponentHandError();
                    ErrorReporter.report(e);
                }
            }
        }).start();
    }
}
