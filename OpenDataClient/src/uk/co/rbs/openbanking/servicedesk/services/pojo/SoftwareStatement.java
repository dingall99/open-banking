
package uk.co.rbs.openbanking.servicedesk.services.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Active",
    "ClientId",
    "ClientName",
    "ClientUri",
    "Description",
    "Id",
    "LogoUri",
    "Mode",
    "ObClientCreated",
    "OnBehalfOfObOrganisation",
    "PolicyUri",
    "RedirectUri",
    "Roles",
    "TermsOfServiceUri",
    "TransportKeyIds",
    "Version",
    "SigningKeyIds"
})
public class SoftwareStatement {

    @JsonProperty("Active")
    private Boolean active;
    @JsonProperty("ClientId")
    private String clientId;
    @JsonProperty("ClientName")
    private String clientName;
    @JsonProperty("ClientUri")
    private String clientUri;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Id")
    private String id;
    @JsonProperty("LogoUri")
    private String logoUri;
    @JsonProperty("Mode")
    private String mode;
    @JsonProperty("ObClientCreated")
    private Boolean obClientCreated;
    @JsonProperty("OnBehalfOfObOrganisation")
    private String onBehalfOfObOrganisation;
    @JsonProperty("PolicyUri")
    private String policyUri;
    @JsonProperty("RedirectUri")
    private List<String> redirectUri = null;
    @JsonProperty("Roles")
    private List<String> roles = null;
    @JsonProperty("TermsOfServiceUri")
    private String termsOfServiceUri;
    @JsonProperty("TransportKeyIds")
    private List<String> transportKeyIds = null;
    @JsonProperty("Version")
    private Double version;
    @JsonProperty("SigningKeyIds")
    private List<String> signingKeyIds = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Active")
    public Boolean getActive() {
        return active;
    }

    @JsonProperty("Active")
    public void setActive(Boolean active) {
        this.active = active;
    }

    @JsonProperty("ClientId")
    public String getClientId() {
        return clientId;
    }

    @JsonProperty("ClientId")
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @JsonProperty("ClientName")
    public String getClientName() {
        return clientName;
    }

    @JsonProperty("ClientName")
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @JsonProperty("ClientUri")
    public String getClientUri() {
        return clientUri;
    }

    @JsonProperty("ClientUri")
    public void setClientUri(String clientUri) {
        this.clientUri = clientUri;
    }

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("Description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("Id")
    public String getId() {
        return id;
    }

    @JsonProperty("Id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("LogoUri")
    public String getLogoUri() {
        return logoUri;
    }

    @JsonProperty("LogoUri")
    public void setLogoUri(String logoUri) {
        this.logoUri = logoUri;
    }

    @JsonProperty("Mode")
    public String getMode() {
        return mode;
    }

    @JsonProperty("Mode")
    public void setMode(String mode) {
        this.mode = mode;
    }

    @JsonProperty("ObClientCreated")
    public Boolean getObClientCreated() {
        return obClientCreated;
    }

    @JsonProperty("ObClientCreated")
    public void setObClientCreated(Boolean obClientCreated) {
        this.obClientCreated = obClientCreated;
    }

    @JsonProperty("OnBehalfOfObOrganisation")
    public String getOnBehalfOfObOrganisation() {
        return onBehalfOfObOrganisation;
    }

    @JsonProperty("OnBehalfOfObOrganisation")
    public void setOnBehalfOfObOrganisation(String onBehalfOfObOrganisation) {
        this.onBehalfOfObOrganisation = onBehalfOfObOrganisation;
    }

    @JsonProperty("PolicyUri")
    public String getPolicyUri() {
        return policyUri;
    }

    @JsonProperty("PolicyUri")
    public void setPolicyUri(String policyUri) {
        this.policyUri = policyUri;
    }

    @JsonProperty("RedirectUri")
    public List<String> getRedirectUri() {
        return redirectUri;
    }

    @JsonProperty("RedirectUri")
    public void setRedirectUri(List<String> redirectUri) {
        this.redirectUri = redirectUri;
    }

    @JsonProperty("Roles")
    public List<String> getRoles() {
        return roles;
    }

    @JsonProperty("Roles")
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @JsonProperty("TermsOfServiceUri")
    public String getTermsOfServiceUri() {
        return termsOfServiceUri;
    }

    @JsonProperty("TermsOfServiceUri")
    public void setTermsOfServiceUri(String termsOfServiceUri) {
        this.termsOfServiceUri = termsOfServiceUri;
    }

    @JsonProperty("TransportKeyIds")
    public List<String> getTransportKeyIds() {
        return transportKeyIds;
    }

    @JsonProperty("TransportKeyIds")
    public void setTransportKeyIds(List<String> transportKeyIds) {
        this.transportKeyIds = transportKeyIds;
    }

    @JsonProperty("Version")
    public Double getVersion() {
        return version;
    }

    @JsonProperty("Version")
    public void setVersion(Double version) {
        this.version = version;
    }

    @JsonProperty("SigningKeyIds")
    public List<String> getSigningKeyIds() {
        return signingKeyIds;
    }

    @JsonProperty("SigningKeyIds")
    public void setSigningKeyIds(List<String> signingKeyIds) {
        this.signingKeyIds = signingKeyIds;
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
