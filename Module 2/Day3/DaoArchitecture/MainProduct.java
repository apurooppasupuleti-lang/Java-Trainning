public class MainProduct {

    public static void main(String[] args) {

        ProductDao dao = new ProductDaoImpl();

        dao.save(new Product(1, "Laptop","Electronics", 75000.00));
        dao.save(new Product(2, "Phone", "Electronics", 30000.00));
        dao.save(new Product(3, "Shirt", "Clothing",    999.00));
        dao.save(new Product(4, "Table", "Furniture", 5000.00));

        System.out.println("----- All Products -----");
        for (Product p : dao.findAll()) {
            System.out.println(p);
        }

        System.out.println("\n----- Find By Id -----");
        System.out.println(dao.findById(3));

        System.out.println("\n----- Find By Category -----");
        for (Product p : dao.findByCategory("Electronics")) {
            System.out.println(p);
        }

        System.out.println("\n----- Sort By Name Asc -----");
        for (Product p : dao.sortByNameAsc()) {
            System.out.println(p);
        }

        System.out.println("\n----- Sort By Name Desc -----");
        for (Product p : dao.sortByNameDesc()) {
            System.out.println(p);
        }

        System.out.println("\n----- Sort By Price Asc -----");
        for (Product p : dao.sortByPriceAsc()) {
            System.out.println(p);
        }

        System.out.println("\n----- Sort By Price Desc -----");
        for (Product p : dao.sortByPriceDesc()) {
            System.out.println(p);
        }

        dao.update(new Product(2, "Phone", "Electronics", 28000.00));

        System.out.println("\n----- After Update -----");
        for (Product p : dao.findAll()) {
            System.out.println(p);
        }

        dao.deleteById(3);

        System.out.println("\n----- After Delete -----");
        for (Product p : dao.findAll()) {
            System.out.println(p);
        }
    }
}
