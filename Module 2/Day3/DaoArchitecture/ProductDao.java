public interface ProductDao {

    void save(Product product);

    Product findById(int id);

    void deleteById(int id);

    void update(Product product);

    void deleteAll();

    Iterable<Product> findAll();
    

    Iterable<Product> findByCategory(String category);

    Iterable<Product> findByName(String name);

    Iterable<Product> sortByNameAsc();

    Iterable<Product> sortByNameDesc();

    Iterable<Product> sortByPriceAsc();

    Iterable<Product> sortByPriceDesc();
}
