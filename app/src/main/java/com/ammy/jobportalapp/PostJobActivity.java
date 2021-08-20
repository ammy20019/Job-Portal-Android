package com.ammy.jobportalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ammy.jobportalapp.Model.Data;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

public class PostJobActivity extends AppCompatActivity {

    private FloatingActionButton fabBtn;

    //Recylcler view activity
    private RecyclerView recyclerView;

    //Firebase..
    private FirebaseAuth mAuth;
    private DatabaseReference JobPostDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);

        fabBtn = findViewById(R.id.fab_add);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser=mAuth.getCurrentUser();
        String uId = mUser.getUid();

        JobPostDatabase= FirebaseDatabase.getInstance().getReference().child("Job Post").child(uId);

        recyclerView=findViewById(R.id.recycler_job_post_id);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),InsertJobPostActivity.class));
            }
        });
    }


    protected void onStart(){
        super.onStart();

//        Query query = FirebaseDatabase.getInstance()
//                .getReference()
//                .child("Job Post");

        FirebaseRecyclerOptions<Data> firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<Data>().setQuery(JobPostDatabase, Data.class).build();
        FirebaseRecyclerAdapter<Data,MyViewHolder> adapter = new FirebaseRecyclerAdapter<Data,MyViewHolder>
                (firebaseRecyclerOptions){
            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.job_post_item, parent, false);
                return new MyViewHolder(view);

            }

            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i, @NonNull Data model) {
                viewHolder.setJobTitle(model.getTitle());
                viewHolder.setJobDate(model.getDate());
                viewHolder.setJobDescription(model.getDescription());
                viewHolder.setJobSkills(model.getSkills());
                viewHolder.setJobSalary(model.getSalary());
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);



    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        View myview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myview = itemView;
        }

        public void setJobTitle(String title){
            TextView mTitle = myview.findViewById(R.id.job_title);
            mTitle.setText(title);
        }

        public void setJobDate(String date){
            TextView mDate = myview.findViewById(R.id.job_date);
            mDate.setText(date);
        }

        public void setJobDescription(String description){
            TextView mDescription= myview.findViewById(R.id.job_description);
            mDescription.setText(description);
        }

        public void setJobSkills(String skills){
            TextView mSkills= myview.findViewById(R.id.job_skills);
            mSkills.setText(skills);
        }

        public void setJobSalary(String salary){
            TextView mSalary = myview.findViewById(R.id.job_salary);
            mSalary.setText("Salary");
        }
    }
}