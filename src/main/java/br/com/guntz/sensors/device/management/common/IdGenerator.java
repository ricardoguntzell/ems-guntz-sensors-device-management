package br.com.guntz.sensors.device.management.common;

import io.hypersistence.tsid.TSID;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
public class IdGenerator {

    private static final TSID.Factory tsidFactory;

    static {
        Optional.ofNullable(System.getenv("tsid.node"))
                .ifPresent(tsidNode -> System.setProperty("tsid.node", tsidNode));

        Optional.ofNullable(System.getenv("tsid.node.count"))
                .ifPresent(tsidNodeCount -> System.setProperty("tsid.node.count", tsidNodeCount));

        tsidFactory = TSID.Factory.builder().build();
    }

    public static TSID generateTSID() {
        return tsidFactory.generate();
    }


}
