package org.wso2.identity.carbon.user.consent.mgt.backend.service;

import org.wso2.identity.carbon.user.consent.mgt.backend.dao.ConsentDao;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ConsentDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.DataControllerDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PurposeDetailsDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ServicesDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.exception.DataAccessException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ConsentImplBAK {
    public static void main(String[] args) throws IOException {

        /*DateFormat dateFormat=new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        System.out.println("Current time : "+dateFormat.format(calendar.getTime()));
        calendar.add(Calendar.DATE,31);
        String out=dateFormat.format(calendar.getTime());
        System.out.println("Time in 31 days : "+out);*/

        try {
            ConsentDao consentDao=new ConsentDao("A1B2C3",true);
            List<ServicesDO> servicesList=consentDao.getServices();

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        /*try {
            int i=consentDaoImpl.getPurposeTerminationDays(2);
            System.out.println(i);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
*/

//        ConsentDemo consentDemo = new ConsentDemo();
        /*Consent consent=consentDemo.getConsentDetails("shanchathusanda@gmail.com");
        Services[] services=consentDemo.getPurposeDetails(consent.getSGUID());
        for(int i=0;i<services.length;i++){
            System.out.println(services[i].getServiceId()+" "+services[i].getServiceDescription()+" "+services[i].getPurposeDetails().getPurposeId()+" "+services[i].getPurposeDetails().getPurpose());
        }*/
//        Services org.wso2.identity.carbon.user.consent.mgt.backend.constants.service=consentDemo.getServicesByUserByServiceId("shanchathusanda@gmail.com",1);
//        PurposeDetails[] purposeDetailsArr=org.wso2.identity.carbon.user.consent.mgt.backend.constants.service.getPurposeDetailsArr();

        /*Services serviceOutput=new Services();
        PurposeDetails[] purposeOutput=new PurposeDetails[purposeDetailsArr.length];

        for(int i=0;i<purposeDetailsArr.length;i++){
//            System.out.println(purposeDetailsArr[i].getPurposeId()+" - "+purposeDetailsArr[i].getPurpose()+" - "+purposeDetailsArr[i].getPiiCategory().getPiiCat());
        }
        System.out.println(org.wso2.identity.carbon.user.consent.mgt.backend.constants.service.getServiceId());
        System.out.println(org.wso2.identity.carbon.user.consent.mgt.backend.constants.service.getServiceDescription());*/

        /*ConsentDao consentDao = null;
        try {
            consentDao = new ConsentDaoImpl("shanchathusanda@gmail.com");
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        for (Consent consent : consentDao.getUserConsent()) {
            System.out.println(consent.getPiiPrincipalId());
        }*/

        /*ServicesDao servicesDao=new ConsentDaoImpl("A1B2C3",true);
        for(Services services:servicesDao.getServices()){
            System.out.println(services.getServiceId()+" - "+services.getServiceDescription()+" - "+services.getPurposeDetails().getPurposeCatShortCode());
        }*/

       /* ConsentDao consentDao = null;
        try {
            consentDao = new ConsentDaoImpl("shanchathusanda@gmail.cm");
            for (Consent consent : consentDao.getUserConsent()) {
                System.out.println(consent.getSGUID() + " - " + consent.getPiiPrincipalId());
            }
        } catch (NullPointerException e) {
            System.out.println("No data to display. Result is empty in ConsentDemo." + e);
        } catch (DataAccessException e) {
            System.out.println(e);
        }*/
    }

        /*ServicesDao servicesDao=new ConsentDaoImpl("A1B2C3","TP02");
        for(Services services:servicesDao.getServices()){
            System.out.println(services.getServiceId()+" - "+services.getServiceDescription()+" - "+services.getPurposeDetails().getThirdPartyName());
        }*/

    //--Returns the consent transaction details by piiPrincipalId--//
    public ConsentDO getConsentDetails(String piiPrincipalId) {
        ConsentDao consentDao = null;
        try {
            consentDao = new ConsentDao(piiPrincipalId);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        for (ConsentDO consentDO : consentDao.getUserConsent()) {
            return consentDO;
        }
        return null;
    }

    //--Returns the org.wso2.identity.carbon.user.consent.mgt.backend.constants.service details by pii principal(SGUID)--//
//    public ServicesDO[] getPurposeDetails(String SGUID) {
//        ServicesDO servicesDO = null;
//        try {
//            servicesDO = new ConsentDao(SGUID, true);
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//        }
//        ServicesDO[] services = servicesDao.getServices().toArray(new ServicesDO[0]);
//        return services;
//    }

    //--Returns the org.wso2.identity.carbon.user.consent.mgt.backend.constants.service and purpose details by user and by third party--//
    /*public ServicesDO[] getPurposeDetailsByThirdParty(String SGUID, String thirdPartyId) {
        ServicesDO servicesDO = null;
        try {
            servicesDO = new ConsentDao(SGUID, thirdPartyId);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        ServicesDO[] services = servicesDO.getServices().toArray(new ServicesDO[0]);
        return services;
    }*/

    //--Sending the user consent details to the org.wso2.identity.carbon.user.consent.mgt.backend.constants.DAO layer--//
    public void adduserConsentDetails(String version, String jurisdiction, String collectionMethod, String SGUID,
            String piiPrincipalId, int dataControllerId) {
        ConsentDO consentDO = new ConsentDO();
        DataControllerDO dataController = new DataControllerDO();
        ServicesDO[] services = new ServicesDO[2];//--Size of this array should be the number of purposes we have and that must be get from the database
        PurposeDetailsDO purposes;

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String consentTimestamp = dateFormat.format(date);

        consentDO.setVersion(version);
        consentDO.setJurisdiction(jurisdiction);
        consentDO.setCollectionMethod(collectionMethod);
        consentDO.setSGUID(SGUID);
        consentDO.setPiiPrincipalId(piiPrincipalId);
        consentDO.setConsentTimestamp(consentTimestamp);
        dataController.setDataControllerId(dataControllerId);
        consentDO.setDataController(dataController);

        for (int i = 0; i < services.length; i++) {
            services[i] = new ServicesDO();//--Creating an object under the services array
            purposes = new PurposeDetailsDO();//--Have to initialize this instance here,coz it has to be a new instance in each iteration

            services[i].setServiceId(1);
            purposes.setPurposeId((i + 1));
            services[i].setPurposeDetails(purposes);
        }

        ConsentDao consentDao = new ConsentDao();
        try {
            consentDao.addUserConsentDetails(consentDO, services);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public ServicesDO getServicesByUserByServiceId(String userId, int serviceId) {
        ConsentDao consentDao = new ConsentDao();
        ServicesDO service = null;
        try {
            service = consentDao.getServicesByUserByServiceId(userId, serviceId);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return service;
    }
}
