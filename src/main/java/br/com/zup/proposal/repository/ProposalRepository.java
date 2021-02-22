package br.com.zup.proposal.repository;

import br.com.zup.proposal.model.Proposal;
import br.com.zup.proposal.model.enums.ProposalStatus;
import org.hibernate.LockOptions;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProposalRepository extends CrudRepository<Proposal, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({
            @QueryHint(name = "javax.persistence.lock.timeout", value = (LockOptions.SKIP_LOCKED + ""))
    })
    List<Proposal> findTop5ByStatusOrderByCreatedAtAsc(ProposalStatus status);

    Optional<Proposal> findByExternalId(UUID externalId);

    Optional<Proposal> findByDocument(String document);

    boolean existsByDocument(String document);

}
