
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
    "LegalAuthorityClaims"
})
public class UrnOpenbankingLegalauthorityclaims10 {

    @JsonProperty("LegalAuthorityClaims")
    private List<LegalAuthorityClaim> legalAuthorityClaims = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("LegalAuthorityClaims")
    public List<LegalAuthorityClaim> getLegalAuthorityClaims() {
        return legalAuthorityClaims;
    }

    @JsonProperty("LegalAuthorityClaims")
    public void setLegalAuthorityClaims(List<LegalAuthorityClaim> legalAuthorityClaims) {
        this.legalAuthorityClaims = legalAuthorityClaims;
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
