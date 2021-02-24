package br.com.zup.proposal.repository;

import br.com.zup.proposal.model.Card;
import br.com.zup.proposal.model.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long> {

    boolean existsByCardAndWallet(Card card, String wallet);

}
