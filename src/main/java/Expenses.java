
public class Expenses {
    String name;
    Integer sumPaid;

    public Expenses(String name, int sumPaid) {
        this.name = name;
        this.sumPaid = sumPaid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSumPaid() {
        return sumPaid;
    }

    public void setSumPaid(int sumPaid) {
        this.sumPaid = sumPaid;
    }


}
