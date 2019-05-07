package com.zhang.api.dao;

import java.util.List;

import com.zhang.api.model.Student;

/**
 * @Description:一个接口，方法名称和
 * @author zoey
 * @date:2018年3月6日
 */
public interface StudentDao {
	List<Student> findAll();
	
	int insertStudent(Student student);
 
	Student findById(int i);
 
	int updateStudent(Student student);
 
	int deleteStudent(int i);
 
	int delteStudent2(Student student);

}
