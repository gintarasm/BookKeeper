package lt.bookkeeper.bookRepository;

import android.content.Context;

import java.util.List;

import bookKeeper.Book;
import bookKeeper.BookDao;
import de.greenrobot.dao.query.QueryBuilder;
import lt.bookkeeper.daoAplication.DaoAplication;


/**
 * Created by Hive on 2014.12.18.
 */
public class BookRepository {
    public static void insertOrUpdate(Context context, Book book) {
        getBookDao(context).insertOrReplace(book);
    }

    public static void deleteBook(Context context, Book book) {
        getBookDao(context).delete(book);
    }

    public static List<Book> getAllBooks(Context context) {
        return getBookDao(context).loadAll();
    }

    public static Book getBookById(Context context, long id) {
        return getBookDao(context).load(id);
    }

    public static List<Book> getBookByType(Context context, String type) {
        QueryBuilder qb = getBookDao(context).queryBuilder().where(BookDao.Properties.Type.eq(type));
        return qb.list();
    }

    private static BookDao getBookDao(Context c) {
        return ((DaoAplication)c.getApplicationContext()).getDaoSession().getBookDao();
    }

}
