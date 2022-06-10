package com.example.herewegoagain.hardware.Repository;

import com.example.herewegoagain.hardware.Hardware;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface HardwareRepository {
    Set<Hardware> findAll();
    Optional<Hardware> findByCode(String code);
    Optional<Hardware> save(Hardware hardware);
    void deleteByCode(String code);

    Optional<Hardware> update(String code, Hardware updatedHardware);
}

