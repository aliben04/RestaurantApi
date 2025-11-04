package com.restaurant.RestaurantApi.controller
import com.restaurant.RestaurantApi.service.PlatPrincipalService
import com.restaurant.RestaurantApi.model.DTO.*
import com.restaurant.RestaurantApi.model.PlatPrincipal
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping("/api/plats")
class PlatprincipalControllor(val platPrincipalService: PlatPrincipalService) {
    @GetMapping
    fun getAllplats():List<PlatPrincipal> = platPrincipalService.getPlatsprincipal()

    @GetMapping("/{id}")
    fun getPlatsbyId(@PathVariable id: Long): PlatPrincipal=platPrincipalService.getplatById(id)

    @PostMapping
    fun savePlats(@Valid @RequestBody plat: platPrincipalRequist): PlatPrincipal=platPrincipalService.savePlatPrincipal(plat)

    @PutMapping("/{id}")
    fun updatePlat( @PathVariable id : Long,@Valid @RequestBody newPlat: platPrincipalRequist): PlatPrincipal=platPrincipalService.uppdatePlatPrincipal(id,newPlat)

    @DeleteMapping("/{id}")
    fun deletePlat(@PathVariable id:Long)=platPrincipalService.deletePlatPrincipal(id)
}