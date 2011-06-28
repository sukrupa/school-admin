package org.sukrupa.need;

import com.google.common.collect.Sets;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.platform.RequiredByFramework;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentRepository;

import java.util.List;
import java.util.Set;

import static java.lang.String.format;

@Component
public class BigNeedService {

    private BigNeedRepository bigNeedRepository;




    @RequiredByFramework
    BigNeedService() {
    }

    @Autowired
    public  BigNeedService( BigNeedRepository bigNeedRepository) {
        this.bigNeedRepository = bigNeedRepository;
            }

    @Transactional
    public void save(BigNeed bigNeed) {
        bigNeedRepository.save(bigNeed);
    }

    public BigNeed getBigNeed(int priority) {
            return bigNeedRepository.load(priority);
    }

    public List<BigNeed> list() {
        return bigNeedRepository.list();
    }



}
