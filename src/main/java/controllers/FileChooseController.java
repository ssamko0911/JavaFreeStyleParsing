package controllers;

import enums.ErrorLabel;
import enums.FrameLabel;
import enums.LogLabel;
import managers.StatusBarManager;
import services.FileLoadResult;
import services.FileLoadService;
import util.AppLogger;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
                try {
                    this.loadFile(file);
                } catch (RuntimeException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void handleCleanContent() {
        this.textArea.setText("");
        this.statusBarManager.setReady();
        this.logger.info(FrameLabel.CLEAN_CONTENT.getLabel());
    }

    private void loadFile(File file) {
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
            this.showErrorDialog();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showErrorDialog() {
        JOptionPane.showMessageDialog(
                this.parentFrame,
                ErrorLabel.FILE_NOT_FOUND.getLabel(),
                ErrorLabel.ERROR.getLabel(),
                JOptionPane.ERROR_MESSAGE
        );
    }
}
