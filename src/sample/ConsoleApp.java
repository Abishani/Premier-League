package sample;
/**
 * Student Id : 2019743
 * Uow Id : w1789987
 * Name : Abishani Muthalagan

*/

import javafx.application.Application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleApp {
    public final static LeagueManager manager = new PremierLeagueManager(); //creating a object for PremierLeagueManager class
    final static Scanner input = new Scanner(System.in);  //creating static Scanner for get the input from the user




    public static void main(String[] args) throws IOException, ClassNotFoundException{
        try {
            manager.loadClubDetails("detailsClub.txt"); //Loading the football club details and added match details into the details.txt file
            manager.loadMatchDetails("detailsMatch.txt");

        }catch (Exception e){    //Exception handling is used to handle FileNotFoundException(If there is file is not found) and IOException(file details are not found)

        }
        manager.getFootballClubList();
        manager.getMatchList();
        menu:  //used to exit the program
        while (true){
            displayMenuOptions(); //calling the displayMenuOptions() method to display options menu to the user to choose

            try {
                int userChoice = input.nextInt();
                //=================switch case to set the available options for the user================================
                switch (userChoice) {
                    case 1:
                        addFootballClub();  //add the football club
                        break;
                    case 2:
                        deleteFootballClub();  //delete the football club
                        break;
                    case 3:
                        printFootballClubStatistics();  //print the football club statistics
                        break;
                    case 4:
                        manager.printPremierLeagueTable();  //print the statistics of the football club in the premier league table
                        break;
                    case 5:
                        addPlayedMatch();  //add the played match
                        break;
                    case 6:
                        manager.saveClubDetails("detailsClub.txt");  //saving the football club details and added match details into the details.txt file
                        manager.saveMatchDetails("detailsMatch.txt");

                        System.out.println("Football club details and match details are saved successfully ");
                        break;
                    case 7:
                        Application.launch(StatisticsTable.class, args);
                        break ;
                    case 8:
                        manager.saveClubDetails("detailsClub.txt");  //saving the football club details and added match details into the details.txt file
                        manager.saveMatchDetails("detailsMatch.txt");
                        System.out.println("Thanks for visiting the Premier League"); //displaying a end statement to the user
                        break menu;  //calling the menu to exit from the program
                    default:
                        System.out.println("Invalid option please select the correct one");  //Display Error message if user enters the option which is not 0-7 and enters String input

                }
            } catch (InputMismatchException ime) {
                System.out.println("Invalid option please select the correct one");  //Display Error message if user enters the option which is not 0-7 and enters String input

                input.next();
            }
        }


    }


    //===========================displaying menu options to the user to choose============================
    public static void displayMenuOptions(){
        System.out.println("\n<><><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("<>WELCOME TO THE BIGGEST TOURNAMENT PREMIER LEAGUE<>");
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("- Press 1 : To Add a new Football club");
        System.out.println("- Press 2 : To Delete a existing Football club");
        System.out.println("- Press 3 : To See the statistics of the football club");
        System.out.println("- Press 4 : To See all the details of the football clubs in the premier league table");
        System.out.println("- Press 5 : To Add Played Match");
        System.out.println("- Press 6 : To Save the Details of the club");
        System.out.println("- Press 7 : To Display GUI");
        System.out.println("- Press 8 : To Quit the program");
        System.out.println("Enter the Option here : ");

    }


    private static void addFootballClub() throws IOException {  //Method to get the inputs from the user to add Football club details
        FootballClub footballClub; //creating a object to display

        System.out.println("Enter the Football Club name : ");  //getting the user input for football club name
        String clubName = input.next();  //declaring input value as String
        while(!clubName.matches("[a-zA-Z]+")) {   //checking the validation of user input
            System.out.println("Invalid please enter again");  //displaying error a message if user input is not matched with String
            System.out.println("Enter the Football Club name : "); //again getting the correct user input
            clubName = input.next();
        }


        System.out.println("Enter the club location : "); //getting the user input for club location
        String clubLocation = input.next();  //declaring input value as String
        while (!clubLocation.matches("[a-zA-Z]+")){  //checking the validation of user input
            System.out.println("Invalid please enter again");  //displaying error a message if user input is not matched with String
            System.out.println("Enter the club location : "); //again getting the correct user input
            clubLocation = input.next();
        }


        footballClub = new FootballClub(clubName,clubLocation,0,0,0,0,0,0,0,0); //creating a new object for the Footballclub

        manager.addFootballClub(footballClub);  //if user input is valid adding that particular new football club details

    }

    private static void deleteFootballClub() throws IOException {  //Method to get the input from the user to delete the football club details

        System.out.println("Enter the Football Club name : "); //getting the user input for football club name
        String clubName = input.next();  //declaring input value as String
        while(!clubName.matches("[a-zA-Z]+")) {  //checking the validation of user input
            System.out.println("Invalid please enter again");  //displaying error a message if user input is not matched with String
            System.out.println("Enter the Football Club name : ");  //again getting the correct user input
            clubName = input.next();
        }

        manager.deleteFootballClub(clubName); // if user input is valid deleting that particular football club details
    }



    private static void printFootballClubStatistics() {  //method to get the input from the user to print the statistics of the football club

        System.out.print("Enter the Football Club Name : "); //getting the user input for football club name
        String clubName = input.next();  //declaring input value as String
        while(!clubName.matches("[a-zA-Z]+")) {   //checking the validation of user input
            System.out.println("Invalid please enter again");  //displaying error a message if user input is not matched with String
            System.out.println("Enter the Football Club name : ");  //again getting the correct user input
            clubName = input.next();
        }


        manager.printFootballClubStatistics(clubName); //if user input is valid printing the statistics of the particular football club



    }

    private static void addPlayedMatch() throws IOException { //Method to get the inputs from the user to add played match details




        int day = 0; //initializing day is 0
        boolean dayValid = false;  //initializing the boolean flag as false
        while (! dayValid) {   //checking the validity of user input
            System.out.println("Enter the Day of the Match : ");  //getting the user input for day of the match
            try {
                day = input.nextInt(); //initializing the user input should be in int
                if(day >= 1 && day <= 31){ //condition that the day is greater than or equals to 1 or less than or equals to 31
                    dayValid = true;   //if day is according to the condition then boolean flag becomes true
                }else{
                    System.out.println("Invalid day is entered enter valid day(1-31)"); //Displaying error message if day is not according to the condition.
                }
            }catch (InputMismatchException ime){ //throwing InputMismatchException exception if input is invalid
                System.out.println("Invalid please enter valid day(1-31)"); //Displaying error message if day is not according to the condition and if it is not int value.
                input.next();
            }
        }

        int month = 0; //initializing month is 0
        boolean monthValid = false;  //initializing the boolean flag as false
        while (! monthValid) {  //checking the validity of user input
            System.out.println("Enter the Month of the Match : ");  //getting the user input for month of the match
            try {
                month = input.nextInt();  //initializing the user input should be in int
                if(month >= 1 && month <= 12){  //condition that the month is greater than or equals to 1 or less than or equals to 12
                    monthValid = true;   //if month is according to the condition then boolean flag becomes true
                }else{
                    System.out.println("Invalid month is entered enter valid month(1-12)");  //Displaying error message if month is not according to the condition.
                }
            }catch (InputMismatchException ime){  //throwing InputMismatchException exception if input is invalid
                System.out.println("Invalid please enter valid month(1-12)");  //Displaying error message if month is not according to the condition and if it is not int value.
                input.next();
            }
            //date.setMonth(month);
        }





        int year = 0;  //initializing year is 0
        boolean yearValid = false;  //initializing the boolean flag as false
        while (! yearValid) {    //checking the validity of user input
            System.out.println("Enter the Year of the Match : ");  //getting the user input for year of the match
            try {
                year = input.nextInt();  //initializing the user input should be in int
                if(year >= 2000){  //condition that the month is greater than or equals to 2020
                    yearValid = true; //if year is according to the condition then boolean flag becomes true
                }else{
                    System.out.println("Enter the current year");
                }
            }catch (InputMismatchException ime){   //throwing InputMismatchException exception if input is invalid
                System.out.println("Invalid please enter valid Year");  //Displaying error message if month is not according to the condition and if it is not int value.
                input.next();
            }
            //date.setYear(year);
        }

        Date date  = new Date(day,month,year);
        System.out.println("Match Date : " + date.toString()); //Displaying the date in a particular format


        System.out.println("Enter the Home team name : ");  //getting the user input for Home team name
        String homeTeam = input.next();    //declaring input value as String
        while(!homeTeam.matches("[a-zA-Z]+")) {  //checking the validation of user input
            System.out.println("Invalid please enter again");  //displaying error a message if user input is not matched with String
            System.out.println("Enter the Home team name : ");  //again asking the user to enter the correct input
            homeTeam = input.next();
        }

        System.out.println("Enter the Away team name : ");  //getting the user input for Away team name
        String awayTeam = input.next();  //declaring input value as String
        while(!awayTeam.matches("[a-zA-Z]+")) {  //checking the validation of user input
            System.out.println("Invalid please enter again"); //displaying error a message if user input is not matched with String
            System.out.println("Enter the Away team name : "); //again asking the user to enter the correct input
            awayTeam = input.next();
        }

        int homeTeamGoals = 0;  //initializing the homeTeam goals so starting value home goals is 0
        boolean homeTeamGoalsValid = false; //initializing the boolean flag as false
        while(! homeTeamGoalsValid) {  //checking the validity of user input
            System.out.println("Enter the Home team goals : "); //getting the user input for Home team goals
            try {
                homeTeamGoals = input.nextInt();
                if(homeTeamGoals >= 0){ //condition that the home team goals should be greater than or equals to 0
                    homeTeamGoalsValid = true; //if Home team goal is according to the condition then boolean flag becomes true
                }else{
                    System.out.println("Invalid"); //Displaying error message if home team goal is not according to the condition.
                }
            }catch (InputMismatchException ime){  //throwing InputMismatchException exception if input is invalid
                System.out.println("Invalid please enter valid number"); //Displaying error message if home team goal is not according to the condition and if it is not int value.
                input.next();
            }

        }


        int awayTeamGoals = 0;  //initializing the awayTeam goals so starting value home goals is 0
        boolean awayTeamGoalsValid = false;  //initializing the boolean flag as false
        while(! awayTeamGoalsValid) {   //checking the validity of user input
            System.out.println("Enter the Away team goals : ");  //getting the user input for away team goals
            try {
                awayTeamGoals = input.nextInt(); //initializing the user input should be in int
                if(awayTeamGoals >= 0){ //condition that the away team goals should be greater than or equals to 0
                    awayTeamGoalsValid = true;  //if away team goal is according to the condition then boolean flag becomes true
                }else{
                    System.out.println("Invalid");  //Displaying error message if away team goal is not according to the condition.
                }
            }catch (InputMismatchException ime){  //throwing InputMismatchException exception if input is invalid
                System.out.println("Invalid please enter valid number"); //Displaying error message if away team goal is not according to the condition and if it is not int value.
                input.next();
            }

        }
        manager.addPlayedMatch(homeTeam,awayTeam,homeTeamGoals,awayTeamGoals,date); // if user inputs are valid adding the played match details of football clubs
        manager.saveClubDetails("detailsClub.txt");  //saving the football club details and added match details into the details.txt file
        manager.saveMatchDetails("detailsMatch.txt");


    }

}
