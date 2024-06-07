package com.guardioes.propostas.web.exception;

import com.guardioes.propostas.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ExcecaoManipulador {
    @ExceptionHandler(ExcecaoCpfDuplicado.class)
    public final ResponseEntity<MensagemErro> excecaoCpfDuplicado(ExcecaoCpfDuplicado ex, HttpServletRequest request) {
        log.error("Erro na API", ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MensagemErro(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(ExcecaoPropostaInexistente.class)
    public final ResponseEntity<MensagemErro> excecaoPropostaInexistente(ExcecaoPropostaInexistente ex, HttpServletRequest request) {
        log.error("Erro na API", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MensagemErro(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(ExcecaoPropostaDuplicada.class)
    public final ResponseEntity<MensagemErro> excecaoPropostaDuplicada(ExcecaoPropostaDuplicada ex, HttpServletRequest request) {
        log.error("Erro na API", ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MensagemErro(request, HttpStatus.CONFLICT, ex.getMessage()));
    }


    @ExceptionHandler(ExcecaoFuncionarioInvalido.class)
    public final ResponseEntity<MensagemErro> excecaoFuncionarioInvalido(ExcecaoFuncionarioInvalido ex, HttpServletRequest request) {
        log.error("Erro na API", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MensagemErro(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }


    @ExceptionHandler(ExcecaoPropostaInativa.class)
    public final ResponseEntity<MensagemErro> excecaoPropostaInativa(ExcecaoPropostaInativa ex, HttpServletRequest request) {
        log.error("Erro na API", ex);
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MensagemErro(request, HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage()));
    }


    @ExceptionHandler(ExcecaoVotoInvalido.class)
    public final ResponseEntity<MensagemErro> excecaoVotoInvalido(ExcecaoVotoInvalido ex, HttpServletRequest request) {
        log.error("Erro na API", ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MensagemErro(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(ExcecaoVotacaoIniciada.class)
    public final ResponseEntity<MensagemErro> ExcecaoVotacaoIniciada(ExcecaoVotacaoIniciada ex, HttpServletRequest request) {
        log.error("Erro na API", ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MensagemErro(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensagemErro> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.error("Erro na API", ex);

        MensagemErro mensagemErro = new MensagemErro(request, HttpStatus.BAD_REQUEST, "Invalid request content.", ex.getBindingResult());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mensagemErro);
    }

    @ExceptionHandler(ExcecaoConexao.class)
    public final ResponseEntity<MensagemErro> ExcecaoConexao(ExcecaoConexao ex, HttpServletRequest request) {
        log.error("Erro na API", ex);
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MensagemErro(request, HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage()));
    }
}