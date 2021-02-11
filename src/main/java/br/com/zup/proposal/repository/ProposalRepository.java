package br.com.zup.proposal.repository;

import br.com.zup.proposal.model.Proposal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProposalRepository extends CrudRepository<Proposal, Long> {

    Optional<Proposal> findByDocument(String document);

}
