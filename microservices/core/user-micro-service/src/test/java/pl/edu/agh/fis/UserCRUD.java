package pl.edu.agh.fis;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * Created by wemstar on 2016-02-28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestUserMicroServiceConfiguration.class)
@WebAppConfiguration
public class UserCRUD {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webContext;

    @Before
    public void setupMockMVC() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }


    @Test
    public void crudTest() throws Exception {

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"email\":\"1234\",\"login\":\"user\",\"password\":\"password\"}")
        ).andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"email\":\"12345\",\"login\":\"log3\",\"password\":\"password\"}")
        ).andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.user[0].email",containsString("1234")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.user[0].login",containsString("user")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.user[0].password",containsString("0ad7e108dbc1a0d6e8bb062c31950e90fb392b4a0e058cb5aaba1259b7e3d4cd")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.user[1].email",containsString("12345")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.user[1].login",containsString("log3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.user[1].password",containsString("704aa0c6ae6dde6be68b69c63278557aca8260798f5e711df38a5c12aa6b0742")));
        mockMvc.perform(put("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"email\":\"1235\",\"login\":\"log2\",\"password\":\"password\"}")
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
        mockMvc.perform(get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.user[0].email",containsString("1235")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.user[0].login",containsString("log2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.user[0].password",containsString("e7892bf61dd386c6f2f5a70926cc5eb787a3a55495d2d879b612eedd597bfc61")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.user[1].email",containsString("12345")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.user[1].login",containsString("log3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.user[1].password",containsString("704aa0c6ae6dde6be68b69c63278557aca8260798f5e711df38a5c12aa6b0742")));


        mockMvc.perform(delete("/user/1")).andExpect(MockMvcResultMatchers.status().isNoContent());
        mockMvc.perform(delete("/user/2")).andExpect(MockMvcResultMatchers.status().isNoContent());

    }

}
