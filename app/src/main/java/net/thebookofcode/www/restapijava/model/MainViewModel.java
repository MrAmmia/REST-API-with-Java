package net.thebookofcode.www.restapijava.model;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.thebookofcode.www.restapijava.api.JsonPlaceHolderApi;
import net.thebookofcode.www.restapijava.entities.Post;
import net.thebookofcode.www.restapijava.interfaces.ApiResponseInterface;
import net.thebookofcode.www.restapijava.repository.MainRepository;
import net.thebookofcode.www.restapijava.singletons.RetrofitInstance;

import java.util.List;

public class MainViewModel extends ViewModel {
    MainRepository repository;
    MutableLiveData<List<Post>> _posts = new MutableLiveData<>();
    MutableLiveData<Integer> mProgressMutableData = new MutableLiveData<>();
    public MainViewModel() {
        mProgressMutableData.postValue(View.GONE);
        JsonPlaceHolderApi api = RetrofitInstance.getInstance().create(JsonPlaceHolderApi.class);
        repository = new MainRepository(api);
    }

    public void makePosts() {
        mProgressMutableData.postValue(View.VISIBLE);
        repository.getPosts(new ApiResponseInterface() {
            @Override
            public void onResponse(List<Post> posts) {
                mProgressMutableData.postValue(View.GONE);
                _posts.postValue(posts);
            }

            @Override
            public void onFailure(Throwable throwable) {
                mProgressMutableData.postValue(View.GONE);

            }
        });
    }

    public LiveData<List<Post>> getPosts(){
        return _posts;
    }

}
