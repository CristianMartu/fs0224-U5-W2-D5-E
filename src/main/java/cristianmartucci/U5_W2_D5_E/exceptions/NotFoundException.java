package cristianmartucci.U5_W2_D5_E.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
	public NotFoundException(UUID id) {
		super(id + " non trovato!");
	}
}