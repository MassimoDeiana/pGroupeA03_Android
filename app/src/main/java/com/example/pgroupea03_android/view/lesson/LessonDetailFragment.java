package com.example.pgroupea03_android.view.lesson;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.pgroupea03_android.R;
import com.example.pgroupea03_android.dtos.interrogation.DtoOutputInterrogation;
import com.example.pgroupea03_android.dtos.lesson.DtoOutputLesson;
import com.example.pgroupea03_android.infrastructure.IInterrogationRepository;
import com.example.pgroupea03_android.infrastructure.ILessonRepository;
import com.example.pgroupea03_android.infrastructure.Retrofit;
import com.example.pgroupea03_android.view.interrogation.InterrogationDetailFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LessonDetailFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ID = "idlesson";

    private int idLesson;
    private DtoOutputLesson currentLesson;
    private ILessonRepository repository;

    private TextView tvSubject;

    private Button btnDelete;

    public LessonDetailFragment() {
        // Required empty public constructor
    }

    public static LessonDetailFragment newInstance(int idLesson) {
        LessonDetailFragment fragment = new LessonDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, idLesson);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idLesson = getArguments().getInt(ARG_ID);
            repository = Retrofit.getInstance(getContext()).create(ILessonRepository.class);
            fetchLessonById(idLesson);
        }
    }


    private void fetchLessonById(int idLesson) {
        repository.getById(idLesson).enqueue(new Callback<DtoOutputLesson>() {
            @Override
            public void onResponse(Call<DtoOutputLesson> call, Response<DtoOutputLesson> response) {
                if(response.code() == 201) {
                    currentLesson = response.body();
                    updateView();
                }
            }

            @Override
            public void onFailure(Call<DtoOutputLesson> call, Throwable t) {

            }
        });
    }

    private void updateView() {
        tvSubject.setText(currentLesson.getSubject());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lesson_detail, container, false);
        tvSubject = view.findViewById(R.id.tv_lessonDetailFragment_subject);
        btnDelete = view.findViewById(R.id.tv_lessonDetailFragment_delete);
        initListeners();
        return view;
    }

    private void initListeners() {
        btnDelete.setOnClickListener(view -> {
            repository.delete(idLesson).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response.code() == 200) {
                        Toast.makeText(getContext(), "Lesson deleted successfully", Toast.LENGTH_LONG).show();
                        fetchLessonById(1);
                    } else {
                        Toast.makeText(getContext(), "This lesson is used", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
        });
    }


}
