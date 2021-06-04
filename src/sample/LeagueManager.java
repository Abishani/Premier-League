package sample;
/**
 * Student Id : 2019743
 * Uow Id : w1789987
 * Name : Abishani Muthalagan
 */


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface LeagueManager {
        void addFootballClub(FootballClub footballClub) throws IOException;    //Method to add a Football club to the premier league

        void deleteFootballClub(String clubName) throws IOException;           //Method to delete a Football club to the premier league

        void printFootballClubStatistics(String clubName);  //Method to print a Football club Statistics

        void printPremierLeagueTable();                     //Method to print a premier league table

        List<FootballClub> getFootballClubList(); //list to store football details

        List<Match> getMatchList();   //list to store played match details

        void saveClubDetails(String clubFile) throws IOException; //Method to save the club details in a file

        void saveMatchDetails(String clubFile) throws IOException; //Method to save the club details in a file

        void loadClubDetails(String clubFile) throws IOException, ClassNotFoundException;  //Method to load the details from the saved file

        void loadMatchDetails(String clubFile) throws IOException, ClassNotFoundException;   //Method to load the details from the saved file

        void addPlayedMatch(String team1, String team2, int team1GoalsScored, int team2GoalsScored, Date date) throws IOException;  //Method to add played match




}
