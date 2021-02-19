package br.com.zup.proposal.repository;

import br.com.zup.proposal.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

    Optional<Card> findByExternalId(UUID externalId);

}
