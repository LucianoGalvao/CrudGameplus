package br.edu.usj.ads.pw.gameplus;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class GameController {

    @Autowired
    GameRepository gameRepository;

    @GetMapping(value="/")
    public ModelAndView getIndex() {

        List<Game> lista = new ArrayList<>();

        lista = gameRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("index");

        modelAndView.addObject("lista", lista);

        return modelAndView;
    }
    
    
    @GetMapping(value="/cadastro")
    public ModelAndView getCadastro() {
        ModelAndView modelAndView = new ModelAndView("cadastro");
        return modelAndView;
    }

    @PostMapping(value="/adicionar")
    public ModelAndView postAdicionar(@RequestParam String name, @RequestParam String year, @RequestParam String genre) {
        Game game = new Game();

        // game.setName(name);
        // game.setYear(year);
        // game.setGenre(genre);
        
        gameRepository.save(game);

        ModelAndView modelAndView = new ModelAndView("detalhes");
        
        modelAndView.addObject("game", game);

        return modelAndView;
    }
    

}
