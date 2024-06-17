package com.cbastian.apicat.resources;

import com.cbastian.apicat.resources.adapter.in.example.ExampleAdapter;
import com.cbastian.apicat.resources.adapter.in.getbreads.GetBreadsAdapter;
import com.cbastian.apicat.resources.adapter.in.getbreadsbyid.GetBreadsByIdAdapter;
import com.cbastian.apicat.resources.adapter.in.searchbreadbyname.SearchBreedByNameAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan( basePackages = {"com.cbastian.apicat.resources.config"},
                basePackageClasses = {
                        //General Dependencies
                        ExampleAdapter.class,
                        GetBreadsAdapter.class,
                        GetBreadsByIdAdapter.class,
                        SearchBreedByNameAdapter.class,

}
)

public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
