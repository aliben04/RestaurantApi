package com.restaurant.RestaurantApi.controller
import com.restaurant.RestaurantApi.service.PlatPrincipalService
import com.restaurant.RestaurantApi.model.DTO.*
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping("/api/plats")
class PlatprincipalControllor(val platPrincipalService: PlatPrincipalService) {
    @GetMapping
    fun getAllplats():List<platprincipalDto> = platPrincipalService.getPlatsprincipal()
    @GetMapping("/{id}")
    fun getPlatsbyId(@PathVariable id: Long): platprincipalDto=platPrincipalService.getplatById(id)
    @PostMapping
    fun savePlats(@Valid @RequestBody plat: platPrincipalRequist): platprincipalDto=platPrincipalService.savePlatPrincipal(plat)
    @PutMapping("/{id}")
    fun updatePlat( @PathVariable id : Long,@Valid @RequestBody newPlat: platPrincipalRequist): platprincipalDto=platPrincipalService.uppdatePlatPrincipal(id,newPlat)
    @DeleteMapping("/{id}")
    fun deletePlat(@PathVariable id:Long)=platPrincipalService.deletePlatPrincipal(id)
}