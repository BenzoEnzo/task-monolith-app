package pl.benzo.enzo.server.api.service.basic;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.server.api.model.entity.AccountEntity;
import pl.benzo.enzo.server.api.model.entity.LinkEntity;
import pl.benzo.enzo.server.api.repository.LinkRepository;

@Service
@RequiredArgsConstructor
public class LinkServiceBasic {
    private final LinkRepository linkRepository;
    private final AccountServiceBasic accountServiceBasic;
    public void createLinkForAccount(LinkEntity linkEntity) {
        linkRepository.save(linkEntity);
    }
    @Transactional
    public void confirmAccount(String generatedVal){
        final LinkEntity linkEntity = linkRepository.findLinkEntityByGeneratedVal(generatedVal);
        final AccountEntity accountEntity = linkEntity.getAccount();
        accountEntity.setEnabled(true);
        accountServiceBasic.update(accountEntity);
        linkRepository.delete(linkEntity);
    }
}
