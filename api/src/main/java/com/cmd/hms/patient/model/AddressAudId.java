package com.cmd.hms.patient.model;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressAudId implements Serializable {
   
    private Long Rev;

    private Long AddressId;

    // default constructor

    public AddressAudId(Long Rev, Long AddressId) {
        this.Rev = Rev;
        this.AddressId = AddressId;
    }
    
    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof AddressAudId)) {
            return false;
        }
        AddressAudId AddressAudId = (AddressAudId) o;
        return Rev == AddressAudId.Rev && AddressId == AddressAudId.AddressId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Rev, AddressId);
    }
}
