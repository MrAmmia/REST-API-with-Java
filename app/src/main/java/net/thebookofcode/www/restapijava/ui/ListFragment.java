package net.thebookofcode.www.restapijava.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.thebookofcode.www.restapijava.R;
import net.thebookofcode.www.restapijava.adapters.RecyclerViewAdapter;
import net.thebookofcode.www.restapijava.entities.Post;
import net.thebookofcode.www.restapijava.interfaces.RecyclerViewListener;
import net.thebookofcode.www.restapijava.model.MainViewModel;

import java.util.List;

public class ListFragment extends Fragment {
    MainViewModel viewModel;
    RecyclerViewAdapter adapter;
    RecyclerViewListener listener;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.makePosts();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        viewModel.getPosts().observe(getViewLifecycleOwner(), new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                adapter = new RecyclerViewAdapter(getContext(),posts);
                recyclerView.setAdapter(adapter);

                adapter.setOnItemClick(new RecyclerViewListener() {
                    @Override
                    public void onItemClick(Post post) {
                        DetailFragment detailFragment = DetailFragment.newInstance(post);
                        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameLayout, detailFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                });
            }
        });
        return view;
    }
}