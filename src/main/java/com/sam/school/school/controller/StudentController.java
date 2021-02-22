/**
 * 
 */
package com.sam.school.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sam.school.school.entity.Student;
import com.sam.school.school.service.StudentService;


/**
 * @author mds-pc
 *
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@PostMapping("/upd")
	public ResponseEntity<?> upd(@RequestBody final Student student){
		
		Student returnStudent = studentService.update(student);
		if(student.getId() != null) 
			simpMessagingTemplate.convertAndSend("/topic/notifications", "Student detail updated -> "+student.getName());
		else
			simpMessagingTemplate.convertAndSend("/topic/notifications", "New Student added -> "+student.getName());
			
		return new ResponseEntity<Student>(returnStudent, HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> list(){
		return new ResponseEntity<List<Student>>(studentService.getList(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getForUpdate(@PathVariable("id") Integer id){
		return new ResponseEntity<Student>(studentService.getById(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/del/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id){
		studentService.deleteById(id);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@MessageMapping("/hello")
	@SendTo("/topic/message")
	public Object greeting(String message) throws Exception {
		System.out.println(message);
		Thread.sleep(1000); // simulated delay
		return "we received your message => " + message;
	}

	
}
