package com.example.project1.ui.mypost;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.MainActivity;
import com.example.project1.R;
import com.example.project1.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class MyPostFragment extends Fragment {

    private MyPostViewModel MypostViewModel;
    private DatabaseReference dbTask;
    private Button refresh;
    public ArrayList<Task> myPost = new ArrayList<>();
    private PostAAdapter adapter;
    private String userName;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MypostViewModel =
                new ViewModelProvider(this).get(MyPostViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mypost, container, false);
//        final TextView textView = root.findViewById(R.id.text_slideshow);
//        MypostViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        // get firebase reference
        dbTask = FirebaseDatabase.getInstance().getReference();


        // get the current logged user
        MainActivity activity = (MainActivity) getActivity();
        userName = activity.getUserName();

        adapter=new PostAAdapter(getContext(), myPost);
        ListView taskList = root.findViewById(R.id.tasklistView);
        taskList.setAdapter(adapter);

        // testing code for UI  list view display
//        Date currentDate=new Date();
//        Date workDate=new Date();
//        workDate.setMonth(6);
//        Date workDate2=new Date();
//        workDate2.setMonth(7);
//        Task temp1 = new Task("title","description",workDate,50,"publisher");
//        myPost.add(temp1);
//        Task temp2 = new Task("title2","description2",workDate2,10,"publisher2");
//        myPost.add(temp2);

        Query query = dbTask.child("Task").orderByChild("publisher").equalTo(userName);
        query.addListenerForSingleValueEvent(valueEventListener);

       // if (myPost.isEmpty()) {
         //   Toast.makeText(getContext(), "no post", Toast.LENGTH_LONG).show();
        //} else {
         //   Collections.sort(myPost, Task.postDateSort);

            // write the adaptor into


       // }

        return root;
    }



    ValueEventListener valueEventListener=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.exists()) {
                for (DataSnapshot taskSnapshot : snapshot.getChildren()) {
//                        String title = taskSnapshot.child("title").getValue().toString();
//                        Date workDate = (Date)taskSnapshot.child("workDate").getValue();
//                        String description = taskSnapshot.child("description").getValue().toString();
//                        int wage = (int)taskSnapshot.child("wage").getValue();
//                        String publisher = taskSnapshot.child("publisher").getValue().toString();
//                        Task task = new Task(title,description,workDate,wage,publisher);
                    Task task = taskSnapshot.getValue(Task.class);
                    System.out.println(task.getTaskId());
                    System.out.println(task.getWorkDate());
                    // append task to task list
                    myPost.add(task);
                }
                adapter.notifyDataSetChanged();

            } else {
                // The user has not posted any task
                String message =  userName+ "has not posted any task yet";
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(getContext(),"DatabaseError, please try again later", Toast.LENGTH_LONG).show();
        }
    };

    class PostAAdapter extends BaseAdapter {
        private ArrayList<Task> postTaskView;

        //private Context context;
        private LayoutInflater inflater;

        public PostAAdapter(Context context, ArrayList<Task> tasklist) {
            //this.context = context;
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
            //Task t = myPost.get(position);
            ViewHolder myView;
            if (view == null) {
                view = inflater.inflate(R.layout.task_item, null);
                myView = new ViewHolder();
                myView.taskTitle = (TextView)view.findViewById(R.id.Title);
                myView.workDay = (TextView)view.findViewById(R.id.workday);
                myView.salary = (TextView)view.findViewById(R.id.Salary);
                myView.edit = (Button)view.findViewById(R.id.editButton);
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
        private Button edit;
   }


}