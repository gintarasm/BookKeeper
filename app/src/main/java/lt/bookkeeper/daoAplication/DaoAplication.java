package lt.bookkeeper.daoAplication;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import bookKeeper.BookType;
import bookKeeper.DaoMaster;
import bookKeeper.DaoSession;
import bookKeeper.Genre;

/**
 * Created by Hive on 2014.12.18.
 */
public class DaoAplication extends Application {
    public DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDatabase();
    }

    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"bookkeeper-dp",null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }
    private void initBookTypes() {
        BookType bt = new BookType();
        bt.setBookTypeCode("PH");
        bt.setBookTypeDesc("Physical book");
        bt.setId(1L);
        daoSession.getBookTypeDao().insertOrReplace(bt);

         bt = new BookType();
        bt.setBookTypeCode("DG");
        bt.setBookTypeDesc("Digital book");
        bt.setId(2L);
        daoSession.getBookTypeDao().insertOrReplace(bt);

         bt = new BookType();
        bt.setBookTypeCode("AU");
        bt.setBookTypeDesc("Audio book");
        bt.setId(3L);
        daoSession.getBookTypeDao().insertOrReplace(bt);

    }

    private void initGenres() {
        Genre gn = new Genre();
        gn.setId(1L);
        gn.setGenreName("Fiction");
        daoSession.getGenreDao().insertOrReplace(gn);

         gn = new Genre();
        gn.setId(2L);
        gn.setGenreName("Science fiction");
        daoSession.getGenreDao().insertOrReplace(gn);

         gn = new Genre();
        gn.setId(3L);
        gn.setGenreName("Fantasy");
        daoSession.getGenreDao().insertOrReplace(gn);

         gn = new Genre();
        gn.setId(4L);
        gn.setGenreName("Non fiction");
        daoSession.getGenreDao().insertOrReplace(gn);
    }
    public DaoSession getDaoSession() {
        return daoSession;
    }
}
