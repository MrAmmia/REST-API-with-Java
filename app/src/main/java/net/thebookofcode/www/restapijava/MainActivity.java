package net.thebookofcode.www.restapijava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import net.thebookofcode.www.restapijava.api.JsonPlaceHolderApi;
import net.thebookofcode.www.restapijava.model.Comment;
import net.thebookofcode.www.restapijava.model.Post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView txtResult;
    private JsonPlaceHolderApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = findViewById(R.id.txtResult);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(JsonPlaceHolderApi.class);

        // getPosts();
        // getComments();
        // createPost();
        // updatePost();
        deletePost();

    }

    private void deletePost() {
        Call<Void> call = api.deletePost(4);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                txtResult.setText(response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                txtResult.setText(t.getMessage());
            }
        });
    }

    private void updatePost() {
        Post post = new Post(23, "New Title", "New Text");
        // Call<Post> call = api.putPost(3,post);
        Call<Post> call = api.patchPost(3,post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    txtResult.setText("Code " + response.code());
                    return;
                }
                Post post = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + post.getId() + "\n";
                content += "User ID: " + post.getUserId() + "\n";
                content += "Title: " + post.getTitle() + "\n";
                content += "Text: " + post.getText() + "\n\n";

                txtResult.append(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                txtResult.setText(t.getMessage());
            }
        });
    }

    private void createPost() {
        /*
        Post post = new Post(23, "New Title", "New Text");

        Call<Post> call = api.createPost(post);
        */
        Call<Post> call = api.createPost(23, "New Title", "New Text");
        Map<String,String> fields = new HashMap<String,String>();
        fields.put("userId","23");
        fields.put("title","New Title");
        fields.put("body","New Text");
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    txtResult.setText("Code " + response.code());
                    return;
                }
                Post post = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + post.getId() + "\n";
                content += "User ID: " + post.getUserId() + "\n";
                content += "Title: " + post.getTitle() + "\n";
                content += "Text: " + post.getText() + "\n\n";

                txtResult.append(content);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                txtResult.setText(t.getMessage());
            }
        });
    }

    private void getComments() {
        Call<List<Comment>> call = api.getComments(2);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    txtResult.setText("Code " + response.code());
                    return;
                }
                List<Comment> comments = response.body();
                for (Comment comment : comments) {
                    String content = "";
                    content += "ID: " + comment.getId() + "\n";
                    content += "Post ID: " + comment.getPostId() + "\n";
                    content += "Email: " + comment.getEmail() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "Text: " + comment.getText() + "\n\n";

                    txtResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                txtResult.setText(t.getMessage());
            }
        });
    }

    private void getPosts() {
        // Call<List<Post>> call = api.getPosts();

        // Call<List<Post>> call = api.getPosts(4,"id","desc");

        //Call<List<Post>> call = api.getPosts(1,4,"id","desc");
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("userId", "4");
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");

        Call<List<Post>> call = api.getPosts(parameters);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    txtResult.setText("Code " + response.code());
                    return;
                }
                List<Post> posts = response.body();
                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    txtResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                txtResult.setText(t.getMessage());
            }
        });
    }
}