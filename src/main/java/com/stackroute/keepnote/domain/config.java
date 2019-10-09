package com.stackroute.keepnote.domain;

import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.repository.NoteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
    @Bean
    public Note getNote(){
        return new Note();
    }
    @Bean
    public NoteRepository getNoteRepository(){
        return new NoteRepository();
    }

}
