package net.thebookofcode.www.restapijava.repository;

import net.thebookofcode.www.restapijava.api.JsonPlaceHolderApi;
import net.thebookofcode.www.restapijava.entities.Post;
import net.thebookofcode.www.restapijava.interfaces.ApiResponseInterface;

import java.util.List;
import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;

public class MainRepository {
    private JsonPlaceHolderApi api;
    public MainRepository(JsonPlaceHolderApi api) {
        this.api = api;
    }

    public void getPosts(ApiResponseInterface responseInterface){
        Call<List<Post>> call = api.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful()){
                    responseInterface.onResponse(response.body());
                }else{
                    responseInterface.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                responseInterface.onFailure(t);
            }
        });
    }
}
