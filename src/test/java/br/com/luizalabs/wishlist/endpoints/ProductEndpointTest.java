package br.com.luizalabs.wishlist.endpoints;

import br.com.luizalabs.wishlist.domain.model.Product;
import br.com.luizalabs.wishlist.domain.services.ProductService;
import br.com.luizalabs.wishlist.rest.endpoints.ProductEndpoint;
import br.com.luizalabs.wishlist.rest.resources.ProductRequest;
import br.com.luizalabs.wishlist.rest.resources.builders.ProductResponseBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductEndpoint.class)
public class ProductEndpointTest {

	ObjectMapper mapper = new ObjectMapper();

	@MockBean
	ProductService productService;

	@MockBean
	ProductResponseBuilder productResponseBuilder;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldReturnCreatedProduct() throws Exception {

		ProductRequest request = new ProductRequest();
		request.setName("Renan");

		Product product = new Product();
		product.setName("Renan");

		when(productService.save(ArgumentMatchers.any())).thenReturn(product);

		mockMvc.perform( post("/rs/products")
				.content( mapper.writeValueAsString(request) )
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

}
