package com.hdtcn.authentication.service;

import com.hdtcn.authentication.entity.GroupName;
import com.hdtcn.authentication.repository.GroupNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class GroupNameServiceImp implements  GroupNameService {
    @Autowired
    private GroupNameRepository groupNameRepository;
    @Override
    public GroupName saveGroup(GroupName groupName) {
        return  groupNameRepository.save(groupName);
    }

    @Override
    public List<GroupName> saveGroups(List<GroupName> groupNames) {
        return groupNameRepository.saveAll(groupNames);
    }

    @Override
    public List<GroupName> getGroupNames() {
        return groupNameRepository.findAll();
    }

    @Override
    public GroupName getGroupNameById(Integer id) {
        return groupNameRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteGroupName(Integer id) {
        try {
            if (groupNameRepository.existsById(id)) {
                groupNameRepository.deleteById(id);
                return "Delete group id: " + id;
            }
        } catch (Exception e) {
            return "Delete group: " + id + " error " + e.getMessage();
        }
        return "Delete group: " + id + " error ";
    }

    @Override
    public GroupName updateGroupName(GroupName groupName) {
        GroupName existGroupName = groupNameRepository.findById(groupName.getId()).orElse(null);
        if (existGroupName != null) {
            existGroupName.setName(groupName.getName());
            return groupNameRepository.save(existGroupName);
        }
        return null;
    }
}
