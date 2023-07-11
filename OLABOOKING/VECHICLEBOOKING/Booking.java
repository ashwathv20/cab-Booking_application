package VECHICLEBOOKING;


import java.util.Scanner;
import SERVICES.Car;
import CUSTOMERS.Customer;
import CAB.TravelAgency;
import SERVICES.*;
public class Booking{
    static int idGen = -1;
    int Id;
    String cabNo;
    String name;
    String cabType;
    String date;
    String time;
    String Source;
    String Destination;
    String number;
    String status;// true for Attended | false for cancelled
    void display(){
    System.out.println("Name : "+name);
    System.out.println("Number : "+number);
   System.out.println("Vehicle Number :"+cabNo); 
   System.out.println("Vehicle Type : "+cabType);
   System.out.println("Date :"+date);
   System.out.println("Booking Time: "+ time);
   System.out.println("Source : "+Source);
   System.out.println("Destination : "+Destination);
   System.out.println("Status :"+this.status);
    }
    Booking(){
        idGen++;
    this.Id = idGen;
    this.status = " ";
    
    }
    public static boolean isCabAvailable(String x){
       int n = TravelAgency.VehicleNumList.length;
    for(int i = 0;i<n;i++){
    if(x.equals(TravelAgency.driverList[i].getType()) && TravelAgency.driverList[i].isAvailable())
    {
    return true;}
    
    
    }
    return false;
    }
     public static void main(){
        Driver.intializeDrivers();
        Services.initialiseSerives();
        TravelAgency.bookingList[0] = bookCab();
        TravelAgency.bookingList[0].display();
    }
    
public static Booking bookCab(){
       Scanner obj = new Scanner(System.in);
       System.out.println("Enter your Cab Type ");
       String type = obj.nextLine();
    if(isCabAvailable(type))
       {
        Booking temp = new Booking();
        temp.cabType = type;
        
           int n = TravelAgency.VehicleNumList.length;
           int t = 0;
    for(int i = 0;i<n;i++){
    if(type.equals(TravelAgency.driverList[i].getType()) && TravelAgency.driverList[i].isAvailable())
    {
    t = i;
    break;

    } }
      int pos = Services.findIndex(TravelAgency.cabTypeList,type);
      System.out.println(pos);
      TravelAgency.serviceList[pos].count--;
     
        temp.cabNo = TravelAgency.driverList[t].vehicleNumber;
         TravelAgency.driverList[t].isAvailable = false;
        System.out.println("Enter the Date('YYYY/MM/DD')");
        temp.date = obj.nextLine();
        System.out.println("Enter the Time(HH:MM)");
        temp.time = obj.nextLine();
      
        System.out.println("Enter Staring Point:");
        temp.Source = obj.nextLine();
        System.out.println("Enter Destination :");
        temp.Destination = obj.nextLine();
        return temp;
        
        }
        else{
        System.out.println("Requested Service not Available");
        }
       return null;
    }
   

}
