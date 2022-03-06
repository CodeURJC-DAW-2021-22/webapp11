package com.example.demo.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.model.Game;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.GameService;
//import com.example.demo.service.ShopService;

@Controller
public class GameWebController {

	@Autowired
	private GameService gamesService;

	//@Autowired
	//private ShopService shopService;

	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {

			model.addAttribute("logged", true);
			model.addAttribute("userName", principal.getName());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));

		} else {
			model.addAttribute("logged", false);
		}
	}

	@GetMapping("/games")
	public String showGames(Model model) {

		model.addAttribute("games", gamesService.findAll());

		return "games";
	}

	@GetMapping("/index")
	public String Index(Model model) {

		//model.addAttribute("ga", gamesService.findAll());

		return "index";
	}
	@GetMapping("/Usuario")
	public String User(Model model) {

		//model.addAttribute("ga", gamesService.findAll());

		return "Usuario";
	}

	@GetMapping("/game/{id}")
	public String showGame(Model model, @PathVariable long id) {

		Optional<Game> game = gamesService.findById(id);
		if (game.isPresent()) {
			model.addAttribute("game", game.get());
			return "game";
		} else {
			return "games";
		}

	}

	@GetMapping("/games/{id}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

		Optional<Game> game = gamesService.findById(id);
		if (game.isPresent() && game.get().getImageFile() != null) {

			Resource file = new InputStreamResource(game.get().getImageFile().getBinaryStream());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(game.get().getImageFile().length()).body(file);

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/*@GetMapping("/removebook/{id}")
	public String removeBook(Model model, @PathVariable long id) {

		Optional<Games> book = gamesService.findById(id);
		if (book.isPresent()) {
			gamesService.delete(id);
			model.addAttribute("book", book.get());
		}
		return "removedbook";
	}

	 */

	@GetMapping("/Addgames")
	public String newgame(Model model) {

		//model.addAttribute("availableShops", shopService.findAll());

		return "Addgames";
	}


	/*
	@PostMapping("/Addgames")
	public String newGameProcess(Model model, Game game, MultipartFile imageField, @RequestParam List<Long> selectedShops) throws IOException {

		if (!imageField.isEmpty()) {
			game.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			game.setImage(true);
		}

		//games.setShops(shopService.findById(selectedShops));

		gamesService.save(game);

		model.addAttribute("gameId", game.getId());

		return "redirect:/games/"+ game.getId();
	}

	 */

	/*@GetMapping("/game.html/{id}")
	public String showGames(Model model, @PathVariable long id) {

		Optional<Games> games = gamesService.findById(id);
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

		gamesService.save(game);

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
				Game dbGame = gamesService.findById(game.getId()).orElseThrow();
				if (dbGame.getImage()) {
					game.setImageFile(BlobProxy.generateProxy(dbGame.getImageFile().getBinaryStream(),
							dbGame.getImageFile().length()));
					game.setImage(true);
				}
			}
		}
	}

}
