package sample;
/**
 * Student Id : 2019743
 *Uow Id : w1789987
 *Name : Abishani Muthalagan
 *References for comparator :
    https://c4code.wordpress.com/
    https://www.geeksforgeeks.org/comparator-interface-java/
    https://www.javatpoint.com/Sorting-in-collection-framework

  *References for string formatting and printing in a tabular foramat :
    https://c4code.wordpress.com/2018/03/17/how-to-print-the-results-to-console-in-a-tabular-format-using-java/
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.Integer.parseInt;


//============================Implementing the league manager interface methods in PremierLeagueManager class==================
public class PremierLeagueManager implements LeagueManager{


    public  List<FootballClub> footballClubList = new ArrayList<>(); //Creating a arraylist to store the football club details
    public List<Match> playedMatch = new ArrayList<>(); //creating a list to add played matches
    final static public int MAX_CLUB_COUNT = 20; //declaring maximum club counts to 20
    private int freeSlots = MAX_CLUB_COUNT;


    @Override
    //================================== Adding a Football club to the premier league using addFootballClub() Method ======================================
    public void addFootballClub(FootballClub footballClub) throws IOException {
        if (footballClubList.contains(footballClub)) {  //checking whether the club is already exists or not in the football club list
            System.out.println(footballClub.getClubName() + " is already added to the football club list."); //Displaying a message if the club is already in the list
            return;
        }
        else if((footballClubList.size() == MAX_CLUB_COUNT)){
            System.out.println("Cannot add the football club \nMaximum clubs participated in Premier League is 20");
        }else{
            footballClubList.add(footballClub);    //adding a football club to football club list
            System.out.println(footballClub.getClubName() + " is successfully added to the football club list.");  //Displaying a message that the club is added to the list
            System.out.println("Available slots in the premier league : " + (20- footballClubList.size()) );  //Displaying a available free slots
        }
        saveClubDetails("detailsClub.txt");  //saving the football club details and added match details into the details.txt file
        saveMatchDetails("detailsMatch.txt");
    }

    @Override
    //======================Deleting a existing football club from the list using deleteFootballClub() method ====================================
    public void deleteFootballClub(String clubName) throws IOException {
        boolean clubFound = false;
        for (FootballClub footballClub : footballClubList) {   //Checking the football club list
            if (footballClub.getClubName().equals(clubName)) {   //checking the equality between the clubs to remove the club from the list
                clubFound = true;                //condition for checking the club is in the list
                footballClubList.remove(footballClub);   //removing the club from the list

                System.out.println(footballClub.getClubName() + " is removed from the football club list.");   //Displaying a message that the club is removed from the list
                System.out.println("Available slots in the premier league : "+ (20- footballClubList.size()));  //Displaying a available free slots
                break;
            }
        }
        if (!clubFound) {   //checking statement if the club is not found in  the list
            System.out.println("Football Club is not found in the football club list.");  //Displaying statement if the club is not found in  the list
        }

        saveClubDetails("detailsClub.txt");  //saving the football club details and added match details into the details.txt file
        saveMatchDetails("detailsMatch.txt");

    }


    @Override
    //===================== displaying a statistics of the football club using printFootballClubStatistics() method ========================
    public void printFootballClubStatistics(String clubName) {
        if (footballClubList.isEmpty()) {            //Checking whether the football club list is empty or not
            System.out.println("Currently there are no any football clubs are added to the football club list"); //Displaying statement if there is no any football clubs are in the list
        } else {
            boolean clubStatistics = false;

            for (FootballClub footballClub : footballClubList) {  //checking the whether user input equal or not to the club name which is already in the club list
                if (footballClub.getClubName().equals(clubName)) {
                    clubStatistics = true;   //boolean flag if the club is exists in the list
                    //===========If the club is already in the list printing the statistics of the club ===================
                    System.out.println("Statistics of the Football club ");
                    System.out.println("Football club Name : " + footballClub.getClubName());
                    System.out.println("Number of Wins : " + footballClub.getNumberOfWins());
                    System.out.println("Number of Draws : " + footballClub.getNumberOfDraws());
                    System.out.println("Number of Defeats : " + footballClub.getNumberOfDefeats());
                    System.out.println("Number of received goals : " + footballClub.getNumberOfGoalsReceived());
                    System.out.println("Number of Scored goals : " + footballClub.getNumberOfGoalsScored());
                    System.out.println("Number of Scored goals : " + footballClub.getNumberOfGoalsDifference());
                    System.out.println("Current Points : " + footballClub.getNumberOfCurrentPoints());
                    System.out.println("Number of Matches Played : " + footballClub.getNumberOfMatchesPlayed());
                    return;
                }
            }

            if (!clubStatistics) {   //boolean flag for club not founded
                System.out.println("The football club is not found"); //Printing the message if the club is found in the football club list
            }
        }
    }





    @Override
    //============================= displaying a premier league using table printPremierLeagueTable() method =====================================
    public void printPremierLeagueTable() {
        //==================Creating table to print the football club statistics in the table=======================================
        System.out.println("Football club details");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("|%18s   |  %15s  |  %5s  |  %5s  |  %5s  |  %15s  |  %12s  |  %16s  |  %15s  |", "Club Name", "Played Matches", "Wins", "Draws", "Lost", "Goals Received", "Goals scored", "Goals Difference", "Current Points");
        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------");
        Collections.sort(footballClubList, Collections.reverseOrder(new sortingPoints()));   //Printing club's goal difference and current points in the descending order

        for (FootballClub footballClub : footballClubList) {
            System.out.format("|%18s   |  %15s  |  %5s  |  %5s  |  %5s  |  %15s  |  %12s  |  %16s  |  %15s  |", footballClub.getClubName(), footballClub.getNumberOfMatchesPlayed(), footballClub.getNumberOfWins(), footballClub.getNumberOfDraws(),
                    footballClub.getNumberOfDefeats(), footballClub.getNumberOfGoalsReceived(), footballClub.getNumberOfGoalsScored(), footballClub.getNumberOfGoalsDifference(), footballClub.getNumberOfCurrentPoints());
            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }


    }


    //=================implementing a comparator interface to sort Points===================================
    static class sortingPoints implements Comparator<FootballClub> {

        @Override
        public int compare(FootballClub club1, FootballClub club2) {  //Comparing the two clubs club1 and club2
            if (club1.getNumberOfCurrentPoints() > club2.getNumberOfCurrentPoints()) {  //comparing the current points between the club1 and club2
                return 1;              //if the current points of club1 is greater than the club2  returning  1(positive value)
            } else if (club1.getNumberOfCurrentPoints() < club2.getNumberOfCurrentPoints()) {
                return -1;      //if the current points of club2 is greater than the club1  returning  -1(negative value)
            } else {
                if (club1.getNumberOfGoalsDifference() > club2.getNumberOfGoalsDifference()) {  //comparing the goals difference between the club1 and club2
                    return 1;  //if the goals difference of club1 is greater than the club2 to returning  1(positive value)
                } else if (club1.getNumberOfGoalsDifference() < club2.getNumberOfGoalsDifference()) {
                    return -1;  //if the goals difference of club2 is greater than the club1 to returning  -1(negative value)
                } else {
                    return 0;   //if the goals difference of club1 and club2 both are equal returning  0
                }
            }

        }
    }

    //=================implementing a comparator interface to sort Date===================================
    static class sortingMatchDate implements Comparator<Match>{
        @Override
        public int compare(Match match1, Match match2){
            String d = "-";   //split day,month,year using "-" operator
            String[] date1 = new String[3];
            date1 = match1.getDate().split(d);
            String[] date2 = new String[3];
            date2 = match2.getDate().split(d);
            if (parseInt(date1[2]) > parseInt(date2[2]))
                return 1;
            else if (parseInt(date1[2]) < parseInt(date2[2]))
                return -1;
            else {
                if (parseInt(date1[1]) > parseInt(date2[1]))
                    return 1;
                else if (parseInt(date1[1]) < parseInt(date2[1]))
                    return -1;
                else {
                    if (parseInt(date1[0]) > parseInt(date2[0]))
                        return 1;
                    else if (parseInt(date1[0]) < parseInt(date2[0]))
                        return -1;
                    else
                        return 0;
                }
            }
        }

    }


    //======================== Creating a arraylist to get the details stored in footballClubList and store it in getFootballClubList() list to get the details to the file =====================================
    public List<FootballClub> getFootballClubList(){
        File file = new File("detailsClub.txt"); //creating a new file to store the details in getFootballClubList()

        try{
            FileInputStream fis = new FileInputStream(file); //to read byte oriented data
            ObjectInputStream ois = new ObjectInputStream(fis); //to read objects into a file
            footballClubList = (List<FootballClub>) ois.readObject();  //read the details in the footballClubList list
            ois.close();  //closing the file
            return footballClubList;

        }catch(Exception e){
            return footballClubList;
        }
    }

    //======================== Creating a arraylist to get the details stored in playedMatch and store it in getMatchList() list to get the details to the file =====================================
    public List<Match> getMatchList(){
        File file = new File("detailsMatch.txt"); //creating a new file to store the details in getMatchList()

        try{
            FileInputStream fis = new FileInputStream(file); //to read byte oriented data
            ObjectInputStream ois = new ObjectInputStream(fis); //to read objects into a file
            playedMatch = (List<Match>) ois.readObject();  //read the details in the playedMatch list
            ois.close();  //closing the ObjectInputStream
            return playedMatch;

        }catch(Exception e){
            return playedMatch;
        }
    }




    //=========================creating a file to save the details of football club ==========================================
    public void saveClubDetails(String clubFile) {
        try {
            FileOutputStream fos = new FileOutputStream(clubFile);  //writing a data to a file
            ObjectOutputStream oos = new ObjectOutputStream(fos);//to write objects into a file
            oos.writeObject(footballClubList);  //write objects into the footballClubList list
            oos.flush();  //using flush() method to store the details into the footballClubList
            fos.close();  //closing the FileOutputStream
            oos.close(); //closing the ObjectInputStream
        }catch(IOException e){
            System.out.println("There are no any club details are saved"); //displaying a message if there are no any details are found in the file
        }


    }

    public void saveMatchDetails(String clubFile){
        try{
            FileOutputStream fos = new FileOutputStream(clubFile);  //writing a data to a file
            ObjectOutputStream oos = new ObjectOutputStream(fos);//to write objects into a file
            oos.writeObject(playedMatch); //write objects into the playedMatch list
            oos.flush();  //using flush() method to store the details into the playedMatch
            fos.close();  //closing the FileOutputStream
            oos.close(); //closing the ObjectInputStream
        }catch (IOException e){
            System.out.println("There are no any match details are saved");  //displaying a message if there are no any details are found in the file

        }

    }



    //============Loading football club and match details====================
    public void loadClubDetails(String clubFile) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(clubFile); //to read byte oriented data
        ObjectInputStream ois = new ObjectInputStream(fis); //to read objects into a file

        for(;;){
            try {
                FootballClub footballClub = (FootballClub) ois.readObject();  //checking whether the football club is in the list
                footballClubList.add(footballClub);                        //if it exists in the list reading the details and statistics of the club
            }
            catch (Exception e){           //if there is an error in getting the particular file handling the exception
                break;
            }
        }

        fis.close();  //closing the FileInputStream
        ois.close();  //closing the ObjectInputStream

        System.out.println("");
        System.out.println("Football Club details are successfully loaded to the file");  //after loading the details from the file displaying a message

    }



    public void loadMatchDetails(String clubFile) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(clubFile); //to read byte oriented data
        ObjectInputStream ois = new ObjectInputStream(fis); //to read objects into a file

        for(;;){
            try {
                Match match = (Match)  ois.readObject();  //checking whether the football club is in the list
                playedMatch.add(match);                        //if it exists in the list reading the details and statistics of the club
            }
            catch (Exception e){           //if there is an error in getting the particular file handling the exception
                break;
            }
        }

        fis.close();  //closing the FileInputStream
        ois.close();  //closing the ObjectInputStream
        System.out.println("");
        System.out.println("Football Played Match details are successfully loaded to the file");  //after loading the details from the file displaying a message

    }






    public void addPlayedMatch(String team1, String team2, int team1GoalsScored, int team2GoalsScored, Date date) {

        if (footballClubList.isEmpty()) {   //checking whether the football Club List is empty or not
            System.out.println("There no any football clubs are added.");   //Displaying a message if there is no any club in the list
        } else {
            FootballClub club1 = null;    //initializing the club1 to null

            //checking the team 1 is exists in the football club list
            for (FootballClub footballClub : footballClubList) {  //checking the football club list
                if (footballClub.getClubName().equals(team1)) {  //checking whether the club name is equals to team1
                    club1 = footballClub;   //if the club1 equals to team1 then club1 is equals to football club
                }
            }
            if (club1 == null) {   //if club1 is equals to null then displaying that team1 is not founded in the list
                System.out.println(team1 + " is not founded in the club list");
                return;
            }

            FootballClub club2 = null;
            //checking the team 2 is exists in the football club list
            for (FootballClub footballClub : footballClubList) { //checking the football club list
                if (footballClub.getClubName().equals(team2)) {  //checking whether the club name is equals to team1
                    club2 = footballClub;   //if the club2 equals to team2 then club2 is equals to football club
                }
            }
            if (club2 == null) {    //if club1 is equals to null then displaying that team2 is not founded in the list
                System.out.println(team2 + " is not founded in the club list");
                return;
            }


            //checking the team 1 goals
            if (team1GoalsScored < 0) {  //if team1 goals are greater than 0 displaying a message to enter the correct number of goals
                System.out.println("Enter the correct number of goals");
                return;
            }


            //checking the team 2 goals
            if (team2GoalsScored < 0) {  //if team2 goals are greater than 0 displaying a message to enter the correct number of goals
                System.out.println("Enter the correct number of goals");
                return;
            }


            Match match = new Match(team1, team2, team1GoalsScored, team2GoalsScored,date.toString() );  //creating a new object for the Match
            match.setTeam1Name(team1);  //setting the team1 name as club1
            match.setTeam2Name(team2);  //setting the team2 name as club2
            match.setTeam1Score(team1GoalsScored);  //setting the team1 score
            match.setTeam2Score(team2GoalsScored);  //setting the team2 score
            playedMatch.add(match);  //adding the match to the played match list
            club1.setNumberOfGoalsScored(club1.getNumberOfGoalsScored() + team1GoalsScored);  //adding the scored goals of team1 with club1's scored goals
            club2.setNumberOfGoalsScored(club2.getNumberOfGoalsScored() + team2GoalsScored);  //adding the scored goals of team1 with club2's scored goals
            club1.setNumberOfGoalsReceived(club1.getNumberOfGoalsReceived() + team2GoalsScored);  //increasing the club1's goals received count
            club2.setNumberOfGoalsReceived(club2.getNumberOfGoalsReceived() + team1GoalsScored);  //increasing the club2's goals received count
            club1.setNumberOfMatchesPlayed(club1.getNumberOfMatchesPlayed() + 1);  //increasing the club1's played match count
            club2.setNumberOfMatchesPlayed(club2.getNumberOfMatchesPlayed() + 1);  //increasing the club1's played match count

            //Conditions to check which team's statistics
            //team 1 goals are high
            if (team1GoalsScored > team2GoalsScored) {
                club1.setNumberOfCurrentPoints(club1.getNumberOfCurrentPoints() + 3); // increasing club1's current points
                club1.setNumberOfWins(club1.getNumberOfWins() + 1);  // increasing club1's win counts
                club2.setNumberOfDefeats(club2.getNumberOfDefeats() + 1); // increasing club2's defeat counts

            } //team 2 goals are high
            else if (team2GoalsScored > team1GoalsScored) {
                club2.setNumberOfCurrentPoints(club2.getNumberOfCurrentPoints() + 3); // increasing club2's current points
                club2.setNumberOfWins(club2.getNumberOfWins() + 1);  // increasing club2's win counts
                club1.setNumberOfDefeats(club1.getNumberOfDefeats() + 1); // increasing club1's defeat counts

            }//team 1 and team 2 got the same goals scored
            else {
                club1.setNumberOfCurrentPoints(club1.getNumberOfCurrentPoints() + 1);  // increasing club1's current points
                club2.setNumberOfCurrentPoints(club2.getNumberOfCurrentPoints() + 1); // increasing club2's current points
                club1.setNumberOfDraws(club1.getNumberOfDraws() + 1); // increasing club1's draw count
                club2.setNumberOfDraws(club2.getNumberOfDraws() + 1); // increasing club2's draw count
            }

        }

        saveClubDetails("detailsClub.txt");
        saveMatchDetails("detailsMatch.txt");


    }

}



