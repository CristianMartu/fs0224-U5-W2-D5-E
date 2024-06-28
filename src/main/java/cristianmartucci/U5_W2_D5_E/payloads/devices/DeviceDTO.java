package cristianmartucci.U5_W2_D5_E.payloads.devices;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record DeviceDTO(@NotBlank String deviceTypology,
                        @NotBlank String deviceStatus,
                        UUID employeeId) {
}
