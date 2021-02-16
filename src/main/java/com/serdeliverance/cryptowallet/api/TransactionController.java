package com.serdeliverance.cryptowallet.api;

import com.serdeliverance.cryptowallet.dto.TransactionDTO;
import com.serdeliverance.cryptowallet.dto.TransferenceDTO;
import com.serdeliverance.cryptowallet.services.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/{userId}")
    public List<TransactionDTO> getHistory(@PathVariable("userId") Integer userId) {
        log.info("Getting transaction history for userId: {}", userId);
        return transactionService.getHistory(userId);
    }

    @PostMapping("/transferences")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transfer(@RequestBody TransferenceDTO transferenceDTO) {
        log.info("Performing transference from issuer {} to receiver {}",
                transferenceDTO.getIssuer(), transferenceDTO.getReceiver());
        transactionService.transfer(transferenceDTO.getIssuer(), transferenceDTO.getReceiver(),
                transferenceDTO.getCryptocurrency(), transferenceDTO.getAmount());
    }
}
