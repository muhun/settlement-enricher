package com.jpmg.asgn.settlementenricher.repo;

import com.jpmg.asgn.settlementenricher.model.SSIInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SSIInfoRepo extends JpaRepository<SSIInfo, Long> {
    SSIInfo findFirstBySsiCode(String ssiCode);
}
