package com.example.pgroupea03_android.view.teacher;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pgroupea03_android.databinding.FragmentTeacherListItemBinding;
import com.example.pgroupea03_android.dtos.teacher.DtoOutputTeacher;

import java.util.List;

public class TeacherRecyclerViewAdapter extends RecyclerView.Adapter<TeacherRecyclerViewAdapter.ViewHolder> {

    private final List<DtoOutputTeacher> mValues;

    public TeacherRecyclerViewAdapter(List<DtoOutputTeacher> teachers) {
        mValues = teachers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentTeacherListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final DtoOutputTeacher dtoOutputTeacher = mValues.get(position);
        holder.mItem = dtoOutputTeacher;
        holder.tvLastName.setText(dtoOutputTeacher.getName());
        holder.tvMail.setText(dtoOutputTeacher.getMail());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvLastName;
        public final TextView tvMail;
        public DtoOutputTeacher mItem;

        public ViewHolder(FragmentTeacherListItemBinding binding) {
            super(binding.getRoot());
            tvLastName = binding.tvTeacherFragmentItemLastName;
            tvMail = binding.tvTeacherFragmentItemMail;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvMail.getText() + "'";
        }
    }
}