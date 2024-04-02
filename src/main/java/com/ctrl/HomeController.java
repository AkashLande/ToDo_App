package com.ctrl;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.TodoDao;
import com.entity.Todo;

@Controller
public class HomeController {

	@Autowired
	ServletContext context;

	@Autowired
	TodoDao todoDao;

	@RequestMapping("/home")
	public String home(Model m) {
		String str = "Home";
		m.addAttribute("page", str);
		List<Todo> l = this.todoDao.getAll();
		m.addAttribute("todos", l);
		return "home";
	}

	@RequestMapping("/add")
	public String addToDo(Model m) {
		Todo t = new Todo();
		m.addAttribute("page", "add");
		m.addAttribute("todo", t);
		return "home";
	}

	@RequestMapping(value = "/saveTodo", method = RequestMethod.POST)
	public String saveTodo(@ModelAttribute("todo") Todo t, Model m) {
		System.out.println(t);
		t.setTodoDate(new Date());
		this.todoDao.save(t);
		m.addAttribute("msg", "Added Successfully");
		return "home";
	}
}
