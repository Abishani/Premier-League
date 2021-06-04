package sample;
/**
 *  * Student Id : 2019743
 *  *Uow Id : w1789987
 *  *Name : Abishani Muthalagan

 * Reference for sorting : https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TableColumn.SortType.html#DESCENDING
 * Reference for table view : https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
 * Reference for common label : https://stackoverflow.com/questions/20514703/how-do-you-erase-a-label-in-javafx/20518980
 * Reference for search details in the match : https://stackoverflow.com/questions/47559491/making-a-search-bar-in-javafx/47560767#47560767
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class StatisticsTable extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private final static LeagueManager manager = new PremierLeagueManager(); //creating a object for PremierLeagueManager class


    public static void tableDetails() {
        final ObservableList<FootballClub> details = FXCollections.observableArrayList(manager.getFootballClubList());


        //window to display table
        //============================= creating a stage to contain table and buttons =====================
        Stage stage = new Stage();
        Scene scene = new Scene(new Group());
        stage.setTitle("Premier League Football Statistics");
        stage.setWidth(2000);
        stage.setHeight(1030);


        //============Displaying Heading for the Table using the label ======================
        Label label = new Label("Premier League Football");
        label.setStyle("-fx-font-style: oblique;-fx-font-size: 80px;-fx-font-weight: bolder;-fx-text-fill: honeydew");
        label.setLayoutX(500);
        label.setLayoutY(10);


        //================== Creating a Table view to display table with columns and rows ============================
        TableView tableView = new TableView();
        tableView.setPrefSize(1215, 700);
        tableView.setEditable(false);
        tableView.setStyle("-fx-border-color: black");


        //========================= Creating table columns ========================
        //===================== Creating a column for contain club name ====================
        TableColumn<String, FootballClub> club = new TableColumn<>("Club");
        club.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        club.setPrefWidth(130);
        club.setStyle("-fx-font-size: 18px;-fx-border-color: black;-fx-font-weight: bolder;-fx-background-color: linear-gradient(lightblue,grey)");

        //===================== Creating a column for contain played match ====================
        TableColumn<Integer, FootballClub> played = new TableColumn<>("Played");
        played.setCellValueFactory(new PropertyValueFactory<>("numberOfMatchesPlayed"));
        played.setPrefWidth(130);
        played.setStyle("-fx-font-size: 18px;-fx-border-color: black;-fx-font-weight: bolder;-fx-background-color: linear-gradient(lightblue,grey)");

        //===================== Creating a column for contain wins ====================
        TableColumn<Integer, FootballClub> wins = new TableColumn<>("Won");
        wins.setCellValueFactory(new PropertyValueFactory<>("numberOfWins"));
        wins.setPrefWidth(130);
        wins.setStyle("-fx-font-size: 18px;-fx-border-color: black;-fx-font-weight: bolder;-fx-background-color: linear-gradient(lightblue,grey)");

        //===================== Creating a column for contain club defeats ====================
        TableColumn<Integer, FootballClub> drawn = new TableColumn<>("Drawn");
        drawn.setCellValueFactory(new PropertyValueFactory<>("numberOfDraws"));
        drawn.setPrefWidth(130);
        drawn.setStyle("-fx-font-size: 18px;-fx-border-color: black;-fx-font-weight: bolder;-fx-background-color: linear-gradient(lightblue,grey)");

        //===================== Creating a column for contain losts ====================
        TableColumn<Integer, FootballClub> lost = new TableColumn<>("Lost");
        lost.setCellValueFactory(new PropertyValueFactory<>("numberOfDefeats"));
        lost.setPrefWidth(130);
        lost.setStyle("-fx-font-size: 18px;-fx-border-color: black;-fx-font-weight: bolder;-fx-background-color: linear-gradient(lightblue,grey)");

        //===================== Creating a column for contain goals scored ====================
        TableColumn<Integer, FootballClub> goalsScored = new TableColumn<>("Goals Scored");
        goalsScored.setCellValueFactory(new PropertyValueFactory<>("numberOfGoalsScored"));
        goalsScored.setPrefWidth(140);
        goalsScored.setStyle("-fx-font-size: 18px;-fx-border-color: black;-fx-font-weight: bolder;-fx-background-color: linear-gradient(lightblue,grey)");


        //===================== Creating a column for contain goals received ====================
        TableColumn<Integer, FootballClub> goalsReceived = new TableColumn<>("Goals Received");
        goalsReceived.setCellValueFactory(new PropertyValueFactory<>("numberOfGoalsReceived"));
        goalsReceived.setPrefWidth(140);
        goalsReceived.setStyle("-fx-font-size: 18px;-fx-border-color: black;-fx-font-weight: bolder;-fx-background-color: linear-gradient(lightblue,grey)");


        //===================== Creating a column for contain goals difference ====================
        TableColumn<Integer, FootballClub> goalsDifference = new TableColumn<>("Goals Difference");
        goalsDifference.setCellValueFactory(new PropertyValueFactory<>("numberOfGoalsDifference"));
        goalsDifference.setPrefWidth(150);
        goalsDifference.setStyle("-fx-font-size: 18px;-fx-border-color: black;-fx-font-weight: bolder;-fx-background-color: linear-gradient(lightblue,grey)");

        //===================== Creating a column for contain points ====================
        TableColumn<Integer, FootballClub> points = new TableColumn<>("Points");
        points.setCellValueFactory(new PropertyValueFactory<>("numberOfCurrentPoints"));
        points.setPrefWidth(130);
        points.setStyle("-fx-font-size: 18px;-fx-border-color: black;-fx-font-weight: bolder;-fx-background-color: linear-gradient(lightblue,grey)");

        //===================== getting the columns names by using getColumns() method and adding those columns to the tables view by using addAll() method ====================
        tableView.getColumns().addAll(club, played, wins, drawn, lost, goalsScored, goalsReceived, goalsDifference, points);


        // ============================== Sorting the Points by using Collections.sort() method and by using reverseOrder() method sorting points in reverse order=====================================
        Collections.sort(details, Collections.reverseOrder(new PremierLeagueManager.sortingPoints()));
        for (FootballClub footballClubDetails : details) {
            FootballClub addDetail = new FootballClub(footballClubDetails.getClubName(), null, footballClubDetails.getNumberOfMatchesPlayed(), footballClubDetails.getNumberOfWins(), footballClubDetails.getNumberOfDraws(), footballClubDetails.getNumberOfDefeats(), footballClubDetails.getNumberOfGoalsScored(), footballClubDetails.getNumberOfGoalsReceived(), footballClubDetails.getNumberOfGoalsDifference(), footballClubDetails.getNumberOfCurrentPoints());
            tableView.getItems().addAll(addDetail);
        }

        //================================Button to sort according to goals scored============================
        Button btn1 = new Button("Sort by Goals Scored");
        btn1.setStyle("-fx-text-fill: black;-fx-font-weight: bolder;-fx-font-size: 25px");
        btn1.setLayoutX(130);
        btn1.setLayoutY(200);
        btn1.setPrefWidth(350);
        btn1.setCursor(Cursor.HAND);
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //=================== Sorting the goals scored in descending order by using SortType.DESCENDING =======================
                goalsScored.setSortType((TableColumn.SortType.DESCENDING));
                tableView.getSortOrder().setAll(goalsScored);
            }
        });


        //===================================Button to sort according to wins=================================
        Button btn2 = new Button("Sort by Wins");
        btn2.setStyle("-fx-text-fill: black;-fx-font-weight: bolder;-fx-font-size: 25px");
        btn2.setLayoutX(130);
        btn2.setLayoutY(300);
        btn2.setPrefWidth(350);
        btn2.setCursor(Cursor.HAND);
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //=================== Sorting the wins in descending order by using SortType.DESCENDING =======================
                wins.setSortType((TableColumn.SortType.DESCENDING));
                tableView.getSortOrder().setAll(wins);
            }
        });


        //===============Creating a new window to display random match with score================
        //==================================creating labels======================================
        Label homeTeamLabel = new Label("");
        homeTeamLabel.setLayoutX(70);
        homeTeamLabel.setLayoutY(30);
        homeTeamLabel.setStyle("-fx-font-weight: bolder;-fx-font-size: 18px;-fx-text-fill: white;-fx-font-size: 30px");


        Label awayTeamLabel = new Label("");
        awayTeamLabel.setLayoutX(360);
        awayTeamLabel.setLayoutY(30);
        awayTeamLabel.setStyle("-fx-font-weight: bolder;-fx-font-size: 18px;-fx-text-fill: white;-fx-font-size: 30px");

        Label homeTeamScore = new Label("");
        homeTeamScore.setLayoutX(260);
        homeTeamScore.setLayoutY(25);
        homeTeamScore.setStyle("-fx-font-weight: bolder;-fx-font-size: 18px;-fx-text-fill: white;-fx-font-size: 40px;-fx-background-color: black");


        Label awayTeamScore = new Label("");
        awayTeamScore.setLayoutX(290);
        awayTeamScore.setLayoutY(25);
        awayTeamScore.setStyle("-fx-font-weight: bolder;-fx-font-size: 18px;-fx-text-fill: white;-fx-font-size: 40px;-fx-background-color: black");



        Pane random = new Pane();
        random.getChildren().addAll(homeTeamLabel, awayTeamLabel, homeTeamScore, awayTeamScore);
        random.setLayoutX(50);
        random.setLayoutY(700);
        random.setPrefWidth(550);
        random.setPrefHeight(100);
        random.setStyle("-fx-background-radius: 35px;-fx-background-color: linear-gradient(black,mediumseagreen);-fx-border-color: black;-fx-border-radius: 30px;-fx-border-width: 10px");



        //==========================Button to generate random played match=================================
        Button btn3 = new Button("Generate Random Match");
        btn3.setStyle("-fx-text-fill: black;-fx-font-weight: bolder;-fx-font-size: 25px");
        btn3.setLayoutX(130);
        btn3.setLayoutY(600);
        btn3.setPrefWidth(350);
        btn3.setCursor(Cursor.HAND);
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {



                //===============Adding Random function to generate random teams score and date==================
                Random rand = new Random();

                //==================Getting the ArrayList function to get the club list and match list============================
                List<FootballClub> clubList = manager.getFootballClubList();
                List<Match> matchList = manager.getMatchList();

                //================ Generating a random match date by using Math.random() method ===================
                int year = (int) (20 * Math.random() + 2000);  //generating a random year using Math.random() method
                int day;
                int month = (int) (12 * Math.random() + 1);  //generating a random month using Math.random() method
                if (month == 2) {
                    day = (int) (28 * Math.random() + 1);  //generating a random day using Math.random() method
                } else {
                    day = (int) (31 * Math.random() + 1);
                }

                //creating a new object of the date
                Date dateOfMatch = new Date(day,month,year);


                FootballClub footballClub1 = anyClub();
                FootballClub footballClub2 = anyClub();
                if(footballClub1 == footballClub2){
                    return;
                }


                int randomScoreC1 = rand.nextInt(10);
                int randomScoreC2 = rand.nextInt(10);

                try {
                    manager.addPlayedMatch(footballClub1.getClubName(), footballClub2.getClubName(), randomScoreC1, randomScoreC2, dateOfMatch);
                    tableView.getItems();
                } catch (Exception e) {

                }


                try {
                    manager.saveClubDetails("detailsClub.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }


                try {
                    manager.saveMatchDetails("detailsMatch.txt");

                } catch (Exception e) {
                    e.printStackTrace();
                }


                //================ displaying the random points by setting the text in each labels ================
                homeTeamLabel.setText(String.valueOf(footballClub1.getClubName()));
                awayTeamLabel.setText(String.valueOf(footballClub2.getClubName()));
                homeTeamScore.setText(String.valueOf(randomScoreC1));
                awayTeamScore.setText(String.valueOf(randomScoreC2));
            }


            //====================generating a random clubs using Random method==========================
            public FootballClub anyClub() {
                Random rand3 = new Random();
                int randomNum = rand3.nextInt(details.size());


                FootballClub randomClub = details.get(randomNum);
                return randomClub;
            }

        });


        //==============================Button to display played match sorted according to date======================================
        Button btn4 = new Button("Played Match");
        btn4.setStyle("-fx-text-fill: black;-fx-font-weight: bolder;-fx-font-size: 25px");
        btn4.setLayoutX(130);
        btn4.setLayoutY(400);
        btn4.setPrefWidth(350);
        btn4.setCursor(Cursor.HAND);
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //===============calling the new window function to display when the button is clicked=====================
                playedMatch();
            }
        });

        Button btn5  = new Button("Search by Date");
        btn5.setStyle("-fx-text-fill: black;-fx-font-weight: bolder;-fx-font-size: 25px");
        btn5.setLayoutX(130);
        btn5.setLayoutY(500);
        btn5.setPrefWidth(350);
        btn5.setCursor(Cursor.HAND);
        btn5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //===============calling the new window function to display when the button is clicked=====================
                searchMatch();
            }
        });



        Image backgroundImg = new Image("file:GroundImage.jpg");
        ImageView imgView1 = new ImageView(backgroundImg);
        imgView1.setFitWidth(2000);
        imgView1.setFitHeight(1030);


        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(150, 0, 0, 620));
        vbox.getChildren().addAll(tableView);


        ((Group) scene.getRoot()).getChildren().addAll(imgView1,random, label,vbox, btn1, btn2, btn3, btn4,btn5);

        stage.setScene(scene);
        stage.show();


    }


    //============================Creating a method to sort the played match details according to date and creating a new window to display the table===================
    public static void playedMatch() {
        final ObservableList<Match> scoreDetails = FXCollections.observableArrayList(manager.getMatchList());


        //===================creating a new stage to display the table in new window==============
        Stage stage2 = new Stage();
        Scene scene2 = new Scene(new Group());
        stage2.setTitle("Played Match with Score");
        stage2.setWidth(1400);
        stage2.setHeight(1000);


        //=================creating the label for heading of the table=============================
        Label playedMatchLabel = new Label("Played Match with Score");
        playedMatchLabel.setStyle("-fx-font-style: oblique;-fx-font-size: 80px;-fx-font-weight: bolder;-fx-text-fill: honeydew");




        //===============creating a table view to display the played clubs in the match with its score===============================
        TableView tView = new TableView();
        tView.setPrefSize(1000, 500);
        tView.setEditable(false);
        tView.setStyle("-fx-border-color: black");

        //table columns
        TableColumn<String, Match> homeTeam = new TableColumn<>("Home Team");
        homeTeam.setCellValueFactory(new PropertyValueFactory<>("team1Name"));
        homeTeam.setPrefWidth(200);
        homeTeam.setStyle("-background-color: transparent;-fx-font-size: 20px;-fx-border-color: black;-fx-font-weight: bolder;-fx-background-color: linear-gradient(lightblue,grey)");


        TableColumn<String, Match> awayTeam = new TableColumn<>("Away Team");
        awayTeam.setCellValueFactory(new PropertyValueFactory<>("team2Name"));
        awayTeam.setPrefWidth(200);
        awayTeam.setStyle("-fx-font-size: 20px;-fx-border-color: black;-fx-font-weight: bolder;-fx-background-color: linear-gradient(lightblue,grey)");


        TableColumn<Integer, Match> score1 = new TableColumn<>("Home Team Score");
        score1.setCellValueFactory(new PropertyValueFactory<>("team1Score"));
        score1.setPrefWidth(200);
        score1.setStyle("-fx-font-size: 20px;-fx-border-color: black;-fx-font-weight: bolder;-fx-background-color: linear-gradient(lightblue,grey)");

        TableColumn<Integer, Match> score2 = new TableColumn<>("Away Team Score");
        score2.setCellValueFactory(new PropertyValueFactory<>("team2Score"));
        score2.setPrefWidth(200);
        score2.setStyle("-fx-font-size: 20px;-fx-border-color: black;-fx-font-weight: bolder;-fx-background-color: linear-gradient(lightblue,grey)");

        TableColumn<Date, Match> matchDate = new TableColumn<>("Date");
        matchDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        matchDate.setPrefWidth(200);
        matchDate.setStyle("-fx-font-size: 20px;-fx-border-color: black;-fx-font-weight: bolder;-fx-background-color: linear-gradient(lightblue,grey)");

        Collections.sort(scoreDetails,new PremierLeagueManager.sortingMatchDate());
        for (Match matchDetails : scoreDetails) {
            Match matchScoreDetails = new Match(matchDetails.getTeam1Name(), matchDetails.getTeam2Name(), matchDetails.getTeam1Score(), matchDetails.getTeam2Score(), matchDetails.getDate());
            tView.getItems().addAll(matchScoreDetails);
        }



        //========================Creating a Searchbar=============================
        FilteredList<Match> searchMatch = new FilteredList(scoreDetails, p -> true);//Pass the data to a filtered list
        tView.setItems(searchMatch);//Set the table's items using the filtered list
        tView.getColumns().addAll(homeTeam, awayTeam, score1, score2, matchDate);

        Label dateLabel = new Label("Filtering match records by Date");
        dateLabel.setLayoutX(300);
        dateLabel.setLayoutY(300);
        dateLabel.setStyle("-fx-text-fill: white;-fx-font-size: 30px");
        Collections.sort(scoreDetails,new PremierLeagueManager.sortingMatchDate());




        TextField textField = new TextField();
        textField.setPromptText("Enter the date here!");
        textField.setOnKeyReleased(keyEvent ->
        {

            searchMatch.setPredicate(p -> p.getDate().toLowerCase().contains(textField.getText().toLowerCase().trim()));//filter table by first name


        });



        Image backgroundImg = new Image("file:GroundImage.jpg");
        ImageView imgView1 = new ImageView(backgroundImg);
        imgView1.setFitWidth(1400);
        imgView1.setFitHeight(1000);


        final VBox vbox = new VBox();
        vbox.setSpacing(30);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(90, 0, 0, 180));
        vbox.getChildren().addAll(playedMatchLabel,dateLabel,textField,tView);

        ((Group) scene2.getRoot()).getChildren().addAll(imgView1,vbox);

        stage2.setScene(scene2);
        stage2.show();


    }

    //================================= Method to Search match details using date ===========================
    public static void searchMatch(){

        //====================== creating a stage to contain all elements =========================
        Stage stage3 = new Stage();
        Scene scene3 = new Scene(new Group());
        stage3.setTitle("Search Match Details");
        stage3.setWidth(900);
        stage3.setHeight(700);

        //============================ Creating label to display heading ===============================
        Label heading = new Label("PLAYED MATCHES");
        heading.setLayoutX(200);
        heading.setLayoutY(10);
        heading.setStyle("-fx-font-style: oblique;-fx-font-size: 60px;-fx-font-weight: bolder;-fx-text-fill: black");


        //============================ Creating label to display day ===============================
        Label dayLabel = new Label("Day");
        dayLabel.setLayoutX(100);
        dayLabel.setLayoutY(130);
        dayLabel.setStyle("-fx-font-weight: bolder;-fx-font-size:25px;-fx-text-fill: black");


        //============================ Creating textfield to get day from the user ===============================
        TextField dayText = new TextField();
        dayText.setPromptText("Day");
        dayText.setLayoutX(80);
        dayText.setLayoutY(165);
        dayText.setPrefWidth(100);
        dayText.setPrefHeight(40);

        //============================ Creating label to display month ===============================
        Label monthLabel = new Label("Month");
        monthLabel.setLayoutX(220);
        monthLabel.setLayoutY(130);
        monthLabel.setStyle("-fx-font-weight: bolder;-fx-font-size:25px;-fx-text-fill: black");


        //============================ Creating textfield to get month from the user ===============================
        TextField monthText = new TextField();
        monthText.setPromptText("Month");
        monthText.setLayoutX(210);
        monthText.setLayoutY(165);
        monthText.setPrefWidth(100);
        monthText.setPrefHeight(40);


        //============================ Creating label to display year ===============================
        Label yearLabel = new Label("Year");
        yearLabel.setLayoutX(340);
        yearLabel.setLayoutY(130);
        yearLabel.setStyle("-fx-font-weight: bolder;-fx-font-size:25px;-fx-text-fill: black");


        //============================ Creating textfield to get year from the user ===============================
        TextField yearText = new TextField();
        yearText.setPromptText("Year");
        yearText.setLayoutX(340);
        yearText.setLayoutY(165);
        yearText.setPrefWidth(100);
        yearText.setPrefHeight(40);




        //creating a pane to contain the buttons,image , textfields and labels
        Pane root = new Pane();
        Label displayResult = new Label("PLAYED MATCHES");
        displayResult.setLayoutX(40);
        displayResult.setLayoutY(300);
        displayResult.setStyle("-fx-text-fill: black;-fx-font-weight: bold;-fx-font-size: 30px");



        //creating a search button to search matches according to the date and display it on a label
        Button searchButton = new Button("Search");
        searchButton.setLayoutX(480);
        searchButton.setLayoutY(155);
        searchButton.setPrefWidth(150);
        searchButton.setPrefHeight(50);
        searchButton.setCursor(Cursor.HAND);
        searchButton.setStyle("-fx-font-size: 25px");
        searchButton.setOnAction(new EventHandler<ActionEvent>() { //creating a setOnAction to handle the button event
            @Override
            public void handle(ActionEvent event) {
                String dayOfTheMatch = dayText.getText();
                String monthOfTheMatch = monthText.getText();
                String yearOfTheMatch = yearText.getText();

                try{
                    int day = parseInt(dayOfTheMatch);
                    int month = parseInt(monthOfTheMatch);
                    int year = parseInt(yearOfTheMatch);

                    String dateofTheMatch = day+"-"+month+"-"+year;

                    List<Match> matchResult = manager.getMatchList();
                    boolean flag = false;

                    for(Match match: matchResult){
                        if(dateofTheMatch.equals(match.getDate())){
                            displayResult.setText("Match Date : "+match.getDate()+"\nHome Team : "+match.getTeam1Name()+"\nHome Team Score : "+match.getTeam1Score()+"\nAway Team : "+match.getTeam2Name()+"\nAway Team Score : "+match.getTeam2Score()+"\n\n");
                            root.getChildren().add(displayResult);
                            flag =true;
                        }
                    }
                    if(!flag){
                        displayResult.setText("No any matches are found in this date");
                        root.getChildren().add(displayResult);
                    }

                }catch(Exception e){

                }
            }
        });








        Image backgroundImg = new Image("file:displayMatch1.jpg");
        ImageView imgView2 = new ImageView(backgroundImg);
        imgView2.setFitWidth(900);
        imgView2.setFitHeight(700);


        root.getChildren().addAll(imgView2,heading,dayLabel,dayText,monthLabel,monthText,yearLabel,yearText,searchButton);
        Scene scene = new Scene(root,900,700);
        stage3.setScene(scene);
        stage3.show();

    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        tableDetails();
    }
}
