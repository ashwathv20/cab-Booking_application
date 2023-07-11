package SERVICES;
import CAB.*;
public class Driver extends Car{
public String driverName;
public  String driverPhoneNum;
static int id = -1;
public String getType(){
return type;
}
public boolean isAvailable(){
return isAvailable;
}
void display(){
System.out.println("Name :"+driverName);
System.out.println("PhoneNumber: "+driverPhoneNum);
System.out.println("Car No :"+vehicleNumber);
System.out.println("Car Type :"+type);


}
Driver(String Type,String Number ,String x, String y){
super(Type,Number);
 id++;
this.driverName = x;
this.driverPhoneNum = y;

}
public static void main(){
intializeDrivers();
displayDrivers();
}
public static void intializeDrivers(){
int n = TravelAgency.VehicleNumList.length;
for(int i = 0;i<n;i++){
TravelAgency.driverList[i] = new Driver(TravelAgency.VehicleTypeList[i],
TravelAgency.VehicleNumList[i],
TravelAgency.DriverNames[(int)(Math.random()*5)],
TravelAgency.DriverPhoneNum[(int)(Math.random()*5)]
);

}
}
public static void displayDrivers(){
int n = TravelAgency.VehicleNumList.length;
for(int i = 0;i<n;i++){
TravelAgency.driverList[i].display();
}
}




}