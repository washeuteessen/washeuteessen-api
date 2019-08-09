package de.washeuteessen.api.health;

import de.washeuteessen.api.health.model.V1Health;
import de.washeuteessen.api.health.model.V1HealthComponent;
import de.washeuteessen.api.health.model.V1HealthStatus;
import de.washeuteessen.api.health.swagger.v1.HealthApiDelegate;
import de.washeuteessen.api.health.swagger.v1.model.HealthApiV1Health;
import de.washeuteessen.api.health.swagger.v1.model.HealthApiV1HealthComponent;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HealthApiDelegateImpl implements HealthApiDelegate {

    private HealthEndpoint healthEndpoint;

    /**
     * custom health checks are needed to differ between up and available
     * <p>
     * spring actuator returns 200 when status is UP and UNKNOWN
     * kubernetes needs to differ from beeing up and running and beeing up and not ready
     * </p>
     *
     * @param component
     * @return
     */
    @Override
    public ResponseEntity<HealthApiV1HealthComponent> getComponentHealth(String component) {

        final Health health = healthEndpoint.healthForComponent(component);
        final V1HealthComponent v1Health = new V1HealthComponent(health);
        final HttpStatus status = V1HealthStatus.getStatusCode(v1Health.getStatus());

        return ResponseEntity.status(status).body(v1Health);
    }

    /**
     * custom health checks are needed to differ between up and available
     * <p>
     * spring actuator returns 200 when status is UP and UNKNOWN
     * kubernetes needs to differ from beeing up and running and beeing up and not ready
     * </p>
     *
     * @return
     */
    @Override
    public ResponseEntity<HealthApiV1Health> getHealth() {

        final Health health = healthEndpoint.health();
        final V1Health v1Health = new V1Health(health);
        final HttpStatus status = V1HealthStatus.getStatusCode(v1Health.getStatus());

        return ResponseEntity.status(status).body(v1Health);
    }
}
