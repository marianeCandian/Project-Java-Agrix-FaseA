package dto;

/**
 * Metodo para facilitar as respostas.
 */
public record ResponseDto<T>(String message, T data) {

}
