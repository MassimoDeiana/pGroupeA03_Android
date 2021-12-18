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
        public DtoOutputInterrogation mItem;

        public ViewHolder(FragmentInterrogationListItemBinding binding) {
            super(binding.getRoot());
            tvSubject = binding.tvInterrogationFragmentItemSubject;
        }

        public void bind(InterrogationListFragment.onInterrogationClickListener onInterrogationClickListener) {
            itemView.setOnClickListener(view -> onInterrogationClickListener.onInterrogationClick(mItem));
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvSubject.getText() + "'";
        }
    }
}