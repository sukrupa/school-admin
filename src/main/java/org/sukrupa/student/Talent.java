package org.sukrupa.student;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.sukrupa.platform.DoNotRemove;

import javax.persistence.*;

@Entity
public class Talent {
	@Id
	@GeneratedValue
	private long id;
	private String description;

	@DoNotRemove
    Talent() {}

	public Talent(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	private static String[] excludedFields = new String[]{"id"};

    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other, excludedFields);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, excludedFields);
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }

}
