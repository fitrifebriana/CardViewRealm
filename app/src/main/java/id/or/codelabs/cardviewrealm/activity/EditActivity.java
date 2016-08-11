package id.or.codelabs.cardviewrealm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import id.or.codelabs.cardviewrealm.R;
import id.or.codelabs.cardviewrealm.helper.RealmHelper;
import id.or.codelabs.cardviewrealm.model.ArticleModel;

/**
 * Created by FitriFebriana on 8/10/2016.
 */

public class EditActivity extends AppCompatActivity {

    private int position;
    private Button btnDelete;
    private Button btnSave;
    private EditText txtTitle;
    private EditText txtDescription;
    private RealmHelper helper;
    private String title;
    private String description;
    private String intentTitle;
    private String intentDescription;
    private ArrayList<ArticleModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        helper = new RealmHelper(EditActivity.this);
        data = new ArrayList<>();
        position = getIntent().getIntExtra("id", 0);
        intentTitle = getIntent().getStringExtra("title");
        intentDescription = getIntent().getStringExtra("description");

        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnSave = (Button) findViewById(R.id.btn_save);

        txtTitle = (EditText) findViewById(R.id.txt_add_title);
        txtDescription = (EditText) findViewById(R.id.txt_add_description);

        txtTitle.setText(intentTitle);
        txtDescription.setText(intentDescription);

        btnDelete.setVisibility(View.VISIBLE);

        //perintah untuk menghapus
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //menghapus data dari database
                helper.deleteData(position);

                //berpindah ke MainActivity
                startActivity(new Intent(EditActivity.this, MainActivity.class));
                finish();
            }
        });

        //perintah mengupdate data
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //mengambil text dari edittext
                title = txtTitle.getText().toString();
                description = txtDescription.getText().toString();

                //melakukan update artikel
                helper.updateArticle(position, title, description);

                //berpindah ke MainActivity
                startActivity(new Intent(EditActivity.this, MainActivity.class));
                finish();
            }
        });


    }


} 