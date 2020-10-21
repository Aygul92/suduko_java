package graphic;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.SudokuSolver;

public class Graphic extends Application {
    SudokuSolver sudokuSolver = new SudokuSolver();


    TextField[][] boxes;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        root.setTop(menu());
        root.setCenter(createBoxes());
        root.setBottom(buttons());

        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    private Parent createBoxes() {
        boxes = new TextField[9][9];

        TilePane root = new TilePane();
        root.setPrefSize(590, 550);

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {

                boxes[x][y] = new TextField();

                if (!((y > 2 && y < 6) && (x < 3 || x > 5)) && !((x > 2 && x < 6) && (y < 3 || y > 5))) {
                    boxes[x][y].setStyle("-fx-background-color: gray;");
                }
                boxes[x][y].setFont(Font.font(30));
                boxes[x][y].setPrefWidth(60);

                // Check if user enter right number or not.
                int finalX = x;
                int finalY = y;
                boxes[x][y].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                    }
                });
                boxes[x][y].addEventHandler(KeyEvent.KEY_RELEASED, e -> {
                    try {
                        // Removes the number inside sudokuSolver if user use backSpace.
                        if (e.getCode().equals(KeyCode.BACK_SPACE)) {
                            sudokuSolver.removeNumber(finalX, finalY);
                        } else if (!e.getCode().equals(KeyCode.TAB)) {
                            int number = Integer.parseInt(boxes[finalX][finalY].getText());
                            if (number < 1 || number > 9) {
                                new Alert(Alert.AlertType.ERROR,
                                        "Choose a number between 1-9!!", ButtonType.OK).show();
                                boxes[finalX][finalY].setText("");
                                sudokuSolver.removeNumber(finalX, finalY);
                            }
                            // Set the number that user enter and if it is not a write number throws and alert.
                            else if (!sudokuSolver.setNumber(finalX, finalY, number)) {
                                Alert alert = new Alert(Alert.AlertType.ERROR,
                                        "Same number exist at the same column, row or Region",ButtonType.OK);
                                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                                alert.show();
                                boxes[finalX][finalY].setText("");
                                sudokuSolver.removeNumber(finalX, finalY);
                            }
                        }

                    } catch (NumberFormatException event) { // If entered string is not a number.
                        new Alert(Alert.AlertType.ERROR, "Please write a number!!", ButtonType.OK).show();
                        boxes[finalX][finalY].setText("");
                    }
                });
                root.setPadding(new Insets(5));
                root.setHgap(5);
                root.setVgap(5);
                root.getChildren().add(boxes[x][y]);
            }
        }
        return root;
    }

    private HBox buttons () {
        HBox hBox = new HBox();

        // Solve Button.
        Button solve = new Button("Solve");
        solve.setPadding(new Insets(10));
        HBox.setMargin(solve, new Insets(15));
        solve.setOnAction(e -> {
            if (!sudokuSolver.solve()){
                new Alert(Alert.AlertType.ERROR,
                        "It is not solvable!!", ButtonType.OK).show();
            } else {
                for (int x = 0; x < 9; x++) {
                    for (int y = 0; y < 9; y++) {
                        try {
                            boxes[x][y].setText((String.valueOf(sudokuSolver.getNr(x, y))));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });

        // Clear Button.
        Button clear = new Button("Clear");
        clear.setPadding(new Insets(10));
        HBox.setMargin(clear, new Insets(15));
        clear.setOnAction(e -> clear());

        hBox.setStyle("-fx-background-color: white;");
        hBox.getChildren().addAll(solve, clear);
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    private MenuBar menu() {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Default Sudokues");
        MenuItem empty = new MenuItem("Empty");
        empty.setOnAction(e -> clear());
        MenuItem unsolvable = new MenuItem("Unsolvable");
        unsolvable.setOnAction(e -> {
            clear();
            sudokuSolver.setNumber(0, 0, 3);
            boxes[0][0].setText("3");
            sudokuSolver.setNumber(0, 1, 7);
            boxes[0][1].setText("7");
            sudokuSolver.setNumber(0, 2, 8);
            boxes[0][2].setText("8");
            sudokuSolver.setNumber(0, 5, 9);
            boxes[0][5].setText("9");
            sudokuSolver.setNumber(0, 7, 6);
            boxes[0][7].setText("6");
            sudokuSolver.setNumber(0, 8, 2);
            boxes[0][8].setText("2");
            sudokuSolver.setNumber(1, 8, 5);
            boxes[1][8].setText("5");
            sudokuSolver.setNumber(2, 0, 1);
            boxes[2][0].setText("1");
            sudokuSolver.setNumber(2, 2, 2);
            boxes[2][2].setText("2");
            sudokuSolver.setNumber(2, 3, 5);
            boxes[2][3].setText("5");
            sudokuSolver.setNumber(3, 3, 2);
            boxes[3][3].setText("2");
            sudokuSolver.setNumber(3, 4, 1);
            boxes[3][4].setText("1");
            sudokuSolver.setNumber(3, 7, 9);
            boxes[3][7].setText("9");
            sudokuSolver.setNumber(4, 1, 5);
            boxes[4][1].setText("5");
            sudokuSolver.setNumber(4, 6, 6);
            boxes[4][6].setText("6");
            sudokuSolver.setNumber(5, 0, 6);
            boxes[5][0].setText("6");
            sudokuSolver.setNumber(5, 7, 2);
            boxes[5][7].setText("2");
            sudokuSolver.setNumber(5, 8, 8);
            boxes[5][8].setText("8");
            sudokuSolver.setNumber(6, 0, 4);
            boxes[6][0].setText("4");
            sudokuSolver.setNumber(6, 1, 1);
            boxes[6][1].setText("1");
            sudokuSolver.setNumber(6, 3, 6);
            boxes[6][3].setText("6");
            sudokuSolver.setNumber(6, 5, 8);
            boxes[6][5].setText("8");
            sudokuSolver.setNumber(7, 0, 8);
            boxes[7][0].setText("8");
            sudokuSolver.setNumber(7, 1, 6);
            boxes[7][1].setText("6");
            sudokuSolver.setNumber(7, 4, 3);
            boxes[7][4].setText("3");
            sudokuSolver.setNumber(7, 6, 1);
            boxes[7][6].setText("1");
            sudokuSolver.setNumber(8, 6, 4);
            boxes[8][6].setText("4");
        });
        MenuItem figure11 = new MenuItem("Figure11");
        figure11.setOnAction( e -> {
            clear();
            sudokuSolver.setNumber(0, 2, 8);
            boxes[0][2].setText("8");
            sudokuSolver.setNumber(0, 5, 9);
            boxes[0][5].setText("9");
            sudokuSolver.setNumber(0, 7, 6);
            boxes[0][7].setText("6");
            sudokuSolver.setNumber(0, 8, 2);
            boxes[0][8].setText("2");
            sudokuSolver.setNumber(1, 8, 5);
            boxes[1][8].setText("5");
            sudokuSolver.setNumber(2, 0, 1);
            boxes[2][0].setText("1");
            sudokuSolver.setNumber(2, 2, 2);
            boxes[2][2].setText("2");
            sudokuSolver.setNumber(2, 3, 5);
            boxes[2][3].setText("5");
            sudokuSolver.setNumber(3, 3, 2);
            boxes[3][3].setText("2");
            sudokuSolver.setNumber(3, 4, 1);
            boxes[3][4].setText("1");
            sudokuSolver.setNumber(3, 7, 9);
            boxes[3][7].setText("9");
            sudokuSolver.setNumber(4, 1, 5);
            boxes[4][1].setText("5");
            sudokuSolver.setNumber(4, 6, 6);
            boxes[4][6].setText("6");
            sudokuSolver.setNumber(5, 0, 6);
            boxes[5][0].setText("6");
            sudokuSolver.setNumber(5, 7, 2);
            boxes[5][7].setText("2");
            sudokuSolver.setNumber(5, 8, 8);
            boxes[5][8].setText("8");
            sudokuSolver.setNumber(6, 0, 4);
            boxes[6][0].setText("4");
            sudokuSolver.setNumber(6, 1, 1);
            boxes[6][1].setText("1");
            sudokuSolver.setNumber(6, 3, 6);
            boxes[6][3].setText("6");
            sudokuSolver.setNumber(6, 5, 8);
            boxes[6][5].setText("8");
            sudokuSolver.setNumber(7, 0, 8);
            boxes[7][0].setText("8");
            sudokuSolver.setNumber(7, 1, 6);
            boxes[7][1].setText("6");
            sudokuSolver.setNumber(7, 4, 3);
            boxes[7][4].setText("3");
            sudokuSolver.setNumber(7, 6, 1);
            boxes[7][6].setText("1");
            sudokuSolver.setNumber(8, 6, 4);
            boxes[8][6].setText("4");
        });

        menu.getItems().addAll(empty, unsolvable, figure11);
        menuBar.getMenus().add(menu);
        return menuBar;
    }

    private void clear() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                boxes[x][y].setText("");
            }
        }
        sudokuSolver = new SudokuSolver();
    }

}
