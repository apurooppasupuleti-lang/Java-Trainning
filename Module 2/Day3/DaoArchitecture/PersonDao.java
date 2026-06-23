public interface PersonDao {

    void save(Person person);

    Person findById(int id);

    void deleteById(int id);

    void update(Person person);

    void deleteAll();

    Iterable<Person> findAll();

    Iterable<Person> findByCity(String city);

    Iterable<Person> findByName(String name);

    Iterable<Person> sortByNameAsc();

    Iterable<Person> sortByNameDesc();

    Iterable<Person> sortByAgeAsc();

    Iterable<Person> sortByAgeDesc();
}
