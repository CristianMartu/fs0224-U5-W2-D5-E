package cristianmartucci.U5_W2_D5_E.payloads.devices;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record DeviceDTO(@NotBlank(message = "Tipologia obbligatoria")
                        String deviceTypology,
                        @NotBlank(message = "Stato obbligatorio")
                        String deviceStatus,
                        UUID employeeId) {
}
