package com.zhang.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.zhang.api.web.StudentController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentControllerTest {
	/*@Autowired
    private StudentController stuController;
	 // 初始化
    @Before
    public void setUp() throws Exception {
        System.out.println("执行初始化");
    }
    // 测试
    @Test
    public void test() throws Exception {
    	List<Student> r = stuController.findAll();
    	System.out.println(r);
    	Assert.assertThat(r.get(0).getStudent_id(),is("1"));
    }
*/
	
	 private MockMvc mockMvc;
	 @Autowired
     private StudentController stuController;
	 
	 @Before
	 public void setUp() throws Exception{
		 System.out.println("数据初始化");
		 mockMvc = MockMvcBuilders.standaloneSetup(stuController).build();
	 }
	 
	 @Test
	 public void testFindAll() throws Exception{
		 RequestBuilder req = MockMvcRequestBuilders.get("/student/findAll")
				 .accept(MediaType.APPLICATION_JSON);
		 
		 ResultActions result = mockMvc.perform(req);
		 MvcResult mvcResult = result.andExpect(MockMvcResultMatchers.status().isOk())
		 		.andDo(MockMvcResultHandlers.print())
		 		.andReturn();
		 
		 String contentAsString = mvcResult.getResponse().getContentAsString();
		 System.out.println(contentAsString);
	 }
	 
	/* @Autowired
	 private TestRestTemplate testRestTemplate;
	 
	 @Test
	 public void testTmp() throws Exception{
		 Map<String, Integer> pMap = new HashMap<String, Integer>();
		 pMap.put("student_id", 1);
		 Student actRes = testRestTemplate.getForObject("/student/findById/{student_id}", Student.class, pMap);
		 
		 actRes.getNick_name();
		 System.out.println(actRes.getPhone());
	 }*/
	 
	 
}
