package com.example.project1.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.project1.LogIn;
import com.example.project1.MainActivity;
import com.example.project1.PostDetail;
import com.example.project1.R;
import com.example.project1.SignUpPage;
import com.example.project1.Task;
import com.example.project1.User;
import com.example.project1.changePwdActivity;
import com.example.project1.ui.mypost.MyPostFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private DatabaseReference dbHistory = FirebaseDatabase.getInstance().getReference("History");
    private HomeViewModel homeViewModel;
    private DatabaseReference dbTask ;
    public ArrayList<Task> allTitles = new ArrayList<>();
    private PostAAdapter adapter;
    private String userName;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MainActivity activity = (MainActivity) getActivity();
        userName = activity.getUserName();
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);



        dbTask= FirebaseDatabase.getInstance().getReference();
        Query query = dbTask.child("Task").orderByChild("publisher");
        query.addListenerForSingleValueEvent(valueEventListener);
        Query query2 = dbTask.child("History");
        query2.addListenerForSingleValueEvent(valueEventListener2);

        adapter = new PostAAdapter(getContext(), allTitles);
        ListView taskList = root.findViewById(R.id.HomeListView);
        SearchView search = root.findViewById(R.id.mainSearchView);
        taskList.setAdapter(adapter);

        /*allTitles.remove(1);*/
        adapter.notifyDataSetChanged();
        adapter = new PostAAdapter(getContext(), allTitles);
        taskList.setAdapter(adapter);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            public boolean onQueryTextSubmit(String query) {
                ArrayList result = Search(getAllTask(),query);

                adapter = new PostAAdapter(getContext(), result);
                taskList.setAdapter(adapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        return root;
    }



    ValueEventListener valueEventListener=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()) {
                for (DataSnapshot taskSnapshot : snapshot.getChildren()) {

                    Task task = taskSnapshot.getValue(Task.class);

                    allTitles.add(task);
                }
                adapter.notifyDataSetChanged();
            } else {

                String message =  "No data here.";
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(getContext(),"DatabaseError, please try again later", Toast.LENGTH_LONG).show();
        }
    };


     ValueEventListener valueEventListener2=new ValueEventListener(){
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                dbHistory.child("History").setValue("???????");
                adapter.notifyDataSetChanged();
            } else {
                String message =  "No data here.";
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Toast.makeText(getContext(), "DatabaseError, Please try again later", Toast.LENGTH_SHORT).show();
        }
    };


    public ArrayList getAllTask(){
        return allTitles;
    }

    public ArrayList<Task> Search(ArrayList<Task> tasks, String keyword){
        ArrayList<Task> afterCompare = new ArrayList<>();

        for(int i = 0; i<tasks.size(); i++){
            if(tasks.get(i).getTitle().contains(keyword)){
                afterCompare.add(tasks.get(i));
            }
        }

        return afterCompare;
    }



    class PostAAdapter extends BaseAdapter {
        private ArrayList<Task> postTaskView;

        private LayoutInflater inflater;

        public PostAAdapter(Context context, ArrayList<Task> tasklist) {
            inflater = LayoutInflater.from(context);
            postTaskView = tasklist;

        }

        @Override
        public int getCount() {
            //return myPost == null? 0 : myPost.size();
            return postTaskView.size();
        }

        @Override
        public Object getItem(int i) {
            //return null;
            return postTaskView.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            ViewHolder myView;
            if (view == null) {
                view = inflater.inflate(R.layout.task_item, null);
                myView = new ViewHolder();
                myView.taskTitle = (TextView)view.findViewById(R.id.Title);
                myView.workDay = (TextView)view.findViewById(R.id.workday);
                myView.salary = (TextView)view.findViewById(R.id.Salary);
                myView.homeTaskLayout = (RelativeLayout)view.findViewById(R.id.tasklistLayout);
                Button editButton=(Button)view.findViewById(R.id.editBtn);
                editButton.setVisibility(View.GONE);
                view.setTag(myView);
            } else {
                myView = (ViewHolder) view.getTag();
            }

            // write the task information into the textView
            myView.taskTitle.setText(postTaskView.get(position).getTitle());
            myView.workDay.setText(postTaskView.get(position).formattedWorkDate());
            String salary = "Salary: " + String.valueOf(postTaskView.get(position).getWage());
            myView.salary.setText(salary);
            myView.homeTaskLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), PostDetail.class);
                    System.out.println(postTaskView.get(position).getTitle());
                    writeHistory();
                    intent.putExtra("task", postTaskView.get(position));
                    getContext().startActivity(intent);
                }
            });

            return view;
        }
    }

    class ViewHolder {
        private RelativeLayout homeTaskLayout;
        private TextView taskTitle;
        private TextView workDay;
        private TextView salary;

    }

    public void writeHistory() {
        dbHistory.child("History").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    //getEditString();


                    dbHistory.child("History").child("aaa").setValue("????");
                    Toast.makeText(getContext(), "Register successfully.", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "DatabaseError, please try again later", Toast.LENGTH_SHORT).show();
            }
        });

    }
}