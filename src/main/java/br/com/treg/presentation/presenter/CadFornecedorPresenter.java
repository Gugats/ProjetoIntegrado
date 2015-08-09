/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.presenter;

import br.com.treg.business.bo.FornecedorBO;
import br.com.treg.business.model.Fornecedor;
import br.com.treg.presentation.view.CadFornecedorView;
import br.com.treg.presentation.view.CadFornecedorView.CadFornecedorViewListener;
import br.com.treg.presentation.view.impl.CadFornecedorViewImpl;

/**
 *
 * @author Gustavo
 */
public class CadFornecedorPresenter implements CadFornecedorViewListener {
    
    CadFornecedorView view = new CadFornecedorViewImpl();
    FornecedorBO bo;
    
    public CadFornecedorPresenter(){
        this.view.addListener(this);
        bo = new FornecedorBO();
    }
    
    public CadFornecedorView getView(){
        return this.view;
    }

    @Override
    public void salvar(Fornecedor fornecedor) {
        bo.saveOrUpdate(fornecedor);
    }
    
}
