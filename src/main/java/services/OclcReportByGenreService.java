package services;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class OclcReportByGenreService {
    public JTable generateReport(Map<String, Vector<String>> genreMap) {
        DefaultTableModel tableModel = new DefaultTableModel(
                this.generateReportRaw(genreMap),
                this.generateReportHeader()
        );

        return new JTable(tableModel);
    }

    private Vector<String> generateReportHeader() {
        return new Vector<>(List.of("Genre", "OCLC Numbers", "Count"));
    }

    private Vector<Vector<Object>> generateReportRaw(Map<String, Vector<String>> genreMap) {
        Vector<Vector<Object>> rows = new Vector<>();

        for (Map.Entry<String, Vector<String>> entry : genreMap.entrySet()) {
            Vector<Object> row = new Vector<>();

            row.add(entry.getKey());
            row.add(String.join(",", entry.getValue()));
            row.add(entry.getValue().size());
            rows.add(row);
        }

        return rows;
    }
}
