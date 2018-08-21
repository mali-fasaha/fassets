package io.github.fasset.fasset.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Provides api for directly accessing the account abstraction
 */
@RestController
public class AccountController {

    @GetMapping("api/accounts")
    public Flux<String> listAccounts(){

        return Flux.just();
    }
}
