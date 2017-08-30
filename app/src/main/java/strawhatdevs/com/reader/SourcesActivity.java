package strawhatdevs.com.reader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class SourcesActivity extends AppCompatActivity {
    Source[] sources;
    GridView gridView;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);

        MobileAds.initialize(this, "ca-app-pub-1414615588937871~1456497964");

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        gridView = (GridView) findViewById(R.id.gridView);

        Toolbar myChildToolbar = (Toolbar) findViewById(R.id.sources_toolbar);
        setSupportActionBar(myChildToolbar);

        loadJSONFromAsset();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String allArticlesUrl = "https://newsapi.org/v1/articles?source=" + sources[i].realId + "&sortBy=" + sources[i].sortBy + "&apiKey=6f76f4001893418c8a02392a87ff94a0";
                Intent intent = new Intent(SourcesActivity.this, ArticlesActivity.class).putExtra("url", allArticlesUrl);
                startActivity(intent);
            }
        });
    }

    public void loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("newssources.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        parseJSON(json);
    }


    private void parseJSON(String jsonString){
        try{
            JSONObject obj = new JSONObject(jsonString);
            JSONArray array = obj.getJSONArray("sources");
            sources = new Source[array.length()];
            for(int i = 0; i < array.length(); ++i){
                JSONObject jsonObject = array.getJSONObject(i);
                Source source = new Source(jsonObject);
                sources[i] = source;
            }

            SourceAdapter sourceAdapter = new SourceAdapter(this, sources);
            gridView.setAdapter(sourceAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
