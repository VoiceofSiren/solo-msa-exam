package com.sparta.msa_exam.product.msa;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service")
public interface AuthClient {

    @GetMapping("/auth/userId/{userId}")
    public Boolean searchByUserId(@PathVariable(name = "userId") String UserId);
}
