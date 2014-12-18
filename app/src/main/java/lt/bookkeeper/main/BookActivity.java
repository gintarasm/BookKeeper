package lt.bookkeeper.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import lt.bookkeeper.R;
import lt.bookkeeper.fragments.bookList.BookFragment;
import lt.bookkeeper.fragments.bookList.BookListFragment;

/**
 * Created by Hive on 2014.12.18.
 */
public class BookActivity extends Activity {
    public static final String EXTRA_BOOK_ID = "bookId";
    public static final String EXTRA_EDIT_MODE = "edit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity);
        if (getFragmentManager().findFragmentById(R.id.editFragmentHolder) == null) {
            BookFragment bookFragment = new BookFragment();

            bookFragment.setArguments(getIntent().getExtras());

            getFragmentManager().beginTransaction().add(R.id.editFragmentHolder, bookFragment).commit();
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
}
