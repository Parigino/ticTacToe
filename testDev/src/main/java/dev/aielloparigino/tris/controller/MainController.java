package dev.aielloparigino.tris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.aielloparigino.tris.model.DBRepository;

@Controller
public class MainController {

	@Autowired
	private DBRepository dbRepository;

	@GetMapping("/games")
	public String loadGame(@RequestParam(name = "ID", required = false) String ID, Model model) {

		System.out.println("LOADING GAME for ID: " + ID);
		model.addAttribute("step", dbRepository.loadStep(ID));

		return "loadedGame.html";

	}
}
