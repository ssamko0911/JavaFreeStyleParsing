package app;

import config.FileChooserAppConfig;
import controllers.FileChooseController;
import entities.BookTableModel;
import enums.AppConfig;
import enums.FrameLabel;
import enums.LogLabel;
import managers.LibraryBookRecordManager;
import managers.StatusBarManager;
import services.*;
import services.parsers.*;
import util.AppLogger;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class FileChooserApp extends JFrame {
    private static final int APP_WIDTH = FileChooserAppConfig.getInt(AppConfig.APP_WIDTH);
    private static final int APP_HEIGHT = FileChooserAppConfig.getInt(AppConfig.APP_HEIGHT);
    //    private static final int TEXT_AREA_ROWS = FileChooserAppConfig.getInt(AppConfig.APP_TEXT_AREA_ROWS);
//    private static final int TEXT_AREA_COLUMNS = FileChooserAppConfig.getInt(AppConfig.APP_TEXT_AREA_COLS);
    private static final boolean TEXT_AREA_EDITABLE = FileChooserAppConfig.getBooleanProperty(AppConfig.APP_TEXT_AREA_EDITABLE);

    private JPanel contentPane;
    private JToolBar toolBar;
    //private JTextArea textArea;
    private JButton chooseFileButton;
    private JButton clearTextButton;
    private JButton validationReportButton;
    private JButton searchButton;
    private JButton showAllButton;
    private JTable bookTable;
    private BookTableModel tableModel;
    private JTabbedPane detailTabbedPane;
    private JTextArea detailTextArea;
    private JTextArea rawTextArea;
    private JSplitPane splitPane;
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
                this,
                statusBarManager,
                fileLoadService,
                logger,
                this.tableModel,
                this.detailTextArea,
                this.rawTextArea,
                bookSearchService
        );
    }

    private void initComponents() {
        this.contentPane = new JPanel(new BorderLayout());

        this.toolBar = new JToolBar();
        this.toolBar.setFloatable(false);

        this.chooseFileButton = new JButton(FrameLabel.CHOOSE_FILE.getLabel());
        this.clearTextButton = new JButton(FrameLabel.CLEAN_CONTENT.getLabel());
        this.validationReportButton = new JButton(FrameLabel.REPORT.getLabel());
        this.searchButton = new JButton(FrameLabel.SEARCH.getLabel());
        this.showAllButton = new JButton(FrameLabel.SHOW_ALL.getLabel());

        this.tableModel = new BookTableModel();
        this.bookTable = new JTable(this.tableModel);
        this.bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.bookTable.setAutoCreateRowSorter(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        this.bookTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        this.detailTextArea = new JTextArea();
        this.detailTextArea.setEditable(FileChooserApp.TEXT_AREA_EDITABLE);

        this.rawTextArea = new JTextArea();
        this.rawTextArea.setEditable(FileChooserApp.TEXT_AREA_EDITABLE);

        this.detailTabbedPane = new JTabbedPane();

        //TODO: update statusbar on search;
        this.statusBar = new JLabel(FrameLabel.READY.getLabel());
        this.statusBar.setBorder(BorderFactory.createEtchedBorder());
    }

    private void layoutComponents() {
        this.setResizable(true);
        this.setMinimumSize(new Dimension(700, 450));

        this.toolBar.add(this.chooseFileButton);
        this.toolBar.add(this.clearTextButton);
        this.toolBar.add(this.searchButton);
        this.toolBar.add(this.showAllButton);
        this.toolBar.addSeparator();
        this.toolBar.add(this.validationReportButton);
//        this.setContentPane(this.contentPane);
//        this.add(this.statusBar, BorderLayout.SOUTH);

        JScrollPane tableScrollPane = new JScrollPane(this.bookTable);
        JScrollPane detailScrollPane = new JScrollPane(this.detailTextArea);
        JScrollPane rawScrollPane = new JScrollPane(this.rawTextArea);
        this.detailTabbedPane.addTab("Record Detail", detailScrollPane);
        this.detailTabbedPane.addTab("Raw Detail", rawScrollPane);

        this.splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tableScrollPane, this.detailTabbedPane);
        this.splitPane.setDividerLocation(450);
        this.splitPane.setResizeWeight(0.5);

        this.contentPane.add(this.toolBar, BorderLayout.NORTH);
        this.contentPane.add(this.splitPane, BorderLayout.CENTER);
        this.contentPane.add(this.statusBar, BorderLayout.SOUTH);
        this.setContentPane(this.contentPane);
    }

    private void attachListeners() {
        this.chooseFileButton.addActionListener(_ -> this.fileChooseController.handleFileSelection());
        this.clearTextButton.addActionListener(_ -> this.fileChooseController.handleCleanContent());
        this.validationReportButton.addActionListener(_ -> this.fileChooseController.handleValidationReport());
        this.searchButton.addActionListener(_ -> this.fileChooseController.handleSearch());
        this.showAllButton.addActionListener(_ -> this.fileChooseController.handleShowAll());

        this.bookTable.getSelectionModel().addListSelectionListener(e -> {
           if (!e.getValueIsAdjusting()) {
               int viewRow = this.bookTable.getSelectedRow();
               int modelRow = viewRow >= 0 ? this.bookTable.convertRowIndexToModel(viewRow) : -1;
               this.fileChooseController.handleTableSelection(modelRow);
           }
        });
    }
}
