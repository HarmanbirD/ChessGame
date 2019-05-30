package ca.bcit.comp2526.assign2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Chess.
 *
 * @author HarmanbirDhillon
 * @version 2018
 */
public class Chess extends Application {
    
        Player one = new Player("white");
        Player two = new Player("black");
        static Board b;

    @Override
    public void start(Stage mainStage) throws Exception {
        BorderPane root = new BorderPane();
        Scene mainScene = new Scene(root);
        mainStage.setScene(mainScene);  
        
        b = new ChessBoard(one, two);
        MenuBar menu = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem save = new MenuItem("Save");
        save.setOnAction( e -> save());
        MenuItem load = new MenuItem("Load");
        load.setOnAction( e -> load());
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction( e -> exit());

        menu.setStyle(
                "-fx-background-color: #FFFDD0;"
        );
        
        menuFile.getItems().addAll(save, load, new SeparatorMenuItem(), exit);
        menu.getMenus().addAll(menuFile);
        root.setCenter(b); 
        root.setTop(menu);
        mainStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Saves the current game.
     */
    public static void save() {
          try {
              FileOutputStream fs = new FileOutputStream("savedChess.sav");
              ObjectOutputStream os = new ObjectOutputStream(fs);
              os.writeObject(b);
              os.close();
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          }
    }
    
    /**
     * Loads previous game.
     */
    public static void load() {
        Board temp = null;
        try {
            FileInputStream fs = new FileInputStream("savedChess.sav");
            ObjectInputStream is = new ObjectInputStream(fs);
            temp = (ChessBoard) is.readObject();
            is.close();
            b.setAgain(temp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Exits game.
     */
    public static void exit() {
        System.exit(0);
    }

}
