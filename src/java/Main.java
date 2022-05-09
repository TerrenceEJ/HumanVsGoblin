import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("display.fxml"));
        Scene scene = new Scene(root);
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Humans Vs Goblins");
        stage.show();
        scene.getRoot().requestFocus();
    }

    public static void main(String[] args) {
        System.out.println("Would you like to start Goblins vs Humans? y/n");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(input.charAt(0) == 'y'){
            launch(args);

        }else{
            System.out.println("Ending game.");
            System.exit(0);
        }


    }
}
