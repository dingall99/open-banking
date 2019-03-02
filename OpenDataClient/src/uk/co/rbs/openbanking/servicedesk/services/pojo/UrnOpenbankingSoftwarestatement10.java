
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
    "SoftwareStatements"
})
public class UrnOpenbankingSoftwarestatement10 {

    @JsonProperty("SoftwareStatements")
    private List<SoftwareStatement> softwareStatements = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("SoftwareStatements")
    public List<SoftwareStatement> getSoftwareStatements() {
        return softwareStatements;
    }

    @JsonProperty("SoftwareStatements")
    public void setSoftwareStatements(List<SoftwareStatement> softwareStatements) {
        this.softwareStatements = softwareStatements;
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
