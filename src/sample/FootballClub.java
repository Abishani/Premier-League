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



public class FootballClub extends SportsClub  {
    //====================creating the instance variables==============
    private int numberOfWins;
    private int numberOfDraws;
    private int numberOfDefeats;
    private int numberOfGoalsReceived;
    private int numberOfGoalsScored;
    private int numberOfGoalsDifference;
    private int numberOfCurrentPoints;
    private int numberOfMatchesPlayed;


    //======================creating constructor===================
    //============Default constructor=====================
    public FootballClub(){
    }


    //=============Constructor with parameters===============
    public FootballClub(String clubName,String clubLocation,int numberOfMatchesPlayed,int numberOfWins,int numberOfDraws,int numberOfDefeats,int numberOfGoalsScored,int numberOfGoalsReceived,int numberOfGoalsDifference,int numberOfCurrentPoints){
        super(clubName, clubLocation);
        this.numberOfMatchesPlayed = numberOfMatchesPlayed;
        this.numberOfWins = numberOfWins;
        this.numberOfDraws = numberOfDraws;
        this.numberOfDefeats = numberOfDefeats;
        this.numberOfGoalsScored = numberOfGoalsScored;
        this.numberOfGoalsReceived = numberOfGoalsReceived;
        this.numberOfGoalsDifference = numberOfGoalsDifference;
        this.numberOfCurrentPoints = numberOfCurrentPoints;

    }



    //=====================creating getters======================


    public int getNumberOfWins(){ //getting the number of wins
        return numberOfWins;
    }

    public int getNumberOfDraws(){ //getting the number of draws
        return numberOfDraws;
    }

    public int getNumberOfDefeats(){   //getting the number of defeats
        return numberOfDefeats;
    }

    public int getNumberOfGoalsReceived(){ //getting the number of received goals
        return numberOfGoalsReceived;
    }

    public int getNumberOfGoalsScored(){   //getting the number of scored goals
        return numberOfGoalsScored;
    }

    public int getNumberOfGoalsDifference() { //getting the number of goals difference
        int goalsDifference =  ( numberOfGoalsScored - numberOfGoalsReceived);
        return goalsDifference;
    }

    public int getNumberOfCurrentPoints(){   //getting the number of current points
        return numberOfCurrentPoints;
    }

    public int getNumberOfMatchesPlayed(){   //getting the number of played match
        return numberOfMatchesPlayed;
    }


    //=========================creating setters==================

    public void setNumberOfWins(int numberOfWins){ //setting the number of wins
        this.numberOfWins = numberOfWins;
    }

    public void setNumberOfDraws(int numberOfDraws){ //setting the number of draws
        this.numberOfDraws = numberOfDraws;
    }

    public void setNumberOfDefeats(int numberOfDefeats){  //setting the number of defeats
        this.numberOfDefeats = numberOfDefeats;
    }

    public void setNumberOfGoalsReceived(int numberOfGoalsReceived){  //setting the number of received goals
        this.numberOfGoalsReceived = numberOfGoalsReceived;
    }

    public void setNumberOfGoalsScored(int numberOfGoalsScored){  //setting the number of scored goals
        this.numberOfGoalsScored = numberOfGoalsScored;
    }

    public void setNumberOfGoalsDifference(int numberOfGoalsDifference) {
        this.numberOfGoalsDifference = numberOfGoalsDifference;
    }

    public void setNumberOfCurrentPoints(int numberOfCurrentPoints){  //setting the number of current points
        this.numberOfCurrentPoints = numberOfCurrentPoints;
    }

    public void setNumberOfMatchesPlayed(int numberOfMatchesPlayed){  //setting the number of played match
        this.numberOfMatchesPlayed = numberOfMatchesPlayed;
    }

    //=======================toString method=====================================
    @Override
    public String toString(){
        return super.toString()
                +"\nWins : " + numberOfWins
                +"\nDraws : " + numberOfDraws
                +"\nDefeats : " + numberOfDefeats
                +"\nReceived Goals : " +  numberOfGoalsReceived
                +"\nScored Goals : " + numberOfGoalsScored
                +"\nGoals Difference : " + numberOfGoalsDifference
                +"\nCurrent Points : " + numberOfCurrentPoints
                +"\nPlayed Matches : " + numberOfMatchesPlayed;
    }

}