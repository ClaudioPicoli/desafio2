package com.rest.mapper;

import com.rest.model.Transacao;
import com.rest.response.TransacaoResponse;

public final class TransacaoMapper {

    /**
     * transforma o model transacao em TransacaoResponse
     *
     * @param transacao
     * @return
     */
    public static TransacaoResponse map(Transacao transacao) {
        return TransacaoResponse.builder()
            .idConta(transacao.getIdConta())
            .valor(transacao.getValor())
            .dataTransacao(transacao.getDataTransacao())
            .build();
    }
}
