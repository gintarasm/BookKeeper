package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class MyDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "bookKeeper");
        Entity book = schema.addEntity("Book");
        book.addIdProperty().autoincrement();
        book.addStringProperty("author").notNull();
        book.addStringProperty("bookName").notNull();
        book.addStringProperty("comments");
        book.addStringProperty("releaseDate");
        book.addIntProperty("rating");
        book.addStringProperty("type");

        Entity series = schema.addEntity("Series");
        series.addIdProperty();
        series.addStringProperty("seriesName").notNull();

        Entity bookType = schema.addEntity("BookType");
        bookType.addIdProperty();
        bookType.addStringProperty("bookTypeCode").notNull();
        bookType.addStringProperty("BookTypeDesc").notNull();

        Entity genre = schema.addEntity("Genre");
        genre.addIdProperty();
        genre.addStringProperty("genreName").notNull();

        Property genreBookId =  book.addLongProperty("genreId").getProperty();
        Property seriesBookIdProperty =  book.addLongProperty("seriesId").getProperty();
        Property bookTypeBookIdProperty =  book.addLongProperty("bookTypeId").getProperty();

        book.addToOne(genre,genreBookId);
        book.addToOne(series,seriesBookIdProperty);
        book.addToOne(bookType,bookTypeBookIdProperty);

        ToMany genret = genre.addToMany(book, genreBookId);
        genret.setName("genres");
        ToMany sert = series.addToMany(book,seriesBookIdProperty);
        sert.setName("series");
        ToMany bookTT = bookType.addToMany(book, bookTypeBookIdProperty);
        bookTT.setName("bookTypes");


        new DaoGenerator().generateAll(schema, args[0]);
    }
}
