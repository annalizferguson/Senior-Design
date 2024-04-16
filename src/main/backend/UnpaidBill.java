package main.backend;

public class UnpaidBill {
    private String unpaidId;
    private float amountDue;
    private float currentAmountDue;
    private String dueDate;

    public UnpaidBill(String id, float amount, String date){
        unpaidId = id;
        amountDue = amount;
        currentAmountDue = amount;
        dueDate = date;
    }

    public UnpaidBill(float amount, String date){
        amountDue = amount;
        dueDate = date;
    }

    public String getUnpaidId(){
        return unpaidId;
    }

    public float getAmountDue(){
        return amountDue;
    }

    public float getCurrentAmountDue(){
        return currentAmountDue;
    }

    public String getDueDate(){
        return dueDate;
    }

    public void setUnpaidId(String id){
        unpaidId = id;
    }

    public void setAmountDue(float amount){
        amountDue = amount;
    }

    public void setCurrentAmountDue(float amount){
        currentAmountDue = amount;
    }

    public void setDueDate(String d){
        dueDate = d;
    }
}
