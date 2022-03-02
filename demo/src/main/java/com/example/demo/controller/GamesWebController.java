package com.example.demo.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.model.Games;
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

import com.example.demo.service.GamesService;
//import com.example.demo.service.ShopService;

@Controller
public class GamesWebController {

	@Autowired
	private GamesService gamesService;

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

	@GetMapping("/")
	public String showGames(Model model) {

		model.addAttribute("games", gamesService.findAll());

		return "products";
	}

	@GetMapping("/single/{id}")
	public String showGame(Model model, @PathVariable long id) {

		Optional<Games> game = gamesService.findById(id);
		if (game.isPresent()) {
			model.addAttribute("games", game.get());
			return "single";
		} else {
			return "games";
		}

	}

	@GetMapping("/games/{id}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

		Optional<Games> game = gamesService.findById(id);
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

	/*@GetMapping("/newbook")
	public String newBook(Model model) {

		model.addAttribute("availableShops", shopService.findAll());

		return "newBookPage";
	}

	 */

	@PostMapping("/newgame")
	public String newBookProcess(Model model, Games games, MultipartFile imageField, @RequestParam List<Long> selectedShops) throws IOException {

		if (!imageField.isEmpty()) {
			games.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			games.setImage(true);
		}

		//games.setShops(shopService.findById(selectedShops));

		gamesService.save(games);

		model.addAttribute("gameId", games.getId());

		return "redirect:/Addgames/"+ games.getId();
	}

	@GetMapping("/edigames/{id}")
	public String editGames(Model model, @PathVariable long id) {

		Optional<Games> games = gamesService.findById(id);
		if (games.isPresent()) {
			model.addAttribute("games", games.get());
			return "single";
		} else {
			return "Products";
		}
	}

	@PostMapping("/editgames")
	public String editBookProcess(Model model, Games games, boolean removeImage, MultipartFile imageField)
			throws IOException, SQLException {

		updateImage(games, removeImage, imageField);

		gamesService.save(games);

		model.addAttribute("gameId", games.getId());

		return "redirect:/single/"+ games.getId();
	}

	private void updateImage(Games games, boolean removeImage, MultipartFile imageField) throws IOException, SQLException {
		
		if (!imageField.isEmpty()) {
			games.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			games.setImage(true);
		} else {
			if (removeImage) {
				games.setImageFile(null);
				games.setImage(false);
			} else {
				// Maintain the same image loading it before updating the book
				Games dbGames = gamesService.findById(games.getId()).orElseThrow();
				if (dbGames.getImage()) {
					games.setImageFile(BlobProxy.generateProxy(dbGames.getImageFile().getBinaryStream(),
							dbGames.getImageFile().length()));
					games.setImage(true);
				}
			}
		}
	}

}
