import config.FileChooserAppConfig;
import enums.ErrorLabels;
import enums.FrameLabels;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileChooserApp extends JFrame {
    private JPanel contentPane;
    private JTextArea textArea;
    private JButton chooseFileButton;
    private JScrollPane scrollPane;

    public FileChooserApp() {
        this.setTitle(FrameLabels.APP_TITLE.getLabel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(
                FileChooserAppConfig.getInt("app.width", 500),
                FileChooserAppConfig.getInt("app.height", 600)
        );
        this.initAppComponents();
    }

    private void initAppComponents() {
        this.initComponents();
        this.layoutComponents();
        this.attachListeners();
    }

    private void initComponents() {
        this.contentPane = new JPanel();
        this.chooseFileButton = new JButton(FrameLabels.CHOOSE_FILE.getLabel());
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
            File file = fileChooser.getSelectedFile();
            this.loadFile(file);
        }
    }

    private void loadFile(File file) {
        try (Scanner fileReader = new Scanner(file)) {
            StringBuilder content = new StringBuilder();

            while (fileReader.hasNextLine()) {
                content.append(fileReader.nextLine()).append("\n");
            }

            this.textArea.setText(content.toString());
        } catch (FileNotFoundException e) {
            this.textArea.setText(ErrorLabels.FILE_NOT_FOUND.getLabel());
            throw new RuntimeException(e);
        }
    }
}
