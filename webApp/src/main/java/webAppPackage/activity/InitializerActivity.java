package webAppPackage.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import webAppPackage.service.GraphService;

@Component
public class InitializerActivity {

    @Autowired GraphService service;

            public void initializer(){
        service.getData();
            }

}
