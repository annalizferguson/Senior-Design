package main.backend;

public class PaidBill {
    private String paidId;
    private String payeeName;
    private String address;
    private float amount;
    private String dateDue;
    private String accountPaidFrom;

    public PaidBill(String id, String name, String addr, float am, String date, String acc){
        paidId = id;
        payeeName = name;
        address = addr;
        amount = am;
        dateDue = date;
        accountPaidFrom = acc;
    }

    public PaidBill(String name, String addr, float am, String date, String acc){
        payeeName = name;
        address = addr;
        amount = am;
        dateDue = date;
        accountPaidFrom = acc;
    }

    public String getPaidId(){
        return paidId;
    }

    public String getPayeeName(){
        return payeeName;
    }

    public String getAddress(){
        return address;
    }

    public float getAmount(){
        return amount;
    }

    public String getDateDue(){
        return dateDue;
    }

    public String getAccountPaidFrom(){
        return accountPaidFrom;
    }

    public void setPaidId(String id){
        paidId = id;
    }

    public void setPayeeName(String name){
        payeeName = name;
    }

    public void setAddress(String addr){
        address = addr;
    }

    public void setAmount(float am){
        amount = am;
    }

    public void setDateDue(String date){
        dateDue = date;
    }

    public void setAccountPaidFrom(String acc){
        accountPaidFrom = acc;
    }
}
