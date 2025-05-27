package br.com.guntz.sensors.device.management.domain.model;

import br.com.guntz.sensors.device.management.api.model.SensorInput;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class Sensor {

    @Id
    @AttributeOverride(name = "value", column = @Column(name = "id", columnDefinition = "BIGINT"))
    private SensorId id;

    private String name;

    private String ip;

    private String location;

    private String protocol;

    private String model;

    private Boolean enabled;

    public void update(SensorInput inputUpdate) {
        BeanUtils.copyProperties(inputUpdate, this);
    }

    public void enable() {
        setEnabled(true);
    }

    public void disable() {
        setEnabled(false);
    }
}
