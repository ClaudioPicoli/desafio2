package com.rest.mapper;

import com.rest.model.Conta;
import com.rest.response.ContaResponse;

public final class ContaMapper {

    /**
     * transforma o model Conta em ContaResponse
     *
     * @param conta
     * @return
     */
    public static ContaResponse map(Conta conta) {
        return ContaResponse.builder()
            .idConta(conta.getIdConta())
            .idPessoa(conta.getIdPessoa())
            .saldo(conta.getSaldo())
            .limiteSaqueDiario(conta.getLimiteSaqueDiario())
            .flagAtivo(conta.isFlagAtivo())
            .tipoConta(conta.getTipoConta())
            .dataCriacao(conta.getDataCriacao())
            .build();
    }
}
