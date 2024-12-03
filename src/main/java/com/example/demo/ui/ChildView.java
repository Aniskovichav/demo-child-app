package com.example.demo.ui;

import com.example.demo.entity.Child;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Route("children")
@RefreshScope
@Component
public class ChildView extends VerticalLayout {

    private final Grid<Child> grid = new Grid<>(Child.class);
    private final TextField nameField = new TextField("Name");
    private final TextField ageField = new TextField("Age");
    private final Button addButton = new Button("Add");
    private final RestTemplate restTemplate;

    @Value("${child.api.uri}")
    private String childApiUri;

    public ChildView(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

        configureGrid();
        configureForm();

        add(grid, nameField, ageField, addButton);

        loadChildren();
    }

    private void configureGrid() {
        grid.addColumn(Child::getId).setHeader("ID");
        grid.addColumn(Child::getName).setHeader("Name");
        grid.addColumn(Child::getAge).setHeader("Age");
        grid.setSizeFull();
    }

    private void configureForm() {
        addButton.addClickListener(event -> {
            try {
                String name = nameField.getValue();
                Integer age = Integer.parseInt(ageField.getValue());

                Child child = new Child(childDTO.getId(), childDTO.getName(), childDTO.getAge());
                child.setName(name);
                child.setAge(age);

                restTemplate.postForObject(childApiUri, child, Child.class);
                loadChildren();
                Notification.show("Child added!");
            } catch (Exception e) {
                Notification.show("Failed to add child: " + e.getMessage(), 5000, Notification.Position.MIDDLE);
            }
        });
    }

    private void loadChildren() {
        try {
            List<Child> children = Arrays.asList(
                    restTemplate.getForObject(childApiUri, Child[].class)
            );
            grid.setItems(children);
        } catch (Exception e) {
            Notification.show("Failed to load children: " + e.getMessage(), 5000, Notification.Position.MIDDLE);
        }
    }
}

