package com.example.pgroupea03_android.view.interrogation;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pgroupea03_android.databinding.FragmentInterrogationListItemBinding;
import com.example.pgroupea03_android.dtos.interrogation.DtoOutputInterrogation;

import java.util.List;

public class InterrogationRecyclerViewAdapter extends RecyclerView.Adapter<InterrogationRecyclerViewAdapter.ViewHolder> {

    private final List<DtoOutputInterrogation> mValues;

    public InterrogationRecyclerViewAdapter(List<DtoOutputInterrogation> interros) {
        mValues = interros;
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
        holder.tvTotal.setText(Integer.toString(dtoOutputInterrogation.getTotal()));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvSubject;
        public final TextView tvTotal;
        public DtoOutputInterrogation mItem;

        public ViewHolder(FragmentInterrogationListItemBinding binding) {
            super(binding.getRoot());
            tvSubject = binding.tvInterrogationFragmentItemSubject;
            tvTotal = binding.tvInterrogationFragmentItemTotal;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvSubject.getText() + "'";
        }
    }
}