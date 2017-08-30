package strawhatdevs.com.reader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by ronitmankad on 27/08/17.
 */

public class SourceAdapter extends BaseAdapter {
    private final Context mContext;
    private final Source[] sources;

    public SourceAdapter(Context context, Source[] sources){
        this.mContext = context;
        this.sources = sources;
    }

    @Override
    public int getCount() {
        return sources.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Source source = sources[i];

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.linearlayout_source, null);
        }

        String sourceImageId = source.id;
        final ImageView imageView = (ImageView) view.findViewById(R.id.sourceLogo);
        final TextView sourceNameTextView = (TextView) view.findViewById(R.id.sourceName);
        //GridView gv = (GridView) view.findViewById(R.id.gridView);
        sourceNameTextView.setText(source.name);

        String uri = "@drawable/" + source.id;
        int imageResource = mContext.getResources().getIdentifier(uri, null, mContext.getPackageName());

        Drawable res = mContext.getResources().getDrawable(imageResource);
        imageView.setImageDrawable(res);

        //imageView.setLayoutParams(new GridView.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, gv.getWidth()/3));

       // Picasso.with(mContext).load(uri).placeholder(R.drawable.user_placeholder).error(R.drawable.user_placeholder).into(imageView);

        return view;
    }
}

