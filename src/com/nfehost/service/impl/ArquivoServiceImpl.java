package com.nfehost.service.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nfehost.dao.ArquivoDAO;
import com.nfehost.model.Arquivo;
import com.nfehost.service.ArquivoService;

@EqualsAndHashCode(callSuper = false)
@Data
public class ArquivoServiceImpl<Pojo extends Arquivo, Dao extends ArquivoDAO<Pojo>> extends ServiceImpl<Pojo, Dao> implements ArquivoService<Pojo> {

}
