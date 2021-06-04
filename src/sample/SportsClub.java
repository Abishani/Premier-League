package sample;
/**
 * Student Id : 2019743
 * Uow Id : w1789987
 * Name : Abishani Muthalagan
 * References for pojo classes :
    https://www.geeksforgeeks.org/pojo-vs-java-beans/
    https://www.edureka.co/blog/pojo-in-java/#POJOInJava
    baeldung.com/java-pojo-class
*/


import java.io.Serializable;
import java.util.Objects;


//================= Creating a Super class SportsClub with Serializable interface to convert and object to stream and save it as a file for later use ===================
public class SportsClub implements Serializable {
    //===================creating the instance variables==========
    private String clubName;
    private String clubLocation;



    //=======================creating constructor==============
    //=============Default constructor==========================
    public SportsClub(){
    }


    //=============Constructor with parameters==========================
    public SportsClub(String clubName,String clubLocation){
        this.clubName = clubName;
        this.clubLocation = clubLocation;
    }



    //==========================creating getters======================

    public String getClubName(){  //to get the club name from the user
        return clubName;
    }

    public String getClubLocation(){ //to get the location from the user
        return clubLocation;
    }



    //========================creating setters======================
    public void setClubName(String clubName){  //setting the club name
        this.clubName = clubName;
    }

    public void setClubLocation(String clubLocation){  //setting the club location
        this.clubLocation = clubLocation;
    }




    //==================toString method=================================
    @Override
    public String toString(){   //return the value of the object in string format
        return "Sports club"
                + "Club Name : " + clubName
                + "Location : " + clubLocation;


    }

    @Override
    //============== To find the equality between the two objects implementing equals method==============
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportsClub that = (SportsClub) o;
        return Objects.equals(clubName, that.clubName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubName, clubLocation); //hashCode method used to find the equality between the attributes of the objects
    }
}