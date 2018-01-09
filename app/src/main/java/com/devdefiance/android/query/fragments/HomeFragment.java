package com.devdefiance.android.query.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devdefiance.android.query.MainActivity;
import com.devdefiance.android.query.R;
import com.devdefiance.android.query.utils.AppConstants;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by darth on 1/9/18.
 * <p>
 * The HomeFragment handles all query inputs and displays
 * results based on the sources that have been allowed by
 * the user.
 */

public class HomeFragment extends Fragment implements
        View.OnClickListener {

    private View mainView;
    private AppCompatEditText searbarEdittext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.main_home_fragment_layout, container, false);

        // Initialize the views
        searbarEdittext = mainView.findViewById(R.id.main_search_bar_edittext);
        // Listen for Mic input
        mainView.findViewById(R.id.main_search_bar_microphone).setOnClickListener(this);

        return mainView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == AppConstants.SPEECH_INPUT_REQUEST) &&
                (resultCode == MainActivity.RESULT_OK)) {

            // A speech input has been received
            if (data == null) {
                showError("Could not receive input");

            } else {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                searbarEdittext.setText(result.get(0));
            }
        }
    }

    @Override
    public void onClick(View view) {
        // Handle view clicks here.
        switch (view.getId()) {

            case R.id.main_search_bar_camera:
                break;

            case R.id.main_search_bar_microphone:
                receiveSpeechInput();
                break;
        }
    }

    private void receiveSpeechInput() {
        // Microphone has been clicked, get speech input
        Intent speech_intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speech_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        speech_intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speech_intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_input_prompt));
        try {
            startActivityForResult(speech_intent, AppConstants.SPEECH_INPUT_REQUEST);
        } catch (ActivityNotFoundException anfe) {
            showError(anfe.getMessage());
        }
    }


    private void showError(String message) {
        //TODO: Implement a better Error block
        Toast.makeText(mainView.getContext(),
                message, Toast.LENGTH_SHORT)
                .show();
    }

}
