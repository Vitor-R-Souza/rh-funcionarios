package personal.rh_funcionarios.exception;

public class GenericException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public GenericException(String message) {
        super(message);
    }
}
