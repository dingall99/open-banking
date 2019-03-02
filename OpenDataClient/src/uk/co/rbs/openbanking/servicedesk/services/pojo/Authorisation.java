
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
    "Psd2Role",
    "MemberState",
    "Active"
})
public class Authorisation {

    @JsonProperty("Psd2Role")
    private String psd2Role;
    @JsonProperty("MemberState")
    private String memberState;
    @JsonProperty("Active")
    private Boolean active;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Psd2Role")
    public String getPsd2Role() {
        return psd2Role;
    }

    @JsonProperty("Psd2Role")
    public void setPsd2Role(String psd2Role) {
        this.psd2Role = psd2Role;
    }

    @JsonProperty("MemberState")
    public String getMemberState() {
        return memberState;
    }

    @JsonProperty("MemberState")
    public void setMemberState(String memberState) {
        this.memberState = memberState;
    }

    @JsonProperty("Active")
    public Boolean getActive() {
        return active;
    }

    @JsonProperty("Active")
    public void setActive(Boolean active) {
        this.active = active;
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
