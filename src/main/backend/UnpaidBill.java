import java.util.Date;

public class UnpaidBill {
    private float amountDue;
    private Date dueDate;

    public UnpaidBill(float amount, Date date){
        amountDue = amount;
        dueDate = date;
    }

    public float getAmountDue(){
        return amountDue;
    }

    public Date getDueDate(){
        return dueDate;
    }

    public void setAmountDue(float amount){
        amountDue = amount;
    }

    public void setDueDate(Date d){
        dueDate = d;
    }
}
