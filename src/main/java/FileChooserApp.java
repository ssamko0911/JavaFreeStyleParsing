import config.FileChooserAppConfig;
import enums.ErrorLabel;
import enums.FrameLabel;
import enums.LogLabel;
import managers.StatusBarManager;
import util.AppLogger;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileChooserApp extends JFrame {
    private static final int APP_WIDTH = FileChooserAppConfig.getInt("app.width", 500);
    private static final int APP_HEIGHT = FileChooserAppConfig.getInt("app.height", 600);
    private static final int TEXT_AREA_ROWS = FileChooserAppConfig.getInt("app.textAreaRows", 30);
    private static final int TEXT_AREA_COLUMNS = FileChooserAppConfig.getInt("app.textAreaCols", 30);
    private static final boolean RESIZABLE = FileChooserAppConfig.getBooleanProperty("app.resizable", false);

    private JPanel contentPane;
    private JTextArea textArea;
    private JButton chooseFileButton;
    private JButton clearTextButton;
    private JScrollPane scrollPane;
    private JLabel statusBar;

    private StatusBarManager statusBarManager;

    private static final AppLogger logger = AppLogger.getInstance();

    public FileChooserApp() {
        this.setTitle(FrameLabel.APP_TITLE.getLabel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FileChooserApp.APP_WIDTH, FileChooserApp.APP_HEIGHT);
        this.initAppComponents();
        logger.info(LogLabel.APP_INIT.getLabel());
    }

    private void initAppComponents() {
        this.initComponents();
        this.layoutComponents();
        this.attachListeners();
    }

    private void initComponents() {
        this.contentPane = new JPanel();
        this.chooseFileButton = new JButton(FrameLabel.CHOOSE_FILE.getLabel());
        this.clearTextButton = new JButton(FrameLabel.CLEAN_CONTENT.getLabel());
        this.textArea = new JTextArea(FileChooserApp.TEXT_AREA_ROWS, FileChooserApp.TEXT_AREA_COLUMNS);
        this.textArea.setEditable(FileChooserApp.RESIZABLE);
        this.scrollPane = new JScrollPane(this.textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.statusBar = new JLabel(FrameLabel.READY.getLabel());
        this.statusBar.setBorder((BorderFactory.createEtchedBorder()));
        this.statusBarManager = new StatusBarManager(this.statusBar);
    }

    private void layoutComponents() {
        this.setResizable(false);
        this.contentPane.add(this.chooseFileButton);
        this.contentPane.add(this.clearTextButton);
        this.contentPane.add(this.scrollPane);
        this.setContentPane(this.contentPane);
        this.add(this.statusBar, BorderLayout.SOUTH);
    }

    private void attachListeners() {
        this.chooseFileButton.addActionListener(_ -> this.handleFileSelection());
        this.clearTextButton.addActionListener(_ -> this.handleCleanContent());
    }

    private void handleCleanContent() {
        this.textArea.setText("");
        this.statusBarManager.setReady();
        logger.info(FrameLabel.CLEAN_CONTENT.getLabel());
    }

    private void handleFileSelection() {
        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            logger.info(LogLabel.SELECT_FILE.getLabel() + fileChooser.getSelectedFile().getAbsolutePath());
            File file = fileChooser.getSelectedFile();
            this.loadFile(file);
        }
    }

    private void loadFile(File file) {
        this.statusBarManager.setLoading(file.getName());
        logger.info(LogLabel.LOAD_FILE.getLabel() + file.getAbsolutePath());

        try (Scanner fileReader = new Scanner(file)) {
            StringBuilder content = new StringBuilder();

            while (fileReader.hasNextLine()) {
                content.append(fileReader.nextLine()).append("\n");
            }

            this.textArea.setText(content.toString());
            this.statusBarManager.setLoaded(file);
        } catch (FileNotFoundException e) {
            this.statusBarManager.setError(ErrorLabel.FILE_NOT_FOUND);
            logger.severe(ErrorLabel.FILE_NOT_FOUND.getLabel(), e);
            JOptionPane.showMessageDialog(
                    this,
                    ErrorLabel.FILE_NOT_FOUND.getLabel(),
                    ErrorLabel.ERROR.getLabel(),
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
