package br.com.luis.bloggingApp.services;

import br.com.luis.bloggingApp.model.Register;
import br.com.luis.bloggingApp.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class RegisterServiceImpl {

    private final Logger LOGGER = Logger.getLogger(RegisterServiceImpl.class.getName());

    @Autowired
    RegisterRepository registerRepository;

    public RegisterServiceImpl(RegisterRepository registerRepository){
        super();
        this.registerRepository = registerRepository;
    }

    public List<Register> getAllUsers() {
        return registerRepository.findAll();
    }

    public Register register(Register register){
        LOGGER.info("User registered!");
        return registerRepository.save(register);
    }
}
