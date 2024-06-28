package cristianmartucci.U5_W2_D5_E.services;

import cristianmartucci.U5_W2_D5_E.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
}
