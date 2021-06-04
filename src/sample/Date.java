package sample;
/**
 * Student Id : 2019743
 * Uow Id : w1789987
 * Name : Abishani Muthalagan
 */

import java.io.Serializable;

public class Date implements Serializable {


    //===========creating instance variables=============
    private int day;
    private int month;
    private int year;

    //==========creating constructor===========
    public Date(int day,int month,int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date() {

    }


    //========creating getters================
    public int getDay(){
        return day;
    }

    public int getMonth(){
        return month;
    }

    public int getYear(){
        return year;
    }

    //========creating setters================
    public int setDay(int day) {
        //=========creating Array to store the days in each month
        int noOfDay[] = {0,31,28,31,30,31,30,31,31,30,31,30,31};
        if (day >= 1 && day <= noOfDay[month])            //validations to check the day is valid and the month is containing 30 or 31 or 29 days
            return day;
        if (month == 2 && day == 29 && year%400 == 0 || (year%4 == 0 && (year%100 != 0)))  //adding validation for february
            return day;
        System.out.println("Invalid day has been entered" + day);  //displaying a error message if incorrect day is entered
        return 1;
    }


    public int setMonth(int month) {
        while (true) {
            if (month > 0 && month <= 12) {  //validation for month is between 1 to 12
                return month;
            } else {
                System.out.println("Invalid month has been entered." + month); //displaying a error message if incorrect month is entered
                return 1;
            }

        }
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString(){
        return String.format(day+"-"+month+"-"+year);  //toString method to display a date in particular format(eg : 09-11-2000)
    }

}
