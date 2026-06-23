import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private List<Product> products = new ArrayList<>();

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public Product findById(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        Product product = findById(id);
        if (product != null) {
            products.remove(product);
        }
    }

    @Override
    public void update(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == product.getId()) {
                products.set(i, product);
                return;
            }
        }
    }

    @Override
    public void deleteAll() {
        products.clear();
    }

    @Override
    public Iterable<Product> findAll() {
        return products;
    }

    @Override
    public Iterable<Product> findByCategory(String category) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                result.add(p);
            }
        }
        return result;
    }

    @Override
    public Iterable<Product> findByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                result.add(p);
            }
        }
        return result;
    }
    
    @Override
    public Iterable<Product> sortByNameAsc() {
        List<Product> result = new ArrayList<>(products);
        Collections.sort(result, Comparator.comparing(Product::getName));
        return result;
    }

    @Override
    public Iterable<Product> sortByNameDesc() {
        List<Product> result = new ArrayList<>(products);
        Collections.sort(result, Comparator.comparing(Product::getName).reversed());
        return result;
    }

    @Override
    public Iterable<Product> sortByPriceAsc() {
        List<Product> result = new ArrayList<>(products);
        Collections.sort(result, Comparator.comparingDouble(Product::getPrice));
        return result;
    }

    @Override
    public Iterable<Product> sortByPriceDesc() {
        List<Product> result = new ArrayList<>(products);
        Collections.sort(result, Comparator.comparingDouble(Product::getPrice).reversed());
        return result;
    }
}

