package guitartab;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class GuitarTab extends Application{
    File tabFile = null;
    TextArea noteArea;

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void parseFile() {
        Scanner in = null;
        try {
            in = new Scanner(tabFile);
        } catch (FileNotFoundException e) {
//            System.out.print("File not found.");
            return;
        }

        String HiEString = in.nextLine();
        String BString = in.nextLine();
        String GString = in.nextLine();
        String DString = in.nextLine();
        String AString = in.nextLine();
        String LowEString = in.nextLine();

        int length = BString.length();
        String[] strings = new String[]{HiEString, BString,
            GString, DString, AString, LowEString};

        int[] stringValues = new int[]{8, 3, 11, 6, 1, 8};
        int i = 0, j;
        String temp;
        ArrayList<Integer> numOutput = new ArrayList<Integer>();

        while (i < length - 1) {
            for (j = 0; j < 6; j++) {
                temp = "" + strings[j].charAt(i);

                if (isNumeric(temp)) {
                    if (isNumeric("" + strings[j].charAt(i + 1))) {
                        temp += strings[j].charAt(i + 1);
                    }
                    try {
                        numOutput.add(Integer.parseInt(temp) + stringValues[j]);

                    } catch (NumberFormatException e) {
                    }

                }
            }
            i++;
        }

        ArrayList<String> finalOutput = new ArrayList<String>();

        Iterator iterator = numOutput.iterator();

        String note = "";
        int tempInt;
        while (iterator.hasNext()) {
            tempInt = (int) (iterator.next()) % 12;

            switch (tempInt) {

                case 0:
                    note = "G#";
                    break;
                case 1:
                    note = "A";
                    break;
                case 2:
                    note = "A#";
                    break;
                case 3:
                    note = "B";
                    break;
                case 4:
                    note = "C";
                    break;
                case 5:
                    note = "C#";
                    break;
                case 6:
                    note = "D";
                    break;
                case 7:
                    note = "D#";
                    break;
                case 8:
                    note = "E";
                    break;
                case 9:
                    note = "F";
                    break;
                case 10:
                    note = "F#";
                    break;
                case 11:
                    note = "G";
                    break;

            }
            finalOutput.add(note);
        }
        noteArea.setText(finalOutput.toString());

    }

    public static boolean isNumeric(String str) {
        try {
            int d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     * <p>
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        Button chooseFile = new Button("choose file");

        TextArea tabArea = new TextArea();
        noteArea = new TextArea();

        chooseFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                chooseFile();
                if (tabFile!= null)
                    parseFile();

            }
        });

        VBox vbox = new VBox();
        vbox.getChildren().add(chooseFile);
//        vbox.getChildren().add(tabArea);
        vbox.getChildren().add(noteArea);
        noteArea.setEditable(false);

        Scene scene = new Scene(vbox, 500, 500);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void chooseFile() {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File ("C:\\Users\\mike spad\\Documents\\NetBeansProjects\\cse214\\guitarTab\\src\\guitartab"));
        fc.setTitle("Open Tab File");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("text files", "*.txt"));
        Stage stage = new Stage();
        stage.setScene((new Scene(new VBox(),100,100)));
        tabFile = fc.showOpenDialog(stage);
//        stage.show();
    }


}
