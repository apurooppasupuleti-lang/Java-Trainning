public interface BookDao {

    void save(Book book);

    Book findById(int id);

    void deleteById(int id);

    void update(Book book);

    void deleteAll();

    Iterable<Book> findAll();

    Iterable<Book> findByAuthor(String author);

    Iterable<Book> findByTitle(String title);

    Iterable<Book> sortByTitleAsc();

    Iterable<Book> sortByTitleDesc();
}