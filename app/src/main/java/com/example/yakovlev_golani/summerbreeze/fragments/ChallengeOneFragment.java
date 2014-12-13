package com.example.yakovlev_golani.summerbreeze.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yakovlev_golani.summerbreeze.R;

/**
 * Created by Yakovlev-Golani on 11/12/14.
 */
public class ChallengeOneFragment extends Fragment{
    private TextView mMirrorText;

    public ChallengeOneFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.challenge1, container, false);

        mMirrorText = ((TextView) rootView.findViewById(R.id.text_mirror));
        ((EditText) rootView.findViewById(R.id.edit_me)).addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        mMirrorText.setText(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                }
        );

        return rootView;
    }
}
