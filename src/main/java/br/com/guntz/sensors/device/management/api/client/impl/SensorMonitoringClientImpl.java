package br.com.guntz.sensors.device.management.api.client.impl;

import br.com.guntz.sensors.device.management.api.client.SensorMonitoringClient;
import io.hypersistence.tsid.TSID;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class SensorMonitoringClientImpl implements SensorMonitoringClient {

    private final RestClient restClient;

    public SensorMonitoringClientImpl(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("http://localhost:8082")
                .build();
    }

    @Override
    public void enableMonitoring(TSID sensorId) {
        restClient.put()
                .uri("/api/sensors/{sensorId}/monitoring/enable", sensorId)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public void disableMonitoring(TSID sensorId) {
        restClient.delete()
                .uri("/api/sensors/{sensorId}/monitoring/enable", sensorId)
                .retrieve()
                .toBodilessEntity();
    }
}
