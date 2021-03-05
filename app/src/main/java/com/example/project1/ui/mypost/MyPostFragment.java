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
    private ArrayList<Task> myPost;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MypostViewModel =
                new ViewModelProvider(this).get(MyPostViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mypost, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        MypostViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        // get firebase reference
        dbTask = FirebaseDatabase.getInstance().getReference();

        // array list to store all post task for a given user
        ArrayList<Task> myPost = new ArrayList<>();
        // get the current logged user
        MainActivity activity = (MainActivity) getActivity();
        String userName = activity.getUserName();

        // sort all task base on the post data
        myPost = getPostTask(userName);
        Collections.sort(myPost, Task.postDateSort);
        PostAAdapter postTaskAdaptor = new PostAAdapter(getContext());

        // write the adaptor into
        ListView taskList = root.findViewById(R.id.tasklistView);
        taskList.setAdapter(postTaskAdaptor);
        return root;
    }

    public ArrayList<Task> getPostTask(String user) {
        ArrayList<Task> userTask = new ArrayList<>();
        Query query = dbTask.child("Task").orderByChild("publisher").equalTo(user);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot taskSnapshot : snapshot.getChildren()) {
                        String title = taskSnapshot.child("title").getValue().toString();
                        Date workDate = (Date)taskSnapshot.child("workDate").getValue();
                        String description = taskSnapshot.child("description").getValue().toString();
                        int wage = (int)taskSnapshot.child("wage").getValue();
                        String publisher = taskSnapshot.child("publisher").getValue().toString();
                        Task task = new Task(title,description,workDate,wage,publisher);
                        // append task to task list
                        userTask.add(task);
                    }

                } else {
                    // The user has not posted any task
                    String message = user + "has not posted any task yet";
                    Toast.makeText(getContext(), "message", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"DatabaseError, please try again later", Toast.LENGTH_LONG).show();
            }
        });
        return userTask;
    }

    class PostAAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater inflater;

        public PostAAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return myPost.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            Task t = myPost.get(position);
            ViewHolder myView = null;
            if (myView == null) {
                myView = new ViewHolder();
                view = inflater.inflate(R.layout.task_item, null);
                myView.taskTitle = (TextView)view.findViewById(R.id.taskTitle);
                myView.workDay = (TextView)view.findViewById(R.id.workday);
                myView.salary = (TextView)view.findViewById(R.id.Salary);
                myView.edit = (Button)view.findViewById(R.id.editTask);
                view.setTag(myView);
            } else {
                myView = (ViewHolder) view.getTag();
            }

            // write the task information into the textView
            myView.taskTitle.setText(t.getTitle());
            myView.workDay.setText(t.getWorkDate());
            String salary = "Salary: " + String.valueOf(t.getWage());
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