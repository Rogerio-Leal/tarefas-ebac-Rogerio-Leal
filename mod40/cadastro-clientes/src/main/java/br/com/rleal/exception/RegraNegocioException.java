package br.com.rleal.exception;

public class RegraNegocioException extends RuntimeException {

	private static final long serialVersionUID = 8511613467027199621L;

	public RegraNegocioException(String mensagem) {
        super(mensagem);
    }
}