package com.restaurant.RestaurantApi.controller

import com.restaurant.RestaurantApi.model.DTO.DessertRequist
import com.restaurant.RestaurantApi.model.Dessert
import com.restaurant.RestaurantApi.service.DessertService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/desserts")
class DessertController(val dessertService: DessertService){
    @GetMapping
    fun getDesserts():List<Dessert> = dessertService.getDesserts()
    @GetMapping("/{id}")
    fun getDessertById(@PathVariable id:Long): Dessert=dessertService.getDesserById(id)
    @PostMapping
    fun saveDessert(@Valid @RequestBody dessert:DessertRequist):Dessert=dessertService.saveDessert(dessert)
    @PutMapping("/{id}")
    fun uppdateDessert( @PathVariable id:Long,@Valid @RequestBody newDessert:DessertRequist):Dessert=dessertService.updateDessert(id,newDessert)

    @DeleteMapping("/{id}")
    fun deleteDessert(@PathVariable id : Long) = dessertService.deleteDessert(id)

}