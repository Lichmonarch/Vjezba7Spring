package com.example.herewegoagain.hardware.Service;

import com.example.herewegoagain.hardware.Hardware;
import com.example.herewegoagain.hardware.HardwareCommand;
import com.example.herewegoagain.hardware.HardwareDTO;
import com.example.herewegoagain.hardware.Repository.HardwareRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HardwareClassService implements HardwareService, Serializable {

    private final HardwareRepository hardwareRepository;

    public HardwareClassService(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    public List<HardwareDTO> findAll() {
        return hardwareRepository.findAll().stream().map(this::mapHardwareToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<HardwareDTO> findByCode(String code) {
        return hardwareRepository.findByCode(code).map(this::mapHardwareToDTO);
    }

    @Override
    public Optional<HardwareDTO> save(HardwareCommand hardwareCommand) {
        return hardwareRepository.save(mapCommandToHardware(hardwareCommand)).map(this::mapHardwareToDTO);
    }

    @Override
    public void deleteByCode(String code) {
        hardwareRepository.deleteByCode(code);
    }

    @Override
    public Optional<HardwareDTO> update(String code, HardwareCommand updateHardwareCommand) {
        return hardwareRepository.update(code, mapCommandToHardware(updateHardwareCommand)).map(this::mapHardwareToDTO);
    }

    private Hardware mapCommandToHardware(HardwareCommand hardwareCommand) {
        return new Hardware( hardwareCommand.getCode(),hardwareCommand.getName(),
                hardwareCommand.getPrice(), hardwareCommand.getType(), hardwareCommand.getStock());
    }

    private HardwareDTO mapHardwareToDTO(final Hardware hardware){
        return new HardwareDTO(hardware.getIndex(), hardware.getName(), hardware.getPrice());
    }
}
