
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
    "Authorisations",
    "AuthorityId",
    "MemberState",
    "RegistrationId"
})
public class UrnOpenbankingCompetentauthorityclaims10 {

    @JsonProperty("Authorisations")
    private List<Authorisation> authorisations = null;
    @JsonProperty("AuthorityId")
    private String authorityId;
    @JsonProperty("MemberState")
    private String memberState;
    @JsonProperty("RegistrationId")
    private String registrationId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Authorisations")
    public List<Authorisation> getAuthorisations() {
        return authorisations;
    }

    @JsonProperty("Authorisations")
    public void setAuthorisations(List<Authorisation> authorisations) {
        this.authorisations = authorisations;
    }

    @JsonProperty("AuthorityId")
    public String getAuthorityId() {
        return authorityId;
    }

    @JsonProperty("AuthorityId")
    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
    }

    @JsonProperty("MemberState")
    public String getMemberState() {
        return memberState;
    }

    @JsonProperty("MemberState")
    public void setMemberState(String memberState) {
        this.memberState = memberState;
    }

    @JsonProperty("RegistrationId")
    public String getRegistrationId() {
        return registrationId;
    }

    @JsonProperty("RegistrationId")
    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
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
