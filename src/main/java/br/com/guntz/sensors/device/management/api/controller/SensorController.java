package br.com.guntz.sensors.device.management.api.controller;

import br.com.guntz.sensors.device.management.api.model.SensorInput;
import br.com.guntz.sensors.device.management.api.model.SensorOutput;
import br.com.guntz.sensors.device.management.common.IdGenerator;
import br.com.guntz.sensors.device.management.domain.model.Sensor;
import br.com.guntz.sensors.device.management.domain.model.SensorId;
import br.com.guntz.sensors.device.management.domain.repository.SensorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    private final SensorRepository sensorRepository;

    @PostMapping
    public ResponseEntity<SensorOutput> create(@RequestBody SensorInput input) {
        Sensor sensor =
                Sensor.builder()
                        .id(new SensorId(IdGenerator.generateTSID()))
                        .name(input.getName())
                        .ip(input.getIp())
                        .location(input.getLocation())
                        .protocol(input.getProtocol())
                        .model(input.getModel())
                        .enabled(false)
                        .build();

        return ResponseEntity.ok(new SensorOutput(sensorRepository.saveAndFlush(sensor)));
    }

}
