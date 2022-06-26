package com.example.demo.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Game;
import com.example.demo.model.User;
import com.example.demo.model.Purchase;
import com.example.demo.service.PurchaseService;
import com.example.demo.service.GameService;
import com.example.demo.service.UserService;

    @Controller
    public class PurchaseController extends DefaultModeAttributes {

        @Autowired
        private PurchaseService purchaseService;
        @Autowired
        private GameService gameService;
        @Autowired
        private UserService userService;


        @PostMapping("/addToCart")
        public String addG(Model model, Long id, HttpServletRequest request) {


            Principal principal = request.getUserPrincipal();
            String sessionName = principal.getName();

            Game game = gameService.findById(id).get();
            User user = userService.findByName(sessionName);
            if(user.getNewPurchase()==null){
                user.setNewPurchase(new Purchase());
                user.getNewPurchase().setGames(new ArrayList<Game>() );
            }
            user.getNewPurchase().getGames().add(game);
            user.getNewPurchase().setPrice(user.getNewPurchase().getPrice()+game.getgamePrice());
            userService.save(user);
            purchaseService.save(user.getNewPurchase());

            //model.addAttribute("dishes1", dishService.getByCategory("Desayuno"));
            //model.addAttribute("dishes2", dishService.getByCategory("Comida"));
            //model.addAttribute("dishes3", dishService.getByCategory("Cena"));

            return "games";
        }





        @GetMapping("/cart")
        public String showPurchase(Model model, HttpServletRequest request) {
            Principal principal = request.getUserPrincipal();
            String userNamexx = principal.getName();
            Purchase newPurchase = new Purchase();
            User user = userService.findByName(userNamexx);
            if(user.getNewPurchase()!=null) {
                newPurchase =userService.findByName(userNamexx).getNewPurchase();
            }else {
                newPurchase.setUser(user);
                user.setNewPurchase(newPurchase);
                userService.save(user);
            }
            model.addAttribute("games", newPurchase.getGames());
            model.addAttribute("price", newPurchase.getPrice());

            return "cart";
        }
        @GetMapping("/profile/{id}")
        public String showProfile(Model model, @PathVariable long id, HttpServletRequest request) {

            Principal principal = request.getUserPrincipal();
            String userNameReal = principal.getName();
            User userReal = userService.findByName(userNameReal);
            Optional<User> user = userService.findById(id);

            if (userReal.getId() == user.get().getId()) {


                model.addAttribute("purchases", purchaseService.getByUser(user.get()));
                model.addAttribute("adminpurchases", purchaseService.findAll());



                model.addAttribute("isempty", purchaseService.getByUser(user.get()).isEmpty());
                model.addAttribute("isadminempty", purchaseService.findAll().isEmpty());


                //model.addAttribute("dishesRecomended", GameService.getRecomended(user.get().getId()));

                model.addAttribute("user", user.get());

                //model.addAttribute("currentPage", 0);

                return "user";
            } else {
                return "error";
            }
        }

        @GetMapping("/purchase/{id}")
        public String showPurchase(Model model, @PathVariable long id, HttpServletRequest request) {

            Principal principal = request.getUserPrincipal();
            String userNameReal = principal.getName();
            User userReal = userService.findByName(userNameReal);
            Purchase purchase = purchaseService.findById(id).get();

            if(userReal.getRoles().contains("ADMIN") || userReal.getId() == purchase.getUser().getId() ) {

                model.addAttribute("purchase",purchase);

                return "purchase";
            }else {
                return "error";
            }

        }
        @GetMapping("/pay")
        public String pagar(Model model, HttpServletRequest request) {
            Principal principal = request.getUserPrincipal();
            String userNameReal = principal.getName();
            User userReal = userService.findByName(userNameReal);
            if (!userReal.getNewPurchase().getGames().isEmpty()) {
                model.addAttribute("price", userReal.getNewPurchase().getPrice());
                return "pay";
            }

            //model.addAttribute("dishes1", dishService.getByCategory("Desayuno"));
           // model.addAttribute("dishes2", dishService.getByCategory("Comida"));
            //model.addAttribute("dishes3", dishService.getByCategory("Cena"));

            return "/index";

        }
        @PostMapping("/processpay")
        public String processPay(Model model, Purchase purchase, HttpServletRequest request) {

            Principal principal = request.getUserPrincipal();
            String userNameReal = principal.getName();

            User userReal = userService.findByName(userNameReal);
            Purchase newPurchase = userReal.getNewPurchase();

            if (purchase.getFirstName().equals(userReal.getFirstName()) && purchase.getSurname().equals(userReal.getSurname())) {
                newPurchase.setFirstName(purchase.getFirstName());
                newPurchase.setSurname(purchase.getSurname());
                newPurchase.setAddress(purchase.getAddress());
                newPurchase.setPostalCode(purchase.getPostalCode());
                newPurchase.setCity(purchase.getCity());
                newPurchase.setCountry(purchase.getCountry());
                newPurchase.setPhoneNumber(purchase.getPhoneNumber());
                Calendar c = Calendar.getInstance();
                newPurchase.setDateDay(c.get(Calendar.DATE));
                newPurchase.setDateMonth(c.get(Calendar.MONTH));
                newPurchase.setDateYear(c.get(Calendar.YEAR));
                newPurchase.setUser(userReal);

                for (Game game : newPurchase.getGames()) {
                    game.setNbuy(game.getNbuy()+1);
                    gameService.save(game);
                }

                purchaseService.save(newPurchase);
                userReal.setNewPurchase(null);
                userReal.getPurchases().add(newPurchase);
                userService.save(userReal);

            }else {
                return "error";}
            return "/paydone";
        }
        public String showPurchase(Model model, @PathVariable long id) {

            model.addAttribute("purchase", purchaseService.findById(id).get());

            return "/paydone";
        }
    }




