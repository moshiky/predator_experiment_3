
package problem.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.FileHandler;

/**
 * Created by Dev on 19/04/2017.
 */
public class Logger {

    private String m_infoLogFilePath;

    public Logger(String infoLogFilePath) {
        try {
            this.m_infoLogFilePath = infoLogFilePath;

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
        try {
            Files.write(Paths.get(this.m_infoLogFilePath), (message + '\n').getBytes(), StandardOpenOption.APPEND);
            if (shouldPrint) {
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
