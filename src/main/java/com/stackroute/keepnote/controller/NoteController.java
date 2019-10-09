package com.stackroute.keepnote.controller;


/*Annotate the class with @Controller annotation. @Controller annotation is used to mark 
 * any POJO class as a controller so that Spring can recognize this class as a Controller
 * */

import com.stackroute.keepnote.domain.config;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.repository.NoteRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
public class NoteController {
	ApplicationContext factory=new AnnotationConfigApplicationContext(config.class);

	NoteRepository noteRepository = (NoteRepository) factory.getBean(NoteRepository.class);


	/*
	ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
	Note note = (Note) ctx.getBean("note");
	NoteRepository noteRepository = (NoteRepository) ctx.getBean("noteRepository");
	 * From the problem statement, we can understand that the application
	 * requires us to implement the following functionalities.
	 * 
	 * 1. display the list of existing notes from the collection. Each note 
	 *    should contain Note Id, title, content, status and created date.
	 * 2. Add a new note which should contain the note id, title, content and status.
	 * 3. Delete an existing note.
	 * 4. Update an existing note.
	 */
	
	/* 
	 * Get the application context from resources/beans.xml file using ClassPathXmlApplicationContext() class.
	 * Retrieve the Note object from the context.
	 * Retrieve the NoteRepository object from the context.
	 */
	
	
	/*Define a handler method to read the existing notes by calling the getAllNotes() method 
	 * of the NoteRepository class and add it to the ModelMap which is an implementation of Map 
	 * for use when building model data for use with views. it should map to the default URL i.e. "/" */
	@RequestMapping("/")
	public ModelAndView add(HttpServletRequest request){

	List<Note> noteList=noteRepository.getAllNotes();
	ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("index");

	  modelAndView.addObject("Value",noteList);
		return modelAndView;
	}
	
	/*Define a handler method which will read the Note data from request parameters and
	 * save the note by calling the addNote() method of NoteRepository class. Please note 
	 * that the createdAt field should always be auto populated with system time and should not be accepted 
	 * from the user. Also, after saving the note, it should show the same along with existing 
	 * notes. Hence, reading notes has to be done here again and the retrieved notes object 
	 * should be sent back to the view using ModelMap.
	 * This handler method should map to the URL "/saveNote". 
	*/
	@RequestMapping("/saveNote")
	public ModelAndView noteAdd(HttpServletRequest request){
		int noteId=Integer.parseInt(request.getParameter("noteId"));
		String noteTitle=request.getParameter("noteTittle");
		String noteContent=request.getParameter("noteContent");
		String noteStatus=request.getParameter("noteStatus");
		LocalDateTime localDateTime=java.time.LocalDateTime.now();
		Note note=(Note) factory.getBean(Note.class);
		note.setNoteId(noteId);
		note.setNoteTitle(noteTitle);
		note.setNoteContent(noteContent);
		note.setNoteStatus(noteStatus);
		note.setCreatedAt(localDateTime);
		noteRepository.addNote(note);

		List<Note> noteList=noteRepository.getAllNotes();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("index");

		modelAndView.addObject("Value",noteList);
		return modelAndView;
	}


	/* Define a handler method to delete an existing note by calling the deleteNote() method 
	 * of the NoteRepository class
	 * This handler method should map to the URL "/deleteNote" 
	*/
	@RequestMapping("/deleteNote")
	public ModelAndView deleteNote(HttpServletRequest request){
		int noteId=Integer.parseInt(request.getParameter("noteId"));

		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("index");
        if(noteRepository.deleteNote(noteId)==true){
			List<Note> noteList=noteRepository.getAllNotes();
			modelAndView.addObject("Value", noteList);
			return modelAndView;
		}

		else {
			List<Note> noteList=noteRepository.getAllNotes();
			modelAndView.addObject("Value",noteList);
			return modelAndView;
		}


	}

}