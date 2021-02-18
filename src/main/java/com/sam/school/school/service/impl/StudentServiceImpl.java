/**
 * 
 */
package com.sam.school.school.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sam.school.school.entity.Student;
import com.sam.school.school.repository.StudentRepository;
import com.sam.school.school.service.StudentService;

/**
 * @author mds-pc
 *
 */
@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public Student update(Student student) {
		student.setUpdatedTime(new Date());
		return studentRepository.save(student);
	}

	@Override
	public List<Student> getList() {
		return studentRepository.findAll();
	}

	@Override
	public void deleteById(Integer id) {
		studentRepository.deleteById(id);
	}

	@Override
	public Student getById(Integer id) {
		return studentRepository.findById(id).get();
	}

}
