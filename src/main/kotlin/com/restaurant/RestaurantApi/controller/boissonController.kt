package com.restaurant.RestaurantApi.controller


import com.restaurant.RestaurantApi.model.Boisson
import com.restaurant.RestaurantApi.service.BoissonService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import com.restaurant.RestaurantApi.model.DTO.BoissonDTO
import com.restaurant.RestaurantApi.model.DTO.BoissonRequest
@RestController
@RequestMapping("/api/boissons")
class BoissonControllor(private val boissonService: BoissonService) {
    @PostMapping
    fun saveBoisson(@Valid @RequestBody boisson: BoissonRequest): Boisson = boissonService.saveBoisson(boisson)

    @GetMapping
    fun getAllBoissons(): List<Boisson> = boissonService.getAllBoissons()

    @GetMapping("/{id}")
    fun getBoissonById(@PathVariable id: Long): Boisson = boissonService.getBoissonById(id)
    @PutMapping("/{id}")
    fun updateBoisson( @PathVariable id: Long,@Valid @RequestBody newBoisson: BoissonRequest): Boisson = boissonService.updateBoisson(id,newBoisson)
    @DeleteMapping("/{id}")
    fun deleteBoisson(@PathVariable id: Long) = boissonService.deleteBoisson(id)

}

