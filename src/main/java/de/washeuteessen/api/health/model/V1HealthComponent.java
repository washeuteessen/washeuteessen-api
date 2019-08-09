package de.washeuteessen.api.health.model;

import de.washeuteessen.api.health.swagger.v1.model.HealthApiV1HealthComponent;
import de.washeuteessen.api.health.swagger.v1.model.HealthApiV1HealthComponentDetails;
import org.springframework.boot.actuate.health.Health;

public class V1HealthComponent extends HealthApiV1HealthComponent {

    public V1HealthComponent(final Object healthEntry) {
        final Health health = (Health) healthEntry;

        super.setStatus(V1HealthStatus.fromStatus(health.getStatus()));
        super.setDetails(new HealthApiV1HealthComponentDetails());

        health.getDetails().forEach((key, value) -> {
            super.getDetails().put(key, value);
        });
    }

}
