package com.restaurant.RestaurantApi.controller



import com.restaurant.RestaurantApi.service.ElementMenuService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import com.restaurant.RestaurantApi.model.DTO.ElementMenuRequist
import com.restaurant.RestaurantApi.model.DTO.ElementMenuDTO

@RestController
@RequestMapping("/api/elements")
class ElementMenuController(private val elementMenuService: ElementMenuService) {


    @GetMapping
    fun getAllElements(): List<ElementMenuDTO> = elementMenuService.getAllElements()

    @GetMapping("/{id}")
    fun getElementById(@PathVariable id: Long): ElementMenuDTO = elementMenuService.getElementById(id)


    @PostMapping
    fun createElement(@Valid @RequestBody element: ElementMenuRequist): ElementMenuDTO = elementMenuService.saveElement(element)

    @PutMapping("/{id}")
    fun updateElement( @PathVariable id: Long,@Valid @RequestBody updated: ElementMenuRequist): ElementMenuDTO =
        elementMenuService.updateElement(id, updated)

    @DeleteMapping("/{id}")
    fun deleteElement(@PathVariable id: Long) = elementMenuService.deleteElement(id)
}