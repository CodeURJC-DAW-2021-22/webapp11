package com.example.demo.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.model.Game;
import com.example.demo.model.User;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.GameService;
import com.example.demo.service.UserService;

//import com.example.demo.service.ShopService;


@Controller
public class GameWebController {

	@Autowired
	private GameService gameService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;



	/*
	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {

			model.addAttribute("logged", true);
			model.addAttribute("id", request.getRequestedSessionId());
			model.addAttribute("userName", principal.getName());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));



		} else {
			model.addAttribute("logged", false);
		}
	}

	 */

	@GetMapping("/games")
	public String showGames(Model model) {

		model.addAttribute("games", gameService.findAll(0));
		model.addAttribute("currentPage", 0);
		return "games";
	}










	@GetMapping("/index")
	public String Index(Model model) {

		//model.addAttribute("ga", gameService.findAll());

		return "index";
	}
	@GetMapping("/Usuario/{id}")
	public String User(Model model) {

		//model.addAttribute("ga", gamesService.findAll());

		return "User";
	}

	@GetMapping("/games/{id}")
	public String showGame(Model model, @PathVariable long id) {

		Optional<Game> game = gameService.findById(id);
		if (game.isPresent()) {
			model.addAttribute("game", game.get());
			return "game";
		} else {
			return "games";
		}

	}

	@GetMapping("/games/{id}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

		Optional<Game> game = gameService.findById(id);
		if (game.isPresent() && game.get().getImageFile() != null) {

			Resource file = new InputStreamResource(game.get().getImageFile().getBinaryStream());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(game.get().getImageFile().length()).body(file);

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/addGames")
	public String addGames(Model model) {


		return "addGames";
	}

	@GetMapping("/newGame")
	public String newGame(Model model) {


		return "games";
	}

	@PostMapping("/newGame")
	public String newBookProcess(Model model, Game game, MultipartFile imageField) throws IOException {

		if (!imageField.isEmpty()) {
			game.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			game.setImage(true);
		}

		gameService.save(game);

		model.addAttribute("gameId", game.getId());

		return "redirect:/games/"+game.getId();
	}
/*
	@PostMapping("/updateUser")
	public String updateUserProcess(Model model, User user, HttpServletRequest request) throws IOException {


		Principal principal = request.getUserPrincipal();
		String sessionName = principal.getName();
		Optional<User> loggedUser = userService.findByName(sessionName);
		userService.findByName(sessionName).setPassword(user.getEncodedPassword());

		return "redirect:/Usuario/"+user.getId();
	}

 */

	 


	/*@GetMapping("/game.html/{id}")
	public String showGames(Model model, @PathVariable long id) {

		Optional<Games> games = gameService.findById(id);
		if (games.isPresent()) {
			model.addAttribute("games", games.get());
			return "single";
		} else {
			return "Products";
		}
	}*/


	/*

	@PostMapping("/editgames")
	public String editBookProcess(Model model, Game game, boolean removeImage, MultipartFile imageField)
			throws IOException, SQLException {

		updateImage(game, removeImage, imageField);

		gameService.save(game);

		model.addAttribute("gameId", game.getId());

		return "redirect:/single/"+ game.getId();
	}

	 */






	private void updateImage(Game game, boolean removeImage, MultipartFile imageField) throws IOException, SQLException {
		
		if (!imageField.isEmpty()) {
			game.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			game.setImage(true);
		} else {
			if (removeImage) {
				game.setImageFile(null);
				game.setImage(false);
			} else {
				// Maintain the same image loading it before updating the book
				Game dbGame = gameService.findById(game.getId()).orElseThrow();
				if (dbGame.getImage()) {
					game.setImageFile(BlobProxy.generateProxy(dbGame.getImageFile().getBinaryStream(),
							dbGame.getImageFile().length()));
					game.setImage(true);
				}
			}
		}
	}

}
