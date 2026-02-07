package controllers;

import entities.LibraryBookRecord;
import entities.ValidationIssue;
import entities.ValidationResult;
import enums.ErrorLabel;
import enums.FrameLabel;
import enums.LogLabel;
import enums.Severity;
import managers.StatusBarManager;
import services.BookRecordValidator;
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
    private final JTextArea textArea;

    public FileChooseController(JFrame parentFrame, StatusBarManager statusBarManager, FileLoadService fileLoadService, AppLogger logger, JTextArea textArea) {
        this.parentFrame = parentFrame;
        this.statusBarManager = statusBarManager;
        this.fileLoadService = fileLoadService;
        this.logger = logger;
        this.textArea = textArea;
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
        this.textArea.setText("");
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
            this.textArea.setText(result.getContent());
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
            JOptionPane.showMessageDialog(this.parentFrame, "No records to validate.", "No validation", JOptionPane.WARNING_MESSAGE);
        } else if (issues.isEmpty()) {
            JOptionPane.showMessageDialog(this.parentFrame, "All records are valid!", "Validation Report", JOptionPane.INFORMATION_MESSAGE);
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

        String summary = String.format("Found %d errors, %d warnings in %d records.\n\n", errors, warnings, recordsChecked);
        JTextArea jTextArea = new JTextArea(summary + report);
        textArea.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.setPreferredSize(new Dimension(500, 400));

        JOptionPane.showMessageDialog(parentFrame, jScrollPane, "Validation Report", JOptionPane.WARNING_MESSAGE);
    }
}
