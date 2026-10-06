package com.example.splitwise.services;

import com.example.splitwise.models.Expense;
import com.example.splitwise.models.Group;
import com.example.splitwise.models.User;
import com.example.splitwise.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;


    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public void createGroup(Group group){
        this.groupRepository.createGroup(group);
    }

    public Group getGroupDetails(String groupId){
       return this.groupRepository.getGroupDetails(groupId);
    }

    public List<Group> groupList(){
        return this.groupRepository.groupList();
    }

    public void addUser(String groupId, User user){
        this.groupRepository.addUser(groupId,user);

    }


}
