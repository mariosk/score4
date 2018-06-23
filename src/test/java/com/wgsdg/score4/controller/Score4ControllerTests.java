/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wgsdg.score4.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wgsdg.score4.Score4Constants;
import com.wgsdg.score4.model.Score4IO;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Score4ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void noParamScore4ShouldReturn404NotFound() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().is4xxClientError());
    }

    @Test
    public void paramScore4SlashCheckShouldReturn200OK() throws Exception {
        Score4IO score4 = new Score4IO(Score4Constants.Score4MoveType.MOVE, new int[]{1, 2, 3});
        this.mockMvc.perform(post("/score4/check")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(score4)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("continue"));
    }

    @Test
    public void paramScore4SlashCheck1() throws Exception {
        Score4IO score4 = new Score4IO(Score4Constants.Score4MoveType.MOVE, new int[]{ 1, 2, 1, 2, 1, 2, 1, 3 });
        this.mockMvc.perform(post("/score4/check")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(score4)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("i_won"));
    }

    @Test
    public void paramScore4SlashCheck2() throws Exception {
        Score4IO score4 = new Score4IO(Score4Constants.Score4MoveType.MOVE, new int[]{ 4, 4, 3, 4, 5, 4, 5, 5, 6, 6, 7 });
        this.mockMvc.perform(post("/score4/check")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(score4)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("i_lost"));
    }

    @Test
    public void paramScore4SlashCheck3() throws Exception {
        Score4IO score4 = new Score4IO(Score4Constants.Score4MoveType.MOVE, new int[]{ 3, 4, 4, 2, 4, 5, 4, 5, 5, 6, 6, 7 });
        this.mockMvc.perform(post("/score4/check")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(score4)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("draw"));
    }

    @Test
    public void paramScore4SlashPlay() throws Exception {
        Score4IO score4 = new Score4IO(Score4Constants.Score4MoveType.MOVE, new int[]{1, 2, 3});
        this.mockMvc.perform(post("/score4/play")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(score4)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("move"));
    }

}
