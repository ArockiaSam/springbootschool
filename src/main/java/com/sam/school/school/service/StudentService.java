/**
 * 
 */
package com.sam.school.school.service;

import java.util.List;

import com.sam.school.school.entity.Student;

/**
 * @author mds-pc
 *
 */
public interface StudentService {
	
//	Student add(Student student);
	
	Student update(Student student);

	List<Student> getList();
	
	void deleteById(Integer id);
	
	Student getById(Integer id);
}
