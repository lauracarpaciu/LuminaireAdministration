package com.laura.carpaciu.controllers.luminaire;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.laura.carpaciu.entity.luminaire.Piece;
import com.laura.carpaciu.services.PieceService;


@Controller // This means that this class is a Controller
@RequestMapping(path="/pices") // This means URL's start with /pieces (after Application path)
public class PieceController {
	@Autowired
	private final PieceService pieceService;
	
	public PieceController(PieceService pieceService) {
		super();
		this.pieceService = pieceService;
	}

	@GetMapping("/piece")
    public String getPartPage(Model model){

        model.addAttribute("part", new Piece());



        return "auto/piece-page";
    }

    @PostMapping("/createPice")
    public String createPiece(@Valid @ModelAttribute ("part") Piece piece, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "auto/piece-page";
        }


        pieceService.addPiece(piece);
        return "redirect:/parts/part";
    }

}
