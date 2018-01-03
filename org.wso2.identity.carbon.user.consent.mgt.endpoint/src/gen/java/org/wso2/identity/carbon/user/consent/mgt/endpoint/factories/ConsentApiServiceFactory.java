package org.wso2.identity.carbon.user.consent.mgt.endpoint.factories;

import org.wso2.identity.carbon.user.consent.mgt.endpoint.ConsentApiService;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.impl.ConsentApiServiceImpl;

public class ConsentApiServiceFactory {

   private final static ConsentApiService service = new ConsentApiServiceImpl();

   public static ConsentApiService getConsentApi()
   {
      return service;
   }
}
