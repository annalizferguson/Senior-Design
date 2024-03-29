import java.util.Date;

public class PaidBill {
    private String payeeName;
    private String address;
    private float amount;
    private Date dateDue;
    private String accountPaidFrom;

    public PaidBill(String name, String addr, float am, Date date, String acc){
        payeeName = name;
        address = addr;
        amount = am;
        dateDue = date;
        accountPaidFrom = acc;
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

    public Date getDateDue(){
        return dateDue;
    }

    public String getAccountPaidFrom(){
        return accountPaidFrom;
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

    public void setDateDue(Date date){
        dateDue = date;
    }

    public void setAccountPaidFrom(String acc){
        accountPaidFrom = acc;
    }
}
