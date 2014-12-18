package lt.bookkeeper.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import lt.bookkeeper.R;
import lt.bookkeeper.comInterface.BookSelect;
import lt.bookkeeper.comInterface.NewBook;
import lt.bookkeeper.fragments.bookList.BookListFragment;

/**
 * Created by Hive on 2014.12.17.
 */
public class ListActivity extends Activity implements BookSelect, NewBook{
    public static final String READ_LIST = "R";
    public static final String NOW_LIST = "N";
    public static final String UP_LIST = "U";
    public static final String WISH_LIST = "W";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        if (findViewById(R.id.fg_container) != null) {
            if(getFragmentManager().findFragmentById(R.id.fg_container) == null) {
                BookListFragment bookFragment = new BookListFragment();

                bookFragment.setArguments(getIntent().getExtras());

                getFragmentManager().beginTransaction().add(R.id.fg_container, bookFragment).commit();
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void bookSelected(Long bookId) {
        Intent editBook = new Intent(this, BookActivity.class);
        editBook.putExtra(BookActivity.EXTRA_BOOK_ID,bookId);
        startActivity(editBook);
    }

    @Override
    public void newBook() {
        Intent newBook = new Intent(this, BookActivity.class);
        startActivity(newBook);
    }
}
