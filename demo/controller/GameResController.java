package com.example.demo.controller;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Game;
import com.example.demo.model.User;
import com.example.demo.service.GameService;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/profile")
public class GameResController {

    @Autowired
    private GameService purchaseService;
    @Autowired
    private UserRepository userService;

    @GetMapping("/games")
    public ResponseEntity<Page<Game>> showMore(Model model, HttpServletRequest request, HttpServletResponse response) {


        int pageRequested = 0;
        String numPage = request.getParameter("numPage");
        if (numPage != null) {
            pageRequested = Integer.parseInt(numPage);
        }

            Page<Game> page = purchaseService.findAll( pageRequested);
            return ResponseEntity.ok(page);

    }

}