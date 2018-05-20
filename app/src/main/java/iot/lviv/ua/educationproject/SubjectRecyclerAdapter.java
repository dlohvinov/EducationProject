package iot.lviv.ua.educationproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

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

    public SubjectRecyclerAdapter(ArrayList<Subject> subjects,MyRecyclerListener listener) {
        this.subjects = subjects;
        this.listener = listener;
    }

    class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView subjectNameTv;
        TextView lectorNameTv;
        ProgressBar progressBar;


        public MainViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.subjectNameTv = itemView.findViewById(R.id.subject_name);
            this.lectorNameTv = itemView.findViewById(R.id.lector_name);
            this.progressBar = itemView.findViewById(R.id.subjext_progress_bar);
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
        ProgressBar progressBar = ((MainViewHolder) holder).progressBar;

        subjectNameTv.setText(subjects.get(position).getSubjectName());
        lectorNameTv.setText(subjects.get(position).getLectorName());
        progressBar.setProgress(subjects.get(position).getProgress());
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    interface MyRecyclerListener{
        void onItemClick(int position);
    }
}
