package com.msbills.repositories;


import com.msbills.configuration.feign.OAuthFeignConfig;
import com.msbills.models.DTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// OAuthFeingConfig.class : para el flujo donde user-service busca el token de acceso en keycloak.
// FeignInterceptor.class : para el flujo donde user-service usa el token de acceso que viene de gateway en el filtro.

@FeignClient(name= "users-service",url = "http://users-service:8086", configuration = OAuthFeignConfig.class)
public interface FeignUsersRepository {

    @RequestMapping(method = RequestMethod.GET,value = "/users/{userName}")
    ResponseEntity<UserDTO> findByUserName(@RequestParam String userName);
}
