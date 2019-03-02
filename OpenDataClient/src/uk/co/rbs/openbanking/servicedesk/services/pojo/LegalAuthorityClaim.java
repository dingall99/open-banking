
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
    "RegistrationAuthorityId",
    "RegisteredName",
    "RegisteredId"
})
public class LegalAuthorityClaim {

    @JsonProperty("RegistrationAuthorityId")
    private String registrationAuthorityId;
    @JsonProperty("RegisteredName")
    private String registeredName;
    @JsonProperty("RegisteredId")
    private String registeredId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("RegistrationAuthorityId")
    public String getRegistrationAuthorityId() {
        return registrationAuthorityId;
    }

    @JsonProperty("RegistrationAuthorityId")
    public void setRegistrationAuthorityId(String registrationAuthorityId) {
        this.registrationAuthorityId = registrationAuthorityId;
    }

    @JsonProperty("RegisteredName")
    public String getRegisteredName() {
        return registeredName;
    }

    @JsonProperty("RegisteredName")
    public void setRegisteredName(String registeredName) {
        this.registeredName = registeredName;
    }

    @JsonProperty("RegisteredId")
    public String getRegisteredId() {
        return registeredId;
    }

    @JsonProperty("RegisteredId")
    public void setRegisteredId(String registeredId) {
        this.registeredId = registeredId;
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
