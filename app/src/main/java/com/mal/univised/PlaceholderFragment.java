package com.mal.univised;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by admin on 1/3/2017.
 */

public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private String [] questions;
    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        questions = getQuestions();
        View rootView = inflater.inflate(R.layout.fragment_layout, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.tv_label);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
        TextView tv_question = (TextView) rootView.findViewById(R.id.tv__question);
        EditText et_answer = (EditText) rootView.findViewById(R.id.et_answer);
        TextView tv_comments = (TextView) rootView.findViewById(R.id.tv_comments);
        EditText et_comments = (EditText) rootView.findViewById(R.id.et_comments);
        tv_question.setText(questions[getArguments().getInt(ARG_SECTION_NUMBER)]);
        return rootView;
    }

    public String[] getQuestions(){
        String [] question = {"How would you rate the cost of living?",
                "How would you rate the universities accommodation?",
                "How would you rate the night life?",
                "How would you rate the university sporting events?",
                "How would you rate the student union?",
                "How would you rate the university facilities?",
                "How would you rate the university lecturers?",
                "How would you rate the university societies?"};
        return question;
    }
}
