package ru.samara.giftshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.samara.giftshop.entity.CategoryEntity;
import ru.samara.giftshop.entity.ProductEntity;
import ru.samara.giftshop.exceptions.NoSuchProductException;
import ru.samara.giftshop.exceptions.ProductAlreadyExistException;
import ru.samara.giftshop.repository.ProductsRepository;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @MockBean
    private ProductsRepository productsRepository;

    @Test
    void testConstructor() {
        assertTrue((new ProductServiceImpl(mock(ProductsRepository.class))).findAll().isEmpty());
    }

    @Test
    void testSaveNewItem() {
        when(this.productsRepository.existsByName(any())).thenReturn(true);

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setId(123L);
        categoryEntity.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(123L);
        productEntity.setName("Name");
        productEntity.setPrice(10.0);
        productEntity.setCategory(categoryEntity);
        productEntity.setHeight(1);
        productEntity.setImgSource("Img Source");
        assertThrows(ProductAlreadyExistException.class, () -> this.productServiceImpl.saveNewItem(productEntity));
        verify(this.productsRepository).existsByName(any());
    }

    @Test
    void testSaveNewItem2() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setId(123L);
        categoryEntity.setProducts(new ArrayList());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(123L);
        productEntity.setName("Name");
        productEntity.setPrice(10.0);
        productEntity.setCategory(categoryEntity);
        productEntity.setHeight(1);
        productEntity.setImgSource("Img Source");
        when(this.productsRepository.save(any())).thenReturn(productEntity);
        when(this.productsRepository.existsByName((String) any())).thenReturn(false);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setCategoryName("Category Name");
        categoryEntity1.setId(123L);
        categoryEntity1.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setId(123L);
        productEntity1.setName("Name");
        productEntity1.setPrice(10.0);
        productEntity1.setCategory(categoryEntity1);
        productEntity1.setHeight(1);
        productEntity1.setImgSource("Img Source");
        this.productServiceImpl.saveNewItem(productEntity1);
        verify(this.productsRepository).existsByName((String) any());
        verify(this.productsRepository).save((ProductEntity) any());
        assertTrue(this.productServiceImpl.findAll().isEmpty());
    }

    @Test
    void testFindAll() {
        when(this.productsRepository.findAll()).thenThrow(new NoSuchProductException());
        assertThrows(NoSuchProductException.class, () -> this.productServiceImpl.findAll());
        verify(this.productsRepository).findAll();
    }

    @Test
    void testDelete() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setId(123L);
        categoryEntity.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(123L);
        productEntity.setName("Name");
        productEntity.setPrice(10.0);
        productEntity.setCategory(categoryEntity);
        productEntity.setHeight(1);
        productEntity.setImgSource("Img Source");
        Optional<ProductEntity> ofResult = Optional.of(productEntity);

        doNothing().when(this.productsRepository).delete(any());
        when(this.productsRepository.findById(any())).thenReturn(ofResult);
        this.productServiceImpl.delete(123L);
        verify(this.productsRepository).delete(any());
        verify(this.productsRepository, atLeast(1)).findById(any());
        assertTrue(this.productServiceImpl.findAll().isEmpty());
    }

    @Test
    void testDelete2() {
        doNothing().when(this.productsRepository).delete(any());
        when(this.productsRepository.findById(any())).thenReturn(Optional.<ProductEntity>empty());
        assertThrows(NoSuchProductException.class, () -> this.productServiceImpl.delete(123L));
        verify(this.productsRepository).findById(any());
    }

    @Test
    void testDelete3() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setId(123L);
        categoryEntity.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(123L);
        productEntity.setName("Name");
        productEntity.setPrice(10.0);
        productEntity.setCategory(categoryEntity);
        productEntity.setHeight(1);
        productEntity.setImgSource("Img Source");
        Optional<ProductEntity> ofResult = Optional.<ProductEntity>of(productEntity);
        doNothing().when(this.productsRepository).delete((ProductEntity) any());
        when(this.productsRepository.findById((Long) any())).thenReturn(ofResult);
        this.productServiceImpl.delete(123L);
        verify(this.productsRepository).delete((ProductEntity) any());
        verify(this.productsRepository, atLeast(1)).findById((Long) any());
        assertTrue(this.productServiceImpl.findAll().isEmpty());
    }

    @Test
    void testDelete4() {
        doNothing().when(this.productsRepository).delete((ProductEntity) any());
        when(this.productsRepository.findById((Long) any())).thenReturn(Optional.<ProductEntity>empty());
        assertThrows(NoSuchProductException.class, () -> this.productServiceImpl.delete(123L));
        verify(this.productsRepository).findById((Long) any());
    }

    @Test
    void testUpdate() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setId(123L);
        categoryEntity.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(123L);
        productEntity.setName("Name");
        productEntity.setPrice(10.0);
        productEntity.setCategory(categoryEntity);
        productEntity.setHeight(1);
        productEntity.setImgSource("Img Source");
        Optional<ProductEntity> ofResult = Optional.<ProductEntity>of(productEntity);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setCategoryName("Category Name");
        categoryEntity1.setId(123L);
        categoryEntity1.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setId(123L);
        productEntity1.setName("Name");
        productEntity1.setPrice(10.0);
        productEntity1.setCategory(categoryEntity1);
        productEntity1.setHeight(1);
        productEntity1.setImgSource("Img Source");
        when(this.productsRepository.save((ProductEntity) any())).thenReturn(productEntity1);
        when(this.productsRepository.findById((Long) any())).thenReturn(ofResult);

        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setCategoryName("Category Name");
        categoryEntity2.setId(123L);
        categoryEntity2.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setId(123L);
        productEntity2.setName("Name");
        productEntity2.setPrice(10.0);
        productEntity2.setCategory(categoryEntity2);
        productEntity2.setHeight(1);
        productEntity2.setImgSource("Img Source");
        this.productServiceImpl.update(productEntity2);
        verify(this.productsRepository).findById((Long) any());
        verify(this.productsRepository).save((ProductEntity) any());
        assertTrue(this.productServiceImpl.findAll().isEmpty());
    }

    @Test
    void testUpdate2() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setId(123L);
        categoryEntity.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(123L);
        productEntity.setName(null);
        productEntity.setPrice(10.0);
        productEntity.setCategory(categoryEntity);
        productEntity.setHeight(1);
        productEntity.setImgSource("Img Source");
        Optional<ProductEntity> ofResult = Optional.<ProductEntity>of(productEntity);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setCategoryName("Category Name");
        categoryEntity1.setId(123L);
        categoryEntity1.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setId(123L);
        productEntity1.setName("Name");
        productEntity1.setPrice(10.0);
        productEntity1.setCategory(categoryEntity1);
        productEntity1.setHeight(1);
        productEntity1.setImgSource("Img Source");
        when(this.productsRepository.save((ProductEntity) any())).thenReturn(productEntity1);
        when(this.productsRepository.findById((Long) any())).thenReturn(ofResult);

        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setCategoryName("Category Name");
        categoryEntity2.setId(123L);
        categoryEntity2.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setId(123L);
        productEntity2.setName("Name");
        productEntity2.setPrice(10.0);
        productEntity2.setCategory(categoryEntity2);
        productEntity2.setHeight(1);
        productEntity2.setImgSource("Img Source");
        this.productServiceImpl.update(productEntity2);
        verify(this.productsRepository).findById((Long) any());
        verify(this.productsRepository).save((ProductEntity) any());
        assertTrue(this.productServiceImpl.findAll().isEmpty());
    }

    @Test
    void testUpdate3() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setId(123L);
        categoryEntity.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(123L);
        productEntity.setName("Name");
        productEntity.setPrice(null);
        productEntity.setCategory(categoryEntity);
        productEntity.setHeight(1);
        productEntity.setImgSource("Img Source");
        Optional<ProductEntity> ofResult = Optional.<ProductEntity>of(productEntity);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setCategoryName("Category Name");
        categoryEntity1.setId(123L);
        categoryEntity1.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setId(123L);
        productEntity1.setName("Name");
        productEntity1.setPrice(10.0);
        productEntity1.setCategory(categoryEntity1);
        productEntity1.setHeight(1);
        productEntity1.setImgSource("Img Source");
        when(this.productsRepository.save((ProductEntity) any())).thenReturn(productEntity1);
        when(this.productsRepository.findById((Long) any())).thenReturn(ofResult);

        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setCategoryName("Category Name");
        categoryEntity2.setId(123L);
        categoryEntity2.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setId(123L);
        productEntity2.setName("Name");
        productEntity2.setPrice(10.0);
        productEntity2.setCategory(categoryEntity2);
        productEntity2.setHeight(1);
        productEntity2.setImgSource("Img Source");
        this.productServiceImpl.update(productEntity2);
        verify(this.productsRepository).findById((Long) any());
        verify(this.productsRepository).save((ProductEntity) any());
        assertTrue(this.productServiceImpl.findAll().isEmpty());
    }

    @Test
    void testUpdate4() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setId(123L);
        categoryEntity.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(123L);
        productEntity.setName("Name");
        productEntity.setPrice(10.0);
        productEntity.setCategory(categoryEntity);
        productEntity.setHeight(null);
        productEntity.setImgSource("Img Source");
        Optional<ProductEntity> ofResult = Optional.<ProductEntity>of(productEntity);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setCategoryName("Category Name");
        categoryEntity1.setId(123L);
        categoryEntity1.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setId(123L);
        productEntity1.setName("Name");
        productEntity1.setPrice(10.0);
        productEntity1.setCategory(categoryEntity1);
        productEntity1.setHeight(1);
        productEntity1.setImgSource("Img Source");
        when(this.productsRepository.save((ProductEntity) any())).thenReturn(productEntity1);
        when(this.productsRepository.findById((Long) any())).thenReturn(ofResult);

        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setCategoryName("Category Name");
        categoryEntity2.setId(123L);
        categoryEntity2.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setId(123L);
        productEntity2.setName("Name");
        productEntity2.setPrice(10.0);
        productEntity2.setCategory(categoryEntity2);
        productEntity2.setHeight(1);
        productEntity2.setImgSource("Img Source");
        this.productServiceImpl.update(productEntity2);
        verify(this.productsRepository).findById((Long) any());
        verify(this.productsRepository).save((ProductEntity) any());
        assertTrue(this.productServiceImpl.findAll().isEmpty());
    }

    @Test
    void testUpdate5() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setId(123L);
        categoryEntity.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(123L);
        productEntity.setName("Name");
        productEntity.setPrice(10.0);
        productEntity.setCategory(categoryEntity);
        productEntity.setHeight(1);
        productEntity.setImgSource(null);
        Optional<ProductEntity> ofResult = Optional.<ProductEntity>of(productEntity);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setCategoryName("Category Name");
        categoryEntity1.setId(123L);
        categoryEntity1.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setId(123L);
        productEntity1.setName("Name");
        productEntity1.setPrice(10.0);
        productEntity1.setCategory(categoryEntity1);
        productEntity1.setHeight(1);
        productEntity1.setImgSource("Img Source");
        when(this.productsRepository.save((ProductEntity) any())).thenReturn(productEntity1);
        when(this.productsRepository.findById((Long) any())).thenReturn(ofResult);

        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setCategoryName("Category Name");
        categoryEntity2.setId(123L);
        categoryEntity2.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setId(123L);
        productEntity2.setName("Name");
        productEntity2.setPrice(10.0);
        productEntity2.setCategory(categoryEntity2);
        productEntity2.setHeight(1);
        productEntity2.setImgSource("Img Source");
        this.productServiceImpl.update(productEntity2);
        verify(this.productsRepository).findById((Long) any());
        verify(this.productsRepository).save((ProductEntity) any());
        assertTrue(this.productServiceImpl.findAll().isEmpty());
    }

    @Test
    void testUpdate6() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setId(123L);
        categoryEntity.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(123L);
        productEntity.setName("Name");
        productEntity.setPrice(10.0);
        productEntity.setCategory(categoryEntity);
        productEntity.setHeight(1);
        productEntity.setImgSource("Img Source");
        when(this.productsRepository.save((ProductEntity) any())).thenReturn(productEntity);
        when(this.productsRepository.findById((Long) any())).thenReturn(Optional.<ProductEntity>empty());

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setCategoryName("Category Name");
        categoryEntity1.setId(123L);
        categoryEntity1.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setId(123L);
        productEntity1.setName("Name");
        productEntity1.setPrice(10.0);
        productEntity1.setCategory(categoryEntity1);
        productEntity1.setHeight(1);
        productEntity1.setImgSource("Img Source");
        assertThrows(NoSuchProductException.class, () -> this.productServiceImpl.update(productEntity1));
        verify(this.productsRepository).findById((Long) any());
    }

    @Test
    void testUpdate7() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName("Category Name");
        categoryEntity.setId(123L);
        categoryEntity.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(123L);
        productEntity.setName("Name");
        productEntity.setPrice(10.0);
        productEntity.setCategory(categoryEntity);
        productEntity.setHeight(1);
        productEntity.setImgSource("Img Source");
        Optional<ProductEntity> ofResult = Optional.<ProductEntity>of(productEntity);

        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setCategoryName("Category Name");
        categoryEntity1.setId(123L);
        categoryEntity1.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setId(123L);
        productEntity1.setName("Name");
        productEntity1.setPrice(10.0);
        productEntity1.setCategory(categoryEntity1);
        productEntity1.setHeight(1);
        productEntity1.setImgSource("Img Source");
        when(this.productsRepository.save((ProductEntity) any())).thenReturn(productEntity1);
        when(this.productsRepository.findById((Long) any())).thenReturn(ofResult);

        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setCategoryName("Category Name");
        categoryEntity2.setId(123L);
        categoryEntity2.setProducts(new ArrayList<ProductEntity>());

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setId(123L);
        productEntity2.setName(null);
        productEntity2.setPrice(10.0);
        productEntity2.setCategory(categoryEntity2);
        productEntity2.setHeight(1);
        productEntity2.setImgSource("Img Source");
        this.productServiceImpl.update(productEntity2);
        verify(this.productsRepository).findById((Long) any());
        verify(this.productsRepository).save((ProductEntity) any());
        assertEquals("Name", productEntity2.getName());
        assertTrue(this.productServiceImpl.findAll().isEmpty());
    }
}

