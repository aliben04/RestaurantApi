package com.restaurant.RestaurantApi.controller


import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * هذا الكلاس يراقب كل الـ Controllers في التطبيق
 * ويلتقط الأخطاء المحددة ويعالجها.
 */
@RestControllerAdvice
class GlobalExceptionHandler {

    /**
     * هذه الدالة تعالج أخطاء التحقق (@Valid) فقط
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // (إرجاع كود 400 - طلب سيء)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): Map<String, String> {

        val errors = mutableMapOf<String, String>()

        // المرور على كل الأخطاء التي حدثت
        ex.bindingResult.fieldErrors.forEach { error ->
            val fieldName = error.field
            val errorMessage = error.defaultMessage ?: "خطأ غير معروف في التحقق"

            // إضافة الخطأ إلى الـ Map
            // (اسم الحقل -> رسالة الخطأ التي كتبتها)
            errors[fieldName] = errorMessage
        }

        return errors
    }
}