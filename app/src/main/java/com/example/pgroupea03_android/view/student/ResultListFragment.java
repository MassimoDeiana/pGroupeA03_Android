package com.example.pgroupea03_android.view.student;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pgroupea03_android.R;
import com.example.pgroupea03_android.dtos.interrogation.DtoOutputInterrogation;
import com.example.pgroupea03_android.dtos.result.DtoOutputResult;
import com.example.pgroupea03_android.infrastructure.IInterrogationRepository;
import com.example.pgroupea03_android.infrastructure.IResultRepository;
import com.example.pgroupea03_android.infrastructure.Retrofit;
import com.example.pgroupea03_android.services.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class ResultListFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_ID_LESSON = "id-lesson";
    private int mColumnCount = 1;
    private int idLesson;

    private retrofit2.Retrofit retrofit;
    private ResultRecyclerViewAdapter resultRecyclerViewAdapter;
    private List<DtoOutputResult> resultList = new ArrayList<>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ResultListFragment() {
    }

    @SuppressWarnings("unused")
    public static ResultListFragment newInstance(int columnCount, int idLesson) {
        ResultListFragment fragment = new ResultListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putInt(ARG_ID_LESSON, idLesson);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            idLesson = getArguments().getInt(ARG_ID_LESSON);
        }
        retrofit = Retrofit.getInstance(getContext());
        fetchResultList();
    }

    private void fetchResultList() {
        SessionManager sessionManager = new SessionManager(getContext());
        int idStudent = sessionManager.fetchAuthId();
        retrofit.create(IResultRepository.class).getResults(idStudent).enqueue(new Callback<List<DtoOutputResult>>() {
            @Override
            public void onResponse(Call<List<DtoOutputResult>> call, Response<List<DtoOutputResult>> response) {
                if (response.code() == 200) {
                    for (DtoOutputResult result :
                            response.body()) {
                        retrofit.create(IInterrogationRepository.class).getById(result.getIdInterro()).enqueue(new Callback<DtoOutputInterrogation>() {
                            @Override
                            public void onResponse(Call<DtoOutputInterrogation> call, Response<DtoOutputInterrogation> response) {
                                if(result.getIdLesson() == idLesson) {
                                    DtoOutputInterrogation interrogation = response.body();
                                    result.setInterro(interrogation.getSubject());
                                    resultList.add(result);
                                    resultRecyclerViewAdapter.notifyItemChanged(0);
                                }
                            }

                            @Override
                            public void onFailure(Call<DtoOutputInterrogation> call, Throwable t) {

                            }
                        });
                    }
                } else {
                    Toast.makeText(getContext(), "You don't have the right permissions to do that.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<DtoOutputResult>> call, Throwable t) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            resultRecyclerViewAdapter = new ResultRecyclerViewAdapter(resultList);
            recyclerView.setAdapter(resultRecyclerViewAdapter);
        }
        return view;
    }
}