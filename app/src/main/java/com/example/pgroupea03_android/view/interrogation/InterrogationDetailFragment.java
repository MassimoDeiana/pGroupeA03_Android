package com.example.pgroupea03_android.view.interrogation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pgroupea03_android.R;
import com.example.pgroupea03_android.dtos.interrogation.DtoOutputInterrogation;
import com.example.pgroupea03_android.infrastructure.IInterrogationRepository;
import com.example.pgroupea03_android.infrastructure.Retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InterrogationDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InterrogationDetailFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ID = "idinterrogation";

    private int idInterrogation;
    private DtoOutputInterrogation currentInterrogation;

    private TextView tvSubject;

    public InterrogationDetailFragment() {
        // Required empty public constructor
    }

    public static InterrogationDetailFragment newInstance(int idInterrogation) {
        InterrogationDetailFragment fragment = new InterrogationDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, idInterrogation);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idInterrogation = getArguments().getInt(ARG_ID);
            fetchInterrogationById(idInterrogation);
        }
    }

    private void fetchInterrogationById(int idInterrogation) {
        System.out.println(idInterrogation);
        Retrofit.getInstance(getContext()).create(IInterrogationRepository.class).getById(idInterrogation).enqueue(new Callback<DtoOutputInterrogation>() {
            @Override
            public void onResponse(Call<DtoOutputInterrogation> call, Response<DtoOutputInterrogation> response) {
                if(response.code() == 201) {
                    currentInterrogation = response.body();
                    updateView();
                }
            }

            @Override
            public void onFailure(Call<DtoOutputInterrogation> call, Throwable t) {

            }
        });
    }

    private void updateView() {
        tvSubject.setText(currentInterrogation.getSubject());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_interrogation_detail, container, false);
        tvSubject = view.findViewById(R.id.tv_interrogationDetailFragment_subject);
        return view;
    }
}