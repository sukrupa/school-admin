package org.sukrupa.student;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.sukrupa.platform.date.Date;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.sukrupa.platform.date.DateManipulation.freezeDateToMidnightOn;
import static org.sukrupa.platform.date.DateManipulation.unfreezeTime;

public class AnnaulClassUpdateServiceTest {

        private static final String ANNUAL_CLASS_UPDATE = "annual class update";



    @Mock
    private StudentRepository studentRepository;

    private AnnualClassUpdateService service;

    @Mock
    private SystemEventLogRepository systemEventLogRepository;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
        service = new AnnualClassUpdateService(studentRepository, systemEventLogRepository);
        freezeDateToMidnightOn(30,12,2011); //This is actually the 29th?
    }

    @After
    public void tearDown() throws Exception {
        unfreezeTime();
    }

    @Test
    public void shouldIncrementClassOfEachStudentByOne() {
        // given
        List<Student> students = new ArrayList<Student>();

        Student sahil = new StudentBuilder().name("sahil").studentClass("1 Std").build();
        students.add(sahil);

        Student mark = new StudentBuilder().name("mark").studentClass("1 Std").build();
        students.add(mark);

        when(studentRepository.findAll()).thenReturn(students);

        // when
        service.promoteStudentsToNextClass();

        // then
        Student promotedSahil = new StudentBuilder().name("sahil").studentClass("2 Std").build();
        Student promotedMark = new StudentBuilder().name("mark").studentClass("2 Std").build();

        Mockito.verify(studentRepository).put(promotedSahil);
        Mockito.verify(studentRepository).put(promotedMark);
    }

    @Test
    public void shouldNotLetUsPromoteStudentsTwiceInOneYear() {
        // given
        Student Sahil = new StudentBuilder().name("sahil").studentClass("2 Std").build();

        // when
        when(studentRepository.findAll()).thenReturn(Collections.singletonList(Sahil));

        String eventName = ANNUAL_CLASS_UPDATE;
        SystemEventLog lastRunLog = new SystemEventLog(eventName, new LocalDate(2011, 03, 20));
        when(systemEventLogRepository.find(eventName)).thenReturn(null).thenReturn(lastRunLog);

        service.promoteStudentsToNextClass();
        service.promoteStudentsToNextClass();

        when(systemEventLogRepository.find(eventName)).thenReturn(lastRunLog);

        assertEquals("3 Std", Sahil.getStudentClass());
    }

     @Test
    public void shouldNotLetUsPromoteStudentsIfItWasDoneThisYear() {
        // given
        Student Sahil = new StudentBuilder().name("sahil").studentClass("2 Std").build();

        // when
        when(studentRepository.findAll()).thenReturn(Collections.singletonList(Sahil));

        String eventName = ANNUAL_CLASS_UPDATE;
        SystemEventLog lastRunLog = new SystemEventLog(eventName, new LocalDate(2011, 03, 20));
        when(systemEventLogRepository.find(eventName)).thenReturn(lastRunLog);

        service.promoteStudentsToNextClass();

        assertEquals("2 Std", Sahil.getStudentClass());
    }


    @Test
    public void shouldLetUsPromoteStudentsIfItWasDoneLastYear() {
        // given
        Student Sahil = new StudentBuilder().name("sahil").studentClass("2 Std").build();

        // when
        when(studentRepository.findAll()).thenReturn(Collections.singletonList(Sahil));

        String eventName = ANNUAL_CLASS_UPDATE;
        SystemEventLog lastRunLog = new SystemEventLog(eventName, new LocalDate(2010, 03, 20));
        when(systemEventLogRepository.find(eventName)).thenReturn(lastRunLog);

        service.promoteStudentsToNextClass();

        assertEquals("3 Std", Sahil.getStudentClass());
    }

    @Test
    public void shouldGiveCountOfStudentsUpdated() {
        List<Student> students = new ArrayList<Student>();
        Student Peter = new StudentBuilder().name("Peter").studentClass("10 Std").build();
        students.add(Peter);
        Student Yael = new StudentBuilder().name("Yael").studentClass("9 Std").build();
        students.add(Yael);
        Student Joel = new StudentBuilder().name("Joel").studentClass("10 Std").status(StudentStatus.ALUMNI).build();
        students.add(Joel);
        Student Jim = new StudentBuilder().name("Joel").studentClass("10 Std").status(StudentStatus.DROPOUT).build();
        students.add(Jim);

        when(studentRepository.findAll()).thenReturn(students);
        when(systemEventLogRepository.find(any(String.class))).thenReturn(null);
        service.promoteStudentsToNextClass();
        assertThat(service.getClassUpdateCount(),is(2));

    }

    @Test
    public void shouldUpdateExistingSystemEventLogWhenPromoting() {
        SystemEventLog eventLog = new SystemEventLog(ANNUAL_CLASS_UPDATE,new LocalDate(2010,03,20));
        when(systemEventLogRepository.find(ANNUAL_CLASS_UPDATE)).thenReturn(eventLog);
        service.promoteStudentsToNextClass();
        LocalDate currentDate = Date.now().getJodaDateTime().toLocalDate();
        assertThat(eventLog.lastHappened(),is(currentDate));
    }

    @Test
    public void getLastUpdateDateBeforeAnyUpdateShouldReturnNull(){
        when(systemEventLogRepository.find(ANNUAL_CLASS_UPDATE)).thenReturn(null);
        assertNull(service.getLastClassUpdateDate());
    }

    @Test
    public void getLastUpdateDateAfterAnUpdateSHouldReturnTheDateOfLastUpdate(){
        SystemEventLog eventLog = mock(SystemEventLog.class);
        when(systemEventLogRepository.find(ANNUAL_CLASS_UPDATE)).thenReturn(eventLog);
        service.getLastClassUpdateDate();
        verify(eventLog).lastHappened();
    }

    @Test
    public void shouldCreateEventLogIfNonExistantWhenPromoting() {
        when(systemEventLogRepository.find(ANNUAL_CLASS_UPDATE)).thenReturn(null);
        service.promoteStudentsToNextClass();
        verify(systemEventLogRepository).put(any(SystemEventLog.class));
    }
}
