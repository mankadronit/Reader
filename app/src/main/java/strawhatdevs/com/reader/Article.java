package strawhatdevs.com.reader;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ronitmankad on 25/08/17.
 */

public class Article {
    public String author;
    public String title;
    public String description;
    public String url;
    public String imageUrl;

    public Article(JSONObject jsonObject){
        try {
            this.author = jsonObject.getString("author");
            this.title = jsonObject.getString("title");
            this.description = jsonObject.getString("description");
            this.url = jsonObject.getString("url");
            this.imageUrl = jsonObject.getString("urlToImage");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
