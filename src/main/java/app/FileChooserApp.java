package app;

import config.FileChooserAppConfig;
import controllers.FileChooseController;
import enums.AppConfig;
import enums.FrameLabel;
import enums.LogLabel;
import managers.LibraryBookRecordManager;
import managers.StatusBarManager;
import services.*;
import util.AppLogger;

import javax.swing.*;
import java.awt.*;

public class FileChooserApp extends JFrame {
    private static final int APP_WIDTH = FileChooserAppConfig.getInt(AppConfig.APP_WIDTH);
    private static final int APP_HEIGHT = FileChooserAppConfig.getInt(AppConfig.APP_HEIGHT);
    private static final int TEXT_AREA_ROWS = FileChooserAppConfig.getInt(AppConfig.APP_TEXT_AREA_ROWS);
    private static final int TEXT_AREA_COLUMNS = FileChooserAppConfig.getInt(AppConfig.APP_TEXT_AREA_COLS);
    private static final boolean TEXT_AREA_EDITABLE = FileChooserAppConfig.getBooleanProperty(AppConfig.APP_TEXT_AREA_EDITABLE);

    private JPanel contentPane;
    private JTextArea textArea;
    private JButton chooseFileButton;
    private JButton clearTextButton;
    private JButton validationReportButton;
    private JButton searchButton;
    private JScrollPane scrollPane;
    private JLabel statusBar;

    private FileChooseController fileChooseController;
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
        this.initController();
        this.layoutComponents();
        this.attachListeners();
    }

    private void initController() {
        StatusBarManager statusBarManager = new StatusBarManager(this.statusBar);
        FileLoadService fileLoadService = new FileLoadService(
                new LibraryBookRecordManager(),
                new LibraryBookRecordParser(
                        new AuthorParser(),
                        new PhysicalDescriptionParser(),
                        new PublisherParser(),
                        new PublicationYearParser(),
                        new TitleParser(),
                        new IsbnParser()
                )
        );
        BookSearchService bookSearchService = new BookSearchService();
        this.fileChooseController = new FileChooseController(
                this, statusBarManager, fileLoadService, logger, this.textArea, bookSearchService
        );
    }

    private void initComponents() {
        this.contentPane = new JPanel();
        this.chooseFileButton = new JButton(FrameLabel.CHOOSE_FILE.getLabel());
        this.clearTextButton = new JButton(FrameLabel.CLEAN_CONTENT.getLabel());
        this.validationReportButton = new JButton(FrameLabel.REPORT.getLabel());
        this.searchButton = new JButton(FrameLabel.SEARCH.getLabel());
        this.textArea = new JTextArea(FileChooserApp.TEXT_AREA_ROWS, FileChooserApp.TEXT_AREA_COLUMNS);
        this.textArea.setEditable(FileChooserApp.TEXT_AREA_EDITABLE);
        this.scrollPane = new JScrollPane(this.textArea);
        this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.statusBar = new JLabel(FrameLabel.READY.getLabel());
        this.statusBar.setBorder(BorderFactory.createEtchedBorder());
    }

    private void layoutComponents() {
        this.setResizable(false);
        this.contentPane.add(this.chooseFileButton);
        this.contentPane.add(this.clearTextButton);
        this.contentPane.add(this.validationReportButton);
        this.contentPane.add(this.searchButton);
        this.contentPane.add(this.scrollPane);
        this.setContentPane(this.contentPane);
        this.add(this.statusBar, BorderLayout.SOUTH);
    }

    private void attachListeners() {
        this.chooseFileButton.addActionListener(_ -> this.fileChooseController.handleFileSelection());
        this.clearTextButton.addActionListener(_ -> this.fileChooseController.handleCleanContent());
        this.validationReportButton.addActionListener(_ -> this.fileChooseController.handleValidationReport());
        this.searchButton.addActionListener(_ -> this.fileChooseController.handleSearch());
    }
}
