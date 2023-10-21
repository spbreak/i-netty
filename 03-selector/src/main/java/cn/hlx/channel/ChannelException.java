package cn.hlx.channel;

/**
 * A {@link RuntimeException} which is thrown when an I/O operation fails.
 */
public class ChannelException extends RuntimeException {
    private static final long serialVersionUID = 2908618315971075004L;

    /**
     * Creates a new exception.
     */
    public ChannelException(String message, Throwable cause) {
        super(message, cause);
    }

}
