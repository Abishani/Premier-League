package sample;
/**
 * Student Id : 2019743
 *Uow Id : w1789987
 *Name : Abishani Muthalagan
 */
import java.io.Serializable;

public class Match implements Serializable {
    //=====================instance variables================================
    private String team1;
    private String team2;
    private int team1GoalsScored;
    private int team2GoalsScored;
    private String date ;

    //=====================creating constructor===================================
    public Match(String team1, String team2, int team1GoalsScored, int team2GoalsScored, String date) {
        this.team1 = team1;
        this.team2 = team2;
        this.team1GoalsScored = team1GoalsScored;
        this.team2GoalsScored = team2GoalsScored;
        this.date = date;
    }

    //==================creating getters and setters======================
    public String getTeam1Name() {
        return team1;
    }

    public void setTeam1Name(String team1Name) {
        this.team1 = team1Name;
    }

    public String getTeam2Name() {
        return team2;
    }

    public void setTeam2Name(String team2Name) {
        this.team2 = team2Name;
    }

    public int getTeam1Score() {
        return team1GoalsScored;
    }

    public void setTeam1Score(int team1Score) {
        this.team1GoalsScored = team1Score;
    }

    public int getTeam2Score() {
        return team2GoalsScored;
    }

    public void setTeam2Score(int team2Score) {
        this.team2GoalsScored = team2Score;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    //================= To convert details to string using a toString() ==========================
    public String toString() {
        return "Match : " +
                "team1 " + team1 +
                "team2 " + team2 +
                "team1GoalsScored " + team1GoalsScored +
                "team2GoalsScored " + team2GoalsScored +
                "date " + date;
    }
}
