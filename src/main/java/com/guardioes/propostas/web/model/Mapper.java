package com.guardioes.propostas.web.model;

import org.modelmapper.ModelMapper;

import java.util.List;

public class Mapper {
    public static <O, D> D paraClasse(O origin, Class<D> destination) {
        return new ModelMapper().map(origin, destination);
    }

    public static <O, D> List<D> paraLista(List<O> origin, Class<D> destination) {
        return origin.stream().map(o -> paraClasse(o, destination)).toList();
    }
}
