package org.wso2.identity.carbon.user.consent.mgt.backend.consentUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
    public static String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String currentTime = dateFormat.format(calendar.getTime());
        return currentTime;
    }
}
