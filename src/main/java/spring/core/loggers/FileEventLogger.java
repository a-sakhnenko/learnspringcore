package spring.core.loggers;

import org.apache.commons.io.FileUtils;
import spring.core.beans.Event;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileEventLogger implements EventLogger {
    protected File file;
    private String fileName;

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString(), Charset.defaultCharset(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void init() throws IOException {
        file = FileUtils.getFile(fileName);
        if (file.exists() && !file.canWrite()) throw new IOException();
    }
}
