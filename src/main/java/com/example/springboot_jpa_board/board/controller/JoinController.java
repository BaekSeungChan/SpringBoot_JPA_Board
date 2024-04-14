package com.example.springboot_jpa_board.board.controller;

import com.example.springboot_jpa_board.board.dto.JoinDTO;
//import com.example.springboot_jpa_board.board.service.JoinService;
import com.example.springboot_jpa_board.board.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @GetMapping("/join")
    public String joinP(){
        return "join";
    }


    @PostMapping("/joinProc")
    public String joinProcess(JoinDTO joinDTO){

        System.out.println(joinDTO.getUsername());

        joinService.joinPrecess(joinDTO);

        return "redirect:/login";
    }
}
