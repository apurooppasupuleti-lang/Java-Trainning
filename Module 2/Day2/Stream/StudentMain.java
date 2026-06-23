import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentMain {
    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();

        students.add(new Student(1, "Apuroop",   85, 90, 78, 88, 92));
        students.add(new Student(2, "Varun",     72, 65, 80, 70, 68));
        students.add(new Student(3, "Noble", 91, 88, 95, 82, 79));
        students.add(new Student(4, "Roshini",   60, 75, 70, 85, 90));
        students.add(new Student(5, "Sam",   78, 82, 88, 74, 76));
        System.out.println("=== Max Marks Per Subject ===");

        students.stream()
                .max(Comparator.comparingInt(Student::getPhy))
                .ifPresent(s -> System.out.println("Physics    : " + s.getPhy()));

        students.stream()
                .max(Comparator.comparingInt(Student::getChem))
                .ifPresent(s -> System.out.println("Chemistry  : " + s.getChem()));
        students.stream()
                .max(Comparator.comparingInt(Student::getMath))
                .ifPresent(s -> System.out.println("Maths      : " + s.getMath()));

        students.stream()
                .max(Comparator.comparingInt(Student::getHistory))
                .ifPresent(s -> System.out.println("History    : " + s.getHistory()));

        students.stream()
                .max(Comparator.comparingInt(Student::getGeography))
                .ifPresent(s -> System.out.println("Geography  : " + s.getGeography()));

        System.out.println("\n=== Topper Per Subject ===");

        students.stream()
                .max(Comparator.comparingInt(Student::getPhy))
                .ifPresent(s -> System.out.println("Physics    : " + s.getName() + " (" + s.getPhy() + ")"));

        students.stream()
                .max(Comparator.comparingInt(Student::getChem))
                .ifPresent(s -> System.out.println("Chemistry  : " + s.getName() + " (" + s.getChem() + ")"));

        students.stream()
                .max(Comparator.comparingInt(Student::getMath))
                .ifPresent(s -> System.out.println("Maths      : " + s.getName() + " (" + s.getMath() + ")"));

        students.stream()
                .max(Comparator.comparingInt(Student::getHistory))
                .ifPresent(s -> System.out.println("History    : " + s.getName() + " (" + s.getHistory() + ")"));

        students.stream()
                .max(Comparator.comparingInt(Student::getGeography))
                .ifPresent(s -> System.out.println("Geography  : " + s.getName() + " (" + s.getGeography() + ")"));

        System.out.println("\n=== Overall Topper ===");
        students.stream()
                .max(Comparator.comparingInt(Student::getTotalMarks))
                .ifPresent(s -> System.out.println(s.getName() + " with total " + s.getTotalMarks()));
                
        System.out.println("\n=== Average Per Subject ===");
        
        int avgPhy     = students.stream().mapToInt(Student::getPhy).sum() / students.size();
        int avgChem    = students.stream().mapToInt(Student::getChem).sum() / students.size();
        int avgMath    = students.stream().mapToInt(Student::getMath).sum() / students.size();
        int avgHistory = students.stream().mapToInt(Student::getHistory).sum() / students.size();
        int avgGeo     = students.stream().mapToInt(Student::getGeography).sum() / students.size();
        

        System.out.println("Physics    : " + avgPhy);
        System.out.println("Chemistry  : " + avgChem);
        System.out.println("Maths      : " + avgMath);
        System.out.println("History    : " + avgHistory);
        System.out.println("Geography  : " + avgGeo);
        
        System.out.println("\n=== Students Above Average in Physics ===");
        System.out.println("(Physics average: " + avgPhy + ")");
        List<Student> aboveAvgPhy = students.stream()
                .filter(s -> s.getPhy() > avgPhy)
                .sorted(Comparator.comparingInt(Student::getPhy).reversed())
                .toList();

        aboveAvgPhy.forEach(System.out::println);
    }
}
