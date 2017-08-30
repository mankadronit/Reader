package strawhatdevs.com.reader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ronitmankad on 25/08/17.
 */

public class MyArticleAdapter extends ArrayAdapter<Article> {

    private static class ViewHolder {
        private TextView titleTextView;
        private TextView authorTextView;
        private ImageView imageView;
    }

    public MyArticleAdapter(Context context, int textViewResourceId, ArrayList<Article> items) {
        super(context, textViewResourceId, items);
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.custom_article_list, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.titleTextView = (TextView) convertView.findViewById(R.id.titleTextView);
            viewHolder.authorTextView = (TextView) convertView.findViewById(R.id.authorTextView);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Article article = getItem(position);
        if (article!= null) {
            // My layout has only one TextView
            // do whatever you want with your string and long
            viewHolder.titleTextView.setText(article.title);
            viewHolder.authorTextView.setText("By " + article.author);

            Picasso.with(getContext()).load(article.imageUrl).error(R.drawable.user_placeholder).placeholder(R.drawable.user_placeholder).into(viewHolder.imageView);
        }

        return convertView;
    }
}
