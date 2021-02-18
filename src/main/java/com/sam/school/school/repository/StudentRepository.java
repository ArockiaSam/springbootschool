/**
 * 
 */
package com.sam.school.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sam.school.school.entity.Student;

/**
 * @author mds-pc
 *
 */
public interface StudentRepository extends JpaRepository<Student, Integer>{

}
