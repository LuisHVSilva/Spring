package br.com.luis.bloggingApp.controller;

import br.com.luis.bloggingApp.dto.RegisterDto;
import br.com.luis.bloggingApp.model.Register;
import br.com.luis.bloggingApp.services.RegisterServiceImpl;
import br.com.luis.bloggingApp.util.MediaType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterServiceImpl registerService;

    @Autowired
    private ModelMapper modelMapper;

    public RegisterController(RegisterServiceImpl registerService, ModelMapper modelMapper) {
        this.registerService = registerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<Register> getTest() {
        return registerService.getAllUsers();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<RegisterDto> register(@RequestBody RegisterDto registerDto){
        Register registerRequest = modelMapper.map(registerDto, Register.class);
        Register register = registerService.register(registerRequest);

        RegisterDto registerResponse = modelMapper.map(register, RegisterDto.class);

        return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
    }
}
