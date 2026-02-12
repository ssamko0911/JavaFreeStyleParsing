package app;

import config.FileChooserAppConfig;
import controllers.FileChooseController;
import entities.BookTableModel;
import enums.AppConfig;
import enums.FrameLabel;
import enums.LogLabel;
import managers.bookRecordManager.impl.LibraryBookRecordManager;
import managers.StatusBarManager;
import managers.bookRecordManager.impl.LibraryBookRecordManagerMapBased;
import services.*;
import services.parsers.*;
import util.AppLogger;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;
import java.util.TreeSet;

public class FileChooserApp extends JFrame {
    private static final int APP_WIDTH = FileChooserAppConfig.getInt(AppConfig.APP_WIDTH);
    private static final int APP_HEIGHT = FileChooserAppConfig.getInt(AppConfig.APP_HEIGHT);
    private static final boolean TEXT_AREA_EDITABLE = FileChooserAppConfig.getBooleanProperty(AppConfig.APP_TEXT_AREA_EDITABLE);

    private JPanel contentPane;
    private JToolBar toolBar;
    private JButton chooseFileButton;
    private JButton clearTextButton;
    private JButton validationReportButton;
    private JButton searchButton;
    private JButton showAllButton;
    private JButton findBuOclcButton;
    private JButton reportOclcByGenreButton;
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
        this.getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.decode("#81C784"));
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
                //new LibraryBookRecordManager(),
                new LibraryBookRecordManagerMapBased(),
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

        if (fileLoadService.getManager().supportsLookupByKey()) {
            this.findBuOclcButton = new JButton("Find by OCLC");
            this.findBuOclcButton.addActionListener(_ -> this.fileChooseController.handleFindByOclc());
        }

        if (fileLoadService.getManager().supportsReportByGenre()) {
            this.reportOclcByGenreButton = new JButton("Report by OCLC");
            this.reportOclcByGenreButton.addActionListener(_ -> {});
        }
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
        this.bookTable.setForeground(Color.decode("#90EE90"));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        this.bookTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        this.detailTextArea = new JTextArea();
        this.detailTextArea.setForeground(Color.decode("#90EE90"));
        this.detailTextArea.setEditable(FileChooserApp.TEXT_AREA_EDITABLE);

        this.rawTextArea = new JTextArea();
        this.rawTextArea.setEditable(FileChooserApp.TEXT_AREA_EDITABLE);
        this.rawTextArea.setForeground(Color.decode("#90EE90"));

        this.detailTabbedPane = new JTabbedPane();

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

        if (this.findBuOclcButton != null) {
            this.toolBar.add(this.findBuOclcButton);
        }

        if (this.reportOclcByGenreButton != null) {
            this.toolBar.add(this.reportOclcByGenreButton);
        }

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

        this.bookTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int col = bookTable.columnAtPoint(e.getPoint());
                    showColumnFilterPopUp(col, e.getX(), e.getY());
                }
            }
        });

        this.bookTable.getRowSorter().addRowSorterListener(_ -> {
            int visible = this.bookTable.getRowCount();
            int total = this.tableModel.getRowCount();
            this.fileChooseController.handleFilterChange(visible, total);
        });
    }


    private void showColumnFilterPopUp(int colIndex, int x, int y) {
        TableRowSorter<?> sorter = (TableRowSorter<?>) this.bookTable.getRowSorter();
        JPopupMenu popup = new JPopupMenu();

        Set<String> values = new TreeSet<>();

        for (int i = 0; i < this.bookTable.getRowCount(); i++) {
            Object value = this.bookTable.getValueAt(i, colIndex);
            if (value != null) {
                values.add(value.toString());
            }
        }

        JMenuItem showAllMenuItem = new JMenuItem("Show All");
        showAllMenuItem.addActionListener(_ -> {
            sorter.setRowFilter(null);
        });
        popup.add(showAllMenuItem);
        popup.addSeparator();

        for (String value : values) {
            JMenuItem item = new JMenuItem(value);
            item.addActionListener(_ -> {
                sorter.setRowFilter(RowFilter.regexFilter(
                        "^" + java.util.regex.Pattern.quote(value) + "$", colIndex
                ));
            });
            popup.add(item);
        }

        popup.show(this.bookTable.getTableHeader(), x, y);
    }
}
