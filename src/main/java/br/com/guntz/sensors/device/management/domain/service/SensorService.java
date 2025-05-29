package br.com.guntz.sensors.device.management.domain.service;

import br.com.guntz.sensors.device.management.api.model.SensorInput;
import br.com.guntz.sensors.device.management.domain.model.Sensor;
import br.com.guntz.sensors.device.management.domain.repository.SensorRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    @Transactional
    public void update(Sensor sensorLocated, SensorInput inputUpdate) {
        sensorLocated.update(inputUpdate);
    }

    @Transactional
    public void remove(Sensor sensorLocated) {
        sensorRepository.delete(sensorLocated);
    }

    @Transactional
    public void enable(Sensor sensorLocated) {
        sensorLocated.enable();
    }

    @Transactional
    public void disable(Sensor sensorLocated) {
        sensorLocated.disable();
    }

}
