package com.spring.shop.gallery;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.shop.utils.FileDto;
import com.spring.shop.utils.SearchDto;

@Repository
public class GalleryDAO implements GalleryRepository {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<GalleryDto> list(SearchDto dto) {
        return sqlSession.getMapper(GalleryMapper.class).list(dto);
    }

    @Override
    public int add(GalleryDto dto) {
        return sqlSession.getMapper(GalleryMapper.class).add(dto);
    }

    @Override
    public GalleryDto select(GalleryDto dto) {
        return sqlSession.getMapper(GalleryMapper.class).select(dto);
    }

    @Override
    public int edit(GalleryDto dto) {
        return sqlSession.getMapper(GalleryMapper.class).edit(dto);
    }

    @Override
    public int delete(GalleryDto dto) {
        return sqlSession.getMapper(GalleryMapper.class).delete(dto);
    }

    @Override
    public int photoDelete(DeleteDto dto) {
        return sqlSession.getMapper(GalleryMapper.class).photoDelete(dto);
    }

    @Override
    public int photoDeleteAll(GalleryDto dto) {
        return sqlSession.getMapper(GalleryMapper.class).photoDeleteAll(dto);
    }

    @Override
    public int savedfile(List<Map<String, Object>> params) {
        return sqlSession.getMapper(GalleryMapper.class).savedFile(params);
    }

    @Override
    public List<FileDto> photoSelect(GalleryDto dto) {
        return sqlSession.getMapper(GalleryMapper.class).selectPhoto(dto);
    }

    @Override
    public int addLogo(GalleryDto dto) {
        return sqlSession.getMapper(GalleryMapper.class).addLogo(dto);
    }
}