package iot.lviv.ua.educationproject;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maksym Ivanov on 20/05/2018.
 */

public class SubjectRecyclerAdapter extends RecyclerView.Adapter {

    ArrayList<Subject> subjects;
    MyRecyclerListener listener;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_subject, parent, false));
    }

    public SubjectRecyclerAdapter(ArrayList<Subject> subjects, MyRecyclerListener listener) {
        this.subjects = subjects;
        this.listener = listener;
    }

    class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView subjectNameTv;
        TextView lectorNameTv;
        TextView averageEvaluation;
        ProgressBar progressBar;


        public MainViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.subjectNameTv = itemView.findViewById(R.id.subject_name);
            this.lectorNameTv = itemView.findViewById(R.id.lector_name);
            this.progressBar = itemView.findViewById(R.id.subjext_progress_bar);
            this.averageEvaluation = itemView.findViewById(R.id.averageEvaluation);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition());
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView lectorNameTv = ((MainViewHolder) holder).lectorNameTv;
        TextView subjectNameTv = ((MainViewHolder) holder).subjectNameTv;
        TextView averageEvaluation = ((MainViewHolder) holder).averageEvaluation;
        ProgressBar progressBar = ((MainViewHolder) holder).progressBar;

        progressBar.setProgress((int)subjects.get(position).getProgress());
        PorterDuff.Mode progressBarMode = PorterDuff.Mode.SRC_IN;

        Drawable progressDrawable = progressBar.getProgressDrawable().mutate();
        if (subjects.get(position).getProgress() < 35){
            progressDrawable.setColorFilter(Color.RED, progressBarMode);

        }else if (subjects.get(position).getProgress() < 75){
            progressDrawable.setColorFilter(Color.BLUE, progressBarMode);

        }else {
            progressDrawable.setColorFilter(Color.GREEN, progressBarMode);
        }
        progressBar.setProgressDrawable(progressDrawable);

        subjectNameTv.setText(subjects.get(position).getSubjectName());
        lectorNameTv.setText(subjects.get(position).getLectorName());
        averageEvaluation.setText(subjects.get(position).getProgress() + "%");
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    interface MyRecyclerListener{
        void onItemClick(int position);
    }
}
