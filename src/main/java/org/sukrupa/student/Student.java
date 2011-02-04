package org.sukrupa.student;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.sukrupa.platform.DoNotRemove;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
	@Column(name = "COMMUNITY_LOCATION")
    private String communityLocation;
    private String gender;
	@Column(name = "STUDENT_CLASS")
	private String studentClass;
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@Column(name = "DATE_OF_BIRTH")
	private LocalDate dateOfBirth;
    @Transient
    private List<Note> notes = new ArrayList<Note>();

	//@Fetch(value = FetchMode.JOIN)
	@ManyToMany//(fetch = FetchType.EAGER, targetEntity = Talent.class)
    @JoinTable(name = "STUDENT_TALENT",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "talent_id")})
	private Set<Talent> talents;

    @DoNotRemove
    public Student() {
    }

	public Student(String studentId, String name, String religion, String caste, String subCaste, String communityLocation, String gender, String studentClass, Set<Talent> talents, LocalDate dateOfBirth) {
		this.studentId = studentId;
		this.name = name;
		this.religion = religion;
		this.caste = caste;
		this.subCaste = subCaste;
		this.communityLocation = communityLocation;
		this.gender = gender;
		this.studentClass = studentClass;
		this.dateOfBirth = dateOfBirth;
		this.talents = talents;
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

    public String getCommunityLocation() {
        return communityLocation;
    }

	public String getStudentId() {
		return studentId;
	}

	public String getGender() {
		return gender;
	}

	public String getStudentClass() {
		return studentClass;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public Set<Talent> getTalents() {
		return talents;
	}

	public int getAge() {
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

	protected LocalDate getCurrentDate() {
		return new LocalDate();
	}

    public void addNote(Note note) {
        notes.add(note);
    }

    public List<Note> getNotes() {
        return notes;
    }
}
