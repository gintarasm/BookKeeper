package lt.bookkeeper.listAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;



/**
 * Created by Hive on 2014.12.17.
 */
public class CustomAdapter extends ArrayAdapter<bookKeeper.Book>{
    private StringBuilder name;
    public CustomAdapter(Context context, int resource, int viewLayoutRow, List<bookKeeper.Book> books) {
        super(context,resource,viewLayoutRow,books);
        name = new StringBuilder();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = super.getView(position, convertView, parent);
        BookViewHolder holder = (BookViewHolder)row.getTag();
        if(holder == null) {
            holder = new BookViewHolder(row);
            row.setTag(holder);
        }

        bookKeeper.Book book = super.getItem(position);
        holder.bookName.setText(book.getBookName());
        if (book.getSeries() != null) {
            name.setLength(0);
            name.append("(");
            name.append(book.getSeries().getSeriesName());
            name.append(")");
            holder.seriesName.setText(name.toString());
        } else {
            holder.seriesName.setText("");
        }
        holder.date.setText(book.getReleaseDate());
        holder.author.setText(book.getAuthor());
        holder.genre.setText(book.getGenre().getGenreName());

        return row;
    }
}
