package com.example.herewegoagain.hardware.Service;

import com.example.herewegoagain.hardware.Hardware;
import com.example.herewegoagain.hardware.HardwareCommand;
import com.example.herewegoagain.hardware.HardwareDTO;

import java.util.List;
import java.util.Optional;

public interface HardwareService {
    List<HardwareDTO> findAll();
    Optional<HardwareDTO> findByCode(String code);
    Optional<HardwareDTO> save(HardwareCommand hardwareCommand);

    void deleteByCode(String code);

    Optional<HardwareDTO> update(String code, HardwareCommand updateHardwareCommand);

}
