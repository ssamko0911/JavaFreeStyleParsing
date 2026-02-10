package managers;

import enums.ErrorLabel;
import enums.FrameLabel;
import services.FileLoadResult;

import javax.swing.*;
import java.io.File;

public class StatusBarManager {
    private static final String WHITESPACE_PATTERN = "\\s+";
    private static final int FILENAME_LENGTH = 15;
    // TODO: redundant with current layout, remove;
    private static final String ELLIPSIS = "... ";
    private static final String PLURAL_ITEMS_SENTENCE_END = "s.";
    private static final String SINGULAR_ITEM_SENTENCE_END = ".";

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
        String content = result.getContent();
        long words = (content == null || content.isBlank()) ? 0 : content.split(StatusBarManager.WHITESPACE_PATTERN).length;
        long chars = content == null ? 0 : content.length();
        long bookRecordsLoaded = result.getLibraryBookRecordManager().getBookRecordsCount();

        this.statusBar.setText(
                String.format(
                        FrameLabel.LOADED.getLabel(),
                        this.trimFileName(file.getName()),
                        file.length(),
                        words,
                        chars,
                        bookRecordsLoaded,
                        bookRecordsLoaded != 1 ? StatusBarManager.PLURAL_ITEMS_SENTENCE_END : StatusBarManager.SINGULAR_ITEM_SENTENCE_END
                )
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

    // TODO: add labels;
    public void setFound(int recordsFound) {
        this.statusBar.setText(String.format("Search Result: %d book%s", recordsFound, recordsFound != 1 ? "s." : "."));
    }

    // TODO: add labels;
    public void setFiltered(int filtered, int total) {
        this.statusBar.setText(String.format("Showing %d of %d books", filtered, total));
    }
}
