package controllers;

import entities.BookTableModel;
import entities.libraryItems.LibraryBookRecord;
import entities.search.SearchCriteria;
import entities.validation.ValidationIssue;
import entities.validation.ValidationResult;
import enums.*;
import managers.StatusBarManager;
import services.BookRecordValidator;
import services.BookSearchService;
import services.FileLoadResult;
import services.FileLoadService;
import util.AppLogger;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class FileChooseController {
    private final JFrame parentFrame;
    private final StatusBarManager statusBarManager;
    private final FileLoadService fileLoadService;
    private final AppLogger logger;
    private final BookTableModel bookTableModel;
    private final JTextArea detailTextArea;
    private final JTextArea rawTextArea;

    private final BookSearchService bookSearchService;

    public FileChooseController(
            JFrame parentFrame,
            StatusBarManager statusBarManager,
            FileLoadService fileLoadService,
            AppLogger logger,
            BookTableModel bookTableModel,
            JTextArea detailTextArea,
            JTextArea rawTextArea,
            BookSearchService bookSearchService) {
        this.parentFrame = parentFrame;
        this.statusBarManager = statusBarManager;
        this.fileLoadService = fileLoadService;
        this.logger = logger;
        this.bookTableModel = bookTableModel;
        this.detailTextArea = detailTextArea;
        this.rawTextArea = rawTextArea;
        this.bookSearchService = bookSearchService;
    }

    public void handleFileSelection() {
        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showOpenDialog(this.parentFrame) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            if (file != null) {
                this.logger.info(String.format(LogLabel.SELECT_FILE.getLabel(), fileChooser.getSelectedFile().getAbsolutePath()));
                this.loadFile(file);
            }
        }
    }

    public void handleCleanContent() {
        this.rawTextArea.setText("");
        this.detailTextArea.setText("");
        this.bookTableModel.clearRecords();
        this.fileLoadService.getManager().clearRecords();
        this.statusBarManager.setReady();
        this.logger.info(FrameLabel.CLEAN_CONTENT.getLabel());
    }

    private void loadFile(File file) {
        this.handleCleanContent();
        this.statusBarManager.setLoading(file.getName());
        this.logger.info(String.format(LogLabel.LOAD_FILE.getLabel(), file.getAbsolutePath()));

        try {
            FileLoadResult result = this.fileLoadService.loadFile(file);
            this.rawTextArea.setText(result.getContent());
            this.bookTableModel.setRecords(this.fileLoadService.getManager().getBookRecords());
            this.statusBarManager.setLoaded(file, result);
            this.logger.info(String.format(LogLabel.LOADED_FILE.getLabel(), file.getName()));
        } catch (FileNotFoundException e) {
            this.statusBarManager.setError(ErrorLabel.FILE_NOT_FOUND);
            this.logger.severe(ErrorLabel.FILE_NOT_FOUND.getLabel(), e);
            this.showErrorDialog(ErrorLabel.FILE_NOT_FOUND);
        } catch (IOException e) {
            this.statusBarManager.setError(ErrorLabel.IO_ERROR);
            this.logger.severe(ErrorLabel.IO_ERROR.getLabel(), e);
            this.showErrorDialog(ErrorLabel.IO_ERROR);
        }
    }

    public void handleTableSelection(int modelRow) {
        if (modelRow < 0) {
            this.detailTextArea.setText("");
            return;
        }

        LibraryBookRecord record = this.bookTableModel.getRecordAt(modelRow);
        this.detailTextArea.setText(record.toString());
        this.detailTextArea.setCaretPosition(0);
    }

    public void handleShowAll() {
        List<LibraryBookRecord> allRecords = this.fileLoadService.getManager().getBookRecords();

        if (allRecords.isEmpty()) {
            return;
        }

        this.bookTableModel.setRecords(allRecords);
        this.detailTextArea.setText("");
        // TODO: get the stat on showAll btn hit;
        //this.statusBarManager.setLoaded();
    }

    private void showErrorDialog(ErrorLabel errorLabel) {
        JOptionPane.showMessageDialog(
                this.parentFrame,
                errorLabel.getLabel(),
                ErrorLabel.ERROR.getLabel(),
                JOptionPane.ERROR_MESSAGE
        );
    }

    public void handleValidationReport() {
        List<LibraryBookRecord> records = this.fileLoadService.getManager().getBookRecords();
        BookRecordValidator validator = new BookRecordValidator();
        List<ValidationResult> issues = validator.validate(records);

        if (records.isEmpty()) {
            JOptionPane.showMessageDialog(this.parentFrame, FrameLabel.DIALOG_NO_RECORDS.getLabel(), FrameLabel.DIALOG_NO_VALIDATION.getLabel(), JOptionPane.WARNING_MESSAGE);
        } else if (issues.isEmpty()) {
            JOptionPane.showMessageDialog(this.parentFrame, FrameLabel.DIALOG_ALL_VALID.getLabel(), FrameLabel.DIALOG_VALIDATION_REPORT.getLabel(), JOptionPane.INFORMATION_MESSAGE);
        } else {
            this.showValidationDialog(issues, records.size());
        }
    }

    private void showValidationDialog(List<ValidationResult> results, int recordsChecked) {
        StringBuilder report = new StringBuilder();
        int errors = 0;
        int warnings = 0;

        for (ValidationResult result : results) {
            report.append("Book: ").append(result.getRecord().getTitle()).append(System.lineSeparator()).append("[ISBN:").append(result.getRecord().getIsbn()).append("]");
            for (ValidationIssue issue : result.getIssues()) {
                report.append(" ").append(issue).append(System.lineSeparator());
                if (issue.getSeverity() == Severity.ERROR) {
                    errors++;
                } else {
                    warnings++;
                }
            }
            report.append(System.lineSeparator());
        }

        String summary = String.format(FrameLabel.DIALOG_VALIDATION_SUMMARY.getLabel(), errors, warnings, recordsChecked);
        JTextArea jTextArea = new JTextArea(summary + report);
        jTextArea.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.setPreferredSize(new Dimension(500, 400));

        JOptionPane.showMessageDialog(parentFrame, jScrollPane, FrameLabel.DIALOG_VALIDATION_REPORT.getLabel(), JOptionPane.WARNING_MESSAGE);
    }

    public void handleSearch() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));

        JComboBox<SearchField> fieldJComboBox = new JComboBox<>(SearchField.values());
        JTextField queryField = new JTextField(20);
        JCheckBox caseSensitiveJCheckBox = new JCheckBox(FrameLabel.DIALOG_CASE_SENSITIVE.getLabel());

        panel.add(new JLabel(FrameLabel.DIALOG_FIELD.getLabel()));
        panel.add(fieldJComboBox);
        panel.add(new JLabel(FrameLabel.DIALOG_SEARCH_QUERY.getLabel()));
        panel.add(queryField);
        panel.add(new JLabel(""));
        panel.add(caseSensitiveJCheckBox);

        int result = JOptionPane.showConfirmDialog(this.parentFrame, panel, FrameLabel.DIALOG_SEARCH.getLabel(), JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION && !queryField.getText().isEmpty()) {
            SearchCriteria criteria = new SearchCriteria(
                    (SearchField) fieldJComboBox.getSelectedItem(),
                    queryField.getText(),
                    caseSensitiveJCheckBox.isSelected()
            );

            List<LibraryBookRecord> results = this.bookSearchService.searchBooks(
                    this.fileLoadService.getManager().getBookRecords(),
                    criteria
            );

            if (results.isEmpty()) {
                JOptionPane.showMessageDialog(this.parentFrame, FrameLabel.DIALOG_NO_BOOKS_FOUND.getLabel(), FrameLabel.DIALOG_SEARCH_RESULT.getLabel(), JOptionPane.INFORMATION_MESSAGE);
            } else {
                this.bookTableModel.setRecords(results);
                this.statusBarManager.setFound(results.size());
            }
        }
    }

    // TODO: add labels;
    private void showSearchResults(List<LibraryBookRecord> results) {
        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(parentFrame, "No records found.", "No search results", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder stringBuilder = new StringBuilder();

            for (LibraryBookRecord result : results) {
                stringBuilder.append(result).append(System.lineSeparator());
            }

            JTextArea jTextArea = new JTextArea(stringBuilder.toString());
            jTextArea.setEditable(false);
            JScrollPane jScrollPane = new JScrollPane(jTextArea);
            jScrollPane.setPreferredSize(new Dimension(500, 400));
            JLabel searchResultLabel = new JLabel(String.format("Search Result: %d book%s", results.size(), results.size() != 1 ? "s." : "."));
            searchResultLabel.setBorder(BorderFactory.createEtchedBorder());
            JPanel jPanel = new JPanel(new BorderLayout());
            jPanel.add(jScrollPane, BorderLayout.CENTER);
            jPanel.add(searchResultLabel, BorderLayout.SOUTH);

            JOptionPane.showMessageDialog(parentFrame, jPanel, "Search Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void handleFilterChange(int visible, int total) {
        if (total == 0) {
            return;
        }

        if (visible == total) {
            this.statusBarManager.setFound(total);
        } else {
            this.statusBarManager.setFiltered(visible, total);
        }
    }
}
