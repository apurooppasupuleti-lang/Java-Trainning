public class Student {

    private int id;
    private String name;
    private int phy;
    private int chem;
    private int math;
    private int history;
    private int geography;

    public Student(int id, String name, int phy, int chem, int math, int history, int geography) {
        this.id = id;
        this.name = name;
        this.phy = phy;
        this.chem = chem;
        this.math = math;
        this.history = history;
        this.geography = geography;
    }

    public int getId()          { return id; }
    public String getName()     { return name; }
    public int getPhy()         { return phy; }
    public int getChem()        { return chem; }
    public int getMath()        { return math; }
    public int getHistory()     { return history; }
    public int getGeography()   { return geography; }

    public int getTotalMarks() {
        return phy + chem + math + history + geography;
    }

    @Override
    public String toString() {
        return "Student [id=" + id +
                ", name=" + name +
                ", phy=" + phy +
                ", chem=" + chem +
                ", math=" + math +
                ", history=" + history +
                ", geography=" + geography +
                ", total=" + getTotalMarks() + "]";
    }
}
