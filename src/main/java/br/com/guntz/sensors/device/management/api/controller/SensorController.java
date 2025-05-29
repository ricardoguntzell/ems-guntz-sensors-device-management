package br.com.guntz.sensors.device.management.api.controller;

import br.com.guntz.sensors.device.management.api.client.SensorMonitoringClient;
import br.com.guntz.sensors.device.management.api.model.SensorInput;
import br.com.guntz.sensors.device.management.api.model.SensorOutput;
import br.com.guntz.sensors.device.management.common.IdGenerator;
import br.com.guntz.sensors.device.management.domain.model.Sensor;
import br.com.guntz.sensors.device.management.domain.model.SensorId;
import br.com.guntz.sensors.device.management.domain.repository.SensorRepository;
import br.com.guntz.sensors.device.management.domain.service.SensorService;
import io.hypersistence.tsid.TSID;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    private final SensorRepository sensorRepository;
    private final SensorService sensorService;
    private final SensorMonitoringClient sensorMonitoringClient;

    @GetMapping
    public ResponseEntity<Page<SensorOutput>> search(@PageableDefault Pageable pageable) {
        Page<SensorOutput> sensors = sensorRepository.findAll(pageable)
                .map(this::convertOutputModel);

        return ResponseEntity.ok(sensors);

    }

    @GetMapping("/{sensorId}")
    public ResponseEntity<SensorOutput> getOne(@PathVariable TSID sensorId) {
        Sensor sensor = findSensorById(sensorId);

        return ResponseEntity.ok(convertOutputModel(sensor));
    }

    @PostMapping
    public ResponseEntity<SensorOutput> create(@RequestBody SensorInput input) {
        Sensor sensor = convertInputModel(input);

        return ResponseEntity.ok(convertOutputModel(sensorRepository.saveAndFlush(sensor)));
    }

    @PutMapping("/{sensorId}")
    public ResponseEntity<SensorOutput> update(@PathVariable TSID sensorId, @Valid @RequestBody SensorInput inputUpdate) {
        Sensor sensorLocated = findSensorById(sensorId);

        sensorService.update(sensorLocated, inputUpdate);

        return ResponseEntity.ok(convertOutputModel(sensorLocated));
    }

    private Sensor findSensorById(TSID sensorId) {
        return sensorRepository.findById(new SensorId(sensorId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{sensorId}")
    public ResponseEntity<Object> remove(@PathVariable TSID sensorId) {
        Sensor sensorLocated = findSensorById(sensorId);

        sensorService.remove(sensorLocated);
        sensorMonitoringClient.disableMonitoring(sensorId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{sensorId}/enable")
    public ResponseEntity<Object> enable(@PathVariable TSID sensorId) {
        Sensor sensorLocated = findSensorById(sensorId);

        sensorService.enable(sensorLocated);
        sensorMonitoringClient.enableMonitoring(sensorId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{sensorId}/enable")
    public ResponseEntity<Object> disable(@PathVariable TSID sensorId) {
        Sensor sensorLocated = findSensorById(sensorId);

        sensorService.disable(sensorLocated);
        sensorMonitoringClient.disableMonitoring(sensorId);

        return ResponseEntity.noContent().build();
    }

    private SensorOutput convertOutputModel(Sensor sensor) {
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

    private Sensor convertInputModel(SensorInput input) {
        return Sensor.builder()
                .id(new SensorId(IdGenerator.generateTSID()))
                .name(input.getName())
                .ip(input.getIp())
                .location(input.getLocation())
                .protocol(input.getProtocol())
                .model(input.getModel())
                .enabled(false)
                .build();

    }

    private Sensor convertInputUpdateModel(SensorInput input) {
        return Sensor.builder()
                .id(new SensorId(IdGenerator.generateTSID()))
                .name(input.getName())
                .ip(input.getIp())
                .location(input.getLocation())
                .protocol(input.getProtocol())
                .model(input.getModel())
                .build();

    }

}
