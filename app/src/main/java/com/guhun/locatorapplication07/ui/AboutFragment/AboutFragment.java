package com.guhun.locatorapplication07.ui.AboutFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guhun.locatorapplication07.R;


public class AboutFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv_about = getActivity().findViewById(R.id.tv_about);
        String about =
                "    <div style=\"text-align: center;color: #456;\">\n" +
                        "        <h1>关于我们</h1>\n" +
                        "        <h2>南昌工程学院</h2>\n" +
                        "        <h3>计科卓越：崔世杰</h3>\n" +
                        "        <h3>指导老师：冯祥胜</h3>\n" +
                        "        <h4>——2021年3月</h4>\n" +
                        "    </div>";
        tv_about.setText(Html.fromHtml(about));

    }
}