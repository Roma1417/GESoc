package utn.dds.tpAnual.db.service.business;

public class ValidationException extends RuntimeException{
    public ValidationException(String msg){
        super(msg);
    }
}
