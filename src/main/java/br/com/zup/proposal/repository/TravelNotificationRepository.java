package br.com.zup.proposal.repository;

import br.com.zup.proposal.model.TravelNotification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelNotificationRepository extends CrudRepository<TravelNotification, Long> {
}
