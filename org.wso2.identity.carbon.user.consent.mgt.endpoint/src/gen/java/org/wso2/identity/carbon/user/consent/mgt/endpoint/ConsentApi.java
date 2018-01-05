package org.wso2.identity.carbon.user.consent.mgt.endpoint;

import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.*;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.ConsentApiService;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.factories.ConsentApiServiceFactory;

import io.swagger.annotations.ApiParam;

import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.DataControllerInputDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PiiCategoryDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PiiCatListDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PurposeCategoryDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PurposeCategoryListDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PurposeDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PurposeListDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ServiceWebFormDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ThirdPartyDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ThirdPartyListDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ConsentReceiptDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.UserConsentWebFormDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ConsentRevokeListDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ServiceListDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ServiceCRDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ConsentByThirdPartyDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path("/consent")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(value = "/consent", description = "the consent API")
public class ConsentApi  {

   private final ConsentApiService delegate = ConsentApiServiceFactory.getConsentApi();

    @DELETE
    @Path("/configuration/dataController")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Delete data controller", notes = "Delete data controller details from the consent database", response = DataControllerInputDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful Delete"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Content not found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error") })

    public Response consentConfigurationDataControllerDelete(@ApiParam(value = "Name of the data controller to be get",required=true) @QueryParam("dataControllerId")  Integer dataControllerId)
    {
    return delegate.consentConfigurationDataControllerDelete(dataControllerId);
    }
    @GET
    @Path("/configuration/dataController")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Getting all data controllers details", notes = "Getting all data controllers details for configuration", response = DataControllerInputDTO.class, responseContainer = "List")
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful Operation"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Content not found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error") })

    public Response consentConfigurationDataControllerGet()
    {
    return delegate.consentConfigurationDataControllerGet();
    }
    @GET
    @Path("/configuration/data-controller/id")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Getting data controller details", notes = "Getting data controller details by the id", response = DataControllerInputDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "content not found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error") })

    public Response consentConfigurationDataControllerIdGet(@ApiParam(value = "Name of the data controller to be get",required=true) @QueryParam("dataControllerId")  Integer dataControllerId)
    {
    return delegate.consentConfigurationDataControllerIdGet(dataControllerId);
    }
    @POST
    @Path("/configuration/dataController")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Adding data controller", notes = "Adding details of the data controller to the consent database", response = DataControllerInputDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "successfully created the data controller"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error") })

    public Response consentConfigurationDataControllerPost(@ApiParam(value = "Details of the data controller" ,required=true ) DataControllerInputDTO dataController)
    {
    return delegate.consentConfigurationDataControllerPost(dataController);
    }
    @PUT
    @Path("/configuration/dataController")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Updating the data controller details", notes = "Updating the data controller details of the consent database", response = DataControllerInputDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successfully updated the data controller"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error") })

    public Response consentConfigurationDataControllerPut(@ApiParam(value = "Details of the data controller" ,required=true ) DataControllerInputDTO dataController)
    {
    return delegate.consentConfigurationDataControllerPut(dataController);
    }
    @GET
    @Path("/configuration/personal-info-cat/id")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Getting a personal info cat", notes = "Getting details of a personal info cat by id", response = PiiCategoryDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful Operation"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Content not found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error") })

    public Response consentConfigurationPersonalInfoCatIdGet(@ApiParam(value = "Category id",required=true) @QueryParam("categoryId")  Integer categoryId)
    {
    return delegate.consentConfigurationPersonalInfoCatIdGet(categoryId);
    }
    @DELETE
    @Path("/configuration/personalInfoCategory")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Delete personally identifiable info category", notes = "Delete personally identifiable info category by category id", response = PiiCategoryDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful Delete"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Content not found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error") })

    public Response consentConfigurationPersonalInfoCategoryDelete(@ApiParam(value = "Category id",required=true) @QueryParam("categoryId")  Integer categoryId)
    {
    return delegate.consentConfigurationPersonalInfoCategoryDelete(categoryId);
    }
    @GET
    @Path("/configuration/personalInfoCategory")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Getting PII categories", notes = "Getting personally identifiable info categories from the database", response = PiiCatListDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "content not found") })

    public Response consentConfigurationPersonalInfoCategoryGet()
    {
    return delegate.consentConfigurationPersonalInfoCategoryGet();
    }
    @POST
    @Path("/configuration/personalInfoCategory")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Adding a PII category", notes = "Adding a personally identifiable info category to the consent database", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successfully added personally identifiable information category to the consent database"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error") })

    public Response consentConfigurationPersonalInfoCategoryPost(@ApiParam(value = "Details of a personally identifiable info category" ,required=true ) PiiCategoryDTO piiCategory)
    {
    return delegate.consentConfigurationPersonalInfoCategoryPost(piiCategory);
    }
    @PUT
    @Path("/configuration/personalInfoCategory")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Updating PII categories", notes = "Updating personally identifiable info categories in the consent database", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "content not found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error") })

    public Response consentConfigurationPersonalInfoCategoryPut(@ApiParam(value = "Details of a personally identifiable info category" ,required=true ) PiiCategoryDTO piiCategory)
    {
    return delegate.consentConfigurationPersonalInfoCategoryPut(piiCategory);
    }
    @DELETE
    @Path("/configuration/purpose-category")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Delete purpose category", notes = "Delete purpose category from the consent database", response = PurposeCategoryDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful Delete"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Content not found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error") })

    public Response consentConfigurationPurposeCategoryDelete(@ApiParam(value = "Purpose category id",required=true) @QueryParam("purposeCategoryId")  Integer purposeCategoryId)
    {
    return delegate.consentConfigurationPurposeCategoryDelete(purposeCategoryId);
    }
    @GET
    @Path("/configuration/purpose-category")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get purpose category", notes = "Get purpose category details from the consent database", response = PurposeCategoryListDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Content not found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error") })

    public Response consentConfigurationPurposeCategoryGet()
    {
    return delegate.consentConfigurationPurposeCategoryGet();
    }
    @GET
    @Path("/configuration/purpose-category/id")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get purpose category details", notes = "Get purpose category details from purpose category id", response = PurposeCategoryDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Content not found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error") })

    public Response consentConfigurationPurposeCategoryIdGet(@ApiParam(value = "Category id",required=true) @QueryParam("categoryId")  Integer categoryId)
    {
    return delegate.consentConfigurationPurposeCategoryIdGet(categoryId);
    }
    @POST
    @Path("/configuration/purpose-category")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Add purpose category", notes = "Add a purpose category to the consent database", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successfully created the purpose category"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error") })

    public Response consentConfigurationPurposeCategoryPost(@ApiParam(value = "purpose category details" ,required=true ) PurposeCategoryDTO purposeCategory)
    {
    return delegate.consentConfigurationPurposeCategoryPost(purposeCategory);
    }
    @PUT
    @Path("/configuration/purpose-category")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update purpose category", notes = "Update purpose category details to the consent database", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successfully updated the purpose category"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Content not found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal error") })

    public Response consentConfigurationPurposeCategoryPut(@ApiParam(value = "purpose category details" ,required=true ) PurposeCategoryDTO purposeCategory)
    {
    return delegate.consentConfigurationPurposeCategoryPut(purposeCategory);
    }
    @DELETE
    @Path("/configuration/purpose")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Delete purpose", notes = "Delete purpose by purpose id", response = PurposeDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful Delete"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Content not found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error") })

    public Response consentConfigurationPurposeDelete(@ApiParam(value = "Category id",required=true) @QueryParam("categoryId")  Integer categoryId)
    {
    return delegate.consentConfigurationPurposeDelete(categoryId);
    }
    @GET
    @Path("/configuration/purpose")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Getting purpose details", notes = "Getting purpose details from the consent database", response = PurposeListDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "purposes not found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error") })

    public Response consentConfigurationPurposeGet()
    {
    return delegate.consentConfigurationPurposeGet();
    }
    @GET
    @Path("/configuration/purpose/id")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get details of a purpose", notes = "Get details of a purpose by purpose id", response = PurposeDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Content not found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error") })

    public Response consentConfigurationPurposeIdGet(@ApiParam(value = "Category id",required=true) @QueryParam("categoryId")  Integer categoryId)
    {
    return delegate.consentConfigurationPurposeIdGet(categoryId);
    }
    @POST
    @Path("/configuration/purpose")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Adding purpose details", notes = "Adding details of a purpose to the consent database", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successfully created the purpose"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error") })

    public Response consentConfigurationPurposePost(@ApiParam(value = "Details of a purpose" ,required=true ) PurposeDTO purpose)
    {
    return delegate.consentConfigurationPurposePost(purpose);
    }
    @PUT
    @Path("/configuration/purpose")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update purpose details", notes = "Update details of a purpose in the consent database", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "purpose not found"),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "method not allowed") })

    public Response consentConfigurationPurposePut(@ApiParam(value = "Details of a purpose" ,required=true ) PurposeDTO purpose)
    {
    return delegate.consentConfigurationPurposePut(purpose);
    }
    @DELETE
    @Path("/configuration/service")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Delete service", notes = "Delete service by service id", response = ServiceWebFormDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful Delete"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Content not found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error") })

    public Response consentConfigurationServiceDelete(@ApiParam(value = "Category id",required=true) @QueryParam("categoryId")  Integer categoryId)
    {
    return delegate.consentConfigurationServiceDelete(categoryId);
    }
    @GET
    @Path("/configuration/service")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Getting services", notes = "Getting details of services from the database", response = ServiceWebFormDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "content not found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error") })

    public Response consentConfigurationServiceGet()
    {
    return delegate.consentConfigurationServiceGet();
    }
    @GET
    @Path("/configuration/service/id")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get a service by id", notes = "Get details of a service from id", response = ServiceWebFormDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful Operation"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Content not found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error") })

    public Response consentConfigurationServiceIdGet(@ApiParam(value = "Category id",required=true) @QueryParam("categoryId")  Integer categoryId)
    {
    return delegate.consentConfigurationServiceIdGet(categoryId);
    }
    @POST
    @Path("/configuration/service")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Adding a service", notes = "Adding details of a service to the consent database", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successfully created the service"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error") })

    public Response consentConfigurationServicePost(@ApiParam(value = "Details of a service" ,required=true ) ServiceWebFormDTO service)
    {
    return delegate.consentConfigurationServicePost(service);
    }
    @PUT
    @Path("/configuration/service")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Updating a service", notes = "Updating details of a service in the consent database", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "content not found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error") })

    public Response consentConfigurationServicePut(@ApiParam(value = "Details of a service" ,required=true ) ServiceWebFormDTO service)
    {
    return delegate.consentConfigurationServicePut(service);
    }
    @DELETE
    @Path("/configuration/third-party")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Delete third party", notes = "Delete third party by third party id", response = ThirdPartyDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful Delete"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Content not found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error") })

    public Response consentConfigurationThirdPartyDelete(@ApiParam(value = "Category id",required=true) @QueryParam("categoryId")  Integer categoryId)
    {
    return delegate.consentConfigurationThirdPartyDelete(categoryId);
    }
    @GET
    @Path("/configuration/third-party")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get third parties", notes = "Get third party details from the consent database", response = ThirdPartyListDTO.class, responseContainer = "List")
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successful operation."),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Content not found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error") })

    public Response consentConfigurationThirdPartyGet()
    {
    return delegate.consentConfigurationThirdPartyGet();
    }
    @POST
    @Path("/configuration/third-party")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Add third party", notes = "Add third party details to the consent database", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successfully created the third party."),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error") })

    public Response consentConfigurationThirdPartyPost(@ApiParam(value = "third party details" ,required=true ) ThirdPartyDTO thirdParty)
    {
    return delegate.consentConfigurationThirdPartyPost(thirdParty);
    }
    @PUT
    @Path("/configuration/third-party")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update third party", notes = "Update third party details to the database", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successfully updated the third party to the consent database"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error") })

    public Response consentConfigurationThirdPartyPut(@ApiParam(value = "third party details" ,required=true ) ThirdPartyDTO thirdParty)
    {
    return delegate.consentConfigurationThirdPartyPut(thirdParty);
    }
    @POST
    @Path("/receipt")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Adding consent details", notes = "Adding user consent details from a JSON which is provided by the user", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "successfully created the consents for the user"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "invalid consent supplied"),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "conflict when creating the consents (mostly the subject name)") })

    public Response consentReceiptPost(@ApiParam(value = "Details of the user which is giving the consents" ,required=true ) ConsentReceiptDTO userDetails)
    {
    return delegate.consentReceiptPost(userDetails);
    }
    @POST
    @Path("/receipt/webForm")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Adding consent details", notes = "Adding user consent details from a web form which is provided by the user", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "successfully created the consent"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error") })

    public Response consentReceiptWebFormPost(@ApiParam(value = "Details of the user consent which is given through a web form" ,required=true ) UserConsentWebFormDTO userConsentWebForm)
    {
    return delegate.consentReceiptWebFormPost(userConsentWebForm);
    }
    @PUT
    @Path("/revoke")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Revoke consent of an user", notes = "Revoke consent of an user by service and purpose", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error") })

    public Response consentRevokePut(@ApiParam(value = "Details for the consent revoke" ,required=true ) ConsentRevokeListDTO revokingConsent)
    {
    return delegate.consentRevokePut(revokingConsent);
    }
    @GET
    @Path("/{user-name}/receipt")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Creating consent receipt", notes = "Getting consent details of an user to create the consent receipt", response = ConsentReceiptDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "invalid subject name supplied"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "consent for this subject name not found") })

    public Response consentUserNameReceiptGet(@ApiParam(value = "user name to filter users",required=true ) @PathParam("user-name")  String userName)
    {
    return delegate.consentUserNameReceiptGet(userName);
    }
    @GET
    @Path("/{user-name}/serviceList")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Getting service details", notes = "Getting details of consented services by an user", response = ServiceListDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "invalid subject name supplied"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "services not found for this subject name") })

    public Response consentUserNameServiceListGet(@ApiParam(value = "user name to filter users",required=true ) @PathParam("user-name")  String userName)
    {
    return delegate.consentUserNameServiceListGet(userName);
    }
    @GET
    @Path("/{user-name}/services/{serviceId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Getting services by service id", notes = "Getting service details regarding to the subject name and the service id", response = ServiceCRDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "invalid subject name supplied"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "services not found for this subject name") })

    public Response consentUserNameServicesServiceIdGet(@ApiParam(value = "user name to filter users",required=true ) @PathParam("user-name")  String userName,
    @ApiParam(value = "service id which is using to filter user requested services",required=true ) @PathParam("serviceId")  Integer serviceId)
    {
    return delegate.consentUserNameServicesServiceIdGet(userName,serviceId);
    }
    @GET
    @Path("/{user-name}/services/{serviceId}/purpose")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Getting details of purposes", notes = "Getting details of purposes of a service by subject name", response = PurposeDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "invalid subject name supplied"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "purpose not found for this purpose") })

    public Response consentUserNameServicesServiceIdPurposeGet(@ApiParam(value = "user name to filter users",required=true ) @PathParam("user-name")  String userName,
    @ApiParam(value = "service id which is using to filter user requested services",required=true ) @PathParam("serviceId")  Integer serviceId,
    @ApiParam(value = "purpose id which is using to filter user requested purposes",required=true) @QueryParam("purposeId")  Integer purposeId)
    {
    return delegate.consentUserNameServicesServiceIdPurposeGet(userName,serviceId,purposeId);
    }
    @GET
    @Path("/{user-name}/thirdParty")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Getting consent details by third party name", notes = "Getting consent details regarding to the third party which operated on the user data", response = ConsentByThirdPartyDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "invalid subject name supplied"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "consent not found") })

    public Response consentUserNameThirdPartyGet(@ApiParam(value = "user name to filter users",required=true ) @PathParam("user-name")  String userName,
    @ApiParam(value = "Third party name which is using to filter the user consents",required=true) @QueryParam("thirdPartyId")  Integer thirdPartyId)
    {
    return delegate.consentUserNameThirdPartyGet(userName,thirdPartyId);
    }
}

