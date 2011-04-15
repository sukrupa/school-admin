package org.sukrupa.student;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.sukrupa.platform.RequiredByFramework;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang.builder.ToStringStyle.DEFAULT_STYLE;

@Entity
public class Talent {
	@Id
	@GeneratedValue
	private long id;
	private String description;

	@RequiredByFramework
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
        return reflectionToString(this, DEFAULT_STYLE);
    }

}
