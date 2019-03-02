
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
    "urn:openbanking:competentauthorityclaims:1.0",
    "AuthorisationServers",
    "urn:openbanking:organisation:1.0",
    "urn:openbanking:legalauthorityclaims:1.0",
    "urn:openbanking:softwarestatement:1.0",
    "id",
    "meta",
    "schemas"
})
public class Resource {

    @JsonProperty("urn:openbanking:competentauthorityclaims:1.0")
    private UrnOpenbankingCompetentauthorityclaims10 urnOpenbankingCompetentauthorityclaims10;
    @JsonProperty("AuthorisationServers")
    private List<AuthorisationServer> authorisationServers = null;
    @JsonProperty("urn:openbanking:organisation:1.0")
    private UrnOpenbankingOrganisation10 urnOpenbankingOrganisation10;
    @JsonProperty("urn:openbanking:legalauthorityclaims:1.0")
    private UrnOpenbankingLegalauthorityclaims10 urnOpenbankingLegalauthorityclaims10;
    @JsonProperty("urn:openbanking:softwarestatement:1.0")
    private UrnOpenbankingSoftwarestatement10 urnOpenbankingSoftwarestatement10;
    @JsonProperty("id")
    private String id;
    @JsonProperty("meta")
    private Meta meta;
    @JsonProperty("schemas")
    private List<String> schemas = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("urn:openbanking:competentauthorityclaims:1.0")
    public UrnOpenbankingCompetentauthorityclaims10 getUrnOpenbankingCompetentauthorityclaims10() {
        return urnOpenbankingCompetentauthorityclaims10;
    }

    @JsonProperty("urn:openbanking:competentauthorityclaims:1.0")
    public void setUrnOpenbankingCompetentauthorityclaims10(UrnOpenbankingCompetentauthorityclaims10 urnOpenbankingCompetentauthorityclaims10) {
        this.urnOpenbankingCompetentauthorityclaims10 = urnOpenbankingCompetentauthorityclaims10;
    }

    @JsonProperty("AuthorisationServers")
    public List<AuthorisationServer> getAuthorisationServers() {
        return authorisationServers;
    }

    @JsonProperty("AuthorisationServers")
    public void setAuthorisationServers(List<AuthorisationServer> authorisationServers) {
        this.authorisationServers = authorisationServers;
    }

    @JsonProperty("urn:openbanking:organisation:1.0")
    public UrnOpenbankingOrganisation10 getUrnOpenbankingOrganisation10() {
        return urnOpenbankingOrganisation10;
    }

    @JsonProperty("urn:openbanking:organisation:1.0")
    public void setUrnOpenbankingOrganisation10(UrnOpenbankingOrganisation10 urnOpenbankingOrganisation10) {
        this.urnOpenbankingOrganisation10 = urnOpenbankingOrganisation10;
    }

    @JsonProperty("urn:openbanking:legalauthorityclaims:1.0")
    public UrnOpenbankingLegalauthorityclaims10 getUrnOpenbankingLegalauthorityclaims10() {
        return urnOpenbankingLegalauthorityclaims10;
    }

    @JsonProperty("urn:openbanking:legalauthorityclaims:1.0")
    public void setUrnOpenbankingLegalauthorityclaims10(UrnOpenbankingLegalauthorityclaims10 urnOpenbankingLegalauthorityclaims10) {
        this.urnOpenbankingLegalauthorityclaims10 = urnOpenbankingLegalauthorityclaims10;
    }

    @JsonProperty("urn:openbanking:softwarestatement:1.0")
    public UrnOpenbankingSoftwarestatement10 getUrnOpenbankingSoftwarestatement10() {
        return urnOpenbankingSoftwarestatement10;
    }

    @JsonProperty("urn:openbanking:softwarestatement:1.0")
    public void setUrnOpenbankingSoftwarestatement10(UrnOpenbankingSoftwarestatement10 urnOpenbankingSoftwarestatement10) {
        this.urnOpenbankingSoftwarestatement10 = urnOpenbankingSoftwarestatement10;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("meta")
    public Meta getMeta() {
        return meta;
    }

    @JsonProperty("meta")
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @JsonProperty("schemas")
    public List<String> getSchemas() {
        return schemas;
    }

    @JsonProperty("schemas")
    public void setSchemas(List<String> schemas) {
        this.schemas = schemas;
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
