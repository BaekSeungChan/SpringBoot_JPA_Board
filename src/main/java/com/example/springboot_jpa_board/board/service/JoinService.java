package com.example.springboot_jpa_board.board.service;

import com.example.springboot_jpa_board.board.dto.JoinDTO;
import com.example.springboot_jpa_board.board.entity.UserEntity;
import com.example.springboot_jpa_board.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinPrecess(JoinDTO joinDTO){

        UserEntity data = new UserEntity();

        data.setUsername(joinDTO.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        data.setRole("ROLE_USER");

        userRepository.save(data);
    }
}
