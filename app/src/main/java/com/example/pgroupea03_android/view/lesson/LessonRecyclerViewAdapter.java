package com.example.pgroupea03_android.view.lesson;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pgroupea03_android.databinding.FragmentInterrogationListItemBinding;
import com.example.pgroupea03_android.databinding.FragmentLessonListItemBinding;
import com.example.pgroupea03_android.dtos.interrogation.DtoOutputInterrogation;
import com.example.pgroupea03_android.dtos.lesson.DtoOutputLesson;
import com.example.pgroupea03_android.infrastructure.IInterrogationRepository;
import com.example.pgroupea03_android.infrastructure.ILessonRepository;
import com.example.pgroupea03_android.infrastructure.Retrofit;
import com.example.pgroupea03_android.view.interrogation.InterrogationListFragment;
import com.example.pgroupea03_android.view.interrogation.InterrogationRecyclerViewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LessonRecyclerViewAdapter extends RecyclerView.Adapter<LessonRecyclerViewAdapter.ViewHolder>{

    private final List<DtoOutputLesson> mValues;
    private LessonListFragment.onLessonClickListener lessonClickListener;

    public LessonRecyclerViewAdapter(List<DtoOutputLesson> lessons, LessonListFragment.onLessonClickListener lessonClickListener) {
        mValues = lessons;
        this.lessonClickListener = lessonClickListener;
    }

    @Override
    public LessonRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new LessonRecyclerViewAdapter.ViewHolder(FragmentLessonListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final LessonRecyclerViewAdapter.ViewHolder holder, int position) {
        final DtoOutputLesson dtoOutputLesson = mValues.get(position);
        holder.mItem = dtoOutputLesson;
        holder.tvSubject.setText(dtoOutputLesson.getSubject());
        holder.bind(lessonClickListener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvSubject;
        public final ImageButton btnDelete;
        public DtoOutputLesson mItem;

        public ViewHolder(FragmentLessonListItemBinding binding) {
            super(binding.getRoot());
            tvSubject = binding.tvLessonFragmentItemSubject;
            btnDelete = binding.btnLessonFragmentItemDelete;
        }

        public void bind(LessonListFragment.onLessonClickListener onLessonClickListener) {
            itemView.setOnClickListener(view -> onLessonClickListener.onLessonClick(mItem));

            //Bouton permettant de supprimer une Lesson
            btnDelete.setOnClickListener(view -> {
                //Dialog permettant de demander une validation avant d'effacer
                DialogInterface.OnClickListener dialogClickListener = (dialogInterface, i) -> {
                    switch (i) {
                        case DialogInterface.BUTTON_POSITIVE:
                            Retrofit.getInstance(view.getContext()).create(ILessonRepository.class)
                                    .delete(mItem.getIdLesson()).enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if(response.code() == 200) {
                                        mValues.remove(mItem);
                                        notifyItemRemoved(getLayoutPosition());
                                        notifyItemRangeChanged(mItem.getIdLesson(), mValues.size());
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
                builder.setMessage("This action will remove the Lesson and all related notes.\nAre you sure ?")
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
