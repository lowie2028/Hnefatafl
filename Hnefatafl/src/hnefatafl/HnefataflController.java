package hnefatafl;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;

public class HnefataflController {
    Hnefatafl hnefataflModel;
    HnefataflView hnefataflView;

    @FXML
    private AnchorPane gamePane;


    @FXML
    private Button unselectBtn;

    @FXML
    void initialize() {
        assert gamePane != null : "fx:id=\"gamePane\" was not injected: check your FXML file 'FXMLHnefataflView.fxml'.";
        gamePane.setOnMouseClicked(event -> handleMouseClick(event));
        unselectBtn.setOnAction(event -> handleUnselect(event));
    }

    public void setModel(Hnefatafl hnefataflModel) {
        this.hnefataflModel = hnefataflModel;
        hnefataflView = new HnefataflView(hnefataflModel);
        gamePane.getChildren().add(hnefataflView);
        gamePane.setFocusTraversable(true);
    }

    public void handleMouseClick(MouseEvent mouseEvent) {
        int x = (int) mouseEvent.getX();
        int y = (int) mouseEvent.getY();
        if (hnefataflModel.isAPieceSelected()) {
            hnefataflModel.moveSelectedPieceTo(hnefataflView.getRow(y), hnefataflView.getColumn(x));
        } else {
            hnefataflModel.selectPiece(hnefataflView.getRow(y), hnefataflView.getColumn(x));
        }

        //printout
        if (hnefataflModel.isAPieceSelected()){
            System.out.println("Selected Piece --> " + hnefataflModel.getSelectedPiece().toString());
        } else {
            System.out.println("No piece selected");
        }

        hnefataflView.update();
    }

    public void handleUnselect(Event e){
        hnefataflModel.unSelectPiece();
    }
}

