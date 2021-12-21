package com.example.pgroupea03_android.view.interrogation;

import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pgroupea03_android.databinding.FragmentInterrogationReportListItemBinding;
import com.example.pgroupea03_android.dtos.interrogationreport.DtoOutputInterrogationReport;
import com.example.pgroupea03_android.infrastructure.INoteRepository;
import com.example.pgroupea03_android.infrastructure.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterrogationReportRecyclerViewAdapter extends RecyclerView.Adapter<InterrogationReportRecyclerViewAdapter.ViewHolder> {

    private final List<DtoOutputInterrogationReport> mValues;

    public InterrogationReportRecyclerViewAdapter(List<DtoOutputInterrogationReport> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentInterrogationReportListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final DtoOutputInterrogationReport dtoOutputInterrogationReport = mValues.get(position);
        holder.mItem = dtoOutputInterrogationReport;
        holder.tvName.setText(dtoOutputInterrogationReport.getName());
        holder.tvFirstName.setText(dtoOutputInterrogationReport.getFirstName());
        holder.tvResult.setText(dtoOutputInterrogationReport.getResult() + "/" + dtoOutputInterrogationReport.getTotal());
        holder.tvMessage.setText(dtoOutputInterrogationReport.getMessage());
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvName;
        public final TextView tvFirstName;
        public final TextView tvResult;
        public final TextView tvMessage;
        public final ImageButton btnDelete;
        public DtoOutputInterrogationReport mItem;

        public ViewHolder(FragmentInterrogationReportListItemBinding binding) {
            super(binding.getRoot());
            tvName = binding.tvInterrogationReportFragmentItemName;
            tvFirstName = binding.tvInterrogationReportFragmentItemFirstName;
            tvResult = binding.tvInterrogationReportFragmentItemResult;
            tvMessage = binding.tvInterrogationReportFragmentItemMessage;

            btnDelete = binding.btnInterrogationReportFragmentItemDelete;
        }

        public void bind() {
            //Bouton permettant de supprimer la note d'un élève
            btnDelete.setOnClickListener(view -> {
                //Dialog permettant de demander une validation avant d'effacer
                DialogInterface.OnClickListener dialogClickListener = (dialogInterface, i) -> {
                    switch (i) {
                        case DialogInterface.BUTTON_POSITIVE:
                            Retrofit.getInstance(view.getContext()).create(INoteRepository.class)
                                    .delete(mItem.getIdInterro(), mItem.getIdStudent()).enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if(response.code() == 200) {
                                        mValues.remove(mItem);
                                        notifyItemRemoved(getLayoutPosition());
                                        notifyItemRangeChanged(mItem.getIdInterro(), mValues.size());
                                    } else {
                                        Toast.makeText(view.getContext(),
                                                "At least one student has a note on this question. Please delete it first.",
                                                Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {

                                }
                            });
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Delete the note");
                builder.setMessage("This action will remove the note of\n" + mItem.getName() +  " " + mItem.getFirstName() + ".\nAre you sure ?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvFirstName.getText() + "'";
        }
    }
}