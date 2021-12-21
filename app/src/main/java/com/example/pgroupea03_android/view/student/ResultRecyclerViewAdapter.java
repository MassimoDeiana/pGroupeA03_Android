package com.example.pgroupea03_android.view.student;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pgroupea03_android.databinding.FragmentResultListItemBinding;
import com.example.pgroupea03_android.dtos.result.DtoOutputResult;

import java.util.List;

public class ResultRecyclerViewAdapter extends RecyclerView.Adapter<ResultRecyclerViewAdapter.ViewHolder> {

    private final List<DtoOutputResult> mValues;
    double sum;

    public ResultRecyclerViewAdapter(List<DtoOutputResult> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentResultListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final DtoOutputResult dtoOutputResult = mValues.get(position);
        holder.mItem = dtoOutputResult;
        holder.tvInterro.setText(dtoOutputResult.getInterro());
        holder.tvResult.setText(dtoOutputResult.getResult() + "/" + dtoOutputResult.getTotal());
        holder.tvMessage.setText(dtoOutputResult.getMessage());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvInterro;
        public final TextView tvResult;
        public final TextView tvMessage;
        public DtoOutputResult mItem;

        public ViewHolder(FragmentResultListItemBinding binding) {
            super(binding.getRoot());
            tvInterro = binding.tvResultFragmentItemInterro;
            tvResult = binding.tvResultFragmentItemResult;
            tvMessage = binding.tvResultFragmentItemMessage;
            sum = 0;
            if (mValues.size() > 0) {
                for (DtoOutputResult result :
                        mValues) {
                    sum += (result.getResult() / result.getTotal()) * 100;
                }
                sum = sum / mValues.size();
                sum = Math.round(sum * 100) / 100.;

                Toast.makeText(tvInterro.getContext(), "You have an average of " + sum + "/100", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvResult.getText() + "'";
        }
    }
}