import com.gluonhq.charm.glisten.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class Controller {

    Land land = new Land();
    int turn = 1;

    @FXML
    private Text show;

    @FXML
    private TextField console;


    public void initialize(){
        show.setText(String.valueOf(land));
    }

    @FXML
    public void move(KeyEvent e){
        show.setText(String.valueOf(land));
        String input = console.getText();
        if(turn > 0 && console.getText().matches("e|s|w|n")) { //inf loot. current plan is to let player play as long as they can
            this.land.fill = land.chase(land.move(input.charAt(0), land.fill));
            this.land.fill = land.chase(land.fill);
            show.setText(String.valueOf(land));

            turn++;
            console.setText("");
        }
        else{
            console.setText("");
        }
    }

    public TextField getConsole() {
        return console;
    }

    public void setConsole(String x) {
        TextField console = null;
        console.setText(x);
        this.console = console;
    }
}

