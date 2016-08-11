package id.or.codelabs.cardviewrealm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import id.or.codelabs.cardviewrealm.R;
import id.or.codelabs.cardviewrealm.adapter.ArticleAdapter;
import id.or.codelabs.cardviewrealm.helper.RealmHelper;
import id.or.codelabs.cardviewrealm.model.ArticleModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    private RecyclerView recyclerView;
    private RealmHelper helper;
    private ArrayList<ArticleModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        data = new ArrayList<>();
        helper = new RealmHelper(MainActivity.this);

        recyclerView = (RecyclerView) findViewById(R.id.rv_article);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddActivity.class));
                finish();
            }
        });

        setRecyclerView();
    }

    public MainActivity() {

    }

    public void setRecyclerView() {
        try {
            data = helper.findAllArticle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArticleAdapter adapter = new ArticleAdapter(data, new ArticleAdapter.OnItemClickListener() {
            @Override
            public void onClick(ArticleModel item) {
                Intent i = new Intent(getApplicationContext(), EditActivity.class);
                i.putExtra("id", item.getId());
                i.putExtra("title", item.getTitle());
                i.putExtra("description", item.getDescription());
                startActivity(i);
                finish();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            data = helper.findAllArticle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setRecyclerView();
    }
}

