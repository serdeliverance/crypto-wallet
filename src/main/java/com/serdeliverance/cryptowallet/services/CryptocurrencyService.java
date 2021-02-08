package com.serdeliverance.cryptowallet.services;

import com.serdeliverance.cryptowallet.dto.CurrencyQuoteDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CryptocurrencyService {
    public List<CurrencyQuoteDTO> getAll() {
        return new ArrayList<>();
    }
}
