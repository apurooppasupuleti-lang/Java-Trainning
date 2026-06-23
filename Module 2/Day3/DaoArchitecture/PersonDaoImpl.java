import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PersonDaoImpl implements PersonDao {

    private List<Person> persons = new ArrayList<>();

    @Override
    public void save(Person person) {
        persons.add(person);
    }

    @Override
    public Person findById(int id) {
        for (Person p : persons) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        Person person = findById(id);
        if (person != null) {
            persons.remove(person);
        }
    }

    @Override
    public void update(Person person) {
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getId() == person.getId()) {
                persons.set(i, person);
                return;
            }
        }
    }

    @Override
    public void deleteAll() {
        persons.clear();
    }

    @Override
    public Iterable<Person> findAll() {
        return persons;
    }

    @Override
    public Iterable<Person> findByCity(String city) {
        List<Person> result = new ArrayList<>();
        for (Person p : persons) {
            if (p.getCity().equalsIgnoreCase(city)) {
                result.add(p);
            }
        }
        return result;
    }

    @Override
    public Iterable<Person> findByName(String name) {
        List<Person> result = new ArrayList<>();
        for (Person p : persons) {
            if (p.getName().equalsIgnoreCase(name)) {
                result.add(p);
            }
        }
        return result;
    }

    @Override
    public Iterable<Person> sortByNameAsc() {
        List<Person> result = new ArrayList<>(persons);
        Collections.sort(result, Comparator.comparing(Person::getName));
        return result;
    }

    @Override
    public Iterable<Person> sortByNameDesc() {
        List<Person> result = new ArrayList<>(persons);
        Collections.sort(result, Comparator.comparing(Person::getName).reversed());
        return result;
    }

    @Override
    public Iterable<Person> sortByAgeAsc() {
        List<Person> result = new ArrayList<>(persons);
        Collections.sort(result, Comparator.comparingInt(Person::getAge));
        return result;
    }

    @Override
    public Iterable<Person> sortByAgeDesc() {
        List<Person> result = new ArrayList<>(persons);
        Collections.sort(result, Comparator.comparingInt(Person::getAge).reversed());
        return result;
    }
}
