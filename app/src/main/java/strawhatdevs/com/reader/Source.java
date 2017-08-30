package strawhatdevs.com.reader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ronitmankad on 27/08/17.
 */

public class Source {
    public String id;
    public String name;
    public String url;
    public String sortBy;
    public String realId;

    public Source(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getString("id");
            this.name = jsonObject.getString("name");
            this.url = jsonObject.getString("url");
            JSONArray arr  = jsonObject.getJSONArray("sortBysAvailable");
            this.sortBy = arr.getString(0);
            this.realId = jsonObject.getString("realid");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
