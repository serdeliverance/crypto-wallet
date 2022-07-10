/* (C)2022 */
package com.serdeliverance.cryptowallet.api;

import static com.serdeliverance.cryptowallet.dto.StatusDTO.ok;

import com.serdeliverance.cryptowallet.dto.StatusDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthcheck")
public class HealthCheckController {

    @GetMapping
    public StatusDTO healthcheck() {
        return ok();
    }
}
