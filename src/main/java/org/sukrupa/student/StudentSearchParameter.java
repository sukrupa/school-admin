package org.sukrupa.student;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.util.ReflectionUtils;
import org.sukrupa.platform.DoNotRemove;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.google.common.collect.Iterables.all;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;

public class StudentSearchParameter {

	private String studentClass = "";
    private String gender = "";
    private String caste = "";
    private String communityLocation = "";
	private String ageFrom = "";
	private String ageTo = "";
	private String talent = "";

	private String religion = "";
    private int page = 1;

    public StudentSearchParameter(String studentClass, String gender, String caste, String communityLocation, String ageFrom, String ageTo, String talent, String religion, int page) {
		this.studentClass = studentClass;
		this.gender = gender;
		this.caste = caste;
		this.communityLocation = communityLocation;
		this.ageFrom = ageFrom;
		this.ageTo = ageTo;
		this.talent = talent;
		this.religion = religion;
        this.page = page;
	}

	@DoNotRemove
	public StudentSearchParameter() {
	}

	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getCommunityLocation() {
		return communityLocation;
	}

	public void setCommunityLocation(String communityLocation) {
		this.communityLocation = communityLocation;
	}

	public String getAgeFrom() {
		return ageFrom;
	}

	public void setAgeFrom(String ageFrom) {
		this.ageFrom = ageFrom;
	}

	public String getAgeTo() {
		return ageTo;
	}

	public void setAgeTo(String ageTo) {
		this.ageTo = ageTo;
	}

	public String getTalent() {
		return talent;
	}

	public void setTalent(String talent) {
		this.talent = talent;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
