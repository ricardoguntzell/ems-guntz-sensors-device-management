package br.com.guntz.sensors.device.management.api.model;

import io.hypersistence.tsid.TSID;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class SensorMonitoringOutput {

    private TSID id;

    private Double lasTemperature;

    private OffsetDateTime updatedAt;

    private Boolean enabled;

}
