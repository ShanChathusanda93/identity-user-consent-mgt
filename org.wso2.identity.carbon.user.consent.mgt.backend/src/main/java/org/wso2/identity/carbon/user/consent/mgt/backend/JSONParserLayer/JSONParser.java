package org.wso2.identity.carbon.user.consent.mgt.backend.JSONParserLayer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/*import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;*/
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.wso2.identity.carbon.user.consent.mgt.backend.DAO.ConsentDao;
import org.wso2.identity.carbon.user.consent.mgt.backend.constants.ConsentReceiptConstants;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ConsentDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.DataControllerDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PiiCategoryDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PurposeDetailsDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ServicesDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.exception.DataAccessException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//import org.wso2.carbon.identity.consent.mgt.data.org.wso2.identity.carbon.user.consent.mgt.backend.constants.ConsentReceiptConstants;

public class JSONParser {
    public static void main(String[] args) {
        JSONParser jsonParser = new JSONParser();
        JSONObject object = new JSONObject();

        object.put("version", "KI-CR-1");
        object.put("jurisdiction", "DW");
        object.put("collectionMethod", "Web-form");
        object.put("SGUID", "abcd");
        object.put("piiPrincipalId", "shanj@wso2.com");
        object.put("consentTimestamp", "1232342342341");
        object.put("dataControllerId", "DC01");

        JSONArray serviceArray = new JSONArray();
        JSONObject jsonService;
        jsonService = new JSONObject();
        jsonService.put("serviceId", "SID01");
        jsonService.put("purposeId", "PID01");
        serviceArray.add(jsonService);

        jsonService = new JSONObject();
        jsonService.put("serviceId", "SID01");
        jsonService.put("purposeId", "PID02");
        serviceArray.add(jsonService);
        object.put("services", serviceArray);
        try {
            jsonParser.createConsentReceipt("shanchathusanda@gmail.com");
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*try {
            jsonParser.readConsentFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }*/

    }

    /**
     * Reads the JSON object and create the corresponding objects and send them to the org.wso2.identity.carbon.user.consent.mgt.backend.constants.DAO layer
     *
     * @param jsonObject
     */
    /*public void addUserConsent(JSONObject jsonObject) {
        int i;
        Consent consent = new Consent();
        DataControllerDO dataController = new DataControllerDO();
        ConsentDaoImpl consentDaoImpl = new ConsentDaoImpl();
        consent.setSGUID((String) jsonObject.get("SGUID"));
        consent.setVersion((String) jsonObject.get(ConsentReceiptConstants.VERSION));
        consent.setJurisdiction((String) jsonObject.get(ConsentReceiptConstants.JURISDICTION));
        consent.setCollectionMethod((String) jsonObject.get(ConsentReceiptConstants.COLLECTION_METHOD));
        consent.setPiiPrincipalId((String) jsonObject.get(ConsentReceiptConstants.SUBJECT));
        consent.setConsentTimestamp((String) jsonObject.get(ConsentReceiptConstants.CONSENT_TIMESTAMP));
        dataController.setDataControllerId((Integer) jsonObject.get("dataControllerId"));
        consent.setDataController(dataController);

        JSONArray servicesArray = (JSONArray) jsonObject.get("services");
        ServicesDO[] services = new ServicesDO[servicesArray.size()];

        for (i = 0; i < servicesArray.size(); i++) {
            JSONObject jsonService = (JSONObject) servicesArray.get(i);
            services[i] = new ServicesDO();
            services[i].setServiceId((Integer) jsonService.get("serviceId"));
            PurposeDetailsDO purpose = new PurposeDetailsDO();
            purpose.setPurposeId((Integer) jsonService.get("purposeId"));
            services[i].setPurposeDetails(purpose);
        }
        try {
            consentDaoImpl.addUserConsentDetails(consent, services);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * Creates the consent receipt for the user
     *
     * @param piiPrincipalId
     * @throws DataAccessException
     */
    public JSONObject createConsentReceipt(String piiPrincipalId) throws DataAccessException, ParseException {
        ConsentDao consentDao = new ConsentDao(piiPrincipalId);
        List<ConsentDO> consentDOList = consentDao.getUserConsent();
        ConsentDO consentDO = consentDOList.get(0);

        JSONObject jsonObject = new JSONObject();

        jsonObject = setUserAndDataControllerDetails(jsonObject, consentDO);

        consentDao = new ConsentDao(consentDO.getSGUID(), true);
        List<ServicesDO> servicesList = consentDao.getServices();
        if (servicesList.size() != 0) {
            List<PurposeDetailsDO> purposeCategoryDetailsList = consentDao.getPurposeCategories(consentDO.getSGUID());
            String temp = "";
            JSONArray jsonServiceArr = new JSONArray();
            JSONObject[] jsonServiceObj = new JSONObject[servicesList.size()];
            for (int i = 0; i < servicesList.size(); i++) {
                ServicesDO service = servicesList.get(i);
                if (i == 0) {
                    jsonServiceObj[i] = new JSONObject();
                    temp = service.getServiceDescription();
                    jsonServiceObj[i].put(ConsentReceiptConstants.SERVICE_NAME, service.getServiceDescription());

                    JSONObject[] jsonPurposeObj = new JSONObject[servicesList.size()];
                    JSONArray jsonPurposeArr = new JSONArray();
                    String tempPurpose = "";
                    for (int j = 0; j < servicesList.size(); j++) {
                        PurposeDetailsDO purpose = servicesList.get(j).getPurposeDetails();
                        if (temp.equals(servicesList.get(j).getServiceDescription())) {
                            if (j == 0) {
                                jsonPurposeObj[j] = new JSONObject();
                                tempPurpose = purpose.getPurpose();

                                jsonPurposeObj[j] = setPurposeDetails(j, servicesList, temp, tempPurpose, purpose,
                                        purposeCategoryDetailsList, jsonPurposeObj[j]);

                            } else {
                                if (!tempPurpose.equals(purpose.getPurpose())) {
                                    jsonPurposeObj[j] = new JSONObject();
                                    tempPurpose = purpose.getPurpose();

                                    jsonPurposeObj[j] = setPurposeDetails(j, servicesList, temp, tempPurpose, purpose,
                                            purposeCategoryDetailsList, jsonPurposeObj[j]);
                                }
                            }
                            if (jsonPurposeObj[j] != null) {
                                jsonPurposeArr.add(jsonPurposeObj[j]);
                            }
                        } else {
                            break;
                        }
                    }
                    jsonServiceObj[i].put(ConsentReceiptConstants.PURPOSES, jsonPurposeArr);
                    jsonServiceArr.add(jsonServiceObj[i]);
                } else {
                    if (!temp.equals(service.getServiceDescription())) {
                        jsonServiceObj[i] = new JSONObject();
                        jsonServiceObj[i].put(ConsentReceiptConstants.SERVICE_NAME, service.getServiceDescription());
                        temp = service.getServiceDescription();

                        JSONObject[] jsonPurposeObj = new JSONObject[servicesList.size()];
                        JSONArray jsonPurposeArr = new JSONArray();
                        String tempPurpose = "";
                        for (int j = i; j < servicesList.size(); j++) {
                            PurposeDetailsDO purpose = servicesList.get(j).getPurposeDetails();
                            if (temp.equals(servicesList.get(j).getServiceDescription())) {
                                if (j == i) {
                                    jsonPurposeObj[j] = new JSONObject();
                                    tempPurpose = purpose.getPurpose();

                                    jsonPurposeObj[j] = setPurposeDetails(j, servicesList, temp, tempPurpose, purpose,
                                            purposeCategoryDetailsList, jsonPurposeObj[j]);
                                } else {
                                    if (!tempPurpose.equals(purpose.getPurpose())) {
                                        jsonPurposeObj[j] = new JSONObject();
                                        tempPurpose = purpose.getPurpose();

                                        jsonPurposeObj[j] = setPurposeDetails(j, servicesList, temp, tempPurpose, purpose,
                                                purposeCategoryDetailsList, jsonPurposeObj[j]);
                                    }
                                }
                                if (jsonPurposeObj[j] != null) {
                                    jsonPurposeArr.add(jsonPurposeObj[j]);
                                }
                            } else {
                                break;
                            }
                        }
                        jsonServiceObj[i].put(ConsentReceiptConstants.PURPOSES, jsonPurposeArr);
                        if (!jsonServiceObj[i].isEmpty()) {
                            jsonServiceArr.add(jsonServiceObj[i]);
                        }
                    }
                }
            }
            jsonObject.put(ConsentReceiptConstants.SERVICES, jsonServiceArr);

            List<PiiCategoryDO> piiCategoryList;
            piiCategoryList = consentDao.getSensitivePersonalInfoCategory(consentDO.getSGUID());
            jsonObject = setSensitivePersonalInfoCategory(jsonObject, piiCategoryList);

          /*  try {
                trackConsentReceipt(jsonObject, consentDO.getSGUID());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }*/
        } else {
            System.out.println("You have not given any approved consents to the system.");
        }
        return jsonObject;
    }

    /**
     * Setting User and Data Controller details to the consent receipt JSON object
     *
     * @param jsonObject is the main JSON object
     * @param consentDO    is the object which contains the required data
     * @return the main JSON object
     */
    private JSONObject setUserAndDataControllerDetails(JSONObject jsonObject, ConsentDO consentDO) {
        jsonObject.put(ConsentReceiptConstants.VERSION, ConsentReceiptConstants.VERSION_NUMBER);
        jsonObject.put(ConsentReceiptConstants.JURISDICTION, "DW"); //--This must be collected from the user's country
        jsonObject.put(ConsentReceiptConstants.CONSENT_TIMESTAMP, consentDO.getConsentTimestamp());
        jsonObject.put(ConsentReceiptConstants.COLLECTION_METHOD, consentDO.getCollectionMethod());
        jsonObject.put(ConsentReceiptConstants.CONSENT_RECEIPT_ID, createUniqueId());
        jsonObject.put(ConsentReceiptConstants.PUBLIC_KEY, consentDO.getDataController().getPublicKey());
        jsonObject.put(ConsentReceiptConstants.SUBJECT, consentDO.getPiiPrincipalId());
        jsonObject.put(ConsentReceiptConstants.POLICY_URL, consentDO.getDataController().getPolicyUrl());

        JSONObject jsonDataController = new JSONObject();
        jsonDataController.put(ConsentReceiptConstants.ORGANIZATION_NAME, consentDO.getDataController().getOrgName());
        jsonDataController.put(ConsentReceiptConstants.CONTACT_NAME, consentDO.getDataController().getContactName());

        JSONObject jsonAddress = new JSONObject();
        jsonAddress.put(ConsentReceiptConstants.STREET, consentDO.getDataController().getStreet());
        jsonAddress.put(ConsentReceiptConstants.COUNTRY, consentDO.getDataController().getCountry());
        jsonDataController.put(ConsentReceiptConstants.ADDRESS, jsonAddress);

        jsonDataController.put(ConsentReceiptConstants.EMAIL, consentDO.getDataController().getEmail());
        jsonDataController.put(ConsentReceiptConstants.PHONE, consentDO.getDataController().getPhoneNo());
        jsonObject.put(ConsentReceiptConstants.DATA_CONTROLLER, jsonDataController);

        return jsonObject;
    }

    private JSONObject setPurposeDetails(int j, List<ServicesDO> servicesList, String temp, String tempPurpose,
                                         PurposeDetailsDO purpose, List<PurposeDetailsDO> purposeCategoryDetailsList,
                                         JSONObject jsonPurposeObj) {
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
        jsonPurposeObj.put(ConsentReceiptConstants.PERSONALLY_IDENTIFIABLE_INFO_CATEGORY, jsonPiiArr);
        jsonPurposeObj.put(ConsentReceiptConstants.PURPOSE, purpose.getPurpose());
        JSONArray jsonPurposeCatArr = new JSONArray();
        for (PurposeDetailsDO purposeDetails : purposeCategoryDetailsList) {
            if (purpose.getPurpose().equals(purposeDetails.getPurpose())) {
                jsonPurposeCatArr.add(purposeDetails.getPurposeCatId() + " - " + purposeDetails
                        .getPurposeCatShortCode());
            }
        }
        jsonPurposeObj.put(ConsentReceiptConstants.PURPOSE_CATEGORY, jsonPurposeCatArr);
        if (purpose.getPrimaryPurpose().equals("1")) {
            jsonPurposeObj.put(ConsentReceiptConstants.PRIMARY_PURPOSE, "Yes");
        } else {
            jsonPurposeObj.put(ConsentReceiptConstants.PRIMARY_PURPOSE, "No");
        }
        jsonPurposeObj.put(ConsentReceiptConstants.TERMINATION, purpose.getTermination());
        if (purpose.getThirdPartyDis().equals("1")) {
            jsonPurposeObj.put(ConsentReceiptConstants.THIRD_PARTY_DISCLOSURE, "Yes");
        } else {
            jsonPurposeObj.put(ConsentReceiptConstants.THIRD_PARTY_DISCLOSURE, "No");
        }
        jsonPurposeObj.put(ConsentReceiptConstants.THIRD_PARTY_NAME, purpose
                .getThirdPartyName());

        return jsonPurposeObj;
    }

    /**
     * Setting sensitive personal info categories to the consent receipt JSON object
     *
     * @param jsonObject      is the main JSON object
     * @param piiCategoryList is the sensitive personal info category list
     * @return the main object
     */
    private JSONObject setSensitivePersonalInfoCategory(JSONObject jsonObject, List<PiiCategoryDO> piiCategoryList) {
        if (piiCategoryList.isEmpty()) {
            jsonObject.put(ConsentReceiptConstants.SENSITIVE, false);
        } else {
            jsonObject.put(ConsentReceiptConstants.SENSITIVE, true);
            JSONArray sensitivePICatArr = new JSONArray();
            for (PiiCategoryDO piiCategory : piiCategoryList) {
                sensitivePICatArr.add(piiCategory.getPiiCatId() + " - " + piiCategory.getPiiCat());
            }
            jsonObject.put(ConsentReceiptConstants.SENSITIVE_PERSONAL_INFO_CATEGORY, sensitivePICatArr);
        }
        return jsonObject;
    }

    private void createConsentReceiptFile(JSONObject jsonObject, String piiPrincipalId) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(jsonObject);

            FileWriter fileWriter = new FileWriter
                    ("/home/shan/gdpr_project/financial-open-banking/consent-" + piiPrincipalId + ".json");
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*private void trackConsentReceipt(JSONObject jsonObject, String SGUID) throws UnsupportedEncodingException, ParseException {
        ConsentDao consentDao = new ConsentDao();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");

        String consentReceiptJWT = Jwts.builder()
                .setSubject("WSO2")
                .setId("wso2123")
                .setIssuedAt(dateFormat.parse(getCurrentDateTime()))
                .setExpiration(new Date())
                .claim("consentJson", jsonObject)
                .signWith(SignatureAlgorithm.HS256, "secret".getBytes("UTF-8"))
                .compact();
        try {
            consentDao.trackConsentReceipt(consentReceiptJWT, (String) jsonObject.get("consentReceiptID"), SGUID, getCurrentDateTime());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }*/

    private String createUniqueId() {
        String uniqueId = UUID.randomUUID().toString();
        return uniqueId;
    }

    private String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String currentTime = dateFormat.format(calendar.getTime());
        return currentTime;
    }

    public void readConsentFile() throws FileNotFoundException, DataAccessException {
        Gson gson = new Gson();

        Reader reader = new FileReader("/home/shan/gdpr_project/financial-open-banking/consent-shanchathusanda@gmail" +
                ".com.json");

        JSONObjectCreator objectCreator = gson.fromJson(reader, JSONObjectCreator.class);
        readConsentReceipt(objectCreator);
    }

    /**
     * This method read the consent receipt from the user and add to the database
     *
     * @throws FileNotFoundException
     * @throws DataAccessException
     */
    private void readConsentReceipt(JSONObjectCreator objectCreator) throws DataAccessException {
        ConsentDao consentDao = new ConsentDao();

        String uniqueId = createUniqueId();
        String currentTime = getCurrentDateTime();

        int dataControllerId = consentDao.isDataControllerExists(objectCreator.getDataController().getOrg());

        /*getting data to actual objects*/
        ConsentDO consentDO = new ConsentDO();
        consentDO.setCollectionMethod(objectCreator.getCollectionMethod());
        consentDO.setSGUID(uniqueId);
        consentDO.setPiiPrincipalId(objectCreator.getSubject());
        consentDO.setConsentTimestamp(currentTime);

        DataControllerDO dataController = new DataControllerDO();
        if (dataControllerId != 0) {
            dataController.setDataControllerId(dataControllerId);
        } else {
            dataController.setOrgName(objectCreator.getDataController().getOrg());
            dataController.setContactName(objectCreator.getDataController().getContact());
            dataController.setStreet(objectCreator.getDataController().getAddress().getStreetAddress());
            dataController.setCountry(objectCreator.getDataController().getAddress().getAddressCountry());
            dataController.setEmail(objectCreator.getDataController().getEmail());
            dataController.setPhoneNo(objectCreator.getDataController().getPhone());
            dataController.setPublicKey(objectCreator.getPublicKey());
            dataController.setPolicyUrl(objectCreator.getPolicyUrl());
            consentDao.addDataController(dataController);
            dataControllerId = consentDao.isDataControllerExists(objectCreator.getDataController().getOrg());
            dataController.setDataControllerId(dataControllerId);
        }
        consentDO.setDataController(dataController);

        ServicesDO[] services = new ServicesDO[objectCreator.getServices().length];
        for (int i = 0; i < objectCreator.getServices().length; i++) {
            services[i] = new ServicesDO();
            int serviceId = 0;
            try {
                serviceId = consentDao.getServiceIdByService(objectCreator.getServices()[i].getServiceName());
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
            PurposeDetailsDO[] purposeDO = new PurposeDetailsDO[objectCreator.getServices()[i].getPurposes()
                    .length];
            if (serviceId != 0) {
                services[i].setServiceId(serviceId);
                for (int j = 0; j < objectCreator.getServices()[i].getPurposes().length; j++) {
                    purposeDO[j] = new PurposeDetailsDO();
                    int purposeId = 0;
                    try {
                        purposeId = consentDao.getPurposeIdByPurpose(objectCreator.getServices()[i].getPurposes()[j]
                                .getPurpose());
                    } catch (DataAccessException e) {
                        e.printStackTrace();
                    }
                    if (purposeId != 0) {
                        int termination = consentDao.getPurposeTerminationDays(purposeId);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(new Date());
                        cal.add(Calendar.DATE, termination);
                        purposeDO[j].setExactTermination(dateFormat.format(cal.getTime()));
                        purposeDO[j].setPurposeId(purposeId);
                        purposeDO[j].setTimestamp(currentTime);
                        purposeDO[j].setCollectionMethod("Web-Form");
                    } else
                        System.out.println("Purpose cant be recognized");
                    }
                }
                services[i].setPurposeDetails(purposeDO);
            }
        consentDao.addUserAndDataControllerDetails(consentDO);
        consentDao.addUserConsentDetails(consentDO, services);
    }
}

