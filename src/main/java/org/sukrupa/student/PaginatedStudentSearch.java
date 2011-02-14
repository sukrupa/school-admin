package org.sukrupa.student;

import java.util.List;

public class PaginatedStudentSearch {
    private final StudentRepository repository;
    private final StudentSearchParameter searchParam;
    static final int NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE = 5;


    public PaginatedStudentSearch(StudentRepository repository, StudentSearchParameter searchParam) {
        this.repository = repository;
        this.searchParam = searchParam;
    }

    public StudentListPage getPage() {
        int firstIndex = (searchParam.getPage() - 1) * NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE;

        List<Student> students = repository.parametricSearch(searchParam, firstIndex, NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);

        int totalNumberOfResults = repository.countResults(searchParam);
        int totalNumberOfPages = (totalNumberOfResults - 1) / NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE + 1;

        return new StudentListPage(students, searchParam.getPage(), totalNumberOfPages);
    }
}
