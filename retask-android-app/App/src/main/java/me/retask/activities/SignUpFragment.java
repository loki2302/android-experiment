package me.retask.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.retask.R;
import roboguice.fragment.RoboFragment;

// TODO
public class SignUpFragment extends RoboFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sign_up_view, container, false);
    }
}
