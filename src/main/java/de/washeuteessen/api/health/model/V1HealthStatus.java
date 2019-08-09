package de.washeuteessen.api.health.model;

import de.washeuteessen.api.health.swagger.v1.model.HealthApiV1Status;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.HttpStatus;

public class V1HealthStatus {

    public static HealthApiV1Status fromStatus(final Status status) {

        switch (status.getCode()) {
            case "DOWN":
                return HealthApiV1Status.DOWN;
            case "UP":
                return HealthApiV1Status.UP;
            case "UNKNOWN":
                return HealthApiV1Status.NOT_READY;
            case "OUT_OF_SERVICE":
                return HealthApiV1Status.DOWN;
            default:
                return HealthApiV1Status.DOWN;
        }

    }

    public static HttpStatus getStatusCode(final HealthApiV1Status status) {
        if (status == HealthApiV1Status.UP) {
            return HttpStatus.OK;
        } else if (status == HealthApiV1Status.NOT_READY) {
            return HttpStatus.SERVICE_UNAVAILABLE;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
