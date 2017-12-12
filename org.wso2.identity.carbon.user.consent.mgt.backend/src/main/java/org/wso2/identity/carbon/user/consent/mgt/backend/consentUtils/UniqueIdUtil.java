package org.wso2.identity.carbon.user.consent.mgt.backend.consentUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class UniqueIdUtil {
    public static String createUniqueId(){
        String uniqueId = UUID.randomUUID().toString();
        return uniqueId;
    }
}
