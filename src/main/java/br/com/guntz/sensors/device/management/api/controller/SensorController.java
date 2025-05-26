package br.com.guntz.sensors.device.management.api.controller;

import br.com.guntz.sensors.device.management.api.model.SensorInput;
import br.com.guntz.sensors.device.management.api.model.SensorOutput;
import br.com.guntz.sensors.device.management.common.IdGenerator;
import br.com.guntz.sensors.device.management.domain.model.Sensor;
import br.com.guntz.sensors.device.management.domain.model.SensorId;
import br.com.guntz.sensors.device.management.domain.repository.SensorRepository;
import io.hypersistence.tsid.TSID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    private final SensorRepository sensorRepository;

    @GetMapping("/{sensorId}")
    public ResponseEntity<SensorOutput> getOne(@PathVariable TSID sensorId) {
        Sensor sensor = sensorRepository.findById(new SensorId(sensorId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok(convertModel(sensor));
    }

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

        return ResponseEntity.ok(convertModel(sensorRepository.saveAndFlush(sensor)));
    }

    private SensorOutput convertModel(Sensor sensor) {
        return SensorOutput.builder()
                .id(sensor.getId().getValue())
                .name(sensor.getName())
                .ip(sensor.getIp())
                .location(sensor.getLocation())
                .protocol(sensor.getProtocol())
                .model(sensor.getModel())
                .enabled(sensor.getEnabled())
                .build();

    }

}
