package br.com.rleal.produto_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RegraNegocioException extends RuntimeException {

	private static final long serialVersionUID = 2120948702023571881L;
	
	public RegraNegocioException(String mensagem) {
        super(mensagem);
    }

}
