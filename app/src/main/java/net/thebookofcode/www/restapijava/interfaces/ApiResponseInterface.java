package net.thebookofcode.www.restapijava.interfaces;

import net.thebookofcode.www.restapijava.entities.Post;

import java.util.List;

public interface ApiResponseInterface {
    void onResponse(List<Post> posts);

    void onFailure(Throwable throwable);
}
