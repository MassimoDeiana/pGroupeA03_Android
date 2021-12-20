package com.example.pgroupea03_android.view.interrogation;

import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pgroupea03_android.NoteAddActivity;
import com.example.pgroupea03_android.databinding.FragmentInterrogationListItemBinding;
import com.example.pgroupea03_android.dtos.interrogation.DtoOutputInterrogation;
import com.example.pgroupea03_android.infrastructure.IInterrogationRepository;
import com.example.pgroupea03_android.infrastructure.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterrogationRecyclerViewAdapter extends RecyclerView.Adapter<InterrogationRecyclerViewAdapter.ViewHolder> {

    private final List<DtoOutputInterrogation> mValues;
    private InterrogationListFragment.onInterrogationClickListener interrogationClickListener;

    public InterrogationRecyclerViewAdapter(List<DtoOutputInterrogation> interros, InterrogationListFragment.onInterrogationClickListener interrogationClickListener) {
        mValues = interros;
        this.interrogationClickListener = interrogationClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentInterrogationListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final DtoOutputInterrogation dtoOutputInterrogation = mValues.get(position);
        holder.mItem = dtoOutputInterrogation;
        holder.tvSubject.setText(dtoOutputInterrogation.getSubject());
        holder.bind(interrogationClickListener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvSubject;
        public final ImageButton btnAdd;
        public final ImageButton btnDelete;
        public DtoOutputInterrogation mItem;

        public ViewHolder(FragmentInterrogationListItemBinding binding) {
            super(binding.getRoot());
            tvSubject = binding.tvInterrogationFragmentItemSubject;
            btnAdd = binding.btnInterrogationFragmentItemAdd;
            btnDelete = binding.btnInterrogationFragmentItemDelete;
        }

        public void bind(InterrogationListFragment.onInterrogationClickListener onInterrogationClickListener) {
            itemView.setOnClickListener(view -> onInterrogationClickListener.onInterrogationClick(mItem));

            btnAdd.setOnClickListener(view -> onInterrogationClickListener.onAddButtonClick(btnAdd));

            //Bouton permettant de supprimer une interrogation
            btnDelete.setOnClickListener(view -> {
                //Dialog permettant de demander une validation avant d'effacer
                DialogInterface.OnClickListener dialogClickListener = (dialogInterface, i) -> {
                    switch (i) {
                        case DialogInterface.BUTTON_POSITIVE:
                            Retrofit.getInstance(view.getContext()).create(IInterrogationRepository.class)
                                    .delete(mItem.getIdInterro()).enqueue(new Callback<Void>() {
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
                builder.setTitle("Delete " + mItem.getSubject());
                builder.setMessage("This action will remove the interrogation and all related notes.\nAre you sure ?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvSubject.getText() + "'";
        }
    }
}