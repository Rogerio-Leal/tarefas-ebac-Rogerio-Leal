package br.com.rleal.cliente_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RegraNegocioException extends RuntimeException {
	
	private static final long serialVersionUID = 8511613467027199621L;

	public RegraNegocioException(String mensagem) {
        super(mensagem);
    }
}