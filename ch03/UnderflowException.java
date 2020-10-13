/**
 * Exception class for access in empty containers
 * such as stacks, queues, and priority queues.
 * @author Mark Allen Weiss
 */
package ch03;

public class UnderflowException extends RuntimeException {
    public static final long serialVersionUID = -1;
}
