package com.nfehost.view;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nfehost.model.Arquivo;
import com.nfehost.model.filter.ArquivoFilter;
import com.nfehost.service.ArquivoService;
import com.nfehost.view.helper.ArquivoHelper;

@EqualsAndHashCode(callSuper = false)
@Data
public class ArquivoListBean<Pojo extends Arquivo, Services extends ArquivoService<Pojo>, Helper extends ArquivoHelper<Pojo>, Filter extends ArquivoFilter> extends ManagerBean<Pojo, Services, Helper, Filter> {

}
