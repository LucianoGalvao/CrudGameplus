package br.edu.usj.ads.pw.gameplus;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GameController {

    @Autowired
    GameRepository gameRepository;

    @GetMapping(value = "/")
    public ModelAndView getIndex() {
        List<Game> lista = new ArrayList<>();

        lista = gameRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("index");

        modelAndView.addObject("lista", lista);

        return modelAndView;
    }

    @GetMapping(value = "/detalhes/{id}")
    public ModelAndView getDetalhes(@PathVariable Long id) {
        Game game = new Game();
        game = gameRepository.findById(id).get();

        ModelAndView modelAndView = new ModelAndView("detalhes");

        modelAndView.addObject("game", game);

        return modelAndView;
    }

    @GetMapping(value = "/editar/{id}")
    public ModelAndView getEditar(@PathVariable Long id) {
        Game game = new Game();
        game = gameRepository.findById(id).get();

        ModelAndView modelAndView = new ModelAndView("cadastro");
        modelAndView.addObject("game", game);

        return modelAndView;
    }

    @GetMapping(value = "/cadastro")
    public ModelAndView getCadastro() {
        Game game = new Game();
        ModelAndView modelAndView = new ModelAndView("cadastro");

        modelAndView.addObject("game", game);

        return modelAndView;
    }

    @PostMapping(value = "/adicionar")
    public ModelAndView postAdicionar(Game game) {
        gameRepository.save(game);

        ModelAndView modelAndView = new ModelAndView("detalhes");

        modelAndView.addObject("game", game);

        return modelAndView;
    }

    @GetMapping(value = "/deletar/{id}")
    public String getDeletar(@PathVariable Long id) {

        gameRepository.deleteById(id);

        return "redirect:/";

    }

}
