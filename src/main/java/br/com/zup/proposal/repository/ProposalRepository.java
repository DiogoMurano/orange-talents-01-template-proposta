package br.com.zup.proposal.repository;

import br.com.zup.proposal.model.Card;
import br.com.zup.proposal.model.Proposal;
import br.com.zup.proposal.model.enums.ProposalStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProposalRepository extends CrudRepository<Proposal, Long> {

    List<Proposal> findAllByStatusAndCard(ProposalStatus status, Card card);

    Optional<Proposal> findByExternalId(UUID externalId);

    Optional<Proposal> findByDocument(String document);

    boolean existsByDocument(String document);

}
