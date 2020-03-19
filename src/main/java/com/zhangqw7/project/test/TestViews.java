package com.zhangqw7.project.test;

import com.zhangqw7.project.controller.UserController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class TestViews {

    @Test
    public void shouldShowRegisterForm() throws Exception {
        UserController controller= new UserController();
        MockMvc mockMvc = standaloneSetup(controller).build();

        mockMvc.perform(get("registering"))
                .andExpect(view().name("registerForm"));
    }


}
