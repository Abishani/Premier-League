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


import java.util.Objects;

public class UniversityFootballClub extends FootballClub {
    //===========creating the instance variables========================
    private String universityName;




    //=============Default constructor==========================
    public UniversityFootballClub(){
    }


    //=============Constructor with parameters==========================

    public UniversityFootballClub(String clubName,String clubLocation,int numberOfMatchesPlayed,int numberOfWins,int numberOfDraws,int numberOfDefeats,int numberOfGoalsScored,int numberOfGoalsReceived,int numberOfGoalsDifference,int numberOfCurrentPoints, String universityName) {
        super(clubName, clubLocation,numberOfMatchesPlayed, numberOfWins, numberOfDraws, numberOfDefeats, numberOfGoalsScored, numberOfGoalsReceived, numberOfGoalsDifference, numberOfCurrentPoints);
        this.universityName = universityName;
    }


    //==================creating getters========================

    public String getUniversityName(){
        return universityName;
    }



    //========================creating setters======================

    public void setUniversityName(String universityName){  //getting the university name
        this.universityName = universityName;
    }


    //==================toString method=================================
    @Override
    public String toString(){
        return super.toString()
                +"\nUniversity Foot ball club"
                +"\nUniversity Name : " + universityName;
    }

    @Override
    public boolean equals(Object o) {
        //============== To find the equality between the two objects implementing equals method==============
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UniversityFootballClub that = (UniversityFootballClub) o;
        return universityName.equals(that.universityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), universityName);
    }

}
