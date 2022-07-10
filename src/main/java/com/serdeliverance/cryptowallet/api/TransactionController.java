/* (C)2022 */
package com.serdeliverance.cryptowallet.api;

import com.serdeliverance.cryptowallet.dto.BuyDTO;
import com.serdeliverance.cryptowallet.dto.SellDTO;
import com.serdeliverance.cryptowallet.dto.TransactionDTO;
import com.serdeliverance.cryptowallet.dto.TransferenceDTO;
import com.serdeliverance.cryptowallet.services.TransactionService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        log.info(
                "Performing transference from issuer {} to receiver {}",
                transferenceDTO.getIssuer(),
                transferenceDTO.getReceiver());
        transactionService.transfer(
                transferenceDTO.getIssuer(),
                transferenceDTO.getReceiver(),
                transferenceDTO.getCryptocurrency(),
                transferenceDTO.getAmount());
    }

    @PostMapping("/buys")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void buy(@Valid @RequestBody BuyDTO buyDTO) {
        log.info("Perfoming buy by user {}", buyDTO.getUserId());
        transactionService.buy(
                buyDTO.getUserId(), buyDTO.getCryptocurrency(), buyDTO.getAmountInUsd());
    }

    @PostMapping("/sells")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void buy(@Valid @RequestBody SellDTO sellDTO) {
        log.info("Perfoming selling by user {}", sellDTO.getUserId());
        transactionService.sell(
                sellDTO.getUserId(), sellDTO.getCryptocurrency(), sellDTO.getAmount());
    }
}
