
package uk.co.rbs.openbanking.servicedesk.pojo.accountrequest;

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
    "AccountRequestId",
    "Permissions",
    "CreationDateTime",
    "ExpirationDateTime",
    "TransactionFromDateTime",
    "TransactionToDateTime",
    "Status"
})
public class Data {

    @JsonProperty("AccountRequestId")
    private String accountRequestId;
    @JsonProperty("Permissions")
    private List<String> permissions = null;
    @JsonProperty("CreationDateTime")
    private String creationDateTime;
    @JsonProperty("ExpirationDateTime")
    private String expirationDateTime;
    @JsonProperty("TransactionFromDateTime")
    private String transactionFromDateTime;
    @JsonProperty("TransactionToDateTime")
    private String transactionToDateTime;
    @JsonProperty("Status")
    private String status;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("AccountRequestId")
    public String getAccountRequestId() {
        return accountRequestId;
    }

    @JsonProperty("AccountRequestId")
    public void setAccountRequestId(String accountRequestId) {
        this.accountRequestId = accountRequestId;
    }

    @JsonProperty("Permissions")
    public List<String> getPermissions() {
        return permissions;
    }

    @JsonProperty("Permissions")
    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    @JsonProperty("CreationDateTime")
    public String getCreationDateTime() {
        return creationDateTime;
    }

    @JsonProperty("CreationDateTime")
    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    @JsonProperty("ExpirationDateTime")
    public String getExpirationDateTime() {
        return expirationDateTime;
    }

    @JsonProperty("ExpirationDateTime")
    public void setExpirationDateTime(String expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    @JsonProperty("TransactionFromDateTime")
    public String getTransactionFromDateTime() {
        return transactionFromDateTime;
    }

    @JsonProperty("TransactionFromDateTime")
    public void setTransactionFromDateTime(String transactionFromDateTime) {
        this.transactionFromDateTime = transactionFromDateTime;
    }

    @JsonProperty("TransactionToDateTime")
    public String getTransactionToDateTime() {
        return transactionToDateTime;
    }

    @JsonProperty("TransactionToDateTime")
    public void setTransactionToDateTime(String transactionToDateTime) {
        this.transactionToDateTime = transactionToDateTime;
    }

    @JsonProperty("Status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("Status")
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
