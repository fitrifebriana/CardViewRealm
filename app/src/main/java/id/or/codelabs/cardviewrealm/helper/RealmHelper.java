package id.or.codelabs.cardviewrealm.helper;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import id.or.codelabs.cardviewrealm.model.ArticleModel;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by FitriFebriana on 8/9/2016.
 */

public class RealmHelper {

    private static final String TAG = "RealmHelper";

    private Realm realm;
    private RealmResults<Article> realmResult;
    public Context context;

    //Konstruktor untuk membuat instance realm
    public RealmHelper(Context context) {
        RealmConfiguration config = new RealmConfiguration.Builder(context).build();
        realm = Realm.getInstance(config);
        this.context = context;
    }

    //menambah data
    public void addArticle(String title, String description) {
        Article article = new Article();
        article.setId((int) (System.currentTimeMillis() / 1000));
        article.setTitle(title);
        article.setDescription(description);

        realm.beginTransaction();
        realm.copyToRealm(article);
        realm.commitTransaction();

        showLog("Added : " + title);
        showToast(title + " berhasil disimpan");
    }

    //mencari semua artikel
    public ArrayList<ArticleModel> findAllArticle() {
        ArrayList<ArticleModel> data = new ArrayList<>();

        realmResult = realm.where(Article.class).findAll();
        realmResult.sort("id", Sort.DESCENDING);
        if (realmResult.size() > 0) {
            showLog("Size : " + realmResult.size());

            for (int i = 0; i < realmResult.size(); i++) {
                String title;
                String description;
                int id = realmResult.get(i).getId();
                title = realmResult.get(i).getTitle();
                description = realmResult.get(i).getDescription();
                data.add(new ArticleModel(id, title, description));
            }

        } else {
            showLog("Size : 0");
            showToast("Database Kosong!");
        }

        return data;
    }

    //memperbaharui data
    public void updateArticle(int id, String title, String description) {
        realm.beginTransaction();
        Article article = realm.where(Article.class).equalTo("id", id).findFirst();
        article.setTitle(title);
        article.setDescription(description);
        realm.commitTransaction();
        showLog("Updated : " + title);

        showToast(title + " berhasil diupdate.");
    }

    //menghapus data berdasarkan id
    public void deleteData(int id) {
        RealmResults<Article> dataResults = realm.where(Article.class).equalTo("id", id).findAll();
        realm.beginTransaction();
        dataResults.deleteLastFromRealm();
        realm.commitTransaction();

        showToast("Hapus data berhasil.");
    }

    //membuat log
    private void showLog(String s) {
        Log.d(TAG, s);
    }

    //menampilkan informasi
    private void showToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }
}