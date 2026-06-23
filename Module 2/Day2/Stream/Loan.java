

public class Loan {

    private int id;
    private int loanAmount;
    private int loanTenure;
    private int loanInterest;
    private String loanStatus;
    private String loanType;


    
    public Loan(int id, int loanAmount, int loanTenure, int loanInterest, String loanStatus, String loanType) {
        this.id = id;
        this.loanAmount = loanAmount;
        this.loanTenure = loanTenure;
        this.loanInterest = loanInterest;
        this.loanStatus = loanStatus;
        this.loanType = loanType;
    }

    
    public int getId() {
        return id;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public int getLoanTenure() {
        return loanTenure;
    }

    public int getLoanInterest() {
        return loanInterest;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public String getLoanType() {
        return loanType;
    }

   
    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setLoanTenure(int loanTenure) {
        this.loanTenure = loanTenure;
    }

    public void setLoanInterest(int loanInterest) {
        this.loanInterest = loanInterest;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }
     
    @Override
    public String toString() {
        return "Loan [id=" + id +
                ", amount=" + loanAmount +
                ", tenure=" + loanTenure +
                ", interest=" + loanInterest +
                ", status=" + loanStatus +
                ", type=" + loanType + "]";
    }
}