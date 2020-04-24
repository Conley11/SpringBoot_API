package com.zhang.api.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zhang.api.model.Student;
import com.zhang.api.service.StudentService;
import com.zhang.api.utils.ApiResult;
import com.zhang.api.utils.ResultUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * @Description:Controller层，调用Service层的方法，返回执行结果
 * @author czhang
 * @date:2019年7月1日
 */
@RestController
@RequestMapping("/student")
@Api("swagger3 ui 注释 api 级别")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	/**
	 * @Description:查询所有的学生信息
	 * 访问地址：http://localhost:8080/student/findAll
	 * @return 返回字符串信息，直接显示在页面
	 * @author czhang
	 * @date:2019年7月1日
	 */
	@RequestMapping(path="/findAll",method=RequestMethod.GET)
	@ResponseBody //使得返回结果为字符串，而不是进入某一个指定的页面
	@ApiOperation("swagger ui 注释 方法级别")
	public List<Student> findAll() {
		System.out.println("进入查询方法");
		List<Student> list = studentService.findAll();
		for(int i=0;i<list.size();i++) {
//			System.out.println("学生:"+list.get(i));
		}
		return list;
	}
	/**
	 * @Description:根据学生id查询学生信息(使用@PathVariable注解接收传入的参数)
	 * 访问地址：http://localhost:8080/student/findById/3
	 * @return
	 * @author:czhang
	 * @date:2019年7月1日
	 */
	@RequestMapping(value="/findById/{student_id}",method=RequestMethod.GET)
	@ResponseBody
	public Student findById(@PathVariable("student_id") Integer student_id) {
		System.out.println("进入根据id查询学生信息方法");
		Student student = studentService.findById(student_id);
		System.out.println(student);
		return student;
	}
	/**
	 * @Description:根据学生id查询学生信息(使用@RequestParam注解接收传入的参数)
	 * 访问地址：http://localhost:8080/student/findById?student_id=3
	 * @param student_id
	 * @return
	 * @author:czhang
	 * @date:2019年7月1日
	 */
	@RequestMapping(value="/findById",method=RequestMethod.GET)
	@ResponseBody
	public String findById2(@RequestParam("student_id") Integer student_id) {
		System.out.println("进入根据id查询学生信息方法");
		Student student = studentService.findById(student_id);
		System.out.println(student);
		return "findById";
	}
	/**
	 * @Description:新增学生信息
	 * 访问地址：http://localhost:8080/student/insertStudent(数据写死)
	 * 		 http://localhost:8080/student/insertStudent?sname=czhang&sage=18(传递参数)
	 * @return 返回字符串信息，直接显示在页面
	 * @author:czhang
	 * @date:2019年7月1日
	 */
	@RequestMapping(value="/insertStudent",method=RequestMethod.POST)
	@ResponseBody
	public String insertStudent(Student student) {
		System.out.println("进入新增方法");
		//Student student = new Student("lan",20);//数据写死
		int result = studentService.insertStudent(student);
		if(result==1) {
			System.out.println("新增学生成功!");
		}else {
			System.out.println("新增学生失败!");
		}
		return "insert";
	}
	/**
	 * @Description:修改学生信息
	 * 访问地址：http://localhost:8080/student/updateStudent?student_id=1&sname=change
	 * @param student
	 * @return
	 * @author:czhang
	 * @date:2019年7月1日
	 */
	@RequestMapping(value="/updateStudent",method=RequestMethod.POST)
	@ResponseBody
	public ApiResult updateStudent(@RequestBody Student student) {
		System.out.println("进入更新方法");
		int result = studentService.updateStudent(student);
		if(result==1) {
			System.out.println("修改学生成功!");
		}else {
			System.out.println("修改学生失败!");
		}
		return ResultUtil.success();
	}
	/**
	 * @Description:删除学生信息(数据写死)
	 * 访问地址：http://localhost:8080/student/deleteStudent
	 * 		 http://localhost:8080/student/deleteStudent?student_id=2(报错)
	 * 报错如下：
	 * 	Optional int parameter 'i' is present but cannot be translated into a null value 
	 * 	due to being declared as a primitive type.
	 * 	Consider declaring it as object wrapper for the corresponding primitive type.
	 * @return
	 * @author:czhang
	 * @date:2019年7月1日
	 */
	@RequestMapping(value="/deleteById",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteById() {
		System.out.println("进入删除方法");
		int result = studentService.deleteStudent(1);//传入的参数是一个整型，不能从地址栏的参数中传递过去
		if(result==1) {
			System.out.println("删除学生成功!");
		}else {
			System.out.println("删除学生失败！");
		}
		return "delete";
	}
	/**
	 * @Description:根据学生id删除学生信息
	 * 访问地址：http://localhost:8080/student/deleteStudent?student_id=2
	 * @param student
	 * @return
	 * @author:czhang
	 * @date:2019年7月1日
	 */
	@RequestMapping(value="/deleteStudent",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteStudent(Student student) {
		int result = studentService.deleteStudent2(student);//传递的参数是一个对象，可以从地址栏的参数中传递过去
		if(result==1) {
			System.out.println("删除学生成功!");
		}else {
			System.out.println("删除学生失败！");
		}
		return "delete2";
	}
}
