package br.com.guntz.sensors.device.management.domain.repository;

import br.com.guntz.sensors.device.management.domain.model.Sensor;
import br.com.guntz.sensors.device.management.domain.model.SensorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, SensorId> {

}
