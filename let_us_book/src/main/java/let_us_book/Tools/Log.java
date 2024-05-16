package let_us_book.Tools;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

    private Logger logger = Logger.getLogger(Log.class.getName());

    public void setupLogger(String file) {
        try {
            String logDirectory = "logs";
            String filePath = logDirectory + File.separator + file;

            //check if the directory exists, if not, create it
            File directory = new File(logDirectory);
            if (!directory.exists()) {
                directory.mkdir();  // Create the directory if it doesn't exist
            }

            FileHandler fileHandler = new FileHandler(filePath, true);
            
            //set a SimpleFormatter to format the log messages
            fileHandler.setFormatter(new SimpleFormatter());
            
            //add handler to the logger
            logger.addHandler(fileHandler);
            
            //set to capture all logs
            logger.setLevel(java.util.logging.Level.ALL);
            
            //disable logging to console
            logger.setUseParentHandlers(false);

        } catch (IOException e) {
            logger.severe("Failed to initialize logger handler.");
        }
    }

    public Log(String file) {
        setupLogger(file);
    }
    
    public void info(String message) {
    	logger.info(message);
    }
    
    public void warning(String message) {
    	logger.warning(message);
    }
    
    public void severe(String message) {
    	logger.severe(message);
    }
}
