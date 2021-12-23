package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UserScreen extends Application {
	
	GridPane newGridPane;
	
	Button newGameButton,newPlayerButton,newPlayerAndGameButton,insertPlayerButton,
	        insertGameButton, insertPlayerAndGameButton,displayButton;
	
	Label firstNameLabel, lastNameLabel, addressLabel, postalCodeLabel, provinceLabel, phoneNumberLabel,gameTitleLabel, 
          playingDateLabel, scoreLabel, playersLabel, gamesLabel;
	
	TextField firstNameTextField, lastNameFieldTextField, addressTextField, postalCodeTextField, 
	          provinceTextField,phoneNumberTextField,gameTitleTextField, scoreTextField;
	
	DatePicker playingDatePikcer;

	ComboBox<Game> gamesCBox;
	ComboBox<Player> playersCBox;
	
	
	public void start(Stage stage) {
		
		stage.setTitle("Player And Game App!");

newGridPane = new GridPane();
newGridPane.setAlignment(Pos.CENTER);
newGridPane.setHgap(10);
newGridPane.setVgap(10);
newGridPane.setPadding(new Insets(25, 25, 25, 25));

newGameButton = new Button("> New GAME");
newGridPane.add(newGameButton, 0, 0);

newPlayerButton = new Button("> New PLAYER");
 newGridPane.add(newPlayerButton, 0, 1);
 
newPlayerAndGameButton = new Button("> New PLAYER & GAME");
newGridPane.add(newPlayerAndGameButton, 0, 2);

displayButton = new Button("> Display PLAYER & GAME");
        newGridPane.add(displayButton, 0, 3);
        
        Scene scene = new Scene(newGridPane,250,250);
        
        stage.setScene(scene);
        stage.show();
 
        
        
        newGameButton.setOnAction(actionEvent->{
        	
        	GridPane gameGrid = new GridPane();
        	gameGrid.setAlignment(Pos.CENTER);
        	gameGrid.setHgap(10);
        	gameGrid.setVgap(10);
        	gameGrid.setPadding(new Insets(25, 25, 25, 25));

            gameTitleLabel = new Label("Game Title");
    gameGrid.add(gameTitleLabel, 0, 0);

    gameTitleTextField = new TextField();
    gameGrid.add(gameTitleTextField, 1, 0);

    insertGameButton = new Button("INSERT");
    gameGrid.add(insertGameButton, 1, 1);

    Scene newGameScene = new Scene(gameGrid, 400, 300);
    Stage newGameWindow = new Stage();
    newGameWindow.setScene(newGameScene);
    newGameWindow.setTitle("New GAME");
    newGameWindow.show();
    
    
    
    insertGameButton.setOnAction(ae->{
    	
    	String gameTitle = gameTitleTextField.getText();
    	Game newGame = new Game(gameTitle);
    	newGameInformation(newGame);
    	newGameWindow.close();
    	
    });
	
	
});

newPlayerButton.setOnAction(actionEvent -> {
    GridPane playerGrid = new GridPane();
    playerGrid.setAlignment(Pos.CENTER);
    playerGrid.setHgap(10);
    playerGrid.setVgap(10);
    playerGrid.setPadding(new Insets(25, 25, 25, 25));

    firstNameLabel = new Label("First Name:");
    playerGrid.add(firstNameLabel, 0, 0);

    firstNameTextField = new TextField();
    playerGrid.add(firstNameTextField, 1, 0);

    lastNameLabel = new Label("Last Name:");
    playerGrid.add(lastNameLabel, 0, 1);

    lastNameFieldTextField = new TextField();
    playerGrid.add(lastNameFieldTextField, 1, 1);

    addressLabel = new Label("Address:");
    playerGrid.add(addressLabel, 0, 2);

    addressTextField = new TextField();
    playerGrid.add(addressTextField, 1, 2);

    postalCodeLabel = new Label("Postal Code:");
    playerGrid.add(postalCodeLabel, 0, 3);

    postalCodeTextField = new TextField();
    playerGrid.add(postalCodeTextField, 1, 3);

    provinceLabel = new Label("Province:");
    playerGrid.add(provinceLabel, 0, 4);

    provinceTextField = new TextField();
    playerGrid.add(provinceTextField, 1, 4);

    phoneNumberLabel = new Label("Phone Number:");
    playerGrid.add(phoneNumberLabel, 0, 5);

    phoneNumberTextField = new TextField();
    playerGrid.add(phoneNumberTextField, 1, 5);

    insertPlayerButton = new Button("INSERT");
    playerGrid.add(insertPlayerButton, 1, 6);

    Scene newPlayerScene = new Scene(playerGrid, 600, 600);
    Stage newPlayerWindow = new Stage();
    newPlayerWindow.setScene(newPlayerScene);
    newPlayerWindow.setTitle("New Player");
        newPlayerWindow.show();

        insertPlayerButton.setOnAction(ae -> {
                String firstName = firstNameTextField.getText();
                String lastName = lastNameFieldTextField.getText();
                String address = addressTextField.getText();
                String postalCode = postalCodeTextField.getText();
                String province = provinceTextField.getText();
                String phoneNumber = phoneNumberTextField.getText();
                
                Player newPlayer = new Player(firstName, lastName, address, postalCode, province, phoneNumber);
                newPlayerInformation(newPlayer);
                newPlayerWindow.close();
        });
});

    
  newPlayerAndGameButton.setOnAction(actionEvent -> {
        GridPane pGgrid = new GridPane();
        pGgrid.setAlignment(Pos.CENTER);
        pGgrid.setHgap(10);
        pGgrid.setVgap(10);
        pGgrid.setPadding(new Insets(25, 25, 25, 25));

        playersLabel = new Label("Select a player");
    pGgrid.add(playersLabel, 0, 0);

  
    List<Player> players = allPlayers();

    playersCBox = new ComboBox<Player>();

    for (Player player: players) {
    	playersCBox.getItems().add(player);
    }
    
    pGgrid.add(playersCBox, 1, 0);

    gamesLabel = new Label("Select a game");
        pGgrid.add(gamesLabel, 0, 1);

        List<Game> games = allGames();

        gamesCBox = new ComboBox<Game>();
  
        for (Game game: games) {
        	gamesCBox.getItems().add(game);
        }
        pGgrid.add(gamesCBox, 1, 1);

    playingDateLabel = new Label("Playig Date");
    pGgrid.add(playingDateLabel, 0, 2);

    playingDatePikcer = new DatePicker();
    pGgrid.add(playingDatePikcer, 1, 2);

    scoreLabel = new Label("Score");
    pGgrid.add(scoreLabel, 0, 3);

    scoreTextField = new TextField();
    pGgrid.add(scoreTextField, 1, 3);

    insertPlayerAndGameButton = new Button("Player and Game!");
    pGgrid.add(insertPlayerAndGameButton, 1, 4);


    Scene newPlayerGameScene = new Scene(pGgrid, 600, 600);
    Stage newPlayerGameWindow = new Stage();
    newPlayerGameWindow.setScene(newPlayerGameScene);
    newPlayerGameWindow.setTitle("Player & Game");

    newPlayerGameWindow.show();

            insertPlayerAndGameButton.setOnAction(ae -> {
                 
                    
                    Player selectedPlayer = playersCBox.getSelectionModel().getSelectedItem();
                    Game selectedGame = gamesCBox.getSelectionModel().getSelectedItem();
                    
                    LocalDate playingDate = playingDatePikcer.getValue();
                    String score = scoreTextField.getText();

                    PlayerAndGame playerGame = new PlayerAndGame(selectedGame.getGameId(), selectedPlayer.getPlayerId(), playingDate,
                                    Integer.parseInt(score));
                    newPlayerAndGameInformation(playerGame);
            });

    }); 
  
  
  
  displayButton.setOnAction(actionEvent->{
	  
	  try {Connection con = PlayerAndGameDataBase.connectionDB();
		
		String qr = "SELECT * FROM PlayerAndGame";
		
		Statement st = con.createStatement();
		ResultSet res = st.executeQuery(qr);
		
		String columns[] = {"playerGameId", "gameId", "playerId", "playingDate", "score"};
		String data[][] = new String[8][5];
		
		
		int i = 0;
		
		while(res.next()) {
			
			int playerGameId = res.getInt("playerGameId");
			int gameId = res.getInt("gameId");
			int playerId = res.getInt("playerId");
			String playingDate = res.getNString("playingDate");
			int score = res.getInt("score");
			
	        data[i][0] = playerGameId + "";
	        data[i][1] = gameId + "";
	        data[i][2] = playerId + "";
	        data[i][3] = playingDate;
	        data[i][4] = score + "";
	        i++;
			
		}
		
		DefaultTableModel model =  new DefaultTableModel(data,columns);
		
		JTable table = new JTable(model);
		
		 table.setShowGrid(true);
	      table.setShowVerticalLines(true);
	      JScrollPane pane = new JScrollPane(table);
	      JFrame frame = new JFrame("Player and Game Table");
	      JPanel panel = new JPanel();
	      panel.add(pane);
	      frame.add(panel);
	      frame.setSize(500, 250);
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setVisible(true);
		

	}
	
	catch(SQLException e) {
		
		e.printStackTrace();
	}

	
	
	
});
  
	}
	
	
	private void newGameInformation(Game newGame) {
		
        Connection conn = null;
        PreparedStatement pStatement = null;
        try {
        	conn = PlayerAndGameDataBase.connectionDB();
        	conn.setAutoCommit(false);
                String qr = "INSERT INTO GAME VALUES (?, ?)";
        pStatement = conn.prepareStatement(qr);
        pStatement.setInt(1, getNewGameId());
        pStatement.setString(2, newGame.getGameTitle());
        int count = pStatement.executeUpdate();
        if (count == 1) {
                this.alert("Success", "New Game Information inserted to Game Data Basse!!", AlertType.INFORMATION);
        } else {
                this.alert("Failure", "!!Error!!", AlertType.ERROR);
                }
        } catch (Exception e) {
                e.printStackTrace();
        } finally {
                if (null != pStatement) {
                        try {
                        	   pStatement.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
                if (null != conn) {
                        try {
                        	conn.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
        }
}
	
	
    private void newPlayerInformation(Player newPlayer) {
        Connection conn = null;
        PreparedStatement pStatement = null;
        try {
        	conn = PlayerAndGameDataBase.connectionDB();
        	conn.setAutoCommit(false);
                String qr = "INSERT INTO PLAYER VALUES (?, ?, ?, ?, ?, ?, ?)";
                pStatement = conn.prepareStatement(qr);
                pStatement.setInt(1, getNewPlayerId());
                pStatement.setString(2, newPlayer.getFirstName());
                pStatement.setString(3, newPlayer.getLastName());
                pStatement.setString(4, newPlayer.getAddress());
                pStatement.setString(5, newPlayer.getPostalCode());
                pStatement.setString(6, newPlayer.getProvince());
                pStatement.setString(7, newPlayer.getPhoneNumber());
        int count = pStatement.executeUpdate();
        if (count == 1) {
                this.alert("Success", "New Player Information inserted to Player Data Basse!!", AlertType.INFORMATION);
        } else {
                this.alert("Failure", "!!Error!!", AlertType.ERROR);
                }

        } catch (Exception e) {
                e.printStackTrace();
        } finally {
                if (null != pStatement) {
                        try {
                        	  pStatement.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
                if (null != conn) {
                        try {
                        	conn.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
        }
}
	

  private void newPlayerAndGameInformation(PlayerAndGame newPlayerAndGame) {
	  
	  Connection conn = null;
	  PreparedStatement pStatement = null;
      try {
      	  conn = PlayerAndGameDataBase.connectionDB();
      	  conn.setAutoCommit(false);
              String qr = "INSERT INTO PlayerAndGame VALUES (?, ?, ?, ?, ?)";
              pStatement = conn.prepareStatement(qr);
              pStatement.setInt(1, getNewPlayerGameId());
              pStatement.setInt(2, newPlayerAndGame.getGameId());
              pStatement.setInt(3, newPlayerAndGame.getPlayerId());
              pStatement.setDate(4, java.sql.Date.valueOf(newPlayerAndGame.getPlayingDate()));
              pStatement.setInt(5, newPlayerAndGame.getScore());
              pStatement.executeUpdate();
              int count = pStatement.executeUpdate();
              if (count == 1) {
                      this.alert("Success", "New Player and Game Information inserted to PlayerAndGame Data Base", AlertType.INFORMATION);
              } else {
                      this.alert("Failure", "!!Error!!", AlertType.ERROR);
              }
      } catch (Exception e) {
              e.printStackTrace();
      } finally {
              if (null != pStatement) {
                      try {
                    	   pStatement.close();
                      } catch (SQLException e) {
                              e.printStackTrace();
                      }
              }
              if (null != conn) {
                      try {
                      	conn.close();
                      } catch (SQLException e) {
                              e.printStackTrace();
                      }
              }
      }
}
  
  private List<Player> allPlayers(){
	  
	  List<Player> players = new ArrayList<Player>();
		Connection conn = null;
        PreparedStatement pStatement = null;
        try {
        	conn = PlayerAndGameDataBase.connectionDB();
        	conn.setAutoCommit(false);
                String qr = "SELECT * FROM PLAYER";
                pStatement = conn.prepareStatement(qr);
                ResultSet rs = pStatement.executeQuery();
                Player player = null;
                while (rs.next()) {
                        player = new Player(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                        player.setPlayerId(rs.getInt(1));
                        players.add(player);
                }
        } catch (Exception e) {
                e.printStackTrace();
        } finally {
                if (null != pStatement) {
                        try {
                        	pStatement.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
                if (null != conn) {
                        try {
                        	conn.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
        }
        return players;
}
  
  
  private List<Game> allGames() {
      List<Game> games = new ArrayList<Game>();
      Connection conn = null;
      PreparedStatement pStatement = null;
      try {
      	conn = PlayerAndGameDataBase.connectionDB();
      	conn.setAutoCommit(false);
              String qr = "SELECT * FROM GAME";
              pStatement = conn.prepareStatement(qr);
              ResultSet rs = pStatement.executeQuery();
              Game game = null;
              while (rs.next()) {
                      game = new Game(rs.getString(2));
                      game.setGameId(rs.getInt(1));
            
                      games.add(game);
              }
      } catch (Exception e) {
              e.printStackTrace();
      } finally {
              if (null != pStatement) {
                      try {
                    	  pStatement.close();
                      } catch (SQLException e) {
                              e.printStackTrace();
                      }
              }
              if (null != conn) {
                      try {
                      	conn.close();
                      } catch (SQLException e) {
                              e.printStackTrace();
                      }
              }
      }
      return games;
} 

  

	private int getNewGameId() {
		
		int newGameId = 0;
		Connection conn = null;
        PreparedStatement pStatement = null;
        try {
        	conn = PlayerAndGameDataBase.connectionDB();
        	conn.setAutoCommit(false);
                String qr = "SELECT MAX(gameId) FROM GAME";
                pStatement = conn.prepareStatement(qr);
                ResultSet rs = pStatement.executeQuery();
                while (rs.next()) {
                	newGameId = rs.getInt(1) + 100;
                }
        } catch (Exception e) {
                e.printStackTrace();
        } finally {
                if (null != pStatement) {
                        try {
                        	pStatement.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
                if (null != conn) {
                        try {
                        	conn.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
        }
        return newGameId;

}
	
	
    private int getNewPlayerId() {
        int newPlayerId = 0;
        Connection conn = null;
        PreparedStatement pStatement = null;
        try {
        	conn = PlayerAndGameDataBase.connectionDB();
        	conn.setAutoCommit(false);
                String qr = "SELECT MAX(playerId) FROM PLAYER";
                pStatement = conn.prepareStatement(qr);
                ResultSet rs = pStatement.executeQuery();
                while (rs.next()) {
                	newPlayerId = rs.getInt(1) + 1;
                }
        } catch (Exception e) {
                e.printStackTrace();
        } finally {
                if (null != pStatement) {
                        try {
                        	pStatement.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
                if (null != conn) {
                        try {
                        	conn.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
        }
        return newPlayerId;

}	
    
   private int getNewPlayerGameId() {
        int newplayerGameId = 0;
        Connection conn = null;
        PreparedStatement pStatement = null;
        try {
        	conn = PlayerAndGameDataBase.connectionDB();
        	conn.setAutoCommit(false);
                String qr = "SELECT MAX(playerGameId) FROM PlayerAndGame";
                pStatement = conn.prepareStatement(qr);
                ResultSet rs = pStatement.executeQuery();
                while (rs.next()) {
                	newplayerGameId = rs.getInt(1) + 10;
                }
        } catch (Exception e) {
                e.printStackTrace();
        } finally {
                if (null != pStatement) {
                        try {
                        	pStatement.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
                if (null != conn) {
                        try {
                        	conn.close();
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
        }
        return newplayerGameId;

}
   
   
  
	
	
	
	

    public void alert(String title, String message, AlertType alertType) {
        Alert newAlert = new Alert(alertType);
        newAlert.setTitle(title);
        newAlert.setHeaderText(null);
        newAlert.setContentText(message);
        newAlert.showAndWait();
}
	
	
	
	
	
	
	public static void main(String[] args) {
		launch(args);

	}

}
