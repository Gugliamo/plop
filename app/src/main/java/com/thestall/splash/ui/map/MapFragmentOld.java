package com.thestall.splash.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.thestall.splash.R;

import java.util.List;

public class MapFragmentOld extends Fragment {

    private MapViewModel mapViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mapViewModel =
                ViewModelProviders.of(this).get(MapViewModel.class);



        View root = inflater.inflate(R.layout.fragment_map_old, container, false);


        //not needed code block, remove when have time
        final TextView textView = root.findViewById(R.id.text_dashboard);
        mapViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        final FragmentManager fm = getChildFragmentManager();
        List<Fragment> fragments = fm.getFragments();

        final Fragment details = this.getChildFragmentManager().findFragmentByTag("locationDetails");
        fm.beginTransaction().hide(details).commit();


        //onclick listener for end point imageButton
        view.findViewById(R.id.endLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(details.isHidden())
                {
                    fm.beginTransaction()
                            .show(details)
                            .commit();
                }
                else
                {
                    fm.beginTransaction()
                            .hide(details)
                            .commit();
                }
            }
        });



    }
}