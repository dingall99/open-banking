
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
    "Type",
    "Town",
    "StreetAddress",
    "Primary",
    "PostCode",
    "Name",
    "Country"
})
public class PostalAddress {

    @JsonProperty("Type")
    private String type;
    @JsonProperty("Town")
    private String town;
    @JsonProperty("StreetAddress")
    private String streetAddress;
    @JsonProperty("Primary")
    private Boolean primary;
    @JsonProperty("PostCode")
    private String postCode;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Country")
    private String country;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    @JsonProperty("Type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("Town")
    public String getTown() {
        return town;
    }

    @JsonProperty("Town")
    public void setTown(String town) {
        this.town = town;
    }

    @JsonProperty("StreetAddress")
    public String getStreetAddress() {
        return streetAddress;
    }

    @JsonProperty("StreetAddress")
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    @JsonProperty("Primary")
    public Boolean getPrimary() {
        return primary;
    }

    @JsonProperty("Primary")
    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    @JsonProperty("PostCode")
    public String getPostCode() {
        return postCode;
    }

    @JsonProperty("PostCode")
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("Country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("Country")
    public void setCountry(String country) {
        this.country = country;
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
