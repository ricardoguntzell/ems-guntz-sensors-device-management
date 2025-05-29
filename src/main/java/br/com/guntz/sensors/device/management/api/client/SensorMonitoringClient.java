package br.com.guntz.sensors.device.management.api.client;

import br.com.guntz.sensors.device.management.api.model.SensorMonitoringOutput;
import io.hypersistence.tsid.TSID;

public interface SensorMonitoringClient {

    void enableMonitoring(TSID sensorId);

    void disableMonitoring(TSID sensorId);

    SensorMonitoringOutput getDetail(TSID sensorId);

}
