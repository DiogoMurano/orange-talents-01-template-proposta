package br.com.zup.proposal.repository;

import br.com.zup.proposal.model.Card;
import br.com.zup.proposal.model.Wallet;
import br.com.zup.proposal.model.enums.WalletType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long> {

    boolean existsByCardAndType(Card card, WalletType type);

}
