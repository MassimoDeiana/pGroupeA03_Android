package com.example.pgroupea03_android.view.teacher;

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
import com.example.pgroupea03_android.dtos.teacher.DtoOutputTeacher;
import com.example.pgroupea03_android.infrastructure.ITeacherRepository;
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
public class TeacherListFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private TeacherRecyclerViewAdapter teacherRecyclerViewAdapter;
    private List<DtoOutputTeacher> teacherList = new ArrayList<>();
    private SessionManager sessionManager;
    private retrofit2.Retrofit retrofit;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TeacherListFragment() {
    }

    @SuppressWarnings("unused")
    public static TeacherListFragment newInstance(int columnCount) {
        TeacherListFragment fragment = new TeacherListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        retrofit = Retrofit.getInstance(getContext());
        fetchTeacherList();
    }

    private void fetchTeacherList() {

        retrofit.create(ITeacherRepository.class).getAll().enqueue(new Callback<List<DtoOutputTeacher>>() {
            @Override
            public void onResponse(Call<List<DtoOutputTeacher>> call, Response<List<DtoOutputTeacher>> response) {
                if(response.code() == 201){
                    teacherList.addAll(response.body());
                    teacherRecyclerViewAdapter.notifyItemChanged(0);
                } else {
                    Toast.makeText(getContext(), "You don't have the right permissions to do that." + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<DtoOutputTeacher>> call, Throwable t) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            teacherRecyclerViewAdapter = new TeacherRecyclerViewAdapter(teacherList);
            recyclerView.setAdapter(teacherRecyclerViewAdapter);
        }
        return view;
    }
}