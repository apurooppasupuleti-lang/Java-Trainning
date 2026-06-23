public class MainBook {

    public static void main(String[] args) {

        BookDao dao = new BookDaoImpl();

        dao.save(new Book(1, "Khaleja", "Trivikram"));
        dao.save(new Book(2, "Maharshi", "Koratala Shiva"));
        dao.save(new Book(3, "Athadu", "Trivikram"));
        dao.save(new Book(4, "Pokiri", "Puri Jaganadh"));

        System.out.println("----- All Books -----");
        for (Book b : dao.findAll()) {
            System.out.println(b);
        }

        System.out.println("\n----- Find By Id -----");
        System.out.println(dao.findById(2));

        System.out.println("\n----- Find By Author -----");
        for (Book b : dao.findByAuthor("Trivikram")) {
            System.out.println(b);
        }

        System.out.println("\n----- Sort Asc -----");
        for (Book b : dao.sortByTitleAsc()) {
            System.out.println(b);
        }

        System.out.println("\n----- Sort Desc -----");
        for (Book b : dao.sortByTitleDesc()) {
            System.out.println(b);
        }

        dao.update(new Book(2, "Maharshi", "Shiva"));

        System.out.println("\n----- After Update -----");
        for (Book b : dao.findAll()) {
            System.out.println(b);
        }

        dao.deleteById(4);

        System.out.println("\n----- After Delete -----");
        for (Book b : dao.findAll()) {
            System.out.println(b);
        }
    }
}