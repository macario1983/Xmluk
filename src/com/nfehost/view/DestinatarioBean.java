package com.nfehost.view;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.nfehost.model.Destinatario;
import com.nfehost.model.filter.EmitenteFilter;
import com.nfehost.service.DestinatarioService;
import com.nfehost.view.helper.DestinatarioHelper;

@EqualsAndHashCode(callSuper = false)
@Data
public class DestinatarioBean<Pojo extends Destinatario, Services extends DestinatarioService<Pojo>, Helper extends DestinatarioHelper<Pojo>, Filter extends EmitenteFilter> extends ManagerBean<Pojo, Services, Helper, Filter> {

}
