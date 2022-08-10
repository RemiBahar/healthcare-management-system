package com.cmd.hms.patient.model;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactAudId implements Serializable {
   
    private Long Rev;

    private Long ContactId;

    // default constructor

    public ContactAudId(Long Rev, Long ContactId) {
        this.Rev = Rev;
        this.ContactId = ContactId;
    }
    
    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof ContactAudId)) {
            return false;
        }
        ContactAudId ContactAudId = (ContactAudId) o;
        return Rev == ContactAudId.Rev && ContactId == ContactAudId.ContactId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Rev, ContactId);
    }
}
