package identity_user_consent_mgt_endpoint.factories;

import identity_user_consent_mgt_endpoint.ConsentApiService;
import identity_user_consent_mgt_endpoint.impl.ConsentApiServiceImpl;

public class ConsentApiServiceFactory {

   private final static ConsentApiService service = new ConsentApiServiceImpl();

   public static ConsentApiService getConsentApi()
   {
      return service;
   }
}
