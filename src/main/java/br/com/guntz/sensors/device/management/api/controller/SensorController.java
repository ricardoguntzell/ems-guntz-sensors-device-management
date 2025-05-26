package br.com.guntz.sensors.device.management.api.controller;

import br.com.guntz.sensors.device.management.api.model.SensorInput;
import br.com.guntz.sensors.device.management.common.IdGenerator;
import br.com.guntz.sensors.device.management.domain.model.Sensor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    @PostMapping
    public ResponseEntity<Sensor> create(@RequestBody SensorInput input) {
        return ResponseEntity.ok(
                Sensor.builder()
                        .id(IdGenerator.generateTSID())
                        .name(input.getName())
                        .ip(input.getIp())
                        .location(input.getLocation())
                        .protocol(input.getProtocol())
                        .model(input.getModel())
                        .enabled(false)
                        .build()

        );
    }

}
