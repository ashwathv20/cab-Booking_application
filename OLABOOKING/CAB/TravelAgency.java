package CAB;
import SERVICES.*;
import CUSTOMERS.Customer;
import VECHICLEBOOKING.Booking;
import VECHICLEBOOKING.Trip;
public interface TravelAgency
{
 String travelAgencyName = "OLA Cab";
 String DriverNames[] = {"Mike","Daniel","Vetri","Aaron","Yuvan","Mike","Daniel","Vetri","Aaron","Yuvan",
   "Mike","Daniel","Vetri","Aaron","Yuvan" };
 String DriverPhoneNum[] = {"4988978451","971578456","456789789","7897894894","789784213"};
 String cabTypeList[] = {"Sedan","Prime","SUV","Auto","Bike","Mini"};
 int cabCountList[] = {2,1,3,2,4,1};
 double cabPriceList[] = {600,700,1000,300,250,400};
 String VehicleNumList[] = {
    "TN-21-7896","TN-21-7854","TN-21-7813","TN-21-6504","TN-21-1134","TN-40-1975","TN-21-7897","TN-21-6565",
    "TN-21-5543",
    "TN-21-7891",
    "TN-21-6178",
    "TN-51-7896",
    "TN-21-8878",
    "TN-21-8393",
    "TN-32-1177"
    };
String VehicleTypeList[] = {
"Sedan","Prime","Sedan","Prime","SUV","SUV","Bike","Auto","Auto","Sedan","Mini","Mini","SUV","Prime",
 "Bike"
};
String TravelAgencyNumList[] = {"979809807","97087845","989709807"};
Customer customerList[] = new Customer[100];
Driver driverList[] = new Driver[100];
Booking bookingList[] = new Booking[100];
Trip tripsList[] = new Trip[100];
Services serviceList[] = new Services[100];
}
