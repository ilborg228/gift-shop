package ru.samara.giftshop.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.samara.giftshop.entity.ProductImage;
import ru.samara.giftshop.service.ImageService;

@RestController
@AllArgsConstructor
public class ProductImageController extends BaseController {

    private final ImageService imageService;

    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = imageService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/products/{productId}/images")
    public ProductImage uploadProductFile(@PathVariable Long productId, @RequestParam("file") MultipartFile file) {
        return imageService.uploadProductFile(productId, file);
    }

    @PostMapping("/categories/{categoryId}/images")
    public void uploadCategoryFile(@PathVariable Long categoryId, @RequestParam("file") MultipartFile file) {
        imageService.uploadCategoryFile(categoryId, file);
    }

    @PatchMapping("/products/{productId}/images/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void makePrimary(@PathVariable Long productId, @PathVariable Long id) {
        imageService.makePrimary(productId, id);
    }
}
