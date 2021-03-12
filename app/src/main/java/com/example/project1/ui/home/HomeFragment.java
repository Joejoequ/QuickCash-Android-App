package com.example.project1.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.project1.PostDetail;
import com.example.project1.R;
import com.example.project1.Task;
import com.example.project1.ui.mypost.MyPostFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private DatabaseReference dbTask ;
    public ArrayList<Task> allTitles = new ArrayList<>();
    public ArrayList<Task> allTitle2 = new ArrayList<>();
    private PostAAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        dbTask= FirebaseDatabase.getInstance().getReference();
        Query query = dbTask.child("Task").orderByChild("publisher");
        query.addListenerForSingleValueEvent(valueEventListener);

        adapter = new PostAAdapter(getContext(), allTitles);
        ListView taskList = root.findViewById(R.id.HomeListView);
        SearchView search = root.findViewById(R.id.mainSearchView);
        taskList.setAdapter(adapter);

        /*allTitles.remove(1);*/
        adapter.notifyDataSetChanged();
        adapter = new PostAAdapter(getContext(), allTitle2);
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
                    System.out.println("11111111111111111111111"+task.getTaskId());
                    System.out.println(task.getWorkDate());
                    // append task to task list
                    allTitles.add(task);
                }
                adapter.notifyDataSetChanged();
            } else {
                System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
                // The user has not posted any task
                String message =  "has not posted any task yet";
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(getContext(),"DatabaseError, please try again later", Toast.LENGTH_LONG).show();
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
                view.setTag(myView);
            } else {
                myView = (ViewHolder) view.getTag();
            }

            // write the task information into the textView
            myView.taskTitle.setText(postTaskView.get(position).getTitle());
            myView.workDay.setText(postTaskView.get(position).formattedWorkDate());
            String salary = "Salary: " + String.valueOf(postTaskView.get(position).getWage());
            myView.salary.setText(salary);

            return view;
        }
    }

    class ViewHolder {
        private TextView taskTitle;
        private TextView workDay;
        private TextView salary;
    }
}