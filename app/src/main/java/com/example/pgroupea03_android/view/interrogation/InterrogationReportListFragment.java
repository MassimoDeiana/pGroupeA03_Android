package com.example.pgroupea03_android.view.interrogation;

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
import com.example.pgroupea03_android.dtos.interrogationreport.DtoOutputInterrogationReport;
import com.example.pgroupea03_android.infrastructure.IInterrogationReportRepository;
import com.example.pgroupea03_android.infrastructure.Retrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class InterrogationReportListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_ID_INTERRO = "id-interro";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private int idInterro;
    private InterrogationReportRecyclerViewAdapter interrogationReportRecyclerViewAdapter;
    private List<DtoOutputInterrogationReport> interrogationReportList = new ArrayList<>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public InterrogationReportListFragment() {
    }

    @SuppressWarnings("unused")
    public static InterrogationReportListFragment newInstance(int columnCount, int idInterro) {
        InterrogationReportListFragment fragment = new InterrogationReportListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putInt(ARG_ID_INTERRO, idInterro);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            idInterro = getArguments().getInt(ARG_ID_INTERRO);
        }
        fetchInterrogationReportList();
    }

    private void fetchInterrogationReportList() {
        Retrofit.getInstance(getContext()).create(IInterrogationReportRepository.class).getStudentsByInterro(idInterro).enqueue(new Callback<List<DtoOutputInterrogationReport>>() {
                    @Override
                    public void onResponse(Call<List<DtoOutputInterrogationReport>> call, Response<List<DtoOutputInterrogationReport>> response) {
                        if(response.code() == 200) {
                            interrogationReportList.addAll(response.body());
                            interrogationReportRecyclerViewAdapter.notifyItemChanged(0);
                        } else {
                            Toast.makeText(getContext(), "You don't have the right permissions to do that.", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<DtoOutputInterrogationReport>> call, Throwable t) {

                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_interrogation_report_list_, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            interrogationReportRecyclerViewAdapter = new InterrogationReportRecyclerViewAdapter(interrogationReportList);
            recyclerView.setAdapter(interrogationReportRecyclerViewAdapter);
        }
        return view;
    }
}