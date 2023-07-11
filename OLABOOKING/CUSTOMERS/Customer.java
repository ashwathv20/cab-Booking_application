package CUSTOMERS;

public class Customer
{
  public static int pos=-1;
  public String name;  
  public String mobileNum;
  public String street;
  public String area;
  public String pinCode;

  public Customer(String x,String y[]){
      pos++;
    this.name = x;
    this.mobileNum = y[0];
    this.street = y[1];
    this.area = y[2];
    this.pinCode = y[3];
    
    }
}
