package com.example.demo.service;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import com.example.demo.model.Games;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.GamesRepository;
//import com.example.demo.repository.ShopRepository;
import com.example.demo.repository.UserRepository;

@Service
public class DatabaseInitializer {

	@Autowired
	private GamesRepository gamesRepository;

	/*@Autowired
	private ShopRepository shopRepository;

	 */

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void init() throws IOException, URISyntaxException {

		// Sample books

		Games games1 = new Games("SUEÑOS DE ACERO Y NEON",
				"Los personajes que protagonizan este relato sobreviven en una sociedad en decadencia a la que, no obstante, lograrán devolver la posibilidad de un futuro. Año 2484. En un mundo dominado por las grandes corporaciones, solo un hombre, Jordi Thompson, detective privado deslenguado y vividor, pero de gran talento y sentido d...");

		setGameImage(games1, "static/Game images/3DS New Super Mario Bros 2.jpg");
		gamesRepository.save(games1);

		Games games2 = new Games("LA VIDA SECRETA DE LA MENTE",
				"La vida secreta de la mentees un viaje especular que recorre el cerebro y el pensamiento: se trata de descubrir nuestra mente para entendernos hasta en los más pequeños rincones que componen lo que somos, cómo forjamos las ideas en los primeros días de vida, cómo damos forma a las decisiones que nos constituyen, cómo soñamos y cómo imaginamos, por qué sentimos ciertas emociones hacia los demás, cómo los demás influyen en nosotros, y cómo el cerebro se transforma y, con él, lo que somos.");

		setGameImage(games2, "static/Game images/DS New Super Mario Bros.jpg");
		gamesRepository.save(games2);

		gamesRepository.save(new Games("CASI SIN QUERER",
				"El amor algunas veces es tan complicado como impredecible. Pero al final lo que más valoramos son los detalles más simples, los más bonitos, los que llegan sin avisar. Y a la hora de escribir sobre sentimientos, no hay nada más limpio que hacerlo desde el corazón. Y eso hace Defreds en este libro."));

		gamesRepository.save(new Games("TERMINAMOS Y OTROS POEMAS SIN TERMINAR",
				"Recopilación de nuevos poemas, textos en prosa y pensamientos del autor. Un sabio dijo una vez: «Pocas cosas hipnotizan tanto en este mundo como una llama y como la luna, será porque no podemos cogerlas o porque nos iluminan en la penumbra». Realmente no sé si alguien dijo esta cita o me la acabo de inventar pero deberían de haberla escrito porque el poder hipnótico que ejercen esa mujer de rojo y esa dama blanca sobre el ser humano es digna de estudio."));

		gamesRepository.save(new Games("LA LEGIÓN PERDIDA",
				"En el año 53 a. C. el cónsul Craso cruzó el Éufrates para conquistar Oriente, pero su ejército fue destrozado en Carrhae. Una legión entera cayó prisionera de los partos. Nadie sabe a ciencia cierta qué pasó con aquella legión perdida.150 años después, Trajano está a punto de volver a cruzar el Éufrates. ..."));

		// Sample Shops

		//shopRepository.save(new Shop("La casa del libro", "C/Falsa 123"));
		//shopRepository.save(new Shop("FNAC", "C/Falsa 456"));


		// Sample users

		userRepository.save(new User("user", passwordEncoder.encode("pass"), "USER"));
		userRepository.save(new User("admin", passwordEncoder.encode("adminpass"), "USER", "ADMIN"));
	}

	public void setGameImage(Games games, String classpathResource) throws IOException {
		games.setImage(true);
		Resource image = new ClassPathResource(classpathResource);
		games.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}
}


