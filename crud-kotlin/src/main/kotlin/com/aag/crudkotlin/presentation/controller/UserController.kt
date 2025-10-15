package com.aag.crudkotlin.presentation.controller

import com.aag.crudkotlin.application.dto.request.AddressRequest
import com.aag.crudkotlin.application.dto.request.UserRequest
import com.aag.crudkotlin.application.dto.response.UserResponse
import com.aag.crudkotlin.domain.entity.User
import com.aag.crudkotlin.application.service.UserService
import lombok.AllArgsConstructor
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
@AllArgsConstructor
class UserController(
    private val userService: UserService
) {

    @PostMapping
    fun create(@RequestBody user: UserRequest): UserResponse {
        return userService.creatUser(user)
    }

    @GetMapping("{id}")
    fun get(@PathVariable id: Long) : UserResponse {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody userRequest: UserRequest) {
        userService.updateUser(id, userRequest);
    }

    @PostMapping("{id}/address")
    fun addAddress(@PathVariable id: Long, @RequestBody addressRequest: AddressRequest) {
        userService.addAddress(id, addressRequest)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        userService.remove(id);
    }

    @DeleteMapping("{id}/address/{adddressId}")
    fun deleteAddress(@PathVariable id: Long, @PathVariable adddressId: Long) {
        userService.removeAddress(id, adddressId);
    }
}
