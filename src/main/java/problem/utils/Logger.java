
package problem.utils;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;

/**
 * Created by moshecohen on 19/04/2017.
 */
public class Logger {

    private String m_infoLogFilePath;
    private JFrame m_curveDisplay;
    private LearningCurveDisplay m_learningCurveDisplay;

    public Logger(String infoLogFilePath) {
        try {
            this.m_infoLogFilePath = infoLogFilePath;
            this.m_curveDisplay = null;
            this.m_learningCurveDisplay = null;

            // create info file
            FileHandler loggingFile = new FileHandler(this.m_infoLogFilePath);
            loggingFile.close();

            this.info("Logger created");

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    public void info(String message) {
        this.info(message, true);
    }

    public void error(String message) {
        this.info("ERROR: " + message, true);
    }

    public void info(String message, boolean shouldPrint) {
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());
        String msg = String.format("[%s] >> %s", timeStamp, message);
        try {
            Files.write(Paths.get(this.m_infoLogFilePath), (msg + '\n').getBytes(), StandardOpenOption.APPEND);
            if (shouldPrint) {
                System.out.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initiateLearningCurveDisplay() {
        this.m_curveDisplay = new JFrame("Learning Curve Display");
        this.m_learningCurveDisplay = new LearningCurveDisplay();

        this.m_curveDisplay.add(this.m_learningCurveDisplay.getPanel());
        this.m_curveDisplay.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.m_curveDisplay.setSize(1400, 800);
        this.m_curveDisplay.setLocationRelativeTo(null);
        this.m_curveDisplay.setVisible(true);
    }

    public void addEpisodeResult(double result) {
        this.m_learningCurveDisplay.addSample(result);
    }

    public void setActiveSeries(String seriesName) {
        this.info(seriesName);
        this.m_learningCurveDisplay.setActiveSeries(seriesName);
    }

    public void addSeriesTime(long timeInSecs) {
        this.m_learningCurveDisplay.addSeriesTime(timeInSecs);
    }
}
