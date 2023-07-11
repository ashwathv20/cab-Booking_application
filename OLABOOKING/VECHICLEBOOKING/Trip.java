package VECHICLEBOOKING;
import java.util.Scanner;
import CUSTOMERS.*;
import CAB.*;
import SERVICES.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class Trip{
int tripId;
boolean isBillReady;
static int Id = -1;
int bookingId;
String driverName;
String driverPhoneNum;
String startTime;
String endTime;
double hours;
double amount;
double discount;
double additionalCharges;
double total;
Trip(String x,String y){
Id++;
tripId  = Id;
isBillReady = false;
bookingId = Id;
driverName = x;
driverPhoneNum = y;
amount = 0;
discount = 0;
additionalCharges = 0;
}
public static void displayBookingsCustomer(String name){
System.out.println("Customer Name : "+name);
boolean flag = false;
    for(int i = 0;i<=Trip.Id;i++){
        if(TravelAgency.bookingList[i].name.equals(name)){
        TravelAgency.bookingList[i].display();
      flag = true;    
    }
}
if(flag==false){
    System.out.println("you have no Prior Bookings");
    }

}
public static void displayCancellationConsumter(String name)
{    System.out.println("All the Cancellations by the Customer");
    boolean flag = false;
    System.out.println("Customer Name : "+name);
    for(int i = 0;i<=Booking.idGen;i++){
        if(TravelAgency.bookingList[i].name.equals(name)&&TravelAgency.bookingList[i].status.equals("Cancelled")){
        TravelAgency.bookingList[i].display();
        flag = true;
        }
 
}if(flag==false){
    System.out.println("you have no Prior Cancellations");
    }
}
public static void getMonthlyAverage(){
int n = Booking.idGen;
String Months[] = {"Jan","Feb","March","April","May","June","July","Augut","September","Octomber","November",
    "December"};
double Amount[] = new double[12];
double count[] = new double[12];
for(int i = 0;i<12;i++){
Amount[i] = 0;
count[i] = 0;
}
for(int i = 0;i<=Trip.Id;i++){
if(TravelAgency.bookingList[i].status.equals("Attended")||true){
int m = Integer.parseInt(TravelAgency.bookingList[i].date.substring(5,7))-1;
System.out.println(TravelAgency.bookingList[i].date.substring(5,7));
count[m]++;
Amount[m] =Amount[m] + TravelAgency.tripsList[i].total;
}
}
for(int i = 0;i<12;i++){
if(count[i]==0){
//Amount[i]=0;
}
else{
Amount[i] = Amount[i]/count[i];
}
}
System.out.println("Printing Monthly Average of Bills of All customers");
System.out.println("Month\tAverage Bills ");
for(int i = 0;i<12;i++){

System.out.println(Months[i]+ "\t"+ Amount[i]);
}


}

void calcCharges(){
Scanner obj = new Scanner(System.in);
if(TravelAgency.bookingList[bookingId].status.equals("Cancelled")){
    this.display();
System.out.println("This Booking has been cancelled "); 
return;}

System.out.print("Enter start time(12 hrs, hh:mm am/pm): ");
startTime = obj.nextLine();
System.out.print("Enter end time(12 hrs, hh:mm am/pm): ");
endTime = obj.nextLine();
SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
Date start = new Date();
Date end = new Date();
String cabType = TravelAgency.bookingList[bookingId].cabType;
    try{
                    start = sdf.parse(startTime);
                    end = sdf.parse(endTime);
}catch(Exception e){

 }
 
 this.isBillReady = true;
   long diff = Math.abs(end.getTime() - start.getTime());
                diff = TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
                
                long diff2 = Math.abs(end.getTime()-start.getTime());
                diff2 = diff2%(3600*1000);
                diff2 = TimeUnit.MINUTES.convert(diff2,TimeUnit.MILLISECONDS);
                  System.out.println(diff2);
                double min = diff2;
                if(diff >=8){
                   this.additionalCharges = 100;
                }

                double total = 0.0;
                Date morningPeakStart = new Date();
                Date morningPeakEnd = new Date();
                Date eveningPeakStart = new Date();
                Date eveningPeakEnd = new Date();
                try{
                    morningPeakStart = sdf.parse("08:00 am"); 
                    morningPeakEnd = sdf.parse("10:00 am"); 
                    eveningPeakStart = sdf.parse("05:00 pm"); 
                    eveningPeakEnd = sdf.parse("08:00 pm");
                }catch(Exception e){
                }
                
                double cabPricePerHours = 0.0;
                for(int j=0; j<TravelAgency.serviceList.length; j++){
                    if(TravelAgency.serviceList[j].type.equals(cabType)){
                        cabPricePerHours = TravelAgency.serviceList[j].rate;
                        break;
                    }
                }


                if(start.after(morningPeakStart) && end.before(morningPeakEnd)){
                    total = (double)(diff+min/60)*1.5*cabPricePerHours;
                    this.additionalCharges += (double)(diff+min/60)*1.5*cabPricePerHours - (double)(diff+min/60)*cabPricePerHours;
                    //System.out.println("First if Running");
                }
                if(start.after(eveningPeakStart) && end.before(eveningPeakEnd)){
                    total =  (double)(diff+min/60)*1.5*cabPricePerHours;
                    this.additionalCharges += (double)(diff+min/60)*1.5*cabPricePerHours-(double)(diff+min/60)*cabPricePerHours;
                     /// System.out.println("second if Running");

                }
                    if(start.after(morningPeakStart) && end.after(morningPeakEnd))
                    {
                        long hoursDiff = Math.abs(start.getTime()-morningPeakEnd.getTime());
                        hoursDiff = TimeUnit.HOURS.convert(hoursDiff, TimeUnit.MILLISECONDS);
                        long minDiff = Math.abs(end.getTime()-start.getTime());
                        minDiff = minDiff%(3600*1000);
                        minDiff = TimeUnit.MINUTES.convert(diff2,TimeUnit.MILLISECONDS);
                        double minDiff2 = (double)minDiff;
                        this.additionalCharges += ((double)hoursDiff+minDiff2/60)*0.5*cabPricePerHours;
                          //System.out.println("third if Running");

                    }
                    if(start.before(morningPeakStart) && end.before(morningPeakEnd)){
                      long hoursDiff = Math.abs(morningPeakStart.getTime()-end.getTime());
                        hoursDiff = TimeUnit.HOURS.convert(hoursDiff, TimeUnit.MILLISECONDS);
                        long minDiff = Math.abs(end.getTime()-start.getTime());
                        minDiff = minDiff%(3600*1000);
                        minDiff = TimeUnit.MINUTES.convert(diff2,TimeUnit.MILLISECONDS);
                        double minDiff2 = (double)minDiff;
                        this.additionalCharges += ((double)hoursDiff+minDiff2/60)*0.5*cabPricePerHours;
                          //System.out.println("Four if Running");

                    }
                    /*if(start.before(morningPeakStart) && end.before(morningPeakEnd)){
                      long hoursDiff = Math.abs(morningPeakStart.getTime()-end.getTime());
                        hoursDiff = TimeUnit.HOURS.convert(hoursDiff, TimeUnit.MILLISECONDS);
                        long minDiff = Math.abs(end.getTime()-start.getTime());
                        minDiff = minDiff%(3600*1000);
                        minDiff = TimeUnit.MINUTES.convert(diff2,TimeUnit.MILLISECONDS);
                        double minDiff2 = (double)minDiff;
                        this.additionalCharges += ((double)hoursDiff+minDiff2/60)*0.5*cabPricePerHours;
                     // System.out.println("fifth if Running");

                    
                    }*/
                    
                    if(start.before(morningPeakStart) && end.after(morningPeakEnd))
                    {
                       this.additionalCharges += 2 *0.5*cabPricePerHours; 
                       //  System.out.println("sixth if Running");

                    }
                    if(start.after(eveningPeakStart) && end.after(eveningPeakEnd))
                    {
                        long hoursDiff = Math.abs(start.getTime()-eveningPeakEnd.getTime());
                        hoursDiff = TimeUnit.HOURS.convert(hoursDiff, TimeUnit.MILLISECONDS);
                        long minDiff = Math.abs(end.getTime()-start.getTime());
                        minDiff = minDiff%(3600*1000);
                        minDiff = TimeUnit.MINUTES.convert(diff2,TimeUnit.MILLISECONDS);
                        double minDiff2 = (double)minDiff;
                        this.additionalCharges += ((double)hoursDiff+minDiff2/60)*0.5*cabPricePerHours;
                         // System.out.println("seventh if Running");

                    }
                    if(start.before(eveningPeakStart) && end.after(eveningPeakEnd))
                    {
                       this.additionalCharges += 2 *0.5*cabPricePerHours; 
                        // System.out.println("eighth if Running");

                    }
                     if(start.before(eveningPeakStart) && end.before(eveningPeakEnd)){
                      long hoursDiff = Math.abs(eveningPeakStart.getTime()-end.getTime());
                        hoursDiff = TimeUnit.HOURS.convert(hoursDiff, TimeUnit.MILLISECONDS);
                        long minDiff = Math.abs(end.getTime()-start.getTime());
                        minDiff = minDiff%(3600*1000);
                        minDiff = TimeUnit.MINUTES.convert(diff2,TimeUnit.MILLISECONDS);
                        double minDiff2 = (double)minDiff;
                        this.additionalCharges += ((double)hoursDiff+minDiff2/60)*0.5*cabPricePerHours;
                      //System.out.println("nineth if Running");

                    
                    }
                    
                    
                    total =  (double)(diff+min/60)*cabPricePerHours;
                
                this.hours = (double)(diff + min/60);

                // If its another trip on the same week
                amount =  (double)(diff+min/60)*cabPricePerHours;
                for(int j=0; j<this.bookingId; j++){
                    // Its the same customer if they have the same phone no.
                    if(TravelAgency.bookingList[j].number.equals(TravelAgency.bookingList[bookingId].number)){
                        // Checking if the same week
                        try{
                            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy/MM/dd");
                            Date lastBookingDate = sdfDate.parse(TravelAgency.bookingList[j].date);
                            Date currentBookingDate = sdfDate.parse(TravelAgency.bookingList[bookingId].date);
                            long dateDiff = TimeUnit.DAYS.convert(Math.abs(currentBookingDate.getTime() - lastBookingDate.getTime()), TimeUnit.MILLISECONDS);
                            if(dateDiff <= 7){
                                discount += 0.005; // 0.5%
                            }
                        }catch(Exception e){

                        }
                    }
                }
                Date bookingTime = new Date();
                try{
                     bookingTime= sdf.parse(TravelAgency.bookingList[bookingId].time);
}catch(Exception e){

 }
                long diff5 = Math.abs(start.getTime()-bookingTime.getTime());
                diff5 = diff5%(3600*1000);
                diff5 = TimeUnit.MINUTES.convert(diff5,TimeUnit.MILLISECONDS);
                if(diff5>=15){
                this.discount +=0.01;
                }
                this.total = this.amount*(1-discount)+additionalCharges;
 
 
 
 
TravelAgency.bookingList[this.bookingId].status = "Attended";
obj.close();
}
public static void displayTrips(){
System.out.println("Displaying All the Trip Details.......\n\n\n");
for(int i = 0;i<=Trip.Id;i++){
if(TravelAgency.tripsList[i].isBillReady){
TravelAgency.tripsList[i].display();
System.out.println();
System.out.println();
}}
}
public static void displaysTrips(String Name){
System.out.println("Displaying All the Trip Details....... of "+ Name +"\n\n\n");
for(int i = 0;i<=Trip.Id;i++){
if(TravelAgency.tripsList[i].driverName.equals(Name) && TravelAgency.tripsList[i].isBillReady);
{
TravelAgency.tripsList[i].display();
System.out.println();
System.out.println();
}
}}
public void displayDriverDetails(){
System.out.println("Driver Name : "+driverName);
System.out.println("Driver Phone Number :"+driverPhoneNum);

}

void display(){
System.out.println("Trip Id : "+tripId);
System.out.println("Booking Id :"+bookingId);
System.out.println("Driver Name :"+driverName);
System.out.println("Driver Phone Number :"+driverPhoneNum);
System.out.println("Start Time :"+startTime);
System.out.println("end Time :"+endTime);
System.out.println("Amount :"+amount);
System.out.println("Discount :"+discount);
System.out.println("Additional Charges :"+additionalCharges);
System.out.println("Total :"+this.total);
System.out.println("Status :"+TravelAgency.bookingList[this.bookingId].status);
}


public void displayBill(){
System.out.print("Travel Agency Name :" + TravelAgency.travelAgencyName);
System.out.print("\t");
System.out.print("Travel Agency Numbers : ");
for(int i = 0;i<TravelAgency.TravelAgencyNumList.length;i++){
if(i==TravelAgency.TravelAgencyNumList.length-1){
System.out.print(TravelAgency.TravelAgencyNumList[i]);
break;
}
System.out.print(TravelAgency.TravelAgencyNumList[i]+",");
}
System.out.println();
System.out.print("Customer Name:"+ TravelAgency.bookingList[bookingId].name);
System.out.print("\t");
System.out.println();
System.out.println("Customer Number:"+TravelAgency.bookingList[bookingId].number);
System.out.println("*****************************************************************");

System.out.println();
System.out.println();
System.out.print("Source : "+TravelAgency.bookingList[bookingId].Source);
System.out.print("\t");
System.out.println("Destination : "+ TravelAgency.bookingList[bookingId].Destination);
System.out.print("Start Time : "+this.startTime);
System.out.print("\t");
System.out.println("End Time : "+this.endTime);
System.out.println("Date : "+TravelAgency.bookingList[bookingId].date);
System.out.println("*****************************************************************");
System.out.println("Total Number of Hours: "+this.hours);
System.out.println("Amount : "+this.amount);
System.out.println("AdditionalCharges :"+this.additionalCharges);
System.out.println("Discount (in %):"+this.discount*100);
System.out.println("Total :"+this.total);
System.out.println();
System.out.println("*****************************************************************");
System.out.print("Driver Name: "+ this.driverName);
System.out.print("Driver Number: "+ this.driverPhoneNum);
System.out.println();
int z = findCustomer(TravelAgency.bookingList[bookingId].name);
System.out.println("Vehicle Number : "+ TravelAgency.bookingList[bookingId].cabNo);
}
public static int findCustomer(String x){
    for(int i = 0;i<TravelAgency.customerList.length;i++){
    if(x.equals(TravelAgency.customerList[i].name))
    {
    return i;
    }
    
    }
 return 0;   
}

public static void main(){
     Driver.intializeDrivers();
        Services.initialiseSerives();
Scanner obj = new Scanner(System.in);
char choice  = 'y';
do{
System.out.println("Welcome to OLA CABS .....");
System.out.println("Enter your Choice");
System.out.println("1. Book your CAB");
System.out.println("2. Cancel your Cab");
System.out.println("3. get Bill");
System.out.println("4. See CAB availablity");
System.out.println("5. Get All trips List");
System.out.println("6. Trips taken by Specific Driver");
System.out.println("7. All the Bookings done by Specific Customer");
System.out.println("8. All the Cancellations done by Specific Customer");
System.out.println("9. Get the Monthly Average ");
int ch = obj.nextInt();
switch(ch){
case 1:System.out.println("Are you a 1.Existing Customer or 2.New Customer ");
       int c = obj.nextInt();
       switch(c){
        case 2: 
        System.out.println("Avialable Services");
        Services.displayServices();
        System.out.println("Starting Registeration Process");
        System.out.println("Enter your Name");
        String t = obj.nextLine();
        String name = obj.nextLine();
        System.out.println("Enter your Mobile Number ");
        String num = obj.nextLine();
        System.out.println("Enter your Street");
        String street = obj.nextLine();
        System.out.println("Enter your Area");
        String Area = obj.nextLine();
        System.out.println("Enter your PinCode");
        String pincode = obj.nextLine();
        String Address[] = {num,street,Area,pincode};
        TravelAgency.customerList[Customer.pos+1] = new Customer(name,Address);
        Booking temp = Booking.bookCab();
        if(temp==null){
        System.out.println("Try Again with Different Cab Type");
        }
        else{
        TravelAgency.bookingList[Booking.idGen] = temp;
        System.out.println("Booking Successfull");
        System.out.println("Your Booking Id is : "+ (Booking.idGen));
        TravelAgency.bookingList[Booking.idGen].number =   TravelAgency.customerList[Customer.pos].mobileNum;
        TravelAgency.bookingList[Booking.idGen].name =   TravelAgency.customerList[Customer.pos].name;
        TravelAgency.tripsList[Booking.idGen] = new Trip(TravelAgency.driverList[Booking.idGen].driverName,
        TravelAgency.driverList[Booking.idGen].driverPhoneNum);
        System.out.println("Details of your Driver and vehicle is given below ");
        TravelAgency.tripsList[Booking.idGen].displayDriverDetails();
        System.out.println("Vehicle Number : " +TravelAgency.bookingList[Booking.idGen].cabNo);
        System.out.println(TravelAgency.bookingList[Booking.idGen].name);
        }
        break;
        
        case 1: System.out.println("Enter your PhoneNumber");
        String temp4 = obj.nextLine();
        String phnum = obj.nextLine();

        int id=-1;
        for(int i = 0;i<=Customer.pos;i++){
        if(TravelAgency.customerList[i].mobileNum.equals(phnum))
        id = i;
        }
        if(id==-1){
        System.out.println("Your Phone doesnt not exist");
        }else{
        Booking temp1 = Booking.bookCab();
        TravelAgency.bookingList[Booking.idGen] = temp1;
        System.out.println("Booking Successfull");
        System.out.println("Your Booking Id is : "+ (Booking.idGen));
        TravelAgency.bookingList[Booking.idGen].number =   TravelAgency.customerList[id].mobileNum;
        TravelAgency.bookingList[Booking.idGen].name =   TravelAgency.customerList[id].name;
        TravelAgency.tripsList[Booking.idGen] = new Trip(TravelAgency.driverList[Booking.idGen].driverName,
        TravelAgency.driverList[Booking.idGen].driverPhoneNum);
        System.out.println("Details of your Driver and vehicle is given below ");
        TravelAgency.tripsList[Booking.idGen].displayDriverDetails();
        System.out.println("Vehicle Number : " +TravelAgency.bookingList[Booking.idGen].cabNo);
    }
    break;
    }
    
    break;

case 4: 

        Services.displayServices();
    break;
    
case 3: System.out.println("Enter your Booking Id");
        int id1 = obj.nextInt();
        String PhoneNum;
        System.out.println("Calculating Cost......");
        TravelAgency.tripsList[id1].calcCharges();
        TravelAgency.tripsList[id1].displayBill();
        break;
case 2: System.out.println("Enter your Booking Id to Cancel");
        int id2 = obj.nextInt();
        System.out.println("This is your Booking");
        TravelAgency.bookingList[id2].display();
        System.out.println("Are you Sure you want to Cancel Y/N");
        char ch1 = obj.next().charAt(0);
        if(ch1=='Y')
        {
            if(TravelAgency.bookingList[id2].status.equals("Attended"))
            {
            System.out.println("Order Already Delivered");
            }
            else{
            TravelAgency.bookingList[id2].status = "Cancelled";
            System.out.println(TravelAgency.bookingList[id2].status);
            System.out.println("CAB has been cancelled Sucessfully");
        }
         
        
        }
        else{
        System.out.println("Cancellation Aborted ");
        }
        
       
   
        
    break;
case 5:displayTrips();
break;
case 6:System.out.println("Enter Driver Name ");
    String name_d = obj.nextLine();
     name_d = obj.nextLine();
    displaysTrips(name_d);
    break;
case 7: System.out.println("Enter Customer Name : ");
String x = obj.nextLine();
x = obj.nextLine();
displayBookingsCustomer(x);
break;
case 8:System.out.println("Enter Customer Name : ");
String x1 = obj.nextLine();
x1 = obj.nextLine();
displayCancellationConsumter(x1);
break;
case 9:getMonthlyAverage();
break;

default:System.out.println("Invalid Choice Try again ");

}




System.out.println("Do you want to Continue Y/N");
choice = obj.next().charAt(0);
} 
while(choice=='Y');




}
}