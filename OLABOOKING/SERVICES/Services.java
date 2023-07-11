package SERVICES;
import CAB.*;
public class Services{
public String type;
public double rate;
public int count;

Services(String type,double rate){
this.type = type;
this.rate = rate;
}
void display(){
System.out.println("Vehicle Type : "+type);
System.out.println("Rate :"+rate);
System.out.println("No of Available Vehicles :"+ count);
}
public static void main(){
Driver.intializeDrivers();
initialiseSerives();
displayServices();


}
public static int findIndex(String Arr[], String x){
for(int i = 0;i<Arr.length;i++){
if(Arr[i].equals(x))
{
return i;}

}
return 0;
}
public static void displayServices(){
int m = TravelAgency.cabTypeList.length;
for(int i = 0;i<m;i++){
    TravelAgency.serviceList[i].display();
}

}


public static void initialiseSerives(){
int n = TravelAgency.VehicleTypeList.length;
int m = TravelAgency.cabTypeList.length;
for(int i = 0;i<m;i++){
TravelAgency.serviceList[i] = new Services(TravelAgency.cabTypeList[i],
TravelAgency.cabPriceList[i]);
}
for(int i = 0;i<n;i++){
if(TravelAgency.driverList[i].isAvailable)
{
TravelAgency.serviceList[findIndex(TravelAgency.cabTypeList,TravelAgency.driverList[i].type)].count++;
}


}

}

}
