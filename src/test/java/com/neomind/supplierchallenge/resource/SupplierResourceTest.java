 package com.neomind.supplierchallenge.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neomind.supplierchallenge.entity.Supplier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class SupplierResourceTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private static final String DEFAULT_NAME = "Luís";
    private static final String DEFAULT_EMAIL = "luis4129@hotmail.com";
    private static final String DEFAULT_CNPJ = "57.770.398/0001-62";
    private static final String DEFAULT_COMMENT = "Melhor fornecedor.";
    private static final String ANOTHER_COMMENT = "Pior fornecedor.";

    @Before
    public void setUp() throws Exception {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void findAllWithNoResults() throws Exception {
        mockMvc.perform(get("/suppliers"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty())
                .andExpect(status().isOk());
    }

    @Test
    public void addValidSupplier() throws Exception {
        mockMvc.perform(post("/suppliers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createSupplier())))
                .andExpect(status().isNoContent());
    }

    @Test
    public void findExistingSupplier() throws Exception {
        addDefaultSupplier();

        mockMvc.perform(get("/suppliers/1"))
                .andExpect(jsonPath("$.id").value(is(1)))
                .andExpect(jsonPath("$.name").value(is(DEFAULT_NAME)))
                .andExpect(jsonPath("$.email").value(is(DEFAULT_EMAIL)))
                .andExpect(jsonPath("$.cnpj").value(is(DEFAULT_CNPJ)))
                .andExpect(jsonPath("$.comment").value(is(DEFAULT_COMMENT)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateSupplier() throws Exception {
        addDefaultSupplier();

        Supplier supplier = createSupplier();
        supplier.setComment(ANOTHER_COMMENT);

        mockMvc.perform(put("/suppliers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(supplier)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void findAll() throws Exception {
        addDefaultSupplier();

        mockMvc.perform(get("/suppliers"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].id").value(is(1)))
                .andExpect(jsonPath("$.[0].name").value(is(DEFAULT_NAME)))
                .andExpect(jsonPath("$.[0].email").value(is(DEFAULT_EMAIL)))
                .andExpect(jsonPath("$.[0].cnpj").value(is(DEFAULT_CNPJ)))
                .andExpect(jsonPath("$.[0].comment").value(is(DEFAULT_COMMENT)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteSupplier() throws Exception {
        addDefaultSupplier();

        mockMvc.perform(delete("/suppliers/1"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/suppliers/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void failToAddSupplierWithoutName() throws Exception {
        Supplier supplier = createSupplier();
        supplier.setName(null);

        mockMvc.perform(post("/suppliers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(supplier)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(is("Nome do fornecedor não informado.")));
    }

    @Test
    public void failToAddSupplierWithoutEmail() throws Exception {
        Supplier supplier = createSupplier();
        supplier.setEmail(null);

        mockMvc.perform(post("/suppliers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(supplier)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(is("E-mail do fornecedor não informado.")));
    }

    @Test
    public void failToAddSupplierWithoutCnpj() throws Exception {
        Supplier supplier = createSupplier();
        supplier.setCnpj(null);

        mockMvc.perform(post("/suppliers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(supplier)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void failToAddupplierWithInvalidCnpj() throws Exception {
        Supplier supplier = createSupplier();
        supplier.setCnpj("Sou um cnpj errado");

        mockMvc.perform(post("/suppliers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(supplier)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(is("CNPJ do fornecedor não é válido.")));
    }

    @Test
    public void failToAddSupplierWithInvalidEmail() throws Exception {
        Supplier supplier = createSupplier();
        supplier.setEmail("Sou um email errado");

        mockMvc.perform(post("/suppliers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(supplier)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void findUnexistingSupplier() throws Exception {
        mockMvc.perform(get("/suppliers/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateUnexistingSupplier() throws Exception {
        Supplier supplier = createSupplier();
        supplier.setComment(ANOTHER_COMMENT);

        mockMvc.perform(put("/suppliers/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(supplier)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteUnexistingSupplier() throws Exception {
        mockMvc.perform(delete("/suppliers/99"))
                .andExpect(status().isNotFound());
    }

    private void addDefaultSupplier() throws Exception {
        mockMvc.perform(post("/suppliers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createSupplier())));
    }

    private Supplier createSupplier() {
        Supplier supplier = new Supplier();

        supplier.setName(DEFAULT_NAME);
        supplier.setEmail(DEFAULT_EMAIL);
        supplier.setCnpj(DEFAULT_CNPJ);
        supplier.setComment(DEFAULT_COMMENT);

        return supplier;
    }

}