package com.hdtcn.authentication.service;

import com.hdtcn.authentication.entity.GroupName;
import com.hdtcn.authentication.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupNameService {
    public GroupName saveGroup(GroupName groupName);
    public List<GroupName> saveGroups(List<GroupName> groupNames);
    public List<GroupName> getGroupNames();
    public GroupName getGroupNameById(Integer id);
    public String deleteGroupName(Integer id);
    public GroupName updateGroupName(GroupName groupName);
}
