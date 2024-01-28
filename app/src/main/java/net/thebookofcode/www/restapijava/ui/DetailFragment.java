package net.thebookofcode.www.restapijava.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.thebookofcode.www.restapijava.R;
import net.thebookofcode.www.restapijava.entities.Post;

public class DetailFragment extends Fragment {
    TextView txtTitle, txtText, txtId, txtUserId;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(Post post) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("title", post.getTitle());
        args.putString("body",post.getText());
        args.putInt("id",post.getId());
        args.putInt("userId",post.getUserId());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        if(getArguments() != null){
            String title = getArguments().getString("title");
            String body = getArguments().getString("body");
            int id = getArguments().getInt("id");
            int userId = getArguments().getInt("userId");

            txtId = view.findViewById(R.id.txtId);
            txtText = view.findViewById(R.id.txtText);
            txtTitle = view.findViewById(R.id.txtTitle);
            txtUserId = view.findViewById(R.id.txtUserId);

            txtId.setText("Post of ID " + id);
            txtUserId.setText("Posted by user " + userId);
            txtTitle.setText(title);
            txtText.setText(body);
        }

        return view;
    }
}