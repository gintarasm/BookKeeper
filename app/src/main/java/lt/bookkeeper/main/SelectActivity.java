package lt.bookkeeper.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;

import lt.bookkeeper.R;


public class SelectActivity extends Activity {
    public static final String LIST_TYPE_EXTRA = "listType";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        Button readBooks = (Button)findViewById(R.id.readButton);
        Button readingBooks  = (Button)findViewById(R.id.nowButton);
        Button upBooks  = (Button)findViewById(R.id.upcomingButton);
        Button wishBooks  = (Button)findViewById(R.id.wishButton);

        readBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startList(ListActivity.READ_LIST);
            }
        });

        readingBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startList(ListActivity.NOW_LIST);
            }
        });

        upBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startList(ListActivity.UP_LIST);
            }
        });

        wishBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startList(ListActivity.WISH_LIST);
            }
        });
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
            Intent about = new Intent(this, AboutActivity.class);
            startActivity(about);
        }

        return super.onOptionsItemSelected(item);
    }

    private void startList(String type) {
        Intent bookList = new Intent(this, ListActivity.class);
        bookList.putExtra(LIST_TYPE_EXTRA, type);
        startActivity(bookList);
    }
}
