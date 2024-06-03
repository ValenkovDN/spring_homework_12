package ru.gbhw.notes.observer;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NoteCreateListener implements ApplicationListener<NoteCreateEvent> {
    //Получу заметку которая отправляется на добавление или изменение
    //Просто отображу ее в консоли
    @Override
    public void onApplicationEvent(NoteCreateEvent event) {
        System.out.println(event.getMessage() + ": " + event.getNote().toString());
    }
}
