package managers;

import enums.ErrorLabel;
import enums.FrameLabel;
import services.FileLoadResult;

import javax.swing.*;
import java.io.File;

public class StatusBarManager {
    private static final int FILENAME_LENGTH = 15;
    private static final String ELLIPSIS = "... ";

    private final JLabel statusBar;

    public StatusBarManager(JLabel statusBar) {
        this.statusBar = statusBar;
    }

    public void setReady() {
        this.statusBar.setText(FrameLabel.READY.getLabel());
    }

    public void setLoading(String fileName) {
        this.statusBar.setText(FrameLabel.LOADING.getLabel() + fileName);
    }

    public void setLoaded(File file, FileLoadResult result) {
        long words = result.getContent().isBlank() ? 0 : result.getContent().split("\\s+").length;
        long chars = result.getContent().length();
        long bookRecordsLoaded = result.getLibraryBookRecordManager().getBookRecordsCount();

        this.statusBar.setText(
                String.format(FrameLabel.LOADED.getLabel(), this.trimFileName(file.getName()), file.length(), words, chars, bookRecordsLoaded)
        );
    }

    public void setError(ErrorLabel error) {
        this.statusBar.setText(error.getLabel());
    }

    private String trimFileName(String fileName) {
        if (fileName.length() > StatusBarManager.FILENAME_LENGTH) {
            return fileName.substring(0, StatusBarManager.FILENAME_LENGTH) + StatusBarManager.ELLIPSIS;
        }

        return fileName;
    }
}
