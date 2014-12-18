package lt.bookkeeper.fragments.bookList;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bookKeeper.Book;
import lt.bookkeeper.R;
import lt.bookkeeper.bookRepository.BookRepository;
import lt.bookkeeper.main.BookActivity;
import lt.bookkeeper.main.ListActivity;

/**
 * Created by Hive on 2014.12.18.
 */
public class BookFragment extends Fragment {
    private Long bookId;
    private Book selectedBook;
    private Button confirm;
    private Button delete;
    private EditText author;
    private EditText book;
    private EditText series;
    private EditText date;
    private EditText note;
    private RadioGroup genre;
    private RadioGroup type;


    public BookFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.book_edit,container,false);


        confirm = (Button)view.findViewById(R.id.confirm);
        delete = (Button)view.findViewById(R.id.delete);

        author = (EditText)view.findViewById(R.id.editAuthor);
        book = (EditText)view.findViewById(R.id.editBook);
        series = (EditText)view.findViewById(R.id.editSeries);
        date = (EditText)view.findViewById(R.id.editDate);
        note = (EditText)view.findViewById(R.id.editComent);

        genre = (RadioGroup)view.findViewById(R.id.editGenre);
        type = (RadioGroup)view.findViewById(R.id.editType);

        if (getArguments() != null) {
            bookId = getArguments().getLong(BookActivity.EXTRA_BOOK_ID);
            setBook();
        } else {
            delete.setVisibility(View.GONE);
        }

        confirm.setOnClickListener(new SaveButtonListener());
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setBook() {
        selectedBook = BookRepository.getBookById(getActivity(),bookId);
        author.setText(selectedBook.getAuthor());
        book.setText(selectedBook.getBookName());
        date.setText(selectedBook.getReleaseDate());
        note.setText(selectedBook.getComments());

        switch (String.valueOf(selectedBook.getGenreId())) {
            case "1":
                genre.check(R.id.editFiction);
                break;
            case "2":
                genre.check(R.id.editScifi);
                break;
            case "3":
                genre.check(R.id.editFantasy);
                break;
            case "4":
                genre.check(R.id.editNonf);
                break;
        }

        switch (selectedBook.getType()) {
            case ListActivity.NOW_LIST:
                type.check(R.id.editReading);
                break;
            case ListActivity.READ_LIST:
                type.check(R.id.editRead);
                break;
            case ListActivity.UP_LIST:
                type.check(R.id.editUpc);
                break;
            case ListActivity.WISH_LIST:
                type.check(R.id.editWish);
                break;
        }
        delete.setOnClickListener(new DeleteButtonListener());
    }

    class SaveButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (selectedBook == null) {
                selectedBook = new Book();
            }
            if (!book.getText().toString().isEmpty()) {
                selectedBook.setReleaseDate(date.getText().toString());
                selectedBook.setBookName(book.getText().toString());
                selectedBook.setAuthor(author.getText().toString());
                selectedBook.setReleaseDate(date.getText().toString());
                selectedBook.setComments(note.getText().toString());

                switch (genre.getCheckedRadioButtonId()) {
                    case R.id.editFiction:
                        selectedBook.setGenreId(1L);
                        break;
                    case R.id.editScifi:
                        selectedBook.setGenreId(2L);
                        break;
                    case R.id.editFantasy:
                        selectedBook.setGenreId(3L);
                        break;
                    case R.id.editNonf:
                        selectedBook.setGenreId(4L);
                        break;
                }

                switch (type.getCheckedRadioButtonId()) {
                    case R.id.editRead:
                        selectedBook.setType(ListActivity.READ_LIST);
                        break;
                    case R.id.editReading:
                        selectedBook.setType(ListActivity.NOW_LIST);
                        break;
                    case R.id.editUpc:
                        selectedBook.setType(ListActivity.UP_LIST);
                        break;
                    case R.id.editWish:
                        selectedBook.setType(ListActivity.WISH_LIST);
                        break;
                }

                BookRepository.insertOrUpdate(getActivity(), selectedBook);
                getActivity().finish();
                Toast.makeText(getActivity(), getString(R.string.success), Toast.LENGTH_LONG).show();
            } else {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setMessage(getString(R.string.errorBook));
                alertDialogBuilder.setPositiveButton(getString(R.string.confirm), null);
                AlertDialog dialog = alertDialogBuilder.show();
                TextView messageText = (TextView)dialog.findViewById(android.R.id.message);
                messageText.setGravity(Gravity.CENTER);
                dialog.show();

            }
        }
    }

    class DeleteButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            BookRepository.deleteBook(getActivity(),selectedBook);
            getActivity().finish();
            Toast.makeText(getActivity(), getString(R.string.success), Toast.LENGTH_LONG).show();
        }
    }
}
