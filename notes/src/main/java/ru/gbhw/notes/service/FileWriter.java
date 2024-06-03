package ru.gbhw.notes.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;
import ru.gbhw.notes.model.Note;

@MessagingGateway(defaultRequestChannel = "inputChanel")
public interface FileWriter {
    void writeToFile(@Header(FileHeaders.FILENAME) String filename, Note note);
}
