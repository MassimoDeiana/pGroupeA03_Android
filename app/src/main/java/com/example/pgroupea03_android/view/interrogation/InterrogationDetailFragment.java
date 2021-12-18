package com.example.pgroupea03_android.view.interrogation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    private IInterrogationRepository repository;

    private TextView tvSubject;
    private TextView tvTotal;

    private Button btnDelete;

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
            repository = Retrofit.getInstance(getContext()).create(IInterrogationRepository.class);
            fetchInterrogationById(idInterrogation);
        }
    }

    private void fetchInterrogationById(int idInterrogation) {
        repository.getById(idInterrogation).enqueue(new Callback<DtoOutputInterrogation>() {
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
        tvTotal.setText(String.valueOf(currentInterrogation.getTotal()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_interrogation_detail, container, false);
        tvSubject = view.findViewById(R.id.tv_interrogationDetailFragment_subject);
        tvTotal = view.findViewById(R.id.tv_interrogationDetailFragment_total);
        btnDelete = view.findViewById(R.id.tv_interrogationDetailFragment_delete);
        initListeners();
        return view;
    }

    private void initListeners() {
        btnDelete.setOnClickListener(view -> {
            repository.delete(idInterrogation).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response.code() == 200) {
                        Toast.makeText(getContext(), "Interrogation deleted successfully", Toast.LENGTH_LONG).show();
                        fetchInterrogationById(1);
                    } else {
                        Toast.makeText(getContext(), "At least one student has a note on this question. Please delete it first.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
        });
    }
}