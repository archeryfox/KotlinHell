package com.sabaka.journal.utils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.servlet.error.ErrorAttributes
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping


@Controller
class ErrorController @Autowired constructor(
    private val errorAttributes: ErrorAttributes,
    private val applicationContext: ApplicationContext,
    @Qualifier("requestMappingHandlerMapping") // Явно указываем бин
    private val requestMappingHandlerMapping: RequestMappingHandlerMapping
) : ErrorController {

    @GetMapping("/error")
    fun handleError(model: Model, webRequest: WebRequest): String {
        // Получаем информацию об ошибке
        val errorAttributesMap = errorAttributes.getErrorAttributes(
            webRequest,
            ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE)
        )
        val pathErr = errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults())
        // Основные атрибуты
        val errorMessage = errorAttributesMap["message"] ?: "Unknown error"
        val errorPath = errorAttributesMap["path"] ?: "Unknown error"
        val status = errorAttributesMap["status"] ?: 500
        val trace = errorAttributesMap["trace"] ?: "No trace available"

        // Добавляем атрибуты об ошибке в модель
        model.addAttribute("status", status)
        model.addAttribute("errorMessage", errorMessage)
        model.addAttribute("errorPath", pathErr["path"])

        model.addAttribute("path", errorPath)
        model.addAttribute("trace", trace)

        // Получаем все возможные пути контроллеров
        val allEndpoints = requestMappingHandlerMapping.handlerMethods.entries
            .flatMap { entry ->
                val patterns = entry.key.patternsCondition?.patterns ?: setOf("Unknown")
                val requestMethods = entry.key.methodsCondition.methods

                // Check if the entry has any patterns and methods
                if (patterns.isNotEmpty() && requestMethods.isNotEmpty() && RequestMethod.GET in requestMethods) {
                    patterns.map { entry.key.toString().replace(Regex("[\\[\\]{}]|GET"), "").trim() }
                } else {
                    emptyList()
                }
            }


        // Добавляем список путей в модель
        model.addAttribute("allEndpoints", allEndpoints)

        return "error_page"  // Имя шаблона страницы ошибки
    }
}
