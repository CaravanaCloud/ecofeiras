package ecomarkets.ui.view;

import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.Route;

import ecomarkets.i18n.I18NService;
import ecomarkets.ui.MainLayout;
import ecomarkets.ui.VerticalView;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;


@Route(value="about", layout = MainLayout.class)
public class AboutView 
    extends VerticalView {
    @Inject
    I18NService i18n;

    @PostConstruct
    void init() {
        var description = new Paragraph(i18n.format("en","about.description"));
        addContent(description);
    }
}
