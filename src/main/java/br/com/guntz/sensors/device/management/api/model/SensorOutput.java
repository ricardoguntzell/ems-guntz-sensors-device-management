package br.com.guntz.sensors.device.management.api.model;

import br.com.guntz.sensors.device.management.domain.model.Sensor;
import io.hypersistence.tsid.TSID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class SensorOutput {

    private TSID id;

    private String name;

    private String ip;

    private String location;

    private String protocol;

    private String model;

    private Boolean enabled;

    public SensorOutput(Sensor sensor) {
        setId(sensor.getId().getValue());
        BeanUtils.copyProperties(sensor, this);
    }
}
