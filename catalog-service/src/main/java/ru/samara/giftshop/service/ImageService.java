package ru.samara.giftshop.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.samara.giftshop.dto.DtoMapper;
import ru.samara.giftshop.dto.ProductImageDto;
import ru.samara.giftshop.entity.Category;
import ru.samara.giftshop.entity.ProductImage;
import ru.samara.giftshop.exceptions.ApiException;
import ru.samara.giftshop.exceptions.DataNotFoundResponse;
import ru.samara.giftshop.exceptions.DataValidationResponse;
import ru.samara.giftshop.repository.CategoryRepository;
import ru.samara.giftshop.repository.ProductImageRepository;

@Service
public class ImageService {

    private final Path root = Paths.get("uploads");

    @Value("${image-service.image-host}")
    private String imageHost;

    private final ProductImageRepository productImageRepository;
    private final CategoryRepository categoryRepository;

    public ImageService(ProductImageRepository productImageRepository,
                        CategoryRepository categoryRepository) {
        this.productImageRepository = productImageRepository;
        this.categoryRepository = categoryRepository;
    }

    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public ProductImage uploadProductFile(Long productId, MultipartFile file) {
        try {
            if (!Files.exists(root)) init();

            Path path = this.root.resolve(Objects.requireNonNull(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), path);

            ProductImageDto dto = new ProductImageDto();
            dto.setProductId(productId);
            dto.setPrimaryImage(false);
            dto.setImageUrl(imageHost + file.getOriginalFilename());
            return productImageRepository.save(DtoMapper.toProductImage(dto));
        } catch (Exception ex) {
            if (ex instanceof FileAlreadyExistsException) {
                throw new ApiException(DataValidationResponse.FILE_ALREADY_EXIST);
            }

            throw new RuntimeException(ex.getMessage());
        }
    }

    public void uploadCategoryFile(Long categoryId, MultipartFile file) {
        try {
            if (!Files.exists(root)) init();

            Path path = this.root.resolve(Objects.requireNonNull(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), path);

            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(()->new ApiException(DataNotFoundResponse.CATEGORY_NOT_FOUND));
            category.setImageUrl(imageHost + file.getOriginalFilename());
            categoryRepository.save(category);
        } catch (Exception ex) {
            if (ex instanceof FileAlreadyExistsException) {
                throw new ApiException(DataValidationResponse.FILE_ALREADY_EXIST);
            }

            throw new RuntimeException(ex.getMessage());
        }
    }

    public void makePrimary(Long productId, Long id) {
        List<ProductImage> images = productImageRepository.findAllByProductId(productId);
        images.forEach(i -> i.setPrimaryImage(Objects.equals(i.getId(), id)));

        productImageRepository.saveAll(images);
    }

    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}