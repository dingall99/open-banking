
package uk.co.rbs.openbanking.servicedesk.services.pojo;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Id",
    "AutoRegistrationSupported",
    "BaseApiDNSUri",
    "CustomerFriendlyDescription",
    "CustomerFriendlyLogoUri",
    "CustomerFriendlyName",
    "DeveloperPortalUri",
    "TermsOfService",
    "OpenIDConfigEndPointUri",
    "PayloadSigningCertLocation"
})
public class AuthorisationServer {

    @JsonProperty("Id")
    private String id;
    @JsonProperty("AutoRegistrationSupported")
    private Boolean autoRegistrationSupported;
    @JsonProperty("BaseApiDNSUri")
    private String baseApiDNSUri;
    @JsonProperty("CustomerFriendlyDescription")
    private String customerFriendlyDescription;
    @JsonProperty("CustomerFriendlyLogoUri")
    private String customerFriendlyLogoUri;
    @JsonProperty("CustomerFriendlyName")
    private String customerFriendlyName;
    @JsonProperty("DeveloperPortalUri")
    private String developerPortalUri;
    @JsonProperty("TermsOfService")
    private String termsOfService;
    @JsonProperty("OpenIDConfigEndPointUri")
    private String openIDConfigEndPointUri;
    @JsonProperty("PayloadSigningCertLocation")
    private String payloadSigningCertLocation;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Id")
    public String getId() {
        return id;
    }

    @JsonProperty("Id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("AutoRegistrationSupported")
    public Boolean getAutoRegistrationSupported() {
        return autoRegistrationSupported;
    }

    @JsonProperty("AutoRegistrationSupported")
    public void setAutoRegistrationSupported(Boolean autoRegistrationSupported) {
        this.autoRegistrationSupported = autoRegistrationSupported;
    }

    @JsonProperty("BaseApiDNSUri")
    public String getBaseApiDNSUri() {
        return baseApiDNSUri;
    }

    @JsonProperty("BaseApiDNSUri")
    public void setBaseApiDNSUri(String baseApiDNSUri) {
        this.baseApiDNSUri = baseApiDNSUri;
    }

    @JsonProperty("CustomerFriendlyDescription")
    public String getCustomerFriendlyDescription() {
        return customerFriendlyDescription;
    }

    @JsonProperty("CustomerFriendlyDescription")
    public void setCustomerFriendlyDescription(String customerFriendlyDescription) {
        this.customerFriendlyDescription = customerFriendlyDescription;
    }

    @JsonProperty("CustomerFriendlyLogoUri")
    public String getCustomerFriendlyLogoUri() {
        return customerFriendlyLogoUri;
    }

    @JsonProperty("CustomerFriendlyLogoUri")
    public void setCustomerFriendlyLogoUri(String customerFriendlyLogoUri) {
        this.customerFriendlyLogoUri = customerFriendlyLogoUri;
    }

    @JsonProperty("CustomerFriendlyName")
    public String getCustomerFriendlyName() {
        return customerFriendlyName;
    }

    @JsonProperty("CustomerFriendlyName")
    public void setCustomerFriendlyName(String customerFriendlyName) {
        this.customerFriendlyName = customerFriendlyName;
    }

    @JsonProperty("DeveloperPortalUri")
    public String getDeveloperPortalUri() {
        return developerPortalUri;
    }

    @JsonProperty("DeveloperPortalUri")
    public void setDeveloperPortalUri(String developerPortalUri) {
        this.developerPortalUri = developerPortalUri;
    }

    @JsonProperty("TermsOfService")
    public String getTermsOfService() {
        return termsOfService;
    }

    @JsonProperty("TermsOfService")
    public void setTermsOfService(String termsOfService) {
        this.termsOfService = termsOfService;
    }

    @JsonProperty("OpenIDConfigEndPointUri")
    public String getOpenIDConfigEndPointUri() {
        return openIDConfigEndPointUri;
    }

    @JsonProperty("OpenIDConfigEndPointUri")
    public void setOpenIDConfigEndPointUri(String openIDConfigEndPointUri) {
        this.openIDConfigEndPointUri = openIDConfigEndPointUri;
    }

    @JsonProperty("PayloadSigningCertLocation")
    public String getPayloadSigningCertLocation() {
        return payloadSigningCertLocation;
    }

    @JsonProperty("PayloadSigningCertLocation")
    public void setPayloadSigningCertLocation(String payloadSigningCertLocation) {
        this.payloadSigningCertLocation = payloadSigningCertLocation;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
