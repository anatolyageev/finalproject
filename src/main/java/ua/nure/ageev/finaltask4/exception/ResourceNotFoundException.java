package ua.nure.ageev.finaltask4.exception;

/**
 * Holder for messages of exceptions.
 *
 * @author A.Ageev
 */
public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
