package cristianmartucci.U5_W2_D5_E.services;

import cristianmartucci.U5_W2_D5_E.entities.Device;
import cristianmartucci.U5_W2_D5_E.entities.Employee;
import cristianmartucci.U5_W2_D5_E.enums.DeviceStatus;
import cristianmartucci.U5_W2_D5_E.enums.DeviceTypology;
import cristianmartucci.U5_W2_D5_E.exceptions.BadRequestException;
import cristianmartucci.U5_W2_D5_E.exceptions.NotFoundException;
import cristianmartucci.U5_W2_D5_E.payloads.devices.DeviceDTO;
import cristianmartucci.U5_W2_D5_E.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private  EmployeeService employeeService;

    public Device saveDevice(DeviceDTO deviceDTO){
        Device device = new Device(stringToDeviceTypology(deviceDTO.deviceTypology()), stringToDeviceStatus(deviceDTO.deviceStatus()), this.employeeService.findByID(deviceDTO.employeeId()));
        return this.deviceRepository.save(device);
    }

    public Page<Device> getAllDevice(int pageNumber, int pageSize, String sortBy){
        if(pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return this.deviceRepository.findAll(pageable);
    }

    public Device findByID(UUID deviceId){
        return this.deviceRepository.findById(deviceId).orElseThrow(() -> new NotFoundException(deviceId));
    }

    public Device updateDevice(UUID deviceId, Device updateDevice){
        Device device = this.findByID(deviceId);
        device.setDeviceTypology(updateDevice.getDeviceTypology());
        device.setDeviceStatus(updateDevice.getDeviceStatus());
        device.setEmployee(updateDevice.getEmployee());
        return this.deviceRepository.save(device);
    }

    public void deleteDevice(UUID deviceId) {
        Device device = this.findByID(deviceId);
        this.deviceRepository.delete(device);
    }

    private static DeviceTypology stringToDeviceTypology(String type) {
        try {
            return DeviceTypology.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Tipologia dispositivo inserito non coretto!\n Tipologie disponibili: LAPTOP, SMARTPHONE, COMPUTER, TABLET.");
        }
    }

    private static DeviceStatus stringToDeviceStatus(String status) {
        try {
            return DeviceStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Stato dispositivo inserito non coretto!\n Stati disponibili: AVAILABLE, ASSIGNED, MAINTENANCE, DECOMMISSIONED");
        }
    }
}
