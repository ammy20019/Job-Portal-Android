package com.ammy.jobportalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AllJobActivity extends AppCompatActivity {

    private Toolbar toolbar;

    //Recycler
    private RecyclerView recyclerView;

    //adding databse firebase
    private DatabaseReference mAllJobPOst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_job);

        toolbar = findViewById(R.id.all_job_post);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All Job Post");

        //dtabase
        mAllJobPOst= FirebaseDatabase.getInstance().getReference().child("Job Post");
        mAllJobPOst.keepSynced(true);


        recyclerView=findViewById(R.id.recycler_all_job);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    public static class AllJobPostViewHolder extends RecyclerView.ViewHolder{

        View myview;
        public AllJobPostViewHolder(@NonNull View itemView) {
            super(itemView);
            myview=itemView;
        }

        public void setJobTitle(String title){
            TextView mTitle = myview.findViewById(R.id.all_job_post_title);
            mTitle.setText(title);
        }
        public void setJobDescription(String description){
            TextView mDescription = myview.findViewById(R.id.all_job_post_description);
            mDescription.setText(description);
        }
        public void setJobDate(String date){
            TextView mDate = myview.findViewById(R.id.all_job_post_date);
            mDate.setText(date);
        }
        public void setJobSkills(String skills){
            TextView mSkills = myview.findViewById(R.id.all_job_post_skills);
            mSkills.setText(skills);
        }
        public void setJobSalary(String salary){
            TextView mSalary = myview.findViewById(R.id.all_job_post_salary);
            mSalary.setText(salary);
        }

    }
}