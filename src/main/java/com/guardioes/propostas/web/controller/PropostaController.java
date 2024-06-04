package com.guardioes.propostas.web.controller;

import com.guardioes.propostas.service.PropostaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PropostaController {

    private final PropostaService propostaService;

}