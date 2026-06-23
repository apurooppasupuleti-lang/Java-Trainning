public class MainPerson {

    public static void main(String[] args) {

        PersonDao dao = new PersonDaoImpl();

        dao.save(new Person(1, "Apuroop",   "Chennai",   28));
        dao.save(new Person(2, "Varun",   "Hyderabad", 24));
        dao.save(new Person(3, "Roshini",  "Vijayawada",   35));
        dao.save(new Person(4, "Noble",   "Kakinada", 30));

        System.out.println("----- All Persons -----");
        for (Person p : dao.findAll()) {
            System.out.println(p);
        }

        System.out.println("\n----- Find By Id -----");
        System.out.println(dao.findById(2));

        System.out.println("\n----- Find By City -----");
        for (Person p : dao.findByCity("Chennai")) {
            System.out.println(p);
        }

        System.out.println("\n----- Sort By Name Asc -----");
        for (Person p : dao.sortByNameAsc()) {
            System.out.println(p);
        }

        System.out.println("\n----- Sort By Name Desc -----");
        for (Person p : dao.sortByNameDesc()) {
            System.out.println(p);
        }

        System.out.println("\n----- Sort By Age Asc -----");
        for (Person p : dao.sortByAgeAsc()) {
            System.out.println(p);
        }

        System.out.println("\n----- Sort By Age Desc -----");
        for (Person p : dao.sortByAgeDesc()) {
            System.out.println(p);
        }

        dao.update(new Person(2, "Varun", "Mumbai", 25));

        System.out.println("\n----- After Update -----");
        for (Person p : dao.findAll()) {
            System.out.println(p);
        }

        dao.deleteById(4);

        System.out.println("\n----- After Delete -----");
        for (Person p : dao.findAll()) {
            System.out.println(p);
        }
    }
}
