package id.or.codelabs.cardviewrealm.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.or.codelabs.cardviewrealm.R;
import id.or.codelabs.cardviewrealm.model.ArticleModel;

/**
 * Created by FitriFebriana on 8/10/2016.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private final OnItemClickListener listener;
    private ArrayList<ArticleModel> article;

    public ArticleAdapter(ArrayList<ArticleModel> article, OnItemClickListener listener) {
        this.article = article;
        this.listener = listener;
    }

    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, null);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ArticleAdapter.ViewHolder holder, int position) {
        holder.click(article.get(position), listener);
        holder.id.setText(String.valueOf(article.get(position).getId()));
        holder.title.setText(article.get(position).getTitle());
        holder.description.setText(article.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return article.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView title;
        TextView description;

        public ViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.txt_id);
            title = (TextView) itemView.findViewById(R.id.txt_title);
            description = (TextView) itemView.findViewById(R.id.txt_description);
        }

        public void click(final ArticleModel articleModel, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(articleModel);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onClick(ArticleModel item);
    }

} 
