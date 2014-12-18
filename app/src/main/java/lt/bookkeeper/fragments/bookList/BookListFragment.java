package lt.bookkeeper.fragments.bookList;

import android.app.ListFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import bookKeeper.Book;
import lt.bookkeeper.R;
import lt.bookkeeper.bookRepository.BookRepository;
import lt.bookkeeper.comInterface.BookSelect;
import lt.bookkeeper.comInterface.NewBook;
import lt.bookkeeper.genreRepository.GenreRepository;
import lt.bookkeeper.listAdapter.CustomAdapter;
import lt.bookkeeper.main.ListActivity;
import lt.bookkeeper.main.SelectActivity;

/**
 * Created by Hive on 2014.12.17.
 */
public class BookListFragment extends ListFragment {
    private String type;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.booklistlayout, container, false);
        /*
        bookKeeper.Book book = new bookKeeper.Book();
        book.setAuthor("Jim butcher");
        book.setBookName("Storm front");
        book.setBookTypeId(1L);
        book.setGenre(GenreRepository.getGenreById(getActivity(), 3L));
        book.setRating(4);
        book.setReleaseDate("2009-07-12");
        book.setType(ListActivity.READ_LIST);

        BookRepository.insertOrUpdate(getActivity(), book);
        */

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        type = getArguments().getString(SelectActivity.LIST_TYPE_EXTRA);
        Button button = (Button)view.findViewById(R.id.newBook);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewBook newBook = (NewBook)getActivity();
                newBook.newBook();
            }
        });
        List<Book> booker = BookRepository.getBookByType(getActivity(),type);
        getListView().setDivider(new ColorDrawable(Color.GRAY));
        getListView().setDividerHeight(2);
        setListAdapter(new CustomAdapter(getActivity(), R.layout.booklist,R.id.bookName,booker));

    }

    @Override //TODO: fix resume
    public void onResume() {
        super.onResume();
        CustomAdapter cs = (CustomAdapter)getListAdapter();
            cs.clear();
            cs.addAll(BookRepository.getBookByType(getActivity(),type));
            cs.notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Book selectedBook = (Book)getListAdapter().getItem(position);
        BookSelect select = (BookSelect)getActivity();
        select.bookSelected(selectedBook.getId());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SelectActivity.LIST_TYPE_EXTRA,type);
    }
}
