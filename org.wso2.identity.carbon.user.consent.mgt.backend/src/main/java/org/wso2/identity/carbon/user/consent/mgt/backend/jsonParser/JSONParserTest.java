package org.wso2.identity.carbon.user.consent.mgt.backend.jsonParser;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.wso2.identity.carbon.user.consent.mgt.backend.dao.ConsentDao;
import org.wso2.identity.carbon.user.consent.mgt.backend.exception.DataAccessException;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ConsentDO;

/*import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;*/
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

/*@Path("/test")*/

public class JSONParserTest {
    public static void main(String[] args) {
        try {
            ConsentDao consentDao=new ConsentDao("shanchathusanda@gmail.com");
            List<ConsentDO> consentDOList=consentDao.getUserConsent();
            System.out.println(consentDOList.size());
            System.out.println(consentDOList.get(0).getPiiPrincipalId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }


        /*JSONParserTest jsonParserTest = new JSONParserTest();
        ConsentDao consentDao=new ConsentDao();
        try {
            consentDao.getSensitivePersonalInfoCategory("A1B2C3");
        } catch (DataAccessException e) {
            e.printStackTrace();
        }*/
        /*try {
            jsonParserTest.readConsentReceipt();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }*/
//        try {
//            jsonParserTest.createConsentReceipt("shanchathusanda@gmail.com");
//        } catch (DataAccessException e) {
//            e.printStackTrace();
//        }
    }

   /* @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)*/
    public JSONObject getNames(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("name","Shan");
        jsonObject.put("age",24);
        return jsonObject;
    }

    /*public void createConsentReceipt(String piiPrincipalId) throws DataAccessException {
        ConsentDaoImpl consentDao = new ConsentDaoImpl(piiPrincipalId);
        List<Consent> consentList = consentDao.getUserConsent();
        Consent consent = consentList.get(0);

        consentDao = new ConsentDaoImpl(consent.getSGUID(), true);
        List<ServicesDO> servicesList = consentDao.getServices();
        String temp = "";
        JSONObject jsonMainObj = new JSONObject();
        JSONArray jsonServiceArr = new JSONArray();

        //--optimizing the code
        JSONObject[] jsonServiceObj = new JSONObject[servicesList.size()];
        for (int i = 0; i < servicesList.size(); i++) {
            ServicesDO org.wso2.identity.carbon.user.consent.mgt.backend.constants.service = servicesList.get(i);
            if (i == 0) {
                jsonServiceObj[i] = new JSONObject();
                temp = org.wso2.identity.carbon.user.consent.mgt.backend.constants.service.getServiceDescription();
                jsonServiceObj[i].put("serviceName", org.wso2.identity.carbon.user.consent.mgt.backend.constants.service.getServiceDescription());

                JSONObject[] jsonPurposeObj = new JSONObject[servicesList.size()];
                JSONArray jsonPurposeArr = new JSONArray();
                String tempPurpose = "";
                for (int j = 0; j < servicesList.size(); j++) {
                    PurposeDetailsDO purpose = servicesList.get(j).getPurposeDetails();
                    if (temp.equals(servicesList.get(j).getServiceDescription())) {
                        if (j == 0) {
                            jsonPurposeObj[j] = new JSONObject();
                            tempPurpose = purpose.getPurpose();

                            JSONArray jsonPiiArr = new JSONArray();
                            for (int k = 0; k < servicesList.size(); k++) {
                                if (temp.equals(servicesList.get(k).getServiceDescription())) {
                                    if (tempPurpose.equals(servicesList.get(k).getPurposeDetails().getPurpose())) {
                                        jsonPiiArr.add(servicesList.get(k).getPurposeDetails().getPiiCategory().getPiiCat());
                                    } else {
                                        break;
                                    }
                                }
                            }
                            jsonPurposeObj[j].put("piiCategory", jsonPiiArr);
                            jsonPurposeObj[j].put("purpose", purpose.getPurpose());
                            jsonPurposeObj[j].put("purposeCategory", purpose.getPurposeCatShortCode());
                            jsonPurposeObj[j].put("primaryPurpose", purpose.getPrimaryPurpose());
                            jsonPurposeObj[j].put("termination", purpose.getTermination());
                            jsonPurposeObj[j].put("thirdPartyDisclosure", purpose.getThirdPartyDis());
                            jsonPurposeObj[j].put("thirdPartyName", purpose.getThirdPartyName());
                        } else {
                            if (!tempPurpose.equals(purpose.getPurpose())) {
                                jsonPurposeObj[j] = new JSONObject();
                                tempPurpose = purpose.getPurpose();

                                JSONArray jsonPiiArr = new JSONArray();
                                for (int k = j; k < servicesList.size(); k++) {
                                    if (temp.equals(servicesList.get(k).getServiceDescription())) {
                                        if (tempPurpose.equals(servicesList.get(k).getPurposeDetails().getPurpose())) {
                                            jsonPiiArr.add(servicesList.get(k).getPurposeDetails().getPiiCategory().getPiiCat());
                                        } else {
                                            break;
                                        }
                                    }
                                }
                                jsonPurposeObj[j].put("piiCategory", jsonPiiArr);
                                jsonPurposeObj[j].put("purpose", purpose.getPurpose());
                                jsonPurposeObj[j].put("purposeCategory", purpose.getPurposeCatShortCode());
                                jsonPurposeObj[j].put("primaryPurpose", purpose.getPrimaryPurpose());
                                jsonPurposeObj[j].put("termination", purpose.getTermination());
                                jsonPurposeObj[j].put("thirdPartyDisclosure", purpose.getThirdPartyDis());
                                jsonPurposeObj[j].put("thirdPartyName", purpose.getThirdPartyName());
                            }
                        }
                        if (jsonPurposeObj[j] != null) {
                            jsonPurposeArr.add(jsonPurposeObj[j]);
                        }
                    } else {
                        break;
                    }
                }
                jsonServiceObj[i].put("purposes", jsonPurposeArr);
                jsonServiceArr.add(jsonServiceObj[i]);
            } else {
                if (!temp.equals(org.wso2.identity.carbon.user.consent.mgt.backend.constants.service.getServiceDescription())) {
                    jsonServiceObj[i] = new JSONObject();
                    jsonServiceObj[i].put("serviceName", org.wso2.identity.carbon.user.consent.mgt.backend.constants.service.getServiceDescription());
                    temp = org.wso2.identity.carbon.user.consent.mgt.backend.constants.service.getServiceDescription();

                    JSONObject[] jsonPurposeObj = new JSONObject[servicesList.size()];
                    JSONArray jsonPurposeArr = new JSONArray();
                    String tempPurpose = "";
                    for (int j = i; j < servicesList.size(); j++) {
                        PurposeDetailsDO purpose = servicesList.get(j).getPurposeDetails();
                        if (temp.equals(servicesList.get(j).getServiceDescription())) {
                            if (j == i) {
                                jsonPurposeObj[j] = new JSONObject();
                                tempPurpose = purpose.getPurpose();

                                JSONArray jsonPiiArr = new JSONArray();
                                for (int k = j; k < servicesList.size(); k++) {
                                    if (temp.equals(servicesList.get(k).getServiceDescription())) {
                                        if (tempPurpose.equals(servicesList.get(k).getPurposeDetails().getPurpose())) {
                                            jsonPiiArr.add(servicesList.get(k).getPurposeDetails().getPiiCategory().getPiiCat());
                                        } else {
                                            break;
                                        }
                                    }
                                }
                                jsonPurposeObj[j].put("piiCategories", jsonPiiArr);
                                jsonPurposeObj[j].put("purpose", purpose.getPurpose());
                                jsonPurposeObj[j].put("purposeCategory", purpose.getPurposeCatShortCode());
                                jsonPurposeObj[j].put("primaryPurpose", purpose.getPrimaryPurpose());
                                jsonPurposeObj[j].put("termination", purpose.getTermination());
                                jsonPurposeObj[j].put("thirdPartyDisclosure", purpose.getThirdPartyDis());
                                jsonPurposeObj[j].put("thirdPartyName", purpose.getThirdPartyName());
                            } else {
                                if (!tempPurpose.equals(purpose.getPurpose())) {
                                    jsonPurposeObj[j] = new JSONObject();
                                    tempPurpose = purpose.getPurpose();

                                    JSONArray jsonPiiArr = new JSONArray();
                                    for (int k = j; k < servicesList.size(); k++) {
                                        if (temp.equals(servicesList.get(k).getServiceDescription())) {
                                            if (tempPurpose.equals(servicesList.get(k).getPurposeDetails().getPurpose())) {
                                                jsonPiiArr.add(servicesList.get(k).getPurposeDetails().getPiiCategory().getPiiCat());
                                            } else {
                                                break;
                                            }
                                        }
                                    }

                                    jsonPurposeObj[j].put("piiCategories", jsonPiiArr);
                                    jsonPurposeObj[j].put("purpose", purpose.getPurpose());
                                    jsonPurposeObj[j].put("purposeCategory", purpose.getPurposeCatShortCode());
                                    jsonPurposeObj[j].put("primaryPurpose", purpose.getPrimaryPurpose());
                                    jsonPurposeObj[j].put("termination", purpose.getTermination());
                                    jsonPurposeObj[j].put("thirdPartyDisclosure", purpose.getThirdPartyDis());
                                    jsonPurposeObj[j].put("thirdPartyName", purpose.getThirdPartyName());
                                }
                            }
                            if (jsonPurposeObj[j] != null) {
                                jsonPurposeArr.add(jsonPurposeObj[j]);
                            }
                        } else {
                            break;
                        }
                    }
                    jsonServiceObj[i].put("purposes", jsonPurposeArr);
                    if (!jsonServiceObj[i].isEmpty()) {
                        jsonServiceArr.add(jsonServiceObj[i]);
                    }
                }

            }
            jsonMainObj.put("services", jsonServiceArr);
        }
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(jsonMainObj);

            FileWriter fileWriter = new FileWriter
                    ("/home/shan/gdpr_project/financial-open-banking/consent-test-" + piiPrincipalId + ".json");
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //--It works


        *//*for (int i = 0; i < servicesList.size(); i++) {
            Services org.wso2.identity.carbon.user.consent.mgt.backend.constants.service = servicesList.get(i);
            JSONObject[] jsonServiceObj = new JSONObject[servicesList.size()];

            if (i == 0) {
                temp = org.wso2.identity.carbon.user.consent.mgt.backend.constants.service.getServiceDescription();
                jsonServiceObj[i] = new JSONObject();
                jsonServiceObj[i].put("serviceName", org.wso2.identity.carbon.user.consent.mgt.backend.constants.service.getServiceDescription());
                JSONArray jsonPurposeArr = new JSONArray();
                for (int j = 0; j < servicesList.size(); j++) {
                    Services service1 = servicesList.get(j);
                    JSONObject jsonPurposeObj = new JSONObject();
                    JSONArray jsonPiiArr = new JSONArray();
                    int k;
                    for (k = j; k < servicesList.size(); k++) {
                        if (jsonServiceObj[i].get("serviceName").equals(servicesList.get(k).getServiceDescription())) {
                            if (k == 0) {
                                jsonPiiArr.add(servicesList.get(k).getPurposeDetails().getPiiCategory().getPiiCat());
                                j++;
                            } else {
                                if (service1.getPurposeDetails().getPurpose().equals(servicesList.get(k).getPurposeDetails()
                                        .getPurpose()
                                )) {
                                    jsonPiiArr.add(servicesList.get(k).getPurposeDetails().getPiiCategory().getPiiCat());
                                    j++;
                                } else {
                                    jsonPurposeObj.put("piiCategory", jsonPiiArr);
                                    jsonPurposeObj.put("purpose", servicesList.get(k - 1).getPurposeDetails().getPurpose());
                                    jsonPurposeObj.put("purposeCategory",servicesList.get(k-1).getPurposeDetails()
                                            .getPurposeCatShortCode());
                                    jsonPurposeObj.put("primaryPurpose",servicesList.get(k-1).getPurposeDetails()
                                            .getPrimaryPurpose());
                                    jsonPurposeObj.put("termination",servicesList.get(k-1).getPurposeDetails()
                                            .getTermination());
                                    jsonPurposeObj.put("thirdPartyDisclosure",servicesList.get(k-1).getPurposeDetails
                                            ().getThirdPartyDis());
                                    jsonPurposeObj.put("thirdPartyName",servicesList.get(k-1).getPurposeDetails()
                                            .getThirdPartyName());
                                    j--;
                                    break;
                                }
                            }
                        } else {
                            //--last one is printing twice, for that I put this if()
                            if (k < servicesList.size() - 1) {
                                //--empty pii arrays came, the I put this if()
                                if(!jsonPiiArr.isEmpty()) {
                                    jsonPurposeObj.put("piiCategory", jsonPiiArr);
                                    jsonPurposeObj.put("purpose", servicesList.get(k - 1).getPurposeDetails().getPurpose());
                                    jsonPurposeObj.put("purposeCategory",servicesList.get(k-1).getPurposeDetails()
                                            .getPurposeCatShortCode());
                                    jsonPurposeObj.put("primaryPurpose",servicesList.get(k-1).getPurposeDetails()
                                            .getPrimaryPurpose());
                                    jsonPurposeObj.put("termination",servicesList.get(k-1).getPurposeDetails()
                                            .getTermination());
                                    jsonPurposeObj.put("thirdPartyDisclosure",servicesList.get(k-1).getPurposeDetails
                                            ().getThirdPartyDis());
                                    jsonPurposeObj.put("thirdPartyName",servicesList.get(k-1).getPurposeDetails()
                                            .getThirdPartyName());
                                }
                            }
                            break;
                        }
                    }
                    //--There is an empty object at the end, to prevent it I put this if()
                    if (!jsonPurposeObj.isEmpty()) {
                        jsonPurposeArr.add(jsonPurposeObj);
                    }
                }
                jsonServiceObj[i].put("purposes", jsonPurposeArr);
                jsonServiceArr.add(jsonServiceObj[i]);
            } else if (!temp.equals(org.wso2.identity.carbon.user.consent.mgt.backend.constants.service.getServiceDescription())) {
                temp = org.wso2.identity.carbon.user.consent.mgt.backend.constants.service.getServiceDescription();
                jsonServiceObj[i] = new JSONObject();
                jsonServiceObj[i].put("serviceName", org.wso2.identity.carbon.user.consent.mgt.backend.constants.service.getServiceDescription());

                JSONArray jsonPurposeArr = new JSONArray();
                for (int j = i; j < servicesList.size(); j++) {
                    Services service1 = servicesList.get(j);
                    JSONObject jsonPurposeObj = new JSONObject();
                    JSONArray jsonPiiArr = new JSONArray();
                    int k;
                    for (k = j; k < servicesList.size(); k++) {
                        if (jsonServiceObj[i].get("serviceName").equals(servicesList.get(k).getServiceDescription())) {
                            if (k == 0) {
                                jsonPiiArr.add(servicesList.get(k).getPurposeDetails().getPiiCategory().getPiiCat());
                                j++;
                            } else {
                                if (service1.getPurposeDetails().getPurpose().equals(servicesList.get(k).getPurposeDetails()
                                        .getPurpose()
                                )) {
                                    jsonPiiArr.add(servicesList.get(k).getPurposeDetails().getPiiCategory().getPiiCat
                                            ());
                                    j++;
                                } else {
                                    jsonPurposeObj.put("piiCategory", jsonPiiArr);
                                    jsonPurposeObj.put("purpose", servicesList.get(k - 1).getPurposeDetails().getPurpose());
                                    jsonPurposeObj.put("purposeCategory",servicesList.get(k-1).getPurposeDetails()
                                            .getPurposeCatShortCode());
                                    jsonPurposeObj.put("primaryPurpose",servicesList.get(k-1).getPurposeDetails()
                                            .getPrimaryPurpose());
                                    jsonPurposeObj.put("termination",servicesList.get(k-1).getPurposeDetails()
                                            .getTermination());
                                    jsonPurposeObj.put("thirdPartyDisclosure",servicesList.get(k-1).getPurposeDetails
                                            ().getThirdPartyDis());
                                    jsonPurposeObj.put("thirdPartyName",servicesList.get(k-1).getPurposeDetails()
                                            .getThirdPartyName());
                                    j--;
                                    break;
                                }
                            }
                        } else {
                            jsonPurposeObj.put("piiCategory", jsonPiiArr);
                            jsonPurposeObj.put("purpose", servicesList.get(k - 1).getPurposeDetails().getPurpose());
                            jsonPurposeObj.put("purposeCategory",servicesList.get(k-1).getPurposeDetails()
                                    .getPurposeCatShortCode());
                            jsonPurposeObj.put("primaryPurpose",servicesList.get(k-1).getPurposeDetails()
                                    .getPrimaryPurpose());
                            jsonPurposeObj.put("termination",servicesList.get(k-1).getPurposeDetails()
                                    .getTermination());
                            jsonPurposeObj.put("thirdPartyDisclosure",servicesList.get(k-1).getPurposeDetails
                                    ().getThirdPartyDis());
                            jsonPurposeObj.put("thirdPartyName",servicesList.get(k-1).getPurposeDetails()
                                    .getThirdPartyName());
                            break;
                        }
                    }
                    jsonPurposeObj.put("piiCategory", jsonPiiArr);
                    jsonPurposeObj.put("purpose", servicesList.get(k - 1).getPurposeDetails().getPurpose());
                    jsonPurposeObj.put("purposeCategory",servicesList.get(k-1).getPurposeDetails()
                            .getPurposeCatShortCode());
                    jsonPurposeObj.put("primaryPurpose",servicesList.get(k-1).getPurposeDetails()
                            .getPrimaryPurpose());
                    jsonPurposeObj.put("termination",servicesList.get(k-1).getPurposeDetails()
                            .getTermination());
                    jsonPurposeObj.put("thirdPartyDisclosure",servicesList.get(k-1).getPurposeDetails
                            ().getThirdPartyDis());
                    jsonPurposeObj.put("thirdPartyName",servicesList.get(k-1).getPurposeDetails()
                            .getThirdPartyName());
                    jsonPurposeArr.add(jsonPurposeObj);
                }
                jsonServiceObj[i].put("purposes", jsonPurposeArr);
                jsonServiceArr.add(jsonServiceObj[i]);
            }

        }
        jsonMainObj.put("services", jsonServiceArr);*//*


    }

    public void validation() throws IOException, ParseException, DataAccessException {
        ConsentDaoImpl consentDaoImpl = new ConsentDaoImpl();
        JSONParser jsonParser = new JSONParser();

        Object object = jsonParser.parse(new FileReader
                ("/home/shan/gdpr_project/financial-open-banking/consent-shanchathusanda@gmail.com.json"));
        Gson gson=new Gson();
        Reader reader=new FileReader("/home/shan/gdpr_project/financial-open-banking/consent-shanchathusanda@gmail" +
                ".com.json");
        JSONObjectCreator objectCreator=gson.fromJson(reader,JSONObjectCreator.class);
        int dataControllerId=consentDaoImpl.isDataControllerExists(objectCreator.getDataController().getOrg());
        if(dataControllerId==0){
            DataControllerDO dataController=new DataControllerDO();
            dataController.setOrgName(objectCreator.getDataController().getOrg());
            dataController.setContactName(objectCreator.getDataController().getContact());
            dataController.setStreet(objectCreator.getDataController().getAddress().getStreetAddress());
            dataController.setCountry(objectCreator.getDataController().getAddress().getAddressCountry());
            dataController.setEmail(objectCreator.getDataController().getEmail());
            dataController.setPhoneNo(objectCreator.getDataController().getPhone());
            dataController.setPublicKey(objectCreator.getPublicKey());
            dataController.setPolicyUrl(objectCreator.getPolicyUrl());
            consentDaoImpl.addDataController(dataController);
        }

        readConsentReceipt();
    }*/

    public void readConsentReceipt() throws IOException, ParseException, DataAccessException {
        ConsentDao consentDao = new ConsentDao();
        /*JSONParser jsonParser = new JSONParser();

        Object object = jsonParser.parse(new FileReader
                ("/home/shan/gdpr_project/financial-open-banking/consent-shanchathusanda@gmail.com.json"));
        JSONObject jsonMainObject = (JSONObject) object;

        String subjectName = (String) jsonMainObject.get("subject");*/
        String uniqueId = UUID.randomUUID().toString();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        Date date = new Date();
        dateFormat.setTimeZone(TimeZone.getTimeZone("IST"));

        /*if (consentDaoImpl.isPiiPrincipalExists(subjectName) != 0) {
            Consent consent = new Consent();
            consent.setVersion((String) jsonMainObject.get("version"));
            consent.setJurisdiction((String) jsonMainObject.get("jurisdiction"));
            consent.setCollectionMethod((String) jsonMainObject.get("collectionMethod"));
            consent.setSGUID(uniqueId);
            consent.setPiiPrincipalId((String) jsonMainObject.get("subject"));
            consent.setConsentTimestamp(dateFormat.format(date));

            DataController dataController = new DataController();
            JSONObject dataControllerObj = (JSONObject) jsonMainObject.get("dataController");
            dataController.setDataControllerId(consentDaoImpl.isDataControllerExists((String) dataControllerObj.get
                    ("org")));

            consent.setDataController(dataController);

            JSONArray jsonServiceArr = new JSONArray();
            jsonServiceArr.add(jsonMainObject.get("services"));
            jsonServiceArr.get(0);
            Services[] org.wso2.identity.carbon.user.consent.mgt.backend.constants.service = new Services[jsonServiceArr.size()];
            JSONObject[] jsonServiceObj = new JSONObject[jsonServiceArr.size()];
            for (int i = 0; i < jsonServiceArr.size(); i++) {
                if (i == 0) {
                    jsonServiceObj[i] = new JSONObject();
                    jsonServiceObj[i].put("org.wso2.identity.carbon.user.consent.mgt.backend.constants.service", jsonServiceArr);
                    org.wso2.identity.carbon.user.consent.mgt.backend.constants.service[i] = new Services();
                    org.wso2.identity.carbon.user.consent.mgt.backend.constants.service[i].setServiceDescription((String) jsonServiceObj[i].get("serviceName"));
                    System.out.println(org.wso2.identity.carbon.user.consent.mgt.backend.constants.service[i].getServiceDescription());
                }
            }
        } else {
            System.out.println("Your subject name is already in the database, please select another subject name");
        }*/

        /* The code below this comment is the correct code

        Gson gson=new Gson();

        Reader reader=new FileReader("/home/shan/gdpr_project/financial-open-banking/consent-shanchathusanda@gmail" +
                ".com.json");

        JSONObjectCreator objectCreator=gson.fromJson(reader,JSONObjectCreator.class);

        int dataControllerId=consentDaoImpl.isDataControllerExists(objectCreator.getDataController().getOrg());

        *//*getting data to actual objects*//*
        Consent consent=new Consent();
        consent.setVersion(objectCreator.getVersion());
        consent.setJurisdiction(objectCreator.getJurisdiction());
        consent.setCollectionMethod(objectCreator.getCollectionMethod());
        consent.setSGUID(uniqueId);
        consent.setPiiPrincipalId(objectCreator.getSubject());
        consent.setConsentTimestamp(dateFormat.format(date));

        DataController dataController=new DataController();
        if(dataControllerId!=0){
            dataController.setDataControllerId(dataControllerId);
        }else{
            dataController.setOrgName(objectCreator.getDataController().getOrg());
            dataController.setContactName(objectCreator.getDataController().getContact());
            dataController.setStreet(objectCreator.getDataController().getAddress().getStreetAddress());
            dataController.setCountry(objectCreator.getDataController().getAddress().getAddressCountry());
            dataController.setEmail(objectCreator.getDataController().getEmail());
            dataController.setPhoneNo(objectCreator.getDataController().getPhone());
            dataController.setPublicKey(objectCreator.getPublicKey());
            dataController.setPolicyUrl(objectCreator.getPolicyUrl());
            consentDaoImpl.addDataController(dataController);
            dataControllerId=consentDaoImpl.isDataControllerExists(objectCreator.getDataController().getOrg());
            dataController.setDataControllerId(dataControllerId);
        }
        consent.setDataController(dataController);

        Services[] services=new Services[objectCreator.getServices().length];
        for(int i=0;i<objectCreator.getServices().length;i++){
            services[i]=new Services();
            int serviceId=0;
            try {
                serviceId=consentDaoImpl.getServiceIdByService(objectCreator.getServices()[i].getServiceName());
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
            if(serviceId!=0){
                services[i].setServiceId(serviceId);
                PurposeDetails[] purpose=new PurposeDetails[objectCreator.getServices()[i].getPurposes().length];
                for(int j=0;j<objectCreator.getServices()[i].getPurposes().length;j++){
                    purpose[j]=new PurposeDetails();
                    int purposeId=0;
                    try {
                        purposeId=consentDaoImpl.getPurposeIdByPurpose(objectCreator.getServices()[i].getPurposes()[j]
                                .getPurpose());
                    } catch (DataAccessException e) {
                        e.printStackTrace();
                    }
                    if(purposeId!=0){
                        purpose[j].setPurposeId(purposeId);
                        purpose[j].setTimestamp(dateFormat.format(date));
                        purpose[j].setCollectionMethod("Web-Form");
                    }else{
                        System.out.println("Purpose cant be recognized");
                    }
                }
                services[i].setPurposeDetails(purpose);
            }
        }
        consentDaoImpl.addUserConsentDetails(consent,services);*/
    }
}
