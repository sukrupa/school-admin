package org.sukrupa.student;

import org.junit.Test;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.sukrupa.app.services.StudentImageRepository;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StudentFormTest {

    @Test
    public void shouldSaveImageOnRepository(){
        StudentForm studentForm = new StudentForm();
        studentForm.setStudentId("12345");
        CommonsMultipartFile image = mock(CommonsMultipartFile.class);
        studentForm.setImageToUpload(image);

        StudentImageRepository studentImageRepository = mock(StudentImageRepository.class);

        studentForm.createImage(studentImageRepository);
        verify(studentImageRepository).save(new Image(image), "12345");
    }



}
