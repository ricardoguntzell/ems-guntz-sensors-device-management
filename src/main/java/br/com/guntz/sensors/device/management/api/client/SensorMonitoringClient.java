package br.com.guntz.sensors.device.management.api.client;

import io.hypersistence.tsid.TSID;

public interface SensorMonitoringClient {

    void enableMonitoring(TSID sensorId);

    void disableMonitoring(TSID sensorId);

}
