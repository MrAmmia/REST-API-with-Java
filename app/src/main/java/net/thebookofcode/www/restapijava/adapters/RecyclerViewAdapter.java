package net.thebookofcode.www.restapijava.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.thebookofcode.www.restapijava.R;
import net.thebookofcode.www.restapijava.entities.Post;
import net.thebookofcode.www.restapijava.interfaces.RecyclerViewListener;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    Context context;
    List<Post> posts;

    RecyclerViewListener listener;

    public RecyclerViewAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.post_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post currentPost = posts.get(position);
        holder.bindPost(currentPost);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle;
        TextView txtBody;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtBody = itemView.findViewById(R.id.txtBody);

            itemView.setOnClickListener(view -> {
                if (listener != null){
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        listener.onItemClick(posts.get(pos));
                    }
                }
            });
        }

        public void bindPost(Post post){
            txtBody.setText(post.getText());
            txtTitle.setText(post.getTitle());
        }
    }

    public void setOnItemClick(RecyclerViewListener listener){
        this.listener = listener;
    }
}
