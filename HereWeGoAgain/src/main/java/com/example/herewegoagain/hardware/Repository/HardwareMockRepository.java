package com.example.herewegoagain.hardware.Repository;

import com.example.herewegoagain.hardware.Hardware;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.*;

import static com.example.herewegoagain.hardware.Type.CPU;
import static com.example.herewegoagain.hardware.Type.GPU;



@Profile("dev")
@Repository
public class HardwareMockRepository implements HardwareRepository, Serializable {

    private final Set<Hardware> hardwareList = new HashSet<>(
            Arrays.asList(new Hardware("001","NVIDIA RTX 3090",  1099.9, "GPU", 3),
                    new Hardware("002","MSI B550 TOMAHAWK",  199.9, "MBO", 4),
                    new Hardware("003","AMD RYZEN 9 5950X",  699.9, "CPU", 5 ),
                    new Hardware("004","KINGSTON FURY BEAST 16GB",  99.9,"RAM", 10)
            )
    );

    @Override
    public Set<Hardware> findAll() {
        return hardwareList;
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        return hardwareList.stream().filter(it -> Objects.equals(it.getIndex(), code)).findAny();
    }

    @Override
    public Optional<Hardware> save(Hardware hardware) {
        if(!hardwareList.contains(hardware)){
            hardwareList.add(hardware);
            return Optional.of(hardware);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteByCode(final String code) {
        hardwareList.removeIf(it -> Objects.equals(it.getIndex(), code));
    }

    @Override
    public Optional<Hardware> update(String code, Hardware updatedHardware) {
        boolean exists = hardwareList.removeIf(
                it -> Objects.equals(it.getIndex(), code) && Objects.equals(it.getIndex(), updatedHardware.getIndex())
        );

        if(exists){
            hardwareList.add(updatedHardware);
            return Optional.of(updatedHardware);
        } else {
            return Optional.empty();
        }
    }

}
