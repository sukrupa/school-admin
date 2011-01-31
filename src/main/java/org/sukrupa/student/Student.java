package org.sukrupa.student;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.sukrupa.platform.DoNotRemove;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Entity
public class Student {

	private static final String DATE_FORMAT = "yyyy/MM/dd";
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
	@Column(name = "DATE_OF_BIRTH")
	private String dateOfBirth;

    @DoNotRemove
    public Student() {
    }

	public Student(String studentId, String name, String religion, String caste, String subCaste, String area, String sex, String studentClass, String dateOfBirth) {
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getAge() {
		DateFormat format = getDateFormat();
		try {
			GregorianCalendar birthDate = new GregorianCalendar();
			birthDate.setTimeInMillis(format.parse(dateOfBirth).getTime());

			return String.valueOf(getDateDifferenceInYears(getCurrentDate(), birthDate));
		} catch (ParseException e) {
			throw new InternalError("Invalid date saved as date of birth");
		}
	}

	private int getDateDifferenceInYears(Calendar currentDate, Calendar dateOfBirth) {
		int years = currentDate.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
		int months = currentDate.get(Calendar.MONTH) - dateOfBirth.get(Calendar.MONTH);

		if (isMonthOfCurrentDateBeforeMonthOfDateOfBirth(months)) {
			years--;
		} else if (isSameMonthAndCurrentDayBeforeBirthdayDay(currentDate, dateOfBirth, months)) {
			years--;
		}
		return years;
	}

	private boolean isSameMonthAndCurrentDayBeforeBirthdayDay(Calendar currentDate, Calendar dateOfBirth, int months) {
		return months == 0 && currentDate.get(Calendar.DAY_OF_MONTH) < dateOfBirth.get(Calendar.DAY_OF_MONTH);
	}

	private boolean isMonthOfCurrentDateBeforeMonthOfDateOfBirth(int months) {
		return months < 0;
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

	public DateFormat getDateFormat() {
		return new SimpleDateFormat(DATE_FORMAT);
	}

	protected Calendar getCurrentDate() {
		return new GregorianCalendar();
	}
}
