package com.challange.catalog.controller;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.challange.catalog.utils.ProductUtils;
import com.challenge.catalog.controller.CatalogController;
import com.challenge.catalog.dto.MessageResponseDTO;
import com.challenge.catalog.dto.ProductDTO;
import com.challenge.catalog.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class CatalogControllerTest {
    
    public static final String PRODUCT_API_URL_PATH = "/productInventory/producatManagement/v1/products";
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;
    
    @InjectMocks
    private CatalogController catalogController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(catalogController)
            .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
            .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
            .build();
    }

    @Test
    void testWhenPOSTisCalledThenAProductShouldBeCreated() throws Exception {
        ProductDTO productDTO = ProductUtils.createFakeProductDTO();
        MessageResponseDTO expectedMessageResponse = MessageResponseDTO.builder()
                .message("Product created with ID " + productDTO.getId())
                .build();

        Mockito.when(productService.create(productDTO)).thenReturn(expectedMessageResponse);

        mockMvc.perform(MockMvcRequestBuilders.post(PRODUCT_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ProductUtils.asJsonString(productDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is(expectedMessageResponse.getMessage())));
    }

    @Test
    void testWhenPOSTwithInvalidNameIsCalledThenBadRequestShouldBeReturned() throws Exception {
        ProductDTO productDTO = ProductUtils.createFakeProductDTO();
        productDTO.setName("");

        mockMvc.perform(MockMvcRequestBuilders.post(PRODUCT_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ProductUtils.asJsonString(productDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
