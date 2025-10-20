package com.aag.crudkotlin.presentation.controller

import com.aag.crudkotlin.application.dto.request.UserRequest
import com.aag.crudkotlin.application.dto.response.BookResponse
import com.aag.crudkotlin.application.dto.response.PageResponse
import com.aag.crudkotlin.application.dto.response.UserResponse
import com.aag.crudkotlin.application.service.AuthService
import com.aag.crudkotlin.application.service.UserService
import com.aag.crudkotlin.infrastructure.security.RequireAdmin
import jakarta.servlet.http.HttpServletRequest
import lombok.AllArgsConstructor
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/users")
@AllArgsConstructor
class UserController(
    private val userService: UserService,
    private val authService: AuthService
) {

    @PostMapping
    fun create(@RequestBody user: UserRequest): UserResponse {
        return userService.creatUser(user)
    }

    @GetMapping("/{id}")
    fun get(
        request: HttpServletRequest,
        @PathVariable id: Long) : UserResponse {
        authService.validateAuthorization(request, id)
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    fun update(
        request: HttpServletRequest,
        @PathVariable id: Long,
        @RequestBody userRequest: UserRequest) {
        authService.validateAuthorization(request, id)
        userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    fun delete(
        request: HttpServletRequest,
        @PathVariable id: Long)
    {
        authService.validateAuthorization(request, id)
        userService.remove(id);
    }

    @RequireAdmin
    @GetMapping("/all")
    fun getAllBooks(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): PageResponse<UserResponse> {

        return userService.getAllUsers(page, size)
    }
}
