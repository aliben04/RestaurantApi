package com.restaurant.RestaurantApi.controller


import com.restaurant.RestaurantApi.service.BoissonService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import com.restaurant.RestaurantApi.model.DTO.BoissonDTO
import com.restaurant.RestaurantApi.model.DTO.BoissonRequest
@RestController
@RequestMapping("/api/boissons")
class BoissonControllor(private val boissonService: BoissonService) {
    @PostMapping
    fun saveBoisson(@Valid @RequestBody boisson: BoissonRequest): BoissonDTO = boissonService.saveBoisson(boisson)

    @GetMapping
    fun getAllBoissons(): List<BoissonDTO> = boissonService.getAllBoissons()

    @GetMapping("/{id}")
    fun getBoissonById(@PathVariable id: Long): BoissonDTO = boissonService.getBoissonById(id)
    @PutMapping("/{id}")
    fun updateBoisson( @PathVariable id: Long,@Valid @RequestBody newBoisson: BoissonRequest): BoissonDTO = boissonService.updateBoisson(id,newBoisson)
    @DeleteMapping("/{id}")
    fun deleteBoisson(@PathVariable id: Long) = boissonService.deleteBoisson(id)

}

