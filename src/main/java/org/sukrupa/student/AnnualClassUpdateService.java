package org.sukrupa.student;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sukrupa.platform.RequiredByFramework;
import org.sukrupa.platform.date.Date;

import java.util.List;

@Service
public class AnnualClassUpdateService {
    private static final String ANNUAL_CLASS_UPDATE = "annual class update";
    StudentRepository studentRepository;
    private SystemEventLogRepository systemEventLogRepository;
    private int classUpdateCount;

    @RequiredByFramework
    AnnualClassUpdateService() {
    }

    @Autowired
    public AnnualClassUpdateService(StudentRepository studentRepository, SystemEventLogRepository systemEventLogRepository) {
        this.studentRepository = studentRepository;
        this.systemEventLogRepository = systemEventLogRepository;
    }


    public void promoteStudentsToNextClass() {
        classUpdateCount = 0;
        SystemEventLog annualClassUpdateEventLog = systemEventLogRepository.find(ANNUAL_CLASS_UPDATE);
        if (!hasBeenUpdatedThisYear(annualClassUpdateEventLog)) {
            List<Student> students = studentRepository.findAll();
            for (Student student : students) {
                if (student.getStatus() == StudentStatus.EXISTING_STUDENT) {
                    classUpdateCount++;
                }
                student.promote();


                studentRepository.put(student);
            }

            LocalDate currentDate = Date.now().getJodaDateTime().toLocalDate();

            if (annualClassUpdateEventLog == null) {
                SystemEventLog annualUpdateLog = new SystemEventLog(ANNUAL_CLASS_UPDATE, currentDate);
                systemEventLogRepository.put(annualUpdateLog);

            } else {
                systemEventLogRepository.put(annualClassUpdateEventLog.newEntry(currentDate));
            }

        }
    }


    public String getLastClassUpdateDate() {
        SystemEventLog eventLog = systemEventLogRepository.find(ANNUAL_CLASS_UPDATE);
        if (null != eventLog && eventLog.lastHappened() != null) {
            return eventLog.lastHappened().toString("dd-MM-yyyy");
        } else {
            return null;
        }
    }

    public boolean classHasBeenUpdatedThisYear() {
        SystemEventLog eventLog = systemEventLogRepository.find(ANNUAL_CLASS_UPDATE);
        return hasBeenUpdatedThisYear(eventLog);
    }

    private boolean hasBeenUpdatedThisYear(SystemEventLog systemEventLog) {
        if (systemEventLog == null) {
            return false;
        } else {
            return systemEventLog.happenedThisYear();
        }
    }

    public int getClassUpdateCount() {
        return classUpdateCount;
    }

}
