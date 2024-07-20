package vn.hoidanit.laptopshop.domain.dto;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;

public class ProductCriteriaDTO {
    private Optional<Integer> page;
    private Optional<String> nameOptional;
    private Optional<List<String>> factoryNames;
    private Optional<List<String>> targetNames;
    private Optional<String> price_range;

    public Optional<Integer> getPage() {
        return page;
    }

    public void setPage(Optional<Integer> page) {
        this.page = page;
    }

    public Optional<String> getNameOptional() {
        return nameOptional;
    }

    public void setNameOptional(Optional<String> nameOptional) {
        this.nameOptional = nameOptional;
    }

    public Optional<List<String>> getFactoryNames() {
        return factoryNames;
    }

    public void setFactoryNames(Optional<List<String>> factoryNames) {
        this.factoryNames = factoryNames;
    }

    public Optional<List<String>> getTargetNames() {
        return targetNames;
    }

    public void setTargetNames(Optional<List<String>> targetNames) {
        this.targetNames = targetNames;
    }

    public Optional<String> getPrice_range() {
        return price_range;
    }

    public void setPrice_range(Optional<String> price_range) {
        this.price_range = price_range;
    }

    public Optional<String> getSortOptional() {
        return sortOptional;
    }

    public void setSortOptional(Optional<String> sortOptional) {
        this.sortOptional = sortOptional;
    }

    private Optional<String> sortOptional;
}
