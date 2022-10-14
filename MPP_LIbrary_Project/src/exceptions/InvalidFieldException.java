package exceptions;

public class InvalidFieldException extends RuntimeException{
    public InvalidFieldException(String msg) {
        super(msg);
    }
    
    public InvalidFieldException(String field, String from){
        super("Invalid field " + field + " | From: " + from + " class");
    }

    public InvalidFieldException(String field, String reason, String from){
        super("Invalid field " + field + " | Reason: " + reason + " | From: " + from + " class");
    }
}
