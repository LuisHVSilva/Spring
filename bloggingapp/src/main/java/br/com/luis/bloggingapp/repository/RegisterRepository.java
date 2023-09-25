package br.com.luis.bloggingApp.repository;

import br.com.luis.bloggingApp.model.Register;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> { }
