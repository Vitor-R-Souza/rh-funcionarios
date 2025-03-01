package personal.rh_funcionarios.exception;

public class NotFoundException extends GenericException {
    private static final long serialVersionUID = 1L;

    public NotFoundException() {
        super("Resource not found.");
    }
}
