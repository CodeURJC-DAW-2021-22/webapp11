package com.example.demo.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.example.demo.model.Game;
import com.example.demo.model.Purchase;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.GameRepository;
//import com.example.demo.repository.ShopRepository;
import com.example.demo.repository.UserRepository;

@Service
public class DatabaseInitializer {

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private PurchaseService purchaseRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@PostConstruct
	public void init() throws IOException, URISyntaxException {

		// Sample games

		Game game1 = new Game("New Sper Mario Bros 2 3DS",

				"Los personajes que protagonizan este relato sobreviven en una sociedad en decadencia a la que, no obstante, lograrán devolver la posibilidad de un futuro. Año 2484. En un mundo dominado por las grandes corporaciones, solo un hombre, Jordi Thompson, detective privado deslenguado y vividor, pero de gran talento y sentido d...",
				35.00f,
				"clasic");

		setGameImage(game1, "/static/Game_images/3DS_New_Super_Mario_Bros_2.jpg");
		gameRepository.save(game1);

		Game game2 = new Game("New Super Mario Bros DS",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"clasic");

		setGameImage(game2, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game2);

		Game game3 = new Game("GBA Super Mario Bros 3",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"clasic");

		setGameImage(game3, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game3);

		Game game4 = new Game("NES Super Mario Bros",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"clasic");

		setGameImage(game4, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game4);

		Game game5 = new Game("SGG Sonic the Hedgehog",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"clasic");

		setGameImage(game5, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game5);

		Game game6 = new Game("SGG Sonic the Hedhog 2",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"clasic");

		setGameImage(game6, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game6);

		Game game7 = new Game("SMD Sonic the Hedgehog",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"clasic");

		setGameImage(game7, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game7);

		Game game8 = new Game("Switch New Super Mario Bros U Deluxe",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"clasic");

		setGameImage(game8, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game8);

		Game game9 = new Game("WII New Super Mario Bros",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"clasic");

		setGameImage(game9, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game9);

		Game game10 = new Game("Red Dead Redemption 2",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"survival");

		setGameImage(game10, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game10);

		Game game11 = new Game("Rust",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"survival");

		setGameImage(game11, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game11);

		Game game12 = new Game("Resident Evil 7: Biohazard",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"survival");

		setGameImage(game12, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game12);

		Game game13 = new Game("Until Dawn",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"survival");

		setGameImage(game13, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game13);

		Game game14 = new Game("Fifa 22",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"sports");

		setGameImage(game14, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game14);

		Game game15 = new Game("Fifa 21",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"sports");

		setGameImage(game15, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game15);

		Game game16 = new Game("2K22",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"sports");

		setGameImage(game16, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game16);

		Game game17 = new Game("2K21",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"sports");

		setGameImage(game17, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game17);

		Game game18 = new Game("Call of Duty: Modern Warfare 2",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"shooter");

		setGameImage(game18, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game18);

		Game game19 = new Game("Call of Duty: Modern Warfare 3",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"shooter");

		setGameImage(game19, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game19);

		Game game20 = new Game("Call of Duty: Black Ops",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"shooter");

		setGameImage(game20, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game20);

		Game game21 = new Game("Final Fantasy VII",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"rpg");

		setGameImage(game21, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game21);

		Game game22 = new Game("World of Warcraft",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"rpg");

		setGameImage(game22, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game22);

		Game game23 = new Game("Call of Duty: Black Ops 2",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"shooter");

		setGameImage(game23, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game23);

		Game game24 = new Game("Diablo II: Lord of Destrction",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"rpg");

		setGameImage(game24, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game24);

		Game game25 = new Game("Civilization VI",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"strategy");

		setGameImage(game25, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game25);

		Game game26 = new Game("XCOM 2",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"strategy");

		setGameImage(game26, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game26);

		Game game27 = new Game("Frost Punk",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"clasic");

		setGameImage(game27, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game27);

		Game game28 = new Game("Gear Tactics",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.",
				55.00f,
				"clasic");

		setGameImage(game28, "static/Game_images/DS New Super Mario Bros.jpg");
		gameRepository.save(game28);



		// Sample users

		User user1 = new User("user1", "Pepe","Pepón","pepepepon@gmail.com",333333333, passwordEncoder.encode("pass"), "USER");
		userRepository.save(user1);
		User user = new User("user", "Pepe","Pepón","pepepepon@gmail.com",222222222, passwordEncoder.encode("pass"), "USER");
		userRepository.save(user);
		userRepository.save(new User("admin","Pepe","Pérez","pepeperez@gmail.com",111111111, passwordEncoder.encode("adminpass"), "USER", "ADMIN"));




		List<Game> games1= new ArrayList<Game>();
		games1.add(game5);
		games1.add(game6);
		games1.add(game7);

		List<Game> games2= new ArrayList<Game>();
		games2.add(game2);
		games2.add(game1);
		games2.add(game6);
		games2.add(game15);

		List<Game> games3= new ArrayList<Game>();
		games3.add(game3);
		games3.add(game8);
		games3.add(game5);

		List<Game> games4= new ArrayList<Game>();
		games4.add(game24);
		games4.add(game22);
		games4.add(game14);

		List<Game> games5= new ArrayList<Game>();
		games5.add(game13);
		games5.add(game17);
		games5.add(game21);



		//Sample purchases
		ArrayList<Purchase> purchases = new ArrayList<Purchase>();

		purchases.add(new Purchase("Manu", "Martín", "Calle Tulipán SN",28934, "Móstoles", "España", 123456789, user1, games1, 3, 5, 2022));
		purchases.add(new Purchase("Marcos", "Aguirre", "Calle Tulipán SN",28934, "Móstoles", "España", 987654321, user, games2, 4, 7, 2022));
		purchases.add(new Purchase("Pepe", "Pepón", "Calle Tulipán SN",28934, "Móstoles", "España", 123456789, user1, games3, 12, 2, 2022));
		purchases.add(new Purchase("Pepe", "Pepón", "Calle Tulipán SN",28934, "Móstoles", "España", 987654321, user, games4, 8, 8, 2022));
		purchases.add(new Purchase("Manu", "Martín", "Calle Tulipán SN",28934, "Móstoles", "España", 123456789, user1, games1, 24, 11, 2022));
		purchases.add(new Purchase("Marcos", "Aguirre", "Calle Tulipán SN",28934, "Móstoles", "España", 987654321, user, games5, 7, 5, 2022));
		purchases.add(new Purchase("Manu", "Martín", "Calle Tulipán SN",28934, "Móstoles", "España", 123456789, user, games4, 24, 11, 2022));
		purchases.add(new Purchase("Marcos", "Aguirre", "Calle Tulipán SN",28934, "Móstoles", "España", 987654321, user, games4, 7, 5, 2022));
		purchases.add(new Purchase("Manu", "Martín", "Calle Tulipán SN",28934, "Móstoles", "España", 123456789, user1, games1, 3, 5, 2022));
		purchases.add(new Purchase("Pepe", "Pepón", "Calle Tulipán SN",28934, "Móstoles", "España", 987654321, user1, games3, 4, 7, 2022));
		purchases.add(new Purchase("Pepe", "Pepón", "Calle Tulipán SN",28934, "Móstoles", "España", 123456789, user, games5, 12, 2, 2022));
		purchases.add(new Purchase("Marcos", "Aguirre", "Calle Tulipán SN",28934, "Móstoles", "España", 123456789, user1, games5, 24, 11, 2022));



		userRepository.save(user1);

		for (Purchase p:purchases) {
			for(Game d:p.getGames()) {
				p.setPrice(p.getPrice()+d.getgamePrice());
				d.setNbuy(d.getNbuy()+1);
			}

		}



	}

	public void setGameImage(Game game, String classpathResource) throws IOException {
		game.setImage(true);
		Resource image = new ClassPathResource(classpathResource);
		game.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}
}


