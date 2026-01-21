package com.codingshuttle.om.school.reopsitory;

import com.codingshuttle.om.school.entity.AdmissionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionRecordRepository extends JpaRepository<AdmissionRecord,Long> {
}
