package com.example.pgroupea03_android.view.interrogation;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.pgroupea03_android.R;
import com.example.pgroupea03_android.dtos.interrogation.DtoOutputInterrogation;
import com.example.pgroupea03_android.infrastructure.IInterrogationRepository;
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
public class InterrogationListFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private InterrogationRecyclerViewAdapter interrogationRecyclerViewAdapter;
    private List<DtoOutputInterrogation> interrogationList = new ArrayList<>();

    public interface onInterrogationClickListener {
        void onInterrogationClick(DtoOutputInterrogation dtoOutputInterrogation);
        void onAddButtonClick(ImageButton imageButton);
    }
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public InterrogationListFragment() {
    }

    @SuppressWarnings("unused")
    public static InterrogationListFragment newInstance(int columnCount) {
        InterrogationListFragment fragment = new InterrogationListFragment();
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
        fetchInterrogationList();
    }

    private void fetchInterrogationList() {
        SessionManager sessionManager = new SessionManager(getContext());
        int idTeacher = sessionManager.fetchAuthId();
        Retrofit.getInstance(getContext()).create(IInterrogationRepository.class).getByTeacher(idTeacher).enqueue(new Callback<List<DtoOutputInterrogation>>() {
            @Override
            public void onResponse(Call<List<DtoOutputInterrogation>> call, Response<List<DtoOutputInterrogation>> response) {
                interrogationList.addAll(response.body());
                interrogationRecyclerViewAdapter.notifyItemChanged(0);
            }

            @Override
            public void onFailure(Call<List<DtoOutputInterrogation>> call, Throwable t) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_interrogation_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            onInterrogationClickListener onInterrogationClickListener = null;
            try {
                onInterrogationClickListener = (onInterrogationClickListener) getActivity();
            } catch (ClassCastException e) {
                Log.e("InterfaceImplementation", "The activity must implement OnObjectClickListener");
            }
            interrogationRecyclerViewAdapter = new InterrogationRecyclerViewAdapter(interrogationList, onInterrogationClickListener);
            recyclerView.setAdapter(interrogationRecyclerViewAdapter);
        }
        return view;
    }
}