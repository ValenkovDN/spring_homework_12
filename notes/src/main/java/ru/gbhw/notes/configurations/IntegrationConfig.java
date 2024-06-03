package ru.gbhw.notes.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;
import ru.gbhw.notes.model.Note;

import java.io.File;

@Configuration
public class IntegrationConfig {
    @Bean
    public MessageChannel inputChanel() {
        return new DirectChannel();
    }
    @Bean
    public MessageChannel fileWriteChanel() {
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "inputChanel", outputChannel = "fileWriteChanel")
    public GenericTransformer<Note, String> mainTransformer() {
        return note -> {
            StringBuilder sb = new StringBuilder();
            sb.append("note_id: ").append(note.getId()).append("\ntitle: ").append(note.getTitle())
                    .append("\ncreation_at: ").append(note.getCreationDate()).append("\n\n")
                    .append(note.getContent()).append("\n");
            return sb.toString();
        };
    }

    @Bean
    @ServiceActivator(inputChannel = "fileWriteChanel")
    public FileWritingMessageHandler messageHandler() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("./notes"));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        handler.setAutoCreateDirectory(true);
        handler.setCharset("UTF-8");
        return handler;
    }
}
