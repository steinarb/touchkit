package org.vaadin.touchkit.itest.navigationmanager;

import org.vaadin.touchkit.AbstractTouchKitIntegrationTest;
import org.vaadin.touchkit.ui.NavigationButton;
import org.vaadin.touchkit.ui.NavigationManager;
import org.vaadin.touchkit.ui.NavigationManager.NavigationEvent.Direction;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

public class SimpleTestWithNavigationButtons extends
        AbstractTouchKitIntegrationTest {

    @Override
    public String getDescription() {
        return "NavigationManager with navigation buttons.";
    }

    public SimpleTestWithNavigationButtons() {

        setSizeFull();

        final NavigationManager navigationManager = new NavigationManager();

        final CssLayout cL = new CssLayout();
        cL.setId("l1");
        final CssLayout nL = new CssLayout();
        nL.setId("l2");
        CssLayout pL = new CssLayout();
        pL.setId("l0");
        final CssLayout yetAnother = new CssLayout();
        yetAnother.setId("l3");

        cL.addComponent(new Label("CURR"));
        NavigationButton button = new NavigationButton("-->");
        cL.addComponent(button);
        button.setTargetView(nL);

        button = new NavigationButton("<--");
        cL.addComponent(button);
        button.setTargetView(pL);

        nL.addComponent(new Label("NEXT"));
        button = new NavigationButton("-->");
        nL.addComponent(button);
        button.setTargetView(yetAnother);

        button = new NavigationButton("<--");
        nL.addComponent(button);
        button.setTargetView(cL);

        yetAnother.addComponent(new Label("YET ANOTHER"));
        button = new NavigationButton("<--");
        yetAnother.addComponent(button);
        button.setTargetView(nL);

        pL.addComponent(new Label("PREV"));
        button = new NavigationButton("-->");
        pL.addComponent(button);
        button.setTargetView(cL);
        button.addClickListener(event -> navigationManager.setNextComponent(nL));

        navigationManager.setPreviousComponent(pL);
        navigationManager.setCurrentComponent(cL);
        navigationManager.setNextComponent(nL);

        navigationManager.addNavigationListener(event -> {
		    if (navigationManager.getCurrentComponent() == nL) {
		        if (event.getDirection() == Direction.FORWARD) {
		            navigationManager.setNextComponent(yetAnother);
		        }
		    }
		});

        addComponent(navigationManager);

    }

}
