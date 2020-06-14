package com.uca.capas.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Estudiante;
import com.uca.capas.service.EstudianteService;

@Controller
public class MainController {
	
	@Autowired
	EstudianteService estudianteService;
	
	@RequestMapping("/inicio")
	public ModelAndView initMain() {
		ModelAndView mav = new ModelAndView();
		Estudiante estudiante = new Estudiante();
		mav.addObject("estudiante",estudiante);
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/listado")
	public ModelAndView showList() {
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		
		try {
			estudiantes = estudianteService.findAll();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("estudiantes",estudiantes);
		mav.addObject("student", new Estudiante());
		mav.setViewName("listado");
		return mav;
	}	
	
	@RequestMapping("/filtrar")
	public ModelAndView filtro(@RequestParam(value = "nombre") String cadena) {
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		
		try {
			estudiantes = estudianteService.filtrarPor(cadena);
		}catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("student", new Estudiante());
		mav.addObject("estudiantes",estudiantes);
		mav.setViewName("listado");
		return mav;
	}	
	
	@RequestMapping("/delete")
	public ModelAndView showNewList(@RequestParam(value = "codigo") int id) {
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		estudianteService.delete(id);
		try {
			estudiantes = estudianteService.findAll();
		}catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("student", new Estudiante());
		mav.addObject("estudiantes",estudiantes);
		mav.setViewName("listado");
		return mav;
	}
	
	@RequestMapping("/editar")
	public ModelAndView editarEstudiante(@RequestParam(value = "code") int id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("estudiante", estudianteService.findOne(id));
		mav.setViewName("estudiante");
		return mav;
	}
	
	@RequestMapping("/guardar")
	public ModelAndView formEstudiante(@Valid @ModelAttribute Estudiante estudiante, BindingResult result) {
		ModelAndView mav  = new ModelAndView();
		if(result.hasErrors()) {
			mav.setViewName("index");
		}
		else{
			estudianteService.save(estudiante);
			Estudiante student = new Estudiante();
			mav.addObject("estudiante", student);
			mav.setViewName("index");
		}
		
		return mav;
	}
	
	@RequestMapping("/editado")
	public ModelAndView editEstudiante(@Valid @ModelAttribute Estudiante estudiante, BindingResult result) {
		ModelAndView mav  = new ModelAndView();
		List<Estudiante> studiantes = null;
		try {
			studiantes = estudianteService.findAll();
		}catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("estudiantes",studiantes);
		if(result.hasErrors()) {
			mav.setViewName("estudiante");
		}
		else{
			estudianteService.save(estudiante);
			mav.setViewName("listado");
		}
		
		return mav;
	}
}
