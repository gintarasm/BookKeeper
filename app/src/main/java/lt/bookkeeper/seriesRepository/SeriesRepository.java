package lt.bookkeeper.seriesRepository;

import android.content.Context;

import java.util.List;

import bookKeeper.Series;
import bookKeeper.SeriesDao;
import de.greenrobot.dao.query.QueryBuilder;
import lt.bookkeeper.daoAplication.DaoAplication;

/**
 * Created by Hive on 2014.12.19.
 */
public class SeriesRepository {
    public static void insertOrUpdate(Context context, Series series) {
        getSeriesDao(context).insertOrReplace(series);
    }

    public static void deleteSeries(Context context, Series book) {
        getSeriesDao(context).delete(book);
    }

    public static List<Series> getAllSeries(Context context) {
        return getSeriesDao(context).loadAll();
    }

    public static Series getSeriesById(Context context, long id) {
        return getSeriesDao(context).load(id);
    }

    private static SeriesDao getSeriesDao(Context c) {
        return ((DaoAplication)c.getApplicationContext()).getDaoSession().getSeriesDao();
    }

}
