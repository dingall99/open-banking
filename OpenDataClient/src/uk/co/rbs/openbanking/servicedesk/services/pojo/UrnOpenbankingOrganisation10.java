
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
    "CreateTimestamp",
    "EmailAddresses",
    "ModifyTimestamp",
    "OBAuthorisationState",
    "OrganisationCommonName",
    "OBOrganisationId",
    "PhoneNumbers",
    "PostalAddresses",
    "status"
})
public class UrnOpenbankingOrganisation10 {

    @JsonProperty("CreateTimestamp")
    private String createTimestamp;
    @JsonProperty("EmailAddresses")
    private List<EmailAddress> emailAddresses = null;
    @JsonProperty("ModifyTimestamp")
    private String modifyTimestamp;
    @JsonProperty("OBAuthorisationState")
    private String oBAuthorisationState;
    @JsonProperty("OrganisationCommonName")
    private String organisationCommonName;
    @JsonProperty("OBOrganisationId")
    private String oBOrganisationId;
    @JsonProperty("PhoneNumbers")
    private List<PhoneNumber> phoneNumbers = null;
    @JsonProperty("PostalAddresses")
    private List<PostalAddress> postalAddresses = null;
    @JsonProperty("status")
    private String status;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("CreateTimestamp")
    public String getCreateTimestamp() {
        return createTimestamp;
    }

    @JsonProperty("CreateTimestamp")
    public void setCreateTimestamp(String createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    @JsonProperty("EmailAddresses")
    public List<EmailAddress> getEmailAddresses() {
        return emailAddresses;
    }

    @JsonProperty("EmailAddresses")
    public void setEmailAddresses(List<EmailAddress> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    @JsonProperty("ModifyTimestamp")
    public String getModifyTimestamp() {
        return modifyTimestamp;
    }

    @JsonProperty("ModifyTimestamp")
    public void setModifyTimestamp(String modifyTimestamp) {
        this.modifyTimestamp = modifyTimestamp;
    }

    @JsonProperty("OBAuthorisationState")
    public String getOBAuthorisationState() {
        return oBAuthorisationState;
    }

    @JsonProperty("OBAuthorisationState")
    public void setOBAuthorisationState(String oBAuthorisationState) {
        this.oBAuthorisationState = oBAuthorisationState;
    }

    @JsonProperty("OrganisationCommonName")
    public String getOrganisationCommonName() {
        return organisationCommonName;
    }

    @JsonProperty("OrganisationCommonName")
    public void setOrganisationCommonName(String organisationCommonName) {
        this.organisationCommonName = organisationCommonName;
    }

    @JsonProperty("OBOrganisationId")
    public String getOBOrganisationId() {
        return oBOrganisationId;
    }

    @JsonProperty("OBOrganisationId")
    public void setOBOrganisationId(String oBOrganisationId) {
        this.oBOrganisationId = oBOrganisationId;
    }

    @JsonProperty("PhoneNumbers")
    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    @JsonProperty("PhoneNumbers")
    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @JsonProperty("PostalAddresses")
    public List<PostalAddress> getPostalAddresses() {
        return postalAddresses;
    }

    @JsonProperty("PostalAddresses")
    public void setPostalAddresses(List<PostalAddress> postalAddresses) {
        this.postalAddresses = postalAddresses;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
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
