package br.com.guntz.sensors.device.management.api.model;

import lombok.Data;

@Data
public class SensorInputUpdate {

    private String name;

    private String ip;

    private String location;

    private String protocol;

    private String model;

}
