package com.example.SpringBootBatchProcessingJPA.Service;

import com.example.SpringBootBatchProcessingJPA.Utils.CommmonUtil;
import com.example.SpringBootBatchProcessingJPA.Repository.DevicesRepository;
import com.example.SpringBootBatchProcessingJPA.Repository.ParseXmlDataRepository;
import com.example.SpringBootBatchProcessingJPA.XMLEntities.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class ParseXmlDataService {

    @Autowired
    DevicesRepository devicesRepository;

    @Autowired
    ParseXmlDataRepository parseXmlDataRepository;

    private void saveXmlDevices(UserXmlEntity userXmlEntityInst, int activityId) {
        try {
            if (CommmonUtil.isNotNullAndNotEmpty(userXmlEntityInst.getActivity().getDeviceXmlEntityList())) {
                DeviceXmlEntityList deviceXmlEntityList = userXmlEntityInst.getActivity().getDeviceXmlEntityList();
                List<DeviceXmlEntity> deviceXmlEntitiesList = deviceXmlEntityList.getDeviceXmlEntityList();
                for (DeviceXmlEntity deviceXmlEntity : deviceXmlEntitiesList) {
                    Devices devices = new Devices();
                    devices.setActivityId(activityId);
                    devices.setDeviceId(deviceXmlEntity.getDeviceId());
                    devices.setType(deviceXmlEntity.getType());
                    devices.setOs(deviceXmlEntity.getOs());
                    devicesRepository.save(devices);
                }
            }
        } catch (NullPointerException e) {
            throw e;
        }
    }

    public void parseAndSaveUsers() throws JAXBException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(UsersListXmlEntity.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            File xmlFile = new File("C:\\Users\\Asus\\OneDrive\\Desktop\\xml_complex_data.xml");
            UsersListXmlEntity usersListXmlEntityList = (UsersListXmlEntity) unmarshaller.unmarshal(xmlFile);
            for (UserXmlEntity userXmlEntityInst : usersListXmlEntityList.getUserXmlEntity()) {
                XmlUserData user = new XmlUserData();
                user.setUserId(userXmlEntityInst.getUserId());
                user.setEmail(userXmlEntityInst.getEmail());
                user.setName(userXmlEntityInst.getName());
                user.setGivenName(userXmlEntityInst.getPersonalInfoEntity().getGivenName());
                user.setFamilyName(userXmlEntityInst.getPersonalInfoEntity().getFamilyName());
                user.setNickname(userXmlEntityInst.getPersonalInfoEntity().getNickname());
                user.setCreatedAt(userXmlEntityInst.getCreatedAt());
                user.setUpdatedAt(userXmlEntityInst.getUpdatedAt());
                user.setEmailVerified(userXmlEntityInst.getEmailVerified());
                user.setLastIp(userXmlEntityInst.getActivity().getLastIp());
                user.setLoginsCount(userXmlEntityInst.getActivity().getLoginsCount());
                user.setLastLogin(userXmlEntityInst.getActivity().getLastLogin());
                user.setLanguage(userXmlEntityInst.getPreferences().getLanguage());
                user.setTimezone(userXmlEntityInst.getPreferences().getTimezone());
                user.setNotificationsEmail(userXmlEntityInst.getPreferences().getNotifications().getNotificationsEmail());
                user.setNotificationsSms(userXmlEntityInst.getPreferences().getNotifications().getNotificationsSms());
                XmlUserData XmlUserId = parseXmlDataRepository.save(user);
                saveXmlDevices(userXmlEntityInst, XmlUserId.getId());
            }
        } catch (JAXBException e) {
            throw e;
        }
    }
}
