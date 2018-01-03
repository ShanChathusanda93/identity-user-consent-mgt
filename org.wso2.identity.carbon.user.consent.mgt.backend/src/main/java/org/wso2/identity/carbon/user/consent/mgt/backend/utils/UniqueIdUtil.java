package org.wso2.identity.carbon.user.consent.mgt.backend.utils;

import java.util.UUID;

public class UniqueIdUtil {
    public static String createUniqueId(){
        String uniqueId = UUID.randomUUID().toString();
        return uniqueId;
    }
}
