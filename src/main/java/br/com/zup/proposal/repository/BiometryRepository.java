package br.com.zup.proposal.repository;

import br.com.zup.proposal.model.Biometry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiometryRepository extends CrudRepository<Biometry, Long> {
}
