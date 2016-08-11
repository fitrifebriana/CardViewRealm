package id.or.codelabs.cardviewrealm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import id.or.codelabs.cardviewrealm.R;
import id.or.codelabs.cardviewrealm.helper.RealmHelper;

/**
 * Created by FitriFebriana on 8/10/2016.
 */

public class AddActivity extends AppCompatActivity {


    private RealmHelper realmHelper;
    private EditText txtDescription;
    private EditText txtTitle;
    private Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        realmHelper = new RealmHelper(AddActivity.this);

        txtTitle = (EditText) findViewById(R.id.txt_add_title);
        txtDescription = (EditText) findViewById(R.id.txt_add_description);
        btnSave = (Button) findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inisialisasi string
                String title;
                String description;

                //mengambil text dr edittext
                title = txtTitle.getText().toString();
                description = txtDescription.getText().toString();

                //menambahkan data pada database
                realmHelper.addArticle(title, description);

                //menutup Add Activity
                finish();

                //kembali ke MainActivity
                Intent i = new Intent(AddActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
} 