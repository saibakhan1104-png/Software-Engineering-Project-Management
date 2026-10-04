class BankAccount {
    String owner;
    double balance;
    static String bankName = "ABC Bank";

    BankAccount(String owner,double balance)
    {
        this.balance=balance;
        this.owner=owner;
    }
    public void seeDetails(){
        System.out.println("Name: "+ owner +
                "         Account balance: "+ balance +
                "         Bank name: "+bankName);
    }
}

public class MainBank {
    public static void main(String[] args){
        BankAccount b1=new BankAccount("Mr Raian",70000);
        BankAccount b2=new BankAccount("Md Rafid",60000);
        BankAccount b3=new BankAccount("Ms Saiba",80000);

        BankAccount.bankName = "XYZ Bank";

        b1.seeDetails();
        b2.seeDetails();
        b3.seeDetails();


    }
}