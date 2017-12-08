package identity_user_consent_mgt_endpoint;

import identity_user_consent_mgt_endpoint.dto.*;
import identity_user_consent_mgt_endpoint.ConsentApiService;
import identity_user_consent_mgt_endpoint.factories.ConsentApiServiceFactory;

import io.swagger.annotations.ApiParam;

import identity_user_consent_mgt_endpoint.dto.DataControllerInputDTO;
import identity_user_consent_mgt_endpoint.dto.PiiCatListDTO;
import identity_user_consent_mgt_endpoint.dto.PiiCategoryDTO;
import identity_user_consent_mgt_endpoint.dto.PurposeListDTO;
import identity_user_consent_mgt_endpoint.dto.PurposeInputDTO;
import identity_user_consent_mgt_endpoint.dto.ServiceInputDTO;
import identity_user_consent_mgt_endpoint.dto.ConsentReceiptDTO;
import identity_user_consent_mgt_endpoint.dto.UserConsentWebFormDTO;
import identity_user_consent_mgt_endpoint.dto.ConsentRevokeListDTO;
import identity_user_consent_mgt_endpoint.dto.ConsentDTO;
import identity_user_consent_mgt_endpoint.dto.ServiceListDTO;
import identity_user_consent_mgt_endpoint.dto.ServiceCRDTO;
import identity_user_consent_mgt_endpoint.dto.PurposeDTO;
import identity_user_consent_mgt_endpoint.dto.ConsentByThirdPartyDTO;

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

    @POST
    @Path("/configuration/dataController")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Adding data controller", notes = "Adding details of the data controller to the consent database", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "successfully created the data controller"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error") })

    public Response consentConfigurationDataControllerPost(@ApiParam(value = "Details of the data controller" ,required=true ) DataControllerInputDTO dataController)
    {
    return delegate.consentConfigurationDataControllerPost(dataController);
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
    @POST
    @Path("/configuration/purpose")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Adding purpose details", notes = "Adding details of a purpose to the consent database", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successfully created the purpose"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error") })

    public Response consentConfigurationPurposePost(@ApiParam(value = "Details of a purpose" ,required=true ) PurposeInputDTO purpose)
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

    public Response consentConfigurationPurposePut(@ApiParam(value = "Details of a purpose" ,required=true ) PurposeInputDTO purpose)
    {
    return delegate.consentConfigurationPurposePut(purpose);
    }
    @GET
    @Path("/configuration/service")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Getting services", notes = "Getting details of services from the database", response = ServiceInputDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "content not found"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error") })

    public Response consentConfigurationServiceGet()
    {
    return delegate.consentConfigurationServiceGet();
    }
    @POST
    @Path("/configuration/service")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Adding a service", notes = "Adding details of a service to the consent database", response = void.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successfully created the service"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "internal server error") })

    public Response consentConfigurationServicePost(@ApiParam(value = "Details of a service" ,required=true ) ServiceInputDTO service)
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

    public Response consentConfigurationServicePut(@ApiParam(value = "Details of a service" ,required=true ) ServiceInputDTO service)
    {
    return delegate.consentConfigurationServicePut(service);
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
    @Path("/{subjectName}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Getting consent details", notes = "Getting consent details of an user to populate the UI", response = ConsentDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "invalid subject name supplied"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "consent for this subject name not found") })

    public Response consentSubjectNameGet(@ApiParam(value = "User name to get the consent details to an user",required=true ) @PathParam("subjectName")  String subjectName)
    {
    return delegate.consentSubjectNameGet(subjectName);
    }
    @GET
    @Path("/{subjectName}/receipt")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Creating consent receipt", notes = "Getting consent details of an user to create the consent receipt", response = ConsentReceiptDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "invalid subject name supplied"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "consent for this subject name not found") })

    public Response consentSubjectNameReceiptGet(@ApiParam(value = "User name to get the consent details to an user",required=true ) @PathParam("subjectName")  String subjectName)
    {
    return delegate.consentSubjectNameReceiptGet(subjectName);
    }
    @GET
    @Path("/{subjectName}/services")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Getting service details", notes = "Getting details of consented services by an user", response = ServiceListDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "invalid subject name supplied"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "services not found for this subject name") })

    public Response consentSubjectNameServicesGet(@ApiParam(value = "User name to get the consent details to an user",required=true ) @PathParam("subjectName")  String subjectName)
    {
    return delegate.consentSubjectNameServicesGet(subjectName);
    }
    @GET
    @Path("/{subjectName}/services/{serviceId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Getting services by service id", notes = "Getting service details regarding to the subject name and the service id", response = ServiceCRDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "invalid subject name supplied"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "services not found for this subject name") })

    public Response consentSubjectNameServicesServiceIdGet(@ApiParam(value = "User name to get the consent details to an user",required=true ) @PathParam("subjectName")  String subjectName,
    @ApiParam(value = "service id which is using to filter user requested services",required=true ) @PathParam("serviceId")  Integer serviceId)
    {
    return delegate.consentSubjectNameServicesServiceIdGet(subjectName,serviceId);
    }
    @GET
    @Path("/{subjectName}/services/{serviceId}/purpose")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Getting details of purposes", notes = "Getting details of purposes of a service by subject name", response = PurposeDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "invalid subject name supplied"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "purpose not found for this purpose") })

    public Response consentSubjectNameServicesServiceIdPurposeGet(@ApiParam(value = "User name to get the consent details to an user",required=true ) @PathParam("subjectName")  String subjectName,
    @ApiParam(value = "service id which is using to filter user requested services",required=true ) @PathParam("serviceId")  Integer serviceId,
    @ApiParam(value = "purpose id which is using to filter user requested purposes",required=true) @QueryParam("purposeId")  Integer purposeId)
    {
    return delegate.consentSubjectNameServicesServiceIdPurposeGet(subjectName,serviceId,purposeId);
    }
    @GET
    @Path("/{subjectName}/thirdParty")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Getting consent details by third party name", notes = "Getting consent details regarding to the third party which operated on the user data", response = ConsentByThirdPartyDTO.class)
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "successful operation"),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "invalid subject name supplied"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "consent not found") })

    public Response consentSubjectNameThirdPartyGet(@ApiParam(value = "User name to get the consent details to an user",required=true ) @PathParam("subjectName")  String subjectName,
    @ApiParam(value = "Third party name which is using to filter the user consents",required=true) @QueryParam("thirdPartyId")  Integer thirdPartyId)
    {
    return delegate.consentSubjectNameThirdPartyGet(subjectName,thirdPartyId);
    }
}
