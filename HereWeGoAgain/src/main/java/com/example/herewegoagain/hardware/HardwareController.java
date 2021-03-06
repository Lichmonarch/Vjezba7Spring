package com.example.herewegoagain.hardware;

import com.example.herewegoagain.hardware.Service.HardwareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hardware")
@CrossOrigin(origins = "http://localhost:4200")
public class HardwareController {

    private final HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("")
    public List<HardwareDTO> findAllHardware() {
        return hardwareService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/{index}")
    public ResponseEntity<HardwareDTO> findHardwareByCode(@PathVariable final String index) {
        return hardwareService.findByCode(index).map(ResponseEntity::ok).orElseGet(
                () -> ResponseEntity.notFound().build()
        );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Secured({"ROLE_ADMIN"})
    @PostMapping("")
    public ResponseEntity<HardwareDTO> save(@Valid @RequestBody final HardwareCommand command) {
        return hardwareService.save(command)
                .map(
                        hardwareDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(hardwareDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
    }

    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{code}")
    public ResponseEntity<HardwareDTO> update(@PathVariable String code, @Valid @RequestBody final HardwareCommand updateHardwareCommand) {
        return hardwareService.update(code, updateHardwareCommand)
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code) {
        hardwareService.deleteByCode(code);
    }
}


