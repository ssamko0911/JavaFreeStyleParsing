package managers;

import enums.ErrorLabel;
import enums.FrameLabel;

import javax.swing.*;
import java.io.File;

public class StatusBarManager {
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

    public void setLoaded(File file) {
        this.statusBar.setText(String.format(FrameLabel.LOADED.getLabel(), file.getName(), file.length()));
    }

    public void setError(ErrorLabel error) {
        this.statusBar.setText(error.getLabel());
    }
}
