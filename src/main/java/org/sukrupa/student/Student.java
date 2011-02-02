package org.sukrupa.student;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.sukrupa.platform.DoNotRemove;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Student {

	@Id
    @GeneratedValue
    private long id;
	@Column(name = "STUDENT_ID")
	private String studentId;
    private String name;
    private String religion;
    private String caste;
    @Column(name = "SUB_CASTE")
    private String subCaste;
    private String area;
    private String sex;
	@Column(name = "STUDENT_CLASS")
	private String studentClass;
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name = "DATE_OF_BIRTH")
	private DateTime dateOfBirth;

    @DoNotRemove
    public Student() {
    }

	public Student(String studentId, String name, String religion, String caste, String subCaste, String area, String sex, String studentClass, DateTime dateOfBirth) {
		this.studentId = studentId;
		this.name = name;
		this.religion = religion;
		this.caste = caste;
		this.subCaste = subCaste;
		this.area = area;
		this.sex = sex;
		this.studentClass = studentClass;
		this.dateOfBirth = dateOfBirth;
	}

    public String getName() {
        return name;
    }

    public String getReligion() {
        return religion;
    }

    public String getCaste() {
        return caste;
    }

    public String getSubCaste() {
        return subCaste;
    }

    public String getArea() {
        return area;
    }

	public String getStudentId() {
		return studentId;
	}

	public String getSex() {
		return sex;
	}

	public String getStudentClass() {
		return studentClass;
	}

	public DateTime getDateOfBirth() {
		return dateOfBirth;
	}

	public int getAge() {
		if (dateOfBirth == null) {
			return 0;
		}
		return Years.yearsBetween(dateOfBirth, getCurrentDate()).getYears();
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

	protected DateTime getCurrentDate() {
		return new DateTime();
	}
}
