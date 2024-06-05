package ProjetGenieLogiciel.isepval.services;

import ProjetGenieLogiciel.isepval.models.PlayerGroup;
import ProjetGenieLogiciel.isepval.repositories.PlayerGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class PlayerGroupService {

    @Autowired
    private PlayerGroupRepository playerGroupRepository;

    public PlayerGroup save(PlayerGroup playerGroup) {
        System.out.println(playerGroup.getId());
        playerGroup.setAddAt(Instant.now());
        return playerGroupRepository.save(playerGroup);
    }

    public void delete(PlayerGroup playerGroup) {
        playerGroupRepository.delete(playerGroup);
    }

    public List<PlayerGroup> findById(long id){return playerGroupRepository.findById(id);}

    public List<PlayerGroup> findByUserId(long id){return playerGroupRepository.findByUserId(id);}

    public PlayerGroup findByUserIdAndGroupId(long userId, long groupId){return playerGroupRepository.findByUserIdAndGroupId(userId, groupId);}
}
