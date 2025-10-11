package com.aag.crudkotlin.controller

import com.aag.crudkotlin.dtos.AddressDto
import com.aag.crudkotlin.dtos.UserDto
import com.aag.crudkotlin.dtos.UserResponseDto
import com.aag.crudkotlin.entities.User
import com.aag.crudkotlin.services.UserService
import lombok.AllArgsConstructor
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
@AllArgsConstructor
class UserController(
    private val userService: UserService
) {

    @PostMapping
    fun create(@RequestBody user: UserDto): User {
        return userService.creatUser(user)
    }

    @GetMapping("{id}")
    fun get(@PathVariable id: Long) : UserResponseDto {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody userDto: UserDto) {
        userService.updateUser(id, userDto);
    }

    @PostMapping("{id}/address")
    fun addAddress(@PathVariable id: Long, @RequestBody addressDto: AddressDto) {
        userService.addAddress(id, addressDto)
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
