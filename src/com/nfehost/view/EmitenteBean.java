package com.nfehost.view;

import com.nfehost.model.Emitente;
import com.nfehost.model.filter.EmitenteFilter;
import com.nfehost.service.EmitenteService;
import com.nfehost.view.helper.EmitenteHelper;

public class EmitenteBean<Pojo extends Emitente, Services extends EmitenteService<Pojo>, Helper extends EmitenteHelper<Pojo>, Filter extends EmitenteFilter> extends ManagerBean<Pojo, Services, Helper, Filter> {

}
