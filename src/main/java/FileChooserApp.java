import config.FileChooserAppConfig;
import enums.ErrorLabel;
import enums.FrameLabel;
import enums.LogLabel;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FileChooserApp extends JFrame {
    private JPanel contentPane;
    private JTextArea textArea;
    private JButton chooseFileButton;
    private JScrollPane scrollPane;

    private Logger appLogger;

    public FileChooserApp() {
        this.setTitle(FrameLabel.APP_TITLE.getLabel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(
                FileChooserAppConfig.getInt("app.width", 500),
                FileChooserAppConfig.getInt("app.height", 600)
        );
        this.initAppComponents();
        this.initLogger();
    }

    private void initLogger() {
        this.appLogger = Logger.getLogger(FileChooserAppConfig.class.getName());
        this.appLogger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler(FileChooserAppConfig.getString("app.logFile"));
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            this.appLogger.addHandler(fileHandler);
        } catch (SecurityException | IOException e) {
            // TODO: Label for error
            this.appLogger.log(Level.SEVERE, ErrorLabel.APP_ERROR.getLabel(), e);
        }

    }

    private void initAppComponents() {
        this.initComponents();
        this.layoutComponents();
        this.attachListeners();
    }

    private void initComponents() {
        this.contentPane = new JPanel();
        this.chooseFileButton = new JButton(FrameLabel.CHOOSE_FILE.getLabel());
        this.textArea = new JTextArea(
                FileChooserAppConfig.getInt("app.textAreaRows", 30),
                FileChooserAppConfig.getInt("app.textAreaCols", 30)
        );
        this.textArea.setEditable(
                FileChooserAppConfig.getBooleanProperty("app.resizable", false)
        );
        this.scrollPane = new JScrollPane(this.textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    private void layoutComponents() {
        this.setResizable(false);
        this.contentPane.add(this.chooseFileButton);
        this.contentPane.add(this.scrollPane);
        this.setContentPane(this.contentPane);
    }

    private void attachListeners() {
        this.chooseFileButton.addActionListener(_ -> this.handleFileSelection());
    }

    private void handleFileSelection() {
        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            this.appLogger.log(Level.INFO, LogLabel.SELECT_FILE.getLabel() + fileChooser.getSelectedFile().getAbsolutePath());
            File file = fileChooser.getSelectedFile();
            this.loadFile(file);
        }
    }

    private void loadFile(File file) {
        this.appLogger.log(Level.INFO, LogLabel.LOAD_FILE.getLabel() + file.getAbsolutePath());
        try (Scanner fileReader = new Scanner(file)) {
            StringBuilder content = new StringBuilder();

            while (fileReader.hasNextLine()) {
                content.append(fileReader.nextLine()).append("\n");
            }

            this.textArea.setText(content.toString());
        } catch (FileNotFoundException e) {
            this.appLogger.log(Level.SEVERE, ErrorLabel.FILE_NOT_FOUND.getLabel(), e);
            this.textArea.setText(ErrorLabel.FILE_NOT_FOUND.getLabel());
            throw new RuntimeException(e);
        }
    }
}
