package lt.bookkeeper.genreRepository;

import android.content.Context;

import java.util.List;

import bookKeeper.Book;
import bookKeeper.BookDao;
import bookKeeper.Genre;
import bookKeeper.GenreDao;
import lt.bookkeeper.daoAplication.DaoAplication;

/**
 * Created by Hive on 2014.12.18.
 */
public class GenreRepository {
    public static void insertOrUpdate(Context context, Genre genre) {
        getGenreDao(context).insertOrReplace(genre);
    }

    public static void deleteBook(Context context, Genre genre) {
        getGenreDao(context).delete(genre);
    }

    public static List<Genre> getAllBooks(Context context) {
        return getGenreDao(context).loadAll();
    }

    public static Genre getGenreById(Context context, long id) {
        return getGenreDao(context).load(id);
    }
    private static GenreDao getGenreDao(Context c) {
        return ((DaoAplication)c.getApplicationContext()).getDaoSession().getGenreDao();
    }
}
