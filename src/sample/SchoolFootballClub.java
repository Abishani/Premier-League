package sample;
/**
 * Student Id : 2019743
 *Uow Id : w1789987
 *Name : Abishani Muthalagan
 *References for pojo classes :
    https://www.geeksforgeeks.org/pojo-vs-java-beans/
    https://www.edureka.co/blog/pojo-in-java/#POJOInJava
    baeldung.com/java-pojo-class
*/


import java.util.Objects;

public class SchoolFootballClub extends FootballClub{
    //==================creating the instance variables====================
    private String schoolName;


    //=============Default constructor==========================
    public SchoolFootballClub(){
    }


    //=============Constructor with parameters==========================
    public SchoolFootballClub(String clubName,String clubLocation,int numberOfMatchesPlayed,int numberOfWins,int numberOfDraws,int numberOfDefeats,int numberOfGoalsScored,int numberOfGoalsReceived,int numberOfGoalsDifference,int numberOfCurrentPoints, String schoolName) {
        super(clubName, clubLocation,numberOfMatchesPlayed, numberOfWins, numberOfDraws, numberOfDefeats, numberOfGoalsScored, numberOfGoalsReceived, numberOfGoalsDifference, numberOfCurrentPoints);
        this.schoolName = schoolName;
    }


    //==========================creating getters======================
    public String getSchoolName(){  //getting the name of the school
        return schoolName;
    }



    //========================creating setters======================

    public void setSchoolName(String schoolName){  //setting the name of the school
        this.schoolName = schoolName;
    }


    //==================toString method==========================
    @Override
    public String toString(){
        return  super.toString()
                +"\nSchool Foot ball club"
                +"\nSchool Name : " + schoolName;
    }

    @Override
    //============== To find the equality between the two objects implementing equals method==============
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SchoolFootballClub that = (SchoolFootballClub) o;
        return schoolName.equals(that.schoolName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), schoolName);
    }
}
