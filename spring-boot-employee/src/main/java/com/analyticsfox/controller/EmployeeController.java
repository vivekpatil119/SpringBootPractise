package com.analyticsfox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.analyticsfox.dao.EmployeeDao;
import com.analyticsfox.model.Employee;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
	@Autowired
	private EmployeeDao empdao;
/*
	@PostMapping("/addemp")
	public String adddemp(@RequestBody Employee employee) {
		empdao.save(employee);
		return "Employee Added Successfully";
	}
*/
	@PostMapping("/addemp")
	public ModelAndView save(@ModelAttribute("empForm") Employee employee) {
		empdao.save(employee);
		return new ModelAndView("redirect:/emp/getemplist");
	}

	/*
	 * 
	 * @GetMapping("/getemplist") public List<Employee> getemplist() { return
	 * (List<Employee>) empdao.findAll(); }
	 * 
	 * @RequestMapping(value = "/deleteemp/{id}", method = RequestMethod.GET) public
	 * String deleteemp(@PathVariable("id") int id) { empdao.delete(id); return
	 * "Employee deleted successfully"; }
	 */

	@RequestMapping(value = "/getemplist", method = RequestMethod.GET)
	public ModelAndView getAllEmployees() {
		ModelAndView model = new ModelAndView("emp_list");
		List<Employee> list = (List<Employee>) empdao.findAll();
		model.addObject("employeeList", list);
		return model;
	}

	@RequestMapping(value = "/deleteemp/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("id") int id) {
		empdao.delete(id);
		return new ModelAndView("redirect:/emp/getemplist");
	}

	@RequestMapping(value = "/viewemp/", method = RequestMethod.GET)
	public ModelAndView addArticle() {
		ModelAndView model = new ModelAndView();
		Employee emp = new Employee();
		model.addObject("empForm", emp);
		model.setViewName("emp_form");
		return model;
	}

	@RequestMapping(value = "/updateEmployee/{id}", method = RequestMethod.GET)
	public ModelAndView updateEmployee(@PathVariable int id) {
		ModelAndView model = new ModelAndView();
		Employee emp = empdao.findOne(id);
		model.addObject("empForm", emp);
		model.setViewName("emp_form");
		return model;
	}
	/*
	 * @RequestMapping(value = "/getemp/{id}", method = RequestMethod.GET) public
	 * Employee findEmployeeById(@PathVariable("id") int id) { Employee emp =
	 * empdao.findOne(id); return emp; }
	 */
}
