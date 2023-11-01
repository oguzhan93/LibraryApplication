package com.libraryApplication.LibraryApplication.utilities.mapper;

import org.modelmapper.ModelMapper;

public interface IModelMapperService {
    ModelMapper forResponse();
    ModelMapper forRequest();
}
