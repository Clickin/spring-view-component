package de.tschuehly.spring.viewcomponent.thymeleaf.application.web

import de.tschuehly.spring.viewcomponent.core.ViewContextContainer
import de.tschuehly.spring.viewcomponent.thymeleaf.application.ThymeleafTestApplication
import de.tschuehly.spring.viewcomponent.thymeleaf.application.web.header.HeaderViewComponent
import de.tschuehly.spring.viewcomponent.thymeleaf.application.web.index.IndexViewComponent
import de.tschuehly.spring.viewcomponent.thymeleaf.application.web.layout.LayoutViewComponent
import de.tschuehly.spring.viewcomponent.thymeleaf.application.web.nested.NestedViewComponent
import de.tschuehly.spring.viewcomponent.thymeleaf.application.web.simple.SimpleViewComponent
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.servlet.ModelAndView

@Controller
class TestController(
    private val simpleViewComponent: SimpleViewComponent,
    private val indexViewComponent: IndexViewComponent,
    private val nestedViewComponent: NestedViewComponent,
    private val layoutViewComponent: LayoutViewComponent,
    private val headerViewComponent: HeaderViewComponent
) {
    @GetMapping("/")
    fun indexComponent() = indexViewComponent.render()
    @GetMapping("/simple")
    fun simpleComponent() = simpleViewComponent.render()

    @GetMapping("/nested")
    fun nestedComponent() = nestedViewComponent.render()

    @GetMapping("/layout")
    fun layoutComponent() = layoutViewComponent.render(simpleViewComponent.render())

    @GetMapping("/multi")
    fun multiComponent() = ViewContextContainer(simpleViewComponent.render(),layoutViewComponent.render(simpleViewComponent.render()))

    @GetMapping("/header")
    fun header(): String {
        return "test"
    }
    @ModelAttribute("header")
    fun headerComponent() = headerViewComponent.render("TestTitle")
}