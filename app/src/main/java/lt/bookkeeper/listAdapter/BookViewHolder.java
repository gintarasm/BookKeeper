package lt.bookkeeper.listAdapter;

import android.view.View;
import android.widget.TextView;


import lt.bookkeeper.R;

/**
 * Created by Hive on 2014.12.17.
 */
public class BookViewHolder {
    TextView bookName;
    TextView seriesName;
    TextView date;
    TextView author;
    TextView genre;

    public BookViewHolder(View row) {
        this.bookName = (TextView)row.findViewById(R.id.bookName);
        this.seriesName = (TextView)row.findViewById(R.id.seriesName);
        this.date = (TextView)row.findViewById(R.id.release);
        this.genre = (TextView)row.findViewById(R.id.genre);
        this.author = (TextView)row.findViewById(R.id.author);


    }
}
