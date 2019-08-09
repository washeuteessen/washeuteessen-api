package de.washeuteessen.api.health.model;

import de.washeuteessen.api.health.swagger.v1.model.HealthApiV1Health;
import de.washeuteessen.api.health.swagger.v1.model.HealthApiV1HealthDetails;
import org.springframework.boot.actuate.health.Health;

public class V1Health extends HealthApiV1Health {

    public V1Health(Health health) {
        super.setStatus(V1HealthStatus.fromStatus(health.getStatus()));
        super.setDetails(new HealthApiV1HealthDetails());
        health.getDetails().forEach((key, value) -> {
            super.getDetails().put(key, new V1HealthComponent(value));
        });
    }

}
