package com.zhang.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhang.api.dao.StudentDao;
import com.zhang.api.model.Student;

/**
 * @Description:Service层，调用mapper层的方法
 * @author czhang
 * @date:2018年3月6日
 */
@Service(value="studentService")
public class StudentService {
	@Autowired
	private StudentDao studentDao;
	
	public List<Student> findAll(){
		return studentDao.findAll();
	}
	
	public int insertStudent(Student student) {
		return studentDao.insertStudent(student);
	}
 
	public Student findById(int i) {
		return studentDao.findById(i);
	}
 
	public int updateStudent(Student student) {
		return studentDao.updateStudent(student);
	}
 
	public int deleteStudent(int i) {
		return studentDao.deleteStudent(i);
	}
 
	public int deleteStudent2(Student student) {
		return studentDao.delteStudent2(student);
	}
}
