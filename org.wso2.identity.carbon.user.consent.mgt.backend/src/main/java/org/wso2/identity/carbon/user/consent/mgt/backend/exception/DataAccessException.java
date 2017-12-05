package org.wso2.identity.carbon.user.consent.mgt.backend.exception;

import org.wso2.carbon.identity.base.IdentityException;

public class DataAccessException extends IdentityException{

    public DataAccessException(String message){
        super(message);
    }

    public DataAccessException(String message, Throwable e){
        super(message,e);
    }

}
